package com.si.service;

import java.util.Calendar;

import javax.script.ScriptException;

import Handler.Auto_Converting;
import Handler.Auto_Mapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.si.consts.Error;
import com.si.consts.TYPE;
import com.si.exception.SIException;
import com.si.helper.ResultHelper;
import com.si.helper.ServiceHelper;
import com.si.model.BrokenPoint;
import com.si.model.ResultAIS;
import com.si.model.SI_Log;
import com.si.model.ServiceResult;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.pre.SIPre;
import com.si.service.invoke.Invoker;

public abstract class SI_Service {

	protected JsonElement data;
	protected JsonElement subData;
	protected JsonElement tempResult;

	protected JsonElement invoked_input;
	protected JsonElement invoked_output;

	protected String siVersion;
	protected ServiceConfig serviceConfig;
	protected String processDataSource;
	protected String logDataSource;

	protected Invoker invoker;

	protected SI_Log log = null;

	protected Gson gson = new Gson();
	protected Gson gson_SerializeNulls = new GsonBuilder().serializeNulls().create();

	protected boolean isManualInvoke = false;
	protected boolean isManualMapping_Input = false;
	protected boolean isManualMapping_Output = false;
	protected boolean isMergeData = false;

	protected Auto_Mapping mapper = new Auto_Mapping();

	public SI_Service(String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log) {

		this.data = this.gson.fromJson(data, JsonElement.class);
		this.isMergeData = isMergeData;

		this.siVersion = siVersion;
		this.processDataSource = processDataSource;
		this.logDataSource = logDataSource;
		this.serviceConfig = serviceConfig;
		this.log = log;
	}

	public void setInvoker(Invoker invoker) {
		this.invoker = invoker;
	}

	/*
	 * MAIN USING -> EXECUTING SERVICE
	 */
	public String execute() throws SIException {

		String result = null;

		/** 1 MAPPING INPUT **/
		// 1.1 businessMapping_Input
		businessMapping_Input();

		/*
		 * 1.2 mappingInput Update to [this.invoked_input]
		 */
		if (!isManualMapping_Input) {
			automap_input();
		} else {
			manualMapping_input();
		}

		customize_InvokedInput();

		/**
		 * 2. INVOKE TARGET SYSTEM --> Update to [this.invokeResult] &
		 * [this.output]
		 **/
		if (!isManualInvoke) {
			invoke();
		} else {
			invoke_manual();
		}

		check_invokeResult();

		// Customize [this.invoked_output] for mapping
		customize_InvokedOutput();

		/** 3. MAPPING OUTPUT **/
		// 3.1 mappingOutput
		if (!isManualMapping_Output) {
			autoMapping_output();
		} else {
			manualMapping_output();
		}

		// 3.2 businessMapping_Output
		businessMapping_Output();

		result = ResultHelper.getResultAsSuccess(this.data, log);
		this.log.setFromOutput(result);

		return result;
	}

	/**
	 * ABSTRACT METHODS
	 * **/
	public abstract void businessMapping_Input() throws SIException;

	public abstract void manualMapping_input() throws SIException;

	public abstract void customize_InvokedInput() throws SIException;

	public abstract void invoke_manual() throws SIException;

	public abstract void check_invokeResult() throws SIException;

	public abstract void customize_InvokedOutput() throws SIException;

	public abstract void manualMapping_output() throws SIException;

	public abstract void businessMapping_Output() throws SIException;

	/**
	 * INTERNAL METHODS
	 * **/

	private void automap_input() throws SIException {
		this.invoked_input = this.invoker.automap_input(this.data);
	}

	private void invoke() throws SIException {

		// invoke target system: DB, ODM, Core...
		this.invoked_output = invoker.invoke(this.invoked_input);
	}

	private void autoMapping_output() throws SIException {
		if (isMergeData) {
			this.data = this.invoker.automap_output(this.invoked_output, this.data);
		} else {
			this.subData = new JsonObject();
			this.data = this.invoker.automap_output(this.invoked_output, this.subData);
		}
	}

	/**
	 * EXTERNAL SUPPORT-METHODS
	 * **/
	public ServiceResult fetchServiceResult(JsonElement data) {
		return new ServiceResult(data, log);
	}

	public void invokeAndUpdateData(SIPre pre) throws SIException {

		String invokeResult = pre.invoke();
		ResultAIS resultAIS = new Gson().fromJson(invokeResult, ResultAIS.class);

		if (!resultAIS.getErrorInfo().getErrorCode().equalsIgnoreCase("00")) {
			throw new SIException(resultAIS.getErrorInfo().getErrorCode(), resultAIS.getErrorInfo().getMessage(), pre.service);
		}
		this.data = this.gson_SerializeNulls.toJsonTree(resultAIS.getData());
	}

	public JsonElement invokeAndGetData(SIPre pre) throws SIException {

		String invokeResult = pre.invoke();
		ResultAIS resultAIS = new Gson().fromJson(invokeResult, ResultAIS.class);

		if (!resultAIS.getErrorInfo().getErrorCode().equalsIgnoreCase("00")) {
			throw new SIException(resultAIS.getErrorInfo().getErrorCode(), resultAIS.getErrorInfo().getMessage());
		}
		return this.gson_SerializeNulls.toJsonTree(resultAIS.getData());
	}

