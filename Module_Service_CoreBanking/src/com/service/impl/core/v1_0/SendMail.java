package com.service.impl.core.v1_0;

import com.si.consts.Error;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.mail.SEmailModel;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.util.SEmail;

public class SendMail extends SI_Service {

	protected SEmailModel sEmailModel;

	public SendMail(String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource, serviceConfig, log);

		super.isManualMapping_Output = true;
		super.isManualMapping_Input = true;
		super.isManualInvoke = true;

	}

	@Override
	public void businessMapping_Input() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub

		this.sEmailModel = super.gson.fromJson(super.data, SEmailModel.class);
	}

	@Override
	public void customize_InvokedInput() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void invoke_manual() throws SIException {
		// TODO Auto-generated method stub

		SEmail slEmail = new SEmail(this.sEmailModel);
		try {
			boolean isSuccess = slEmail.sentLosMail();
			if (!isSuccess) {
				throw new SIException(Error.MAIL_99, "Error in sending email");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new SIException(Error.EX_99, "Error in sending email", e);
		}
	}

	@Override
	public void check_invokeResult() throws SIException {

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
