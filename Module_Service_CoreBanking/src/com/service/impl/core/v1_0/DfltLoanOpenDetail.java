package com.service.impl.core.v1_0;

import java.text.MessageFormat;

import com.model.dfltloan_open_detail.req.v_1_0.DfltLoanOpenDetailRequest;
import com.model.dfltloan_open_detail.res.v_1_0.DfltLoanOpenDetailResponse;
import com.si.consts.Error;
import com.si.consts.Message;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.CoreBankingInvoker;

public class DfltLoanOpenDetail extends SI_Service{

	public DfltLoanOpenDetail(String data, boolean isMergeData,
			String siVersion, String processDataSource, String logDataSource,
			ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource,
				serviceConfig, log);
		super.isManualMapping_Output = true;
		super.invoker = new CoreBankingInvoker(serviceConfig, processDataSource, DfltLoanOpenDetailRequest.class, DfltLoanOpenDetailResponse.class, log);
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
		DfltLoanOpenDetailResponse dfltLoanOpenDetailResponse = super.gson.fromJson(super.invoked_output, DfltLoanOpenDetailResponse.class);
		if(dfltLoanOpenDetailResponse.getExecuteFinacleScriptCustomData()==null){
			throw new SIException(Error.SV_99, MessageFormat.format(Message.NO_DATA_RESPONSE, super.log.getSystem().getTo(), super.log.getService()));
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
