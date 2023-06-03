package com.service.impl.communication.v1_0;

import java.text.MessageFormat;

import com.model.sendsms.req.v_1_0.SMSRequestBody;
import com.model.sendsms.req.v_1_0.SendSMSRequest;
import com.model.sendsms.res.v_1_0.SMSResponseBody;
import com.model.sendsms.res.v_1_0.SendSMSResponse;
import com.si.consts.Error;
import com.si.exception.SIException;
import com.si.helper.XmlHelper;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.CoreBankingInvoker;

public class SendSMS extends SI_Service {

	CoreBankingInvoker smsInvoker;
	String responseBodyXml;

	public SendSMS(String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource, serviceConfig, log);

		super.isManualMapping_Output = true;
		super.isManualMapping_Input = true;
		super.isManualInvoke = true;

		this.smsInvoker = new CoreBankingInvoker(serviceConfig, processDataSource, SendSMSRequest.class, SendSMSResponse.class, log);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void businessMapping_Input() throws SIException {
		// TODO Auto-generated method stub 

	}

	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub

		String preSMS = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>{0}";
		SMSRequestBody smsRequestBody = super.gson.fromJson(super.data, SMSRequestBody.class);
		String reqData = XmlHelper.convertObjectToXml(SMSRequestBody.class, smsRequestBody);

		String xmlData = MessageFormat.format(preSMS, reqData);
		SendSMSRequest request = new SendSMSRequest(xmlData);
		super.invoked_input = super.gson.toJsonTree(request);
		
	}

	@Override
	public void customize_InvokedInput() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void invoke_manual() throws SIException {
		// TODO Auto-generated method stub
		this.responseBodyXml = this.smsInvoker.invoke(super.invoked_input, "SMSResponse");
	}

	@Override
	public void check_invokeResult() throws SIException {

		SMSResponseBody smsResponseBody = null;
		try {
			smsResponseBody = XmlHelper.convertXmlToObject(SMSResponseBody.class, this.responseBodyXml);
		} catch (Exception e) {
			throw new SIException(Error.EX_99, "Error in converting SMSResponseBody", e);
		}

		if (!smsResponseBody.getErrorCode().equalsIgnoreCase("0")) {
			String code = MessageFormat.format("SV-{0}", smsResponseBody.getErrorCode());
			throw new SIException(code, smsResponseBody.getErrorDesc());
		}
	}

	@Override
	public void customize_InvokedOutput() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void manualMapping_output() throws SIException {
		// TODO Auto-generated method stub
		super.data = null;
	}

	@Override
	public void businessMapping_Output() throws SIException {
		// TODO Auto-generated method stub

	}

}
