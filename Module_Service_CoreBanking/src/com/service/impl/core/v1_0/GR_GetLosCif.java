package com.service.impl.core.v1_0;

import java.util.List;

import com.consts.Service;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pre.impl.core.Pre_GetLOSCIF;
import com.pre.impl.db.Pre_DB_InsertCustomer;
import com.pre.impl.db.Pre_DB_SearchCustomer;
import com.si.consts.Error;
import com.si.exception.SIException;
import com.si.model.ResultAIS;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.pre.SIPre;
import com.si.service.SI_Service;

public class GR_GetLosCif extends SI_Service{
	
	protected SIPre pre;
	protected JsonElement losData = null;

	public GR_GetLosCif(String data, boolean isMergeData, String siVersion,
			String processDataSource, String logDataSource,
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
		List<String> services = super.serviceConfig.getGroup().getServices();
		for (String service : services) {
			
			switch (service) {
			case Service.GetLOSCIF:
				pre = new Pre_GetLOSCIF(this.data.toString(), this.siVersion, null, super.processDataSource, super.logDataSource, this.log);
				break;
			
			case Service.DB_InsertCustomer:
				losData = this.data;
				if(this.data.getAsJsonObject().get("loaiKH").getAsString().equals("DN")){
					this.data.getAsJsonObject().addProperty("quocTich", "VNM");
				}
				this.data.getAsJsonObject().add("userInfo",this.invoked_input.getAsJsonObject().get("userInfo"));
				pre = new Pre_DB_InsertCustomer(Service.DB_InsertCustomer, false, this.data.toString(), this.siVersion, null, super.processDataSource, super.logDataSource, this.log);
				break;
				
			case Service.DB_SearchCustomer:
				pre = new Pre_DB_SearchCustomer(Service.DB_SearchCustomer, false, losData.toString(), this.siVersion, null, super.processDataSource, super.logDataSource, this.log);;
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
		
		this.invoked_output = this.data;
		this.data = new JsonObject();
		JsonObject dataT = this.data.getAsJsonObject();
		dataT.add("searchUser", this.invoked_output);
		dataT.add("losData", this.losData);
	}

	@Override
	public void businessMapping_Output() throws SIException {
		// TODO Auto-generated method stub
		
	}

}
