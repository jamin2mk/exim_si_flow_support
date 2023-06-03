package com.pre.impl.core;

import com.consts.Service;
import com.consts.Version;
import com.si.consts.Error;
import com.si.consts.SYS;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.ServiceInfo;
import com.si.pre.SIPre;

public class Pre_CreditCardPublishSub extends SIPre{

	public Pre_CreditCardPublishSub(String data, String siVersion, String processInfo, String processDataSource, String logDataSource) {
		super(Service.CreditCardPublish_Sub, data, siVersion, processInfo, processDataSource, logDataSource);
		super.initLog();
		super.log.setSystem(SYS.BPM, null, SYS.CORE);
	}
	
	public Pre_CreditCardPublishSub(String data, String siVersion, String processInfo, String processDataSource, String logDataSource, SI_Log log) {
		super(Service.CreditCardPublish_Sub, data, siVersion, processInfo, processDataSource, logDataSource, log);
		super.initLog();
		super.log.setSystem(SYS.BPM, null, SYS.CORE);
	}

	@Override
	public String executeService(ServiceInfo serviceInfo) throws SIException {
		String result = null;

		// VERSIONING
		switch (serviceInfo.getVersion()) {

		case Version.v1_0:
			result = new com.service.impl.core.v1_0.CreditCardPublishSub(super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log).execute();
			break;

		default:
			throw new SIException(Error.VER_99, String.format("Service [%s] has no version [%s]", super.service, serviceInfo.getVersion()));
		}
		return result;
	}
	
}
