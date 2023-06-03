package com.pre.impl.odm.v1_0;

import com.consts.Version;
import com.si.consts.Error;
import com.si.consts.SYS;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.ServiceInfo;
import com.si.pre.SIPre;

public class Pre_ODM_Gendoc_Service extends SIPre{
	
	/** USING FOR SINGLE **/
	public Pre_ODM_Gendoc_Service(String rule, boolean isMergeData, String data, String siVersion, String processInfo, String dataSourceName, String logDataSource) {
		super(rule, data, siVersion, processInfo, dataSourceName, logDataSource);
		super.isMergeData = isMergeData;

		super.initLog();
		super.log.setSystem(SYS.BPM, null, SYS.ODM);
	}

	/** USING FOR GROUP **/
	public Pre_ODM_Gendoc_Service(String rule, boolean isMergeData, String data, String siVersion, String processInfo, String dataSourceName, String logDataSource, SI_Log log) {
		super(rule, data, siVersion, processInfo, dataSourceName, logDataSource, log);
		super.isMergeData = isMergeData;

		super.initLog();
		super.log.setSystem(SYS.BPM, null, SYS.ODM);
	}

	@Override
	public String executeService(ServiceInfo serviceInfo) throws SIException {
		String result = null;

		// VERSIONING
		switch (serviceInfo.getVersion()) {

		case Version.v1_0:
			result = new com.service.impl.odm.v1_0.ODM_Gendoc_Service(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log).execute();
			break;

		default:
			throw new SIException(Error.VER_99, String.format("Service [%s] has no version [%s]", super.service, serviceInfo.getVersion()));
		}
		return result;
	}


}
