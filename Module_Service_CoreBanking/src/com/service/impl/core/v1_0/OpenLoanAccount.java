package com.service.impl.core.v1_0;

import java.text.MessageFormat;

import com.google.gson.JsonObject;
import com.model.error.res.v_1_0.ErrorDetail;
import com.model.openloanaccount.req.v_1_0.OpenLoanAccountRequest;
import com.model.openloanaccount.res.v_1_0.Body;
import com.model.openloanaccount.res.v_1_0.OpenLoanAccountResponse;
import com.si.consts.Error;
import com.si.consts.Message;
import com.si.exception.SIException;
import com.si.helper.XmlHelper;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.CoreBankingInvoker;

public class OpenLoanAccount extends SI_Service{

	protected Body body = null;
	public OpenLoanAccount(String data, boolean isMergeData, String siVersion,
			String processDataSource, String logDataSource,
			ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource,
				serviceConfig, log);
		super.isManualMapping_Output = true;
		super.isManualMapping_Input = true;
		super.invoker = new CoreBankingInvoker(serviceConfig, processDataSource, OpenLoanAccountRequest.class, OpenLoanAccountResponse.class, log);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void businessMapping_Input() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub
		String json = "{\"CustId\":118385271,\"LASchmCode\":\"OA001\",\"LASchmType\":\"LAA\",\"LAAcctCurr\":\"VND\",\"LABranchId\":1503,\"GenLedgerSubHeadCode\":21120,\"CurCode\":\"VND\",\"AcctStmtMode\":\"N\",\"LoanPeriodMonths\":0,\"LoanPeriodDays\":23,\"RePmtMethod\":\"E\",\"AcctId\":150310102000283,\"SchmCode\":10102,\"SchmType\":\"CAA\",\"AcctCurr\":\"VND\",\"BankId\":\"01\",\"BranchId\":1503,\"EqInstallFlg\":\"N\",\"InstallmentId1\":\"PRDEM\",\"InstallStartDt1\":\"2023-03-28\",\"InstallFreq_Cal1\":\"00\",\"InstallFreq_Type1\":\"M\",\"InstallFreq_StartDt1\":28,\"InstallFreq_WeekDay1\":0,\"InstallFreq_HolStat1\":\"N\",\"IntFreq_Cal1\":\"00\",\"IntFreq_Type1\":\"M\",\"IntFreq_StartDt1\":28,\"IntFreq_WeekDay1\":0,\"IntFreq_HolStat1\":\"N\",\"NoOfInstall\":1,\"IntStartDt1\":\"2023-03-28\",\"FlowAmt_amountValue1\":500000000,\"FlowAmt_currencyCode1\":\"VND\",\"InstallmentId2\":\"INDEM\",\"InstallStartDt2\":\"2023-03-28\",\"InstallFreq_Cal2\":\"00\",\"InstallFreq_Type2\":\"M\",\"InstallFreq_StartDt2\":28,\"InstallFreq_WeekDay2\":0,\"InstallFreq_HolStat2\":\"N\",\"IntFreq_Cal2\":\"00\",\"IntFreq_Type2\":\"M\",\"IntFreq_StartDt2\":28,\"IntFreq_WeekDay2\":0,\"IntFreq_HolStat2\":\"N\",\"IntStartDt2\":\"2023-03-28\",\"FlowAmt_amountValue2\":0,\"FlowAmt_currencyCode2\":\"VND\",\"LoanAmt_amountValue\":500000000,\"LoanAmt_currencyCode\":\"VND\",\"HoldInOperAcctFlg\":\"N\",\"AcctDrPrefPcnt\":8,\"MaxIntPcntDr\":99,\"MinIntPcntDr\":0,\"OCCUPATION\":99,\"REGION\":\"HCMC\",\"PRODUCT\":\"00000\",\"MARKET\":\"00000\",\"otherType2\":\"9KHAC\",\"ACCT_LIM_ENT\":1,\"DRWNG_PCNT\":100,\"LIM_DOC_DATE\":\"28-02-2023\",\"LIM_EXP_DATE\":\"27-03-2023\",\"LIM_LEVL_INT\":\"N\",\"LIM_PREFIX\":\"LAV230000028\",\"LIM_SUFFIX\":1503,\"SANCTL_LIM\":500000000,\"SANCTL_LIM_CRNCY\":\"VND\",\"SANCT_DATE\":\"28-02-2023\",\"DRWNG_IND\":\"P\",\"LIM_CIF_ID\":118385271,\"LIM_CRNCY_CODE\":\"VND\",\"LIM_SCHM_CODE\":\"OA001\"}";
		JsonObject obj = super.gson.fromJson(json, JsonObject.class);
		JsonObject inputJson = super.data.getAsJsonObject().get("addLimitNodeOutputVO").getAsJsonObject();
		obj.addProperty("LIM_PREFIX",inputJson.getAsJsonObject().get("limitPrefix").getAsString());
		obj.addProperty("LABranchId",inputJson.getAsJsonObject().get("limitSuffix").getAsString());
		obj.addProperty("BranchId",inputJson.getAsJsonObject().get("limitSuffix").getAsString());
		obj.addProperty("LIM_SUFFIX",inputJson.getAsJsonObject().get("limitSuffix").getAsString());
		super.invoked_input = obj;
//		super.invoked_input = super.gson.fromJson(super.data, JsonObject.class);
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
		if (this.body.getLoanAcctAddResponse() == null) {
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
