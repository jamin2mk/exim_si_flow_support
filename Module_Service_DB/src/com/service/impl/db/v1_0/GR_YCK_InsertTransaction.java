package com.service.impl.db.v1_0;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.consts.Service;
import com.google.gson.JsonObject;
import com.pre.impl.db.Pre_DB_INSERT_BOTT_YCK_TRANSACTIONS;
import com.pre.impl.db.Pre_DB_Service;
import com.pre.impl.db.Pre_DB_UPSERT_BOTT_YCK_PROCESS;
import com.si.consts.DATE_PATTERN;
import com.si.consts.Error;
import com.si.exception.SIException;
import com.si.helper.DateHelper;
import com.si.helper.SLAHelper;
import com.si.model.ResultAIS;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.pre.SIPre;
import com.si.service.SI_Service;

public class GR_YCK_InsertTransaction extends SI_Service{
	
	protected SIPre pre;
	
	protected JsonObject boYCK = null;
	protected JsonObject losHandler = null;

	public GR_YCK_InsertTransaction(String data, boolean isMergeData,
			String siVersion, String processDataSource, String logDataSource,
			ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource,
				serviceConfig, log);
		super.isManualMapping_Input = true;
		super.isManualMapping_Output = true;
		super.isManualInvoke = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void businessMapping_Input() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub
		this.invoked_input = this.data;
		
	}

	@Override
	public void customize_InvokedInput() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void invoke_manual() throws SIException {
		// TODO Auto-generated method stub
		this.invoked_output = new JsonObject();
		int time = 0;
		String NEXT_STATUS_NAME = "";
		String NEXT_STATUS_CODE = "";
		List<String> services = super.serviceConfig.getGroup().getServices();
		this.boYCK = this.invoked_input.getAsJsonObject().get("BO_YCK").getAsJsonObject();
		this.losHandler = this.invoked_input.getAsJsonObject().get("LOS_Handler").getAsJsonObject();
		JsonObject LOS_BPM_INFORMATION = this.boYCK.get("LOS_BPM_INFORMATION").getAsJsonObject();
		
		for (String service : services) {
			
			switch (service) {
			case Service.DB_GetNextRouting:
				JsonObject object = new JsonObject();
				object.add("LOS_Handler", this.losHandler);
				object.add("LOS_BPM_INFORMATION", this.boYCK.get("LOS_BPM_INFORMATION").getAsJsonObject());
				object.addProperty("iTOTAL", "");
				
				
				String action = LOS_BPM_INFORMATION.get("ACTION").getAsString();
				if(action.equals("REJECT")||action.equals("CANCEL")|| this.losHandler.get("next_step").getAsString().equals("END")){
					LOS_BPM_INFORMATION.addProperty("IS_COMPLETE", "1");
					
					this.losHandler.addProperty("pre_role",this.losHandler.get("next_role").getAsString());
					this.losHandler.addProperty("pre_function",this.losHandler.get("next_function").getAsString());
					this.losHandler.addProperty("pre_role_name",this.losHandler.get("next_role_name").getAsString());
					this.losHandler.addProperty("pre_step",this.losHandler.get("next_step").getAsString());
					this.losHandler.addProperty("pre_user",this.losHandler.get("next_user").getAsString());
					
					this.losHandler.addProperty("next_role","");
					this.losHandler.addProperty("next_function","");
					this.losHandler.addProperty("next_role_name","");
					this.losHandler.addProperty("next_step","");
					this.losHandler.addProperty("next_user","");
					
				}
				
				pre = new Pre_DB_Service("ADMIN_V1.LOAD_NEXT_ROUTING", false, object.toString(), this.siVersion,null,this.processDataSource, this.logDataSource, this.log);
				break;
			
			case Service.DB_INSERT_BOTT_YCK_TRANSACTIONS:
				Calendar date = Calendar.getInstance();
				
				Calendar timeBpm;
				try {
//					LOS_BPM_INFORMATION.addProperty("TIME_COMPLETE_TASK", DateHelper.convertDateToBpmDateString(new SimpleDateFormat(DATE_PATTERN.SLASH_LONG_S3).format(date), DATE_PATTERN.SLASH_LONG_S3, DATE_PATTERN.HYPHEN_LONG_T_S3_OFFSET));
//					
//					timeBpm = DateHelper.convertBpmDateStringToCalendar(LOS_BPM_INFORMATION.get("TIME_RECEIVE_TASK").getAsString());
//					LOS_BPM_INFORMATION.addProperty("TOTAL_TIME_COMPLETE", date.getTime()-timeBpm.getTimeInMillis());
//					timeBpm = DateHelper.convertBpmDateStringToCalendar(LOS_BPM_INFORMATION.get("TIME_HANDLE_TASK").getAsString());
//					LOS_BPM_INFORMATION.addProperty("TOTAL_TIME_HADLE", date.getTime()-timeBpm.getTimeInMillis());
					
					LOS_BPM_INFORMATION.addProperty("TIME_COMPLETE_TASK", new SimpleDateFormat(DATE_PATTERN.HYPHEN_LONG_T_S3_OFFSET).format(date.getTime()));
					timeBpm = DateHelper.convertStringToCalendar(LOS_BPM_INFORMATION.get("TIME_RECEIVE_TASK").getAsString(),DATE_PATTERN.HYPHEN_LONG_T_S3_OFFSET);
					
					LOS_BPM_INFORMATION.addProperty("TOTAL_TIME_COMPLETE", SLAHelper.caculateTotalTime(date.getTimeInMillis()-timeBpm.getTimeInMillis(), true));
					timeBpm = DateHelper.convertStringToCalendar(LOS_BPM_INFORMATION.get("TIME_HANDLE_TASK").getAsString(),DATE_PATTERN.HYPHEN_LONG_T_S3_OFFSET);
					LOS_BPM_INFORMATION.addProperty("TOTAL_TIME_HANDLE", SLAHelper.caculateTotalTime(date.getTimeInMillis()-timeBpm.getTimeInMillis(), true));
					
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					throw new SIException(Error.GR_99, "Invalid Data");
				}
				
				
				//----
				if(this.data.isJsonArray()){
					
					NEXT_STATUS_NAME=this.data.getAsJsonArray().get(0).getAsJsonObject().get("NEXT_STATUS_NAME").getAsString();
					NEXT_STATUS_CODE=this.data.getAsJsonArray().get(0).getAsJsonObject().get("NEXT_STATUS_CODE").getAsString();
					if(!this.losHandler.get("next_step").getAsString().equals("END")){
						time = this.data.getAsJsonArray().get(0).getAsJsonObject().get("TIME_SLA").getAsInt();
						String timeHandleTask = LOS_BPM_INFORMATION.get("TIME_COMPLETE_TASK").getAsString();
			
						String dateStr;
						try {
							Date d1 = SLAHelper.caculateTimeSlaCalendar(time, DateHelper.convertStringToCalendar(timeHandleTask,DATE_PATTERN.HYPHEN_LONG_T_S3_OFFSET),super.processDataSource);
							dateStr = new SimpleDateFormat(DATE_PATTERN.HYPHEN_LONG_T_S3_OFFSET).format(d1);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							throw new SIException(Error.GR_99, "Parse Error");
						}
						LOS_BPM_INFORMATION.addProperty("SLA_COMPLETE_TASK", dateStr);
						LOS_BPM_INFORMATION.addProperty("SLA", time);
					}
				}
				
				//LOS_BPM_INFORMATION
//				LOS_BPM_INFORMATION.addProperty("FUNCTION_BEFORE", LOS_BPM_INFORMATION.get("FUNCTION_UPDATE").getAsString());
//				LOS_BPM_INFORMATION.addProperty("ROLE_BEFORE", LOS_BPM_INFORMATION.get("ROLE_UPDATED").getAsString());
//				LOS_BPM_INFORMATION.addProperty("ROLE_BEFORE_NAME", LOS_BPM_INFORMATION.get("ROLE_UPDATE_NAME").getAsString());
//				LOS_BPM_INFORMATION.addProperty("FULLNAME_BEFORE", LOS_BPM_INFORMATION.get("FULLNAME_UPDATED").getAsString());
//				LOS_BPM_INFORMATION.addProperty("ROLE_UPDATE_NAME",this.losHandler.get("next_role_name").getAsString());
//				LOS_BPM_INFORMATION.addProperty("ROLE_UPDATED",this.losHandler.get("next_role").getAsString());
//				LOS_BPM_INFORMATION.addProperty("FUNCTION_UPDATE",this.losHandler.get("next_function").getAsString());
				LOS_BPM_INFORMATION.addProperty("STATUS_BEFORE", LOS_BPM_INFORMATION.get("STATUS_UPDATE").getAsString());
				LOS_BPM_INFORMATION.addProperty("STATUS_BEFORE_NAME", LOS_BPM_INFORMATION.get("STATUS_UPDATE_NAME").getAsString());
				LOS_BPM_INFORMATION.addProperty("STATUS_UPDATE_NAME",NEXT_STATUS_NAME);
				LOS_BPM_INFORMATION.addProperty("STATUS_UPDATE",NEXT_STATUS_CODE);
				
				pre = new Pre_DB_INSERT_BOTT_YCK_TRANSACTIONS(false, this.boYCK.toString(), this.siVersion, null, super.processDataSource, super.logDataSource, this.log);
				break;
				
			case Service.DB_UPSERT_BOTT_YCK_PROCESS:
				
				pre = new Pre_DB_UPSERT_BOTT_YCK_PROCESS(false, this.boYCK.toString(), this.siVersion, null, super.processDataSource, super.logDataSource, this.log);
				break;
				
			default:
				throw new SIException(Error.GR_99, "Invalid service");
			}
			
			String invokeResult = pre.invoke();
			ResultAIS resultAIS = super.gson.fromJson(invokeResult, ResultAIS.class);
			
			if(!resultAIS.getErrorInfo().getErrorCode().equalsIgnoreCase("00")){
				throw new SIException(resultAIS.getErrorInfo().getErrorCode(), resultAIS.getErrorInfo().getMessage());
			}
			this.data = super.gson_SerializeNulls.toJsonTree(resultAIS.getData());
			
		}
		
		
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
		this.invoked_output.getAsJsonObject().add("LOS_Handler", this.losHandler);
		this.invoked_output.getAsJsonObject().add("BO_YCK", this.boYCK);
		this.data = this.invoked_output;
		
	}

	@Override
	public void businessMapping_Output() throws SIException {
		// TODO Auto-generated method stub
		
	}

}
