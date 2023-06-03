package com.service.impl.core.v1_0;

import java.text.MessageFormat;

import com.google.gson.JsonObject;
import com.model.creditcardpublish.req.v_1_0.CreditCardPublishRequest;
import com.model.creditcardpublish.res.v_1_0.CreditCardPublishResponse;
import com.si.consts.Error;
import com.si.consts.Message;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.CoreBankingInvoker;

public class CreditCardPublish extends SI_Service{

	public CreditCardPublish(String data, boolean isMergeData,
			String siVersion, String processDataSource, String logDataSource,
			ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource,
				serviceConfig, log);
		super.isManualMapping_Input = true;
		super.isManualMapping_Output = true;
		super.invoker = new CoreBankingInvoker(serviceConfig, processDataSource, CreditCardPublishRequest.class, CreditCardPublishResponse.class, log);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void businessMapping_Input() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub
		super.invoked_input = super.gson.fromJson(super.data, JsonObject.class);
		
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
		
		CreditCardPublishResponse creditCardPublishResponse = super.gson.fromJson(super.invoked_output, CreditCardPublishResponse.class);
		
		if(creditCardPublishResponse!=null){
			if(creditCardPublishResponse.getStatusReturn().equals("0")){
				throw new SIException(String.format("SV-%s",creditCardPublishResponse.getStatusReturn()), creditCardPublishResponse.getDescriptionMessage());
			}
		}else {
			throw new SIException(Error.SV_99, MessageFormat.format(Message.NO_DATA_RESPONSE,super.log.getSystem().getTo(), super.log.getService()));
		}
		
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
