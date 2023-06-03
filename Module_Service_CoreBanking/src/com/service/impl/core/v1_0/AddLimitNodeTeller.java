package com.service.impl.core.v1_0;

import java.text.MessageFormat;

import com.model.add_limit_node_teller.req.v_1_0.AddLimitNodeTellerRequest;
import com.model.add_limit_node_teller.res.v_1_0.AddLimitNodeResponse;
import com.model.error.res.v_1_0.fix.ErrorDetail;
import com.si.consts.Error;
import com.si.consts.Message;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.CoreBankingInvoker;

public class AddLimitNodeTeller extends SI_Service {

	public AddLimitNodeTeller(String data, boolean isMergeData,
			String siVersion, String processDataSource, String logDataSource,
			ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource,
				serviceConfig, log);
		super.isManualMapping_Output = true;
		super.isManualMapping_Input = true;
		super.invoker = new CoreBankingInvoker(serviceConfig,
				processDataSource, AddLimitNodeTellerRequest.class,
				AddLimitNodeResponse.class, log);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void businessMapping_Input() throws SIException {
		// TODO Auto-generated method stub

	}

	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub
//		String json = "{\"cifId\":106058979,\"drwngPowerInd\":\"D\",\"limitDesc\":\"TEST VAY CCOL\",\"limitEffDate\":\"2023-01-03\",\"limitExpiryDate\":\"2023-12-03\",\"limitPrefix\":\"LAV230013392\",\"limitReviewDate\":\"2023-12-03\",\"limitSanctDate\":\"2023-01-03\",\"limitSigningDate\":\"2023-01-03\",\"limitSuffix\":1504,\"limitType\":\"C\",\"serial_num1\":\"0001\",\"primaryCustomer1\":\"Y\",\"categoryType1\":\"I\",\"categoryCode1\":106058979,\"activeFlg1\":\"Y\",\"serial_num2\":\"0002\",\"primaryCustomer2\":\"Y\",\"categoryType2\":\"C\",\"categoryCode2\":\"VND\",\"activeFlg2\":\"Y\",\"serial_num3\":\"0003\",\"primaryCustomer3\":\"Y\",\"categoryType3\":\"P\",\"categoryCode3\":\"02SL\",\"activeFlg3\":\"Y\",\"sanctAuthCode\":\"001\",\"sanctLevelCode\":\"01\",\"amountValue\":150000000,\"currencyCode\":\"VND\",\"custSanctLim\":150000000,\"termType\":\"00002\",\"month\":10,\"day\":0,\"matDate_ui\":\"03-12-2023\",\"grcMonth\":1,\"grcDay\":0,\"limitAmt\":150000000,\"limUnsecAmt\":0,\"limitSolId\":1000,\"maxMonth\":20,\"maxDay\":0,\"isCollateral\":\"\",\"isTermAccount\":\"N\",\"isValuecollateral\":0,\"titName\":\"\",\"proName\":\"\",\"appName\":\"\",\"PRODUCT_TYPE\":\"02SL\",\"MASTER_CODE\":\"OPNEW\",\"totSAmt\":0}";
//		JsonObject obj = super.gson.fromJson(json, JsonObject.class);
//		obj.addProperty("limitPrefix",super.data.getAsJsonObject().get("limitPrefix").getAsString());
//		obj.addProperty("limitSuffix",super.data.getAsJsonObject().get("limitSuffix").getAsString());
//		super.invoked_input = obj;
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

		AddLimitNodeResponse addLimitNodeResponse = super.gson.fromJson(
				super.invoked_output, AddLimitNodeResponse.class);

		if (addLimitNodeResponse != null) {
			if (addLimitNodeResponse.getFiBusinessException() != null) {
				String errorCode = "SV-98";
				String errorDesc = "";

				for (ErrorDetail er : addLimitNodeResponse.getFiBusinessException().getErrorDetail()) {
					errorDesc += er.getErrorDesc() + " [Source: "+ er.getErrorSource() + "]; ";
				}

				throw new SIException(errorCode, errorDesc);
			} else if (!addLimitNodeResponse.getErrorCode().equals("0")) {
				throw new SIException(String.format("SV-%s",addLimitNodeResponse.getErrorCode()),addLimitNodeResponse.getErrorDesc());

			}
		} else {
			throw new SIException(Error.SV_99, MessageFormat.format(
					Message.NO_DATA_RESPONSE, super.log.getSystem().getTo(),
					super.log.getService()));
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
