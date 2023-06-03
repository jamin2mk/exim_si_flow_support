package com.service.impl.db.v1_0;

import java.util.concurrent.ExecutionException;
import com.si.cache.CData;
import com.si.consts.Error;
import com.si.exception.SIException;
import com.si.helper.CacheHelper;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.DbInvoker;

public class DB_ServiceCache extends SI_Service {

	public DB_ServiceCache(String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource, serviceConfig, log);
		super.invoker = new DbInvoker(serviceConfig, processDataSource, log);
		super.isManualInvoke = true;
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
		try {
			this.invoked_output = CData.SERVICE_DATA.get(CacheHelper.generateKeyProcedure(this.invoked_input.getAsJsonArray(), super.processDataSource,super.serviceConfig.getDb()));
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			throw new SIException(Error.CACHE_99, "load cache error");
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
