package com.pre.impl.communication;

import com.consts.Service;
import com.consts.Version;
import com.si.consts.Error;
import com.si.consts.SYS;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.ServiceInfo;
import com.si.pre.SIPre;

public class Pre_SendSMS extends SIPre{
	
	public Pre_SendSMS(String data, String siVersion, String processInfo, String processDataSource, String logDataSource) {
		super(Service.SendSMS, data, siVersion, processInfo, processDataSource, logDataSource);
		super.initLog();
		super.log.setSystem(SYS.BPM, null, SYS.SMS);
	}
	
	public Pre_SendSMS(String data, String siVersion, String processInfo, String processDataSource, String logDataSource, SI_Log log) {
		super(Service.SendSMS, data, siVersion, processInfo, processDataSource, logDataSource, log);
		super.initLog();
		super.log.setSystem(SYS.BPM, null, SYS.SMS);
	}

	@Override
	public String executeService(ServiceInfo serviceInfo) throws SIException {
		String result = null;

		// VERSIONING
		switch (serviceInfo.getVersion()) {

		case Version.v1_0:
			result = new com.service.impl.communication.v1_0.SendSMS(super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log).execute();
			break;

		default:
			throw new SIException(Error.VER_99, String.format("Service [%s] has no version [%s]", super.service, serviceInfo.getVersion()));
		}
		return result;
	}

}
