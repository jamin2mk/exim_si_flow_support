package com.pre.impl.db;

import com.consts.Version;
import com.si.consts.Error;
import com.si.consts.SYS;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.ServiceInfo;
import com.si.pre.SIPre;

public class Pre_DB_Service_4Function extends SIPre {

	/** USING FOR SINGLE **/
	public Pre_DB_Service_4Function(String procedure, boolean isMergeData, String data, String siVersion, String processInfo, String processDataSource, String logDataSource) {

		super(procedure, data, siVersion, processInfo, processDataSource, logDataSource);
		super.isMergeData = isMergeData;

		super.isBuiltIn = true;
		super.isLoadPckProtocol = true;

		super.initLog();
		super.log.setSystem(SYS.BPM, null, SYS.DB);

	}

	/** USING FOR GROUP **/
	public Pre_DB_Service_4Function(String procedure, boolean isMergeData, String data, String siVersion, String processInfo, String processDataSource, String logDataSource, SI_Log log) {

		super(procedure, data, siVersion, processInfo, processDataSource, logDataSource, log);
		super.isMergeData = isMergeData;

		super.isBuiltIn = true;
		super.isLoadPckProtocol = true;

		super.initLog();
		super.log.setSystem(SYS.BPM, null, SYS.DB);
	}

	@Override
	public String executeService(ServiceInfo serviceInfo) throws SIException {
		String result = null;

		// VERSIONING
		switch (serviceInfo.getVersion()) {

		case Version.v1_0:
			result = new com.service.impl.db.v1_0.DB_Service_4Function(super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log).execute();
			break;

		default:
			throw new SIException(Error.VER_99, String.format("Service [%s] has no version [%s]", super.service, serviceInfo.getVersion()));
		}
		return result;
	}
}
