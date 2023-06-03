package com.si.pre;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.si.consts.Error;
import com.si.consts.Log;
import com.si.exception.SIException;
import com.si.helper.LogHelper;
import com.si.helper.ResultHelper;
import com.si.helper.ServiceHelper;
import com.si.model.FromInput;
import com.si.model.SI_Log;
import com.si.model.ServiceInfo;

public abstract class SIPre {

	private final int DATA_SIZE_LIMIT = 100;
	public String service;
	protected String data;
	protected String siVersion;
	protected String processInfo;
	protected String processDataSource;
	protected String logDataSource;

	protected SI_Log log;

	public boolean isMergeData = false;
	public boolean isTracing = false;

	public boolean isBuiltIn;
	public boolean isLoadPckProtocol;

	public SIPre(String service, String data, String siVersion, String processInfo, String processDataSource, String logDataSource) {
		this.data = data;
		this.service = service;
		this.siVersion = siVersion;

		this.processInfo = processInfo;
		this.processDataSource = processDataSource;
		this.logDataSource = logDataSource;

		this.log = new SI_Log();

		this.log.setServiceName(service);
		this.log.setProcessInfo(processInfo);
	}

	public SIPre(String service, String data, String siVersion, String processInfo, String processDataSource, String logDataSource, SI_Log log) {
		this.data = data;
		this.service = service;
		this.siVersion = siVersion;

		this.processInfo = processInfo;
		this.processDataSource = processDataSource;
		this.logDataSource = logDataSource;

		this.log = new SI_Log();

		// set [parent_code] from sub-service
		this.log.setParentCode(log.getLogCode());

		this.log.setServiceName(service);
		this.log.setProcessInfo(processInfo);
	}

	public void initLog() {

		JsonObject data_input = new GsonBuilder().serializeNulls().create().fromJson(this.data, JsonObject.class);

		if (isBuiltIn) {
			this.log.setFromInput(new FromInput(service, isMergeData, data_input, siVersion, processDataSource));
		} else {
			this.log.setFromInput(new FromInput(data_input, siVersion, processDataSource));
		}
	}

	/**
	 * @return
	 */
	public String invoke() {

		String result = null;
		String resultAIS = null;
		
		try {
			// check size data input
			if(this.data.getBytes().length /1024 > this.DATA_SIZE_LIMIT){
				throw new SIException(Error.DATA_99, "Data input over size [" + DATA_SIZE_LIMIT +" Kb]");
			}
			
			
			/* GET VERSION */
			ServiceInfo serviceInfo = ServiceHelper.fetchServiceInfo(this.siVersion, this.service, this.isLoadPckProtocol, this.processDataSource);

			/** Update service's version to log [fromInput.serviceVersion] **/
			this.log.setServiceVersion(serviceInfo.getVersion());

			result = executeService(serviceInfo);

		} catch (SIException siex) {

			String result_ex = ResultHelper.getResultWithException(siex, this.log);
			if (this.isTracing) {
				result = ResultHelper.addSiBreaking(this.service, siex.getService(), result_ex);
			} else {
				result = result_ex;
			}
			LogHelper.printStacktrace(siex);

		} catch (Exception ex) {
			result = ResultHelper.getResultWithException(ex, this.log);
			LogHelper.printStacktrace(ex);

		} catch (NoClassDefFoundError ex) {
			result = ResultHelper.getResultWithException(ex, this.log);
			LogHelper.printStacktrace(ex);
		} finally {
			/** WRITE LOG **/
			LogHelper.writeLogToDB(this.log, this.processDataSource);
			resultAIS = ResultHelper.addLogIdToErrorInfo(result, this.log);

			// Fetch result
			LogHelper.writeLog(Log.INFO, Log.FROM_OUTPUT, this.log.getService(), resultAIS);
		}
		return resultAIS;
	}

	public abstract String executeService(ServiceInfo serviceInfo) throws SIException;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
