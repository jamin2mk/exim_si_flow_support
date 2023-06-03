package com.service.impl.core.v1_0;

import java.text.MessageFormat;

import com.model.error.res.v_1_0.ErrorDetail;
import com.model.unlink_collateral_details.req.v_1_0.UnlinkCollateralDetailsRequest;
import com.model.unlink_collateral_details.res.v_1_0.Body;
import com.model.unlink_collateral_details.res.v_1_0.UnlinkCollateralDetailsResponse;
import com.si.consts.Error;
import com.si.consts.Message;
import com.si.exception.SIException;
import com.si.helper.XmlHelper;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.CoreBankingInvoker;

public class UnlinkCollateralDetails extends SI_Service {
	
	protected Body body = null;

	public UnlinkCollateralDetails(String data, boolean isMergeData,
			String siVersion, String processDataSource, String logDataSource,
			ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource,
				serviceConfig, log);
		super.isManualMapping_Output = true;
		super.invoker = new CoreBankingInvoker(serviceConfig,
				processDataSource, UnlinkCollateralDetailsRequest.class,
				UnlinkCollateralDetailsResponse.class, log);

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

	}

	@Override
	public void invoke_manual() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void check_invokeResult() throws SIException {
		// TODO Auto-generated method stub


		String s = XmlHelper.fetchSubElementAsString(super.invoked_output
				.getAsJsonObject().get("resultResponse").getAsString(), "Body");
		try {
			this.body = XmlHelper.convertXmlToObject(Body.class, s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new SIException("MAP-99", e.getMessage());
		}
		if (this.body.getUnlinkCollateralDetailsResponseBody() == null) {
			if (this.body.getError() != null) {
				String errorCode = "SV-98";
				String errorDesc = "";
				
				for (ErrorDetail er : this.body
						.getError().getFiBusinessException().getErrorDetail()) {
					errorDesc += er.getErrorDesc() + " [Source: " + er.getErrorSource() + "]; ";
				}
				throw new SIException(errorCode, errorDesc);
			} else {
				throw new SIException(Error.SV_99, MessageFormat.format(
						Message.NO_DATA_RESPONSE,
						super.log.getSystem().getTo(), super.log.getService()));
			}
		}
		super.invoked_output = super.gson.toJsonTree(this.body);


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
