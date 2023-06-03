package com.service.impl.core.v1_0;

import java.text.MessageFormat;

import com.model.error.res.v_1_0.fix.ErrorDetail;
import com.model.odacctaddrequest.req.v_1_0.ODAcctAddRequest;
import com.model.odacctaddrequest.res.v_1_0.ODAcctAddResponse;
import com.si.consts.Error;
import com.si.consts.Message;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.CoreBankingInvoker;

public class ODAcctAdd extends SI_Service {

	public ODAcctAdd(String data, boolean isMergeData, String siVersion,
			String processDataSource, String logDataSource,
			ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource,
				serviceConfig, log);
		super.isManualMapping_Input = true;
		super.isManualMapping_Output = true;
		super.invoker = new CoreBankingInvoker(serviceConfig,
				processDataSource, ODAcctAddRequest.class,
				ODAcctAddResponse.class, log);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void businessMapping_Input() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub
		super.invoked_input = super.data;
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
		
//		super.invoked_output = super.gson.fromJson("{\"ODAcctId\":{\"AcctId\":\"1002ODPCC2200288\",\"AcctType\":{\"SchmCode\":\"\",\"SchmType\":\"\"},\"AcctCurr\":\"\",\"BankInfo\":{\"BankId\":\"\",\"Name\":\"\",\"BranchId\":\"\",\"BranchName\":\"\",\"PostAddr\":{\"Addr1\":\"\",\"Addr2\":\"\",\"Addr3\":\"\",\"City\":\"\",\"StateProv\":\"\",\"PostalCode\":\"\",\"Country\":\"\",\"AddrType\":\"\"}}}}",JsonObject.class);
		ODAcctAddResponse odAcctAddResponse = super.gson.fromJson(super.invoked_output, ODAcctAddResponse.class);

		if (odAcctAddResponse != null) {
			if (odAcctAddResponse.getFiBusinessException() != null) {
				String errorCode = "SV-98";
				String errorDesc = "";

				for (ErrorDetail er : odAcctAddResponse.getFiBusinessException().getErrorDetail()) {
					errorDesc += er.getErrorDesc() + " [Source: "+ er.getErrorSource() + "]; ";
				}

				throw new SIException(errorCode, errorDesc);	
			} else if(odAcctAddResponse.getOdAcctId()==null){
				throw new SIException(Error.SV_99, MessageFormat.format(Message.NO_DATA_RESPONSE, super.log.getSystem().getTo(), super.log.getService()));
			}
		} else throw new SIException(Error.SV_99, MessageFormat.format(Message.NO_DATA_RESPONSE, super.log.getSystem().getTo(), super.log.getService()));
	}

	@Override
	public void customize_InvokedOutput() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void manualMapping_output() throws SIException {
		// TODO Auto-generated method stub
		super.data = super.invoked_output;
	}

	@Override
	public void businessMapping_Output() throws SIException {
		// TODO Auto-generated method stub

	}

}
