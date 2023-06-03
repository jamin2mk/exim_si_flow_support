package com.service.impl.gendoc.v1_0;

import Handler.Auto_Mapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.helper.SupportBMHelper;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.helper.DateHelper;
import com.si.helper.MoneyHelper;
import com.si.service.invoke.impl.GenDocInvoker;

import java.util.HashMap;
import java.util.Map;

public class BM152 extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public BM152(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource, serviceConfig, log);
		GenDocEnv genDocEnv = new Gson().fromJson(super.data.getAsJsonObject().get("env"), GenDocEnv.class);
		genDocInvoker  = new GenDocInvoker(docName, genDocEnv, processDataSource, log);
		super.invoker = genDocInvoker;
		super.isMergeData = true;
		super.isManualMapping_Input =true;
		super.isManualInvoke =true;
		this.docName = docName;
		this.dependentRequired = dependentRequired;
	}

	@Override
	public void businessMapping_Input() throws SIException {
//		JsonObject BO = super.data.getAsJsonObject().get("data").getAsJsonObject();
//		if(BO.getAsJsonObject().get("LOS_BPM_INFORMATION").getAsJsonObject().get("IS_ADJUST").getAsString().equalsIgnoreCase("0")){
//			BO.addProperty("TABLE_DBM_BPBD", "2");
//		}
		JsonObject BO = super.data.getAsJsonObject().get("data").getAsJsonObject();
		if(BO.getAsJsonObject().get("LOS_BPM_INFORMATION").getAsJsonObject().get("IS_ADJUST").getAsString().equalsIgnoreCase("0")){
			BO.addProperty("TABLE_DBM_BPBD", "2");
		}

		BO.addProperty("GENDOC_CODE", docName);
		BO.addProperty("DATE_FM", DateHelper.getCurrentDate("YYYMMdd"));
		String PCA = BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE") != null ? BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE").getAsString() : null;
		String[] split = PCA.split("\\.");
		BO.addProperty("PCA", split != null ? String.join("_", split):"");
		
		
		/////////////////MASTER DATA///////////////////////////
		SupportBMHelper support = new SupportBMHelper(super.mapper, super.processDataSource);
		String newDependentRequired = support.modifiedDIR(this.dependentRequired, "#.TT_BAO_CAO.TT_YC_BAOLANH");
		int indexOfN = support.findIndexOfN(BO, newDependentRequired, "ID_BAOLANH");
		super.invoker= ((GenDocInvoker)super.invoker).setMapper(indexOfN);
		super.mapper = new Auto_Mapping(indexOfN);
		String dirParent = String.format("#.TT_BAO_CAO.TT_YC_BAOLANH[%s]", indexOfN);
		Map<String, String> map = new HashMap<String, String>();
		
		////////////////////////////////
		map.put("LOAIID", "LOAI_ID, BL_ID_TYPE");
		map.put("NOICAPID", "NOI_CAP, BL_DN_ISSUED_BY");
		BO = support.getMasterData(map,"#.TT_BAO_CAO.TT_CTD_KH.TT_DINH_DANH[0]" , BO);
		map.clear();
		////////////////////////////////		
		map.put("LOAITIEN", "LOAI_TIEN, BL_CURRENCY_1_TEXT");
		BO = support.getMasterData(map,dirParent , BO);
		map.clear();
		////////////////////////////////
         /////////////////MASTER DATA-END///////////////////////////	
		
		//////////////CHECK-START////////////////////
		BO = support.toCheck(dirParent+".NGAY_HH_CTHE", "BL_EXACT_DAY", BO);
//		BO = support.toCheck(dirParent+".NGAY_HH_CTHE", "BL_THE_EXACT_DAY", BO);
		BO = support.toCheck(dirParent+".SONGAY", "NO_DAYS_1|BL_NO_DATE_1", BO);
		BO = support.toCheck(dirParent+".CUTHE_NGAYHETHIEULUC", "BL_SPECIFICALLY_1", BO);
		BO = support.toCheck(dirParent+".NGAY_HH_SUKIEN", "GNBL_EVENT_1|BL_EVENT_1", BO);
//		BO = support.toCheck("#.DEXUAT_STHAO_GN.IS_BAOLANH", "GUARANTEE", BO);
		///////////////CHECK-END/////////////////////////
		
		
		//////////////CUSTOME FIELD-START////////////////////
		try {
			JsonElement js = get_value_byDir(BO, dirParent+".SOTIEN_BLANH");
			String moneyText = MoneyHelper.readMoneyToText(js.getAsString());
			BO.addProperty("BY_GUARANTEE_AMOUNT", moneyText);
		} catch (Exception e) {
			BO.addProperty("BY_GUARANTEE_AMOUNT", "");
		}
		
		
        //////////////CUSTOME FIELD-END////////////////////


	}

	@Override
	public void manualMapping_input() throws SIException {
		
		super.invoked_output = super.data;
	}

	@Override
	public void customize_InvokedInput() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void invoke_manual() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void check_invokeResult() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void customize_InvokedOutput() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void manualMapping_output() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void businessMapping_Output() throws SIException {
		// Handle business
		JsonArray inputData = super.data.getAsJsonObject().get("InputData").getAsJsonArray();
		
		if(inputData != null && inputData.size() > 0){
			JsonObject input = inputData.get(0).getAsJsonObject();
			// prop handle 
			//WF_TYPE
			input.addProperty("WF_TYPE", "10");
			
			input.addProperty("ADDRESS_CHECK", "CHECK");
		}
		String stringInputData = inputData.toString();
		super.data.getAsJsonObject().addProperty("InputData", stringInputData);
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;
	}
	
}
