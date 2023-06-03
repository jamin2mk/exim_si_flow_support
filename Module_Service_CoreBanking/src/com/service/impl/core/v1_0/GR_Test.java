package com.service.impl.core.v1_0;

import java.util.List;

import com.consts.Service;
import com.pre.impl.core.Pre_AddLimitNodeCore;
import com.pre.impl.core.Pre_AddLimitNodeTeller;
import com.pre.impl.core.Pre_OpenLoanAccount;
import com.si.consts.Error;
import com.si.exception.SIException;
import com.si.model.ResultAIS;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.pre.SIPre;
import com.si.service.SI_Service;

public class GR_Test extends SI_Service {

	protected SIPre pre;
	
	public GR_Test(String data, boolean isMergeData, String siVersion,
			String processDataSource, String logDataSource,
			ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource,
				serviceConfig, log);
		super.isManualMapping_Input = true;
		super.isManualMapping_Output = true;
		super.isManualInvoke = true;
		// super.invoker = new CoreBankingInvoker(serviceConfig,
		// processDataSource, requestType, responseType, log)
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
			case Service.AddLimitNodeCore:
				pre = new Pre_AddLimitNodeCore(this.data.toString(), this.siVersion, null, super.processDataSource, super.logDataSource, this.log);
				break;
			case Service.AddLimitNodeTeller:
				pre = new Pre_AddLimitNodeTeller(this.data.toString(), this.siVersion, null, super.processDataSource, super.logDataSource);
				break;
			case Service.OpenLoanAccount:
				pre = new Pre_OpenLoanAccount(this.data.toString(), this.siVersion, null, super.processDataSource, super.logDataSource);
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

	}

	@Override
	public void businessMapping_Output() throws SIException {
		// TODO Auto-generated method stub

	}

}
