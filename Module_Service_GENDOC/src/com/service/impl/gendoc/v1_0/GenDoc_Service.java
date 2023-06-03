package com.service.impl.gendoc.v1_0;

import com.google.gson.Gson;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class GenDoc_Service extends SI_Service {

	public GenDoc_Service(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource, serviceConfig, log);

		GenDocEnv genDocEnv = new Gson().fromJson(super.data.getAsJsonObject().get("env"), GenDocEnv.class);
		GenDocInvoker odmInvoker = new GenDocInvoker(docName, genDocEnv, processDataSource, log);

		super.invoker = odmInvoker;
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