	public void mapping(JsonObject fromData, JsonObject toData, String subjectCode) throws SIException {

		try {
			JsonArray map_info = ServiceHelper.loadMapInfo(subjectCode, this.processDataSource);
			this.mapper.start_mapping_bo(fromData, toData, map_info);

		} catch (Exception e) {
			throw new SIException(Error.MAP_99, "Error in mapping", e);
		}
	}

	public void convertMasterdataCode(String service, String convert_type, JsonObject data) throws SIException {

		try {
			JsonArray convert_info = ServiceHelper.loadConvertInfo(service, convert_type, this.processDataSource);
			Auto_Converting convertor = new Auto_Converting();
			convertor.start_converting_bo(data, convert_type, convert_info);

		} catch (Exception e) {
			throw new SIException(Error.MAP_99, "Error in converting masterdata-code", e);				
		}
	}
	
	public String convertMasterdataCodeToName(String groupCode, String code) throws SIException {

		String result = null;
		try {
			JsonArray masterData_Info = ServiceHelper.loadMasterdata(groupCode, code, this.processDataSource);
			if(masterData_Info != null && masterData_Info.size() > 0){
				for (JsonElement masterData : masterData_Info) {
					if(code.equalsIgnoreCase(masterData.getAsJsonObject().get("code").getAsString())){
						result = masterData.getAsJsonObject().get("name").getAsString();
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new SIException(Error.MAP_99, "Error in converting masterdata-code-to-name", e);				
		}
		return result;
	}

	public JsonElement get_value_byDir(JsonObject BO, String dir) throws SIException {

		try {
			JsonObject result = this.mapper.get_value_byDir(BO, "#", dir);
			if (result.get(TYPE.GET_HASVALUE).getAsBoolean()) {
				return result.get(TYPE.GET_RES);
			} else {
				throw new SIException(Error.MAP_99, String.format("Has no properties from dir: [%s] ", dir));
			}
		} catch (NumberFormatException | ScriptException e) {
			// TODO Auto-generated catch block
			throw new SIException(Error.MAP_99, String.format("Can't get value by dir: [%s]", dir), e);
		}
	}

	public void check_invokeResult(String successCode) throws SIException {

		JsonArray result_JA = this.gson.fromJson(this.invoked_output, JsonArray.class);
		String log_id = result_JA.get(0).getAsJsonObject().get("LOG_ID").getAsString();

		if (!log_id.equalsIgnoreCase(successCode)) {

			String errorCode = String.format("DB-%s", log_id);
			String message = null;

			JsonElement code_JE = result_JA.get(0).getAsJsonObject().get("CODE");
			if (code_JE != null) {
				message = code_JE.getAsString();
			} else {
				JsonElement log_code_JE = result_JA.get(0).getAsJsonObject().get("LOG_CODE");
				message = log_code_JE.getAsString();
			}
			throw new SIException(errorCode, message);
		}
	}

	public String checkBrokenPoint() {

		String service = null;

		JsonElement brokenPoint_JE = this.data.getAsJsonObject().get("brokenPoint");

		if (brokenPoint_JE != null) {
			BrokenPoint brokenPoint = this.gson.fromJson(brokenPoint_JE, BrokenPoint.class);
			service = brokenPoint.getService();
		}

		return service;
	}

	/*
	 * SUPPORTING
	 */
	public static int[] calAgeWithMonth(Calendar fromDate, Calendar toDate) {

		int[] agetWithMonth = new int[2];

		int ydiff = toDate.get(Calendar.YEAR) - fromDate.get(Calendar.YEAR);
		int mdiff = toDate.get(Calendar.MONTH) - fromDate.get(Calendar.MONTH);

		if (mdiff < 0) {
			--ydiff;
			mdiff += 12;
		}
		agetWithMonth[0] = ydiff;
		agetWithMonth[1] = mdiff;

		return agetWithMonth;
	}
	
	// GET ROLE_NAME BY ROLE_CODE
	public String convertAuthdataCodeToName(String roleCode) throws SIException {

		String result = null;
		
		try {
			JsonArray authData_Info = ServiceHelper.loadRoleNamedata(roleCode, this.processDataSource);
			
			if(authData_Info != null && authData_Info.size() > 0){
				for (JsonElement authData : authData_Info) {
					if(roleCode.equalsIgnoreCase(authData.getAsJsonObject().get("role_code").getAsString())){
						result = authData.getAsJsonObject().get("role_name").getAsString();
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new SIException(Error.MAP_99, "Error in converting authdata-code-to-name", e);				
		}
		
		return result;
	}
	public JsonArray queryByPrepareStatement(String SQL,String... params) throws Exception{
		JsonArray rs = null;
		try {
			rs = ServiceHelper.queryByPrepareStatement(SQL,this.processDataSource,params);
		} catch (SIException e) {
			throw new SIException(Error.EX_99, "Error in queryByPrepareStatement", e);				
		}
		return rs;
	}
}
