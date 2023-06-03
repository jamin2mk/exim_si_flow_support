package com.service.impl.db.v1_0;

import com.google.gson.JsonArray;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.DbInvoker;

public class DB_InsertCustomer extends SI_Service {

	public DB_InsertCustomer(String data, boolean isMergeData,
			String siVersion, String processDataSource, String logDataSource,
			ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource,
				serviceConfig, log);
		super.isManualMapping_Output = true;
		super.invoker = new DbInvoker(serviceConfig, processDataSource, log);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void businessMapping_Input() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void customize_InvokedInput() throws SIException {
		// TODO Auto-generated method stub

		JsonArray arr = this.invoked_input.getAsJsonArray().get(2)
				.getAsJsonObject().get("item").getAsJsonArray();
		if (arr.size() > 0) {
			arr.get(0).getAsJsonObject().addProperty("IS_DEFAULT", "1");
		}

//		JsonArray phones = this.data.getAsJsonObject().get("phone").getAsJsonArray();
//		JsonObject customerData = this.invoked_input.getAsJsonArray().get(1)
//				.getAsJsonObject().get("item").getAsJsonObject();
//
//		for (JsonElement phone : phones) {
////			if (phone.getAsJsonObject().get("phoneType").getAsString().equals("CELLPH")) {
////				customerData.addProperty("PHONE",
////						phone.getAsJsonObject().get("phoneNo").getAsString());
////			} else if (phone.getAsJsonObject().get("phoneType").getAsString().equals("HOMEPH")){
////				customerData.addProperty("PHONE",phone.getAsJsonObject().get("phoneNo").getAsString());
////			} else {
//				customerData.addProperty("PHONE",phone.getAsJsonObject().get("phoneNo").getAsString());
//				break;
////			}
//			}

	}

	@Override
	public void invoke_manual() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void check_invokeResult() throws SIException {
		// TODO Auto-generated method stub
		JsonArray check = this.invoked_output.getAsJsonArray();

			if(!check.get(0).getAsJsonObject().get("LOG_ID").getAsString().equals("0")){
//				System.err.println(check);
//			}else {
				throw new SIException("DB-99", "Insert Customer Error : " + check.get(0).getAsJsonObject().get("LOG_CODE").getAsString());
			}
	
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
		// TODO Auto-generated method stub

	}

}
