package com.service.impl.db.v1_0;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.si.consts.Error;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.DbInvoker;

public class DB_INSERT_BOTT_ST_TRANSACTIONS extends SI_Service{

	public DB_INSERT_BOTT_ST_TRANSACTIONS(String data, boolean isMergeData,
			String siVersion, String processDataSource, String logDataSource,
			ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource,
				serviceConfig, log);
		super.invoker = new DbInvoker(serviceConfig, processDataSource, log);
		super.isManualMapping_Input = true;
		super.isManualMapping_Output = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void businessMapping_Input() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub
		JsonArray arr =  new JsonArray();
		JsonObject data = new JsonObject();
		data.add("item", this.data);
		arr.add(data);
		this.invoked_input = arr;
		
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
		JsonArray array = invoked_output.getAsJsonArray();
		if(array.size()==0){
			throw new SIException(Error.DB_99, "Invalid Data");
		}
		if(!array.get(0).getAsJsonObject().get("LOG_ID").getAsString().equals("0")){
			throw new SIException(Error.DB_99, array.get(0).getAsJsonObject().get("LOG_CODE").getAsString());
		}
	}

	@Override
	public void customize_InvokedOutput() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manualMapping_output() throws SIException {
		// TODO Auto-generated method stub
		this.data = this.invoked_output;
	}

	@Override
	public void businessMapping_Output() throws SIException {
		// TODO Auto-generated method stub
		
	}

}
