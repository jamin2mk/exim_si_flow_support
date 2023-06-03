package com.pre.impl.gendoc.v1_0;

import com.consts.Service;
import com.consts.Version;
import com.si.consts.Error;
import com.si.consts.SYS;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.ServiceInfo;
import com.si.pre.SIPre;

public class Pre_GenDoc_Service extends SIPre {
	private String dependentRequired;
		/** USING FOR MULTIPLE TEMPLATES **/
	public Pre_GenDoc_Service(String docName, boolean isMergeData, String data, String siVersion, String processInfo, String dataSourceName, String logDataSource,String dependentRequired) {
		super(docName, data, siVersion, processInfo, dataSourceName, logDataSource);
		super.isMergeData = isMergeData;
		super.initLog();
		super.log.setSystem(SYS.BPM, null, SYS.EXF);
		this.dependentRequired = dependentRequired;
	}
	/** USING FOR SINGLE **/
	public Pre_GenDoc_Service(String docName, boolean isMergeData, String data, String siVersion, String processInfo, String dataSourceName, String logDataSource) {
		super(docName, data, siVersion, processInfo, dataSourceName, logDataSource);
		super.isMergeData = isMergeData;
		super.initLog();
		super.log.setSystem(SYS.BPM, null, SYS.EXF);
	
	}
	
	/** USING FOR GROUP **/
	public Pre_GenDoc_Service(String docName, boolean isMergeData, String data, String siVersion, String processInfo, String dataSourceName, String logDataSource, SI_Log log,String depend) {
		super(docName, data, siVersion, processInfo, dataSourceName, logDataSource, log);
		super.isMergeData = isMergeData;
		super.initLog();
		super.log.setSystem(SYS.BPM, null, SYS.EXF);
	}

	@Override
	public String executeService(ServiceInfo serviceInfo) throws SIException {
		String result = null;
		System.out.println("Service version Info:"+serviceInfo.getVersion());
		// VERSIONING
		switch (serviceInfo.getVersion()) {

		case Version.v1_0:

			switch (super.service) {

			case Service.BM24:
				result = new com.service.impl.gendoc.v1_0.BM24(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM128:
				result = new com.service.impl.gendoc.v1_0.BM128(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log,dependentRequired).execute();
				break;
			case Service.BM129:
				result = new com.service.impl.gendoc.v1_0.BM129(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM130:
				result = new com.service.impl.gendoc.v1_0.BM130(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM124:
				result = new com.service.impl.gendoc.v1_0.BM124(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM122:
				result = new com.service.impl.gendoc.v1_0.BM122(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log).execute();
				break;
			case Service.BM35:
				result = new com.service.impl.gendoc.v1_0.BM35(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM148:
				result = new com.service.impl.gendoc.v1_0.BM148(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log,dependentRequired).execute();
				break;
			case Service.BM149:
				result = new com.service.impl.gendoc.v1_0.BM149(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log,dependentRequired).execute();
				break;
			case Service.BM150:
				result = new com.service.impl.gendoc.v1_0.BM150(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM151:
				result = new com.service.impl.gendoc.v1_0.BM151(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log,dependentRequired).execute();
				break;
			case Service.BM152:
				result = new com.service.impl.gendoc.v1_0.BM152(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM153:
				result = new com.service.impl.gendoc.v1_0.BM153(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM39:
				result = new com.service.impl.gendoc.v1_0.BM39(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM40:
				result = new com.service.impl.gendoc.v1_0.BM40(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM41:
				result = new com.service.impl.gendoc.v1_0.BM41(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM42:
				result = new com.service.impl.gendoc.v1_0.BM42(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM43:
				result = new com.service.impl.gendoc.v1_0.BM43(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM44:
				result = new com.service.impl.gendoc.v1_0.BM44(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM45:
				result = new com.service.impl.gendoc.v1_0.BM45(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM46:
				result = new com.service.impl.gendoc.v1_0.BM46(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM47:
				result = new com.service.impl.gendoc.v1_0.BM47(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM49:
				result = new com.service.impl.gendoc.v1_0.BM49(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM10:
				result = new com.service.impl.gendoc.v1_0.BM10(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM11:
				result = new com.service.impl.gendoc.v1_0.BM11(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM12:
				result = new com.service.impl.gendoc.v1_0.BM12(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM13:
				result = new com.service.impl.gendoc.v1_0.BM13(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM17:
				result = new com.service.impl.gendoc.v1_0.BM17(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM18:
				result = new com.service.impl.gendoc.v1_0.BM18(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM19:
				result = new com.service.impl.gendoc.v1_0.BM19(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM20:
				result = new com.service.impl.gendoc.v1_0.BM20(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM21:
				result = new com.service.impl.gendoc.v1_0.BM21(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM22:
				result = new com.service.impl.gendoc.v1_0.BM22(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM52:
				result = new com.service.impl.gendoc.v1_0.BM52(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM54:
				result = new com.service.impl.gendoc.v1_0.BM54(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM56:
				result = new com.service.impl.gendoc.v1_0.BM56(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM58:
				result = new com.service.impl.gendoc.v1_0.BM58(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM60:
				result = new com.service.impl.gendoc.v1_0.BM60(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM62:
				result = new com.service.impl.gendoc.v1_0.BM62(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM64:
				result = new com.service.impl.gendoc.v1_0.BM64(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM66:
				result = new com.service.impl.gendoc.v1_0.BM66(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM68:
				result = new com.service.impl.gendoc.v1_0.BM68(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM99:
				result = new com.service.impl.gendoc.v1_0.BM99(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM01TTVTTT:
				result = new com.service.impl.gendoc.v1_0.BM01TTVTTT(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM04TTVTTT:
				result = new com.service.impl.gendoc.v1_0.BM04TTVTTT(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM85:
				result = new com.service.impl.gendoc.v1_0.BM85(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM86:
				result = new com.service.impl.gendoc.v1_0.BM86(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM88:
				result = new com.service.impl.gendoc.v1_0.BM88(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM89:
				result = new com.service.impl.gendoc.v1_0.BM89(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM100:
				result = new com.service.impl.gendoc.v1_0.BM100(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM102:
				result = new com.service.impl.gendoc.v1_0.BM102(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BBXDTS01:
				result = new com.service.impl.gendoc.v1_0.BBXDTS01(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.MS01a:
				result = new com.service.impl.gendoc.v1_0.MS01a(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM25:
				result = new com.service.impl.gendoc.v1_0.BM25(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;	
			case Service.YCST:
				result =  new com.service.impl.gendoc.v1_0.YCST(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log,dependentRequired).execute();
				break;
			case Service.BM146:
				result =  new com.service.impl.gendoc.v1_0.BM146(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log,dependentRequired).execute();
				break;
			case Service.BM147:
				result =  new com.service.impl.gendoc.v1_0.BM147(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log,dependentRequired).execute();
				break;
			case Service.BM166:
				result =  new com.service.impl.gendoc.v1_0.BM166(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log).execute();
				break;
			case Service.BM71:
				result =  new com.service.impl.gendoc.v1_0.BM71(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log , dependentRequired).execute();
				break;
			case Service.BM72:
				result =  new com.service.impl.gendoc.v1_0.BM72(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log,dependentRequired).execute();
				break;
			case Service.BM73:
				result =  new com.service.impl.gendoc.v1_0.BM73(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM23:
				result = new com.service.impl.gendoc.v1_0.BM23(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM28:
				result = new com.service.impl.gendoc.v1_0.BM28(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM26:
				result = new com.service.impl.gendoc.v1_0.BM26(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM27:
				result = new com.service.impl.gendoc.v1_0.BM27(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM29:
				result = new com.service.impl.gendoc.v1_0.BM29(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			case Service.BM30:
				result = new com.service.impl.gendoc.v1_0.BM30(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;		
			case Service.BM74:
				result = new com.service.impl.gendoc.v1_0.BM74(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log,dependentRequired).execute();
				break;
			case Service.BM79:
				result = new com.service.impl.gendoc.v1_0.BM79(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log,dependentRequired).execute();
				break;	
			case Service.BM103:
				result = new com.service.impl.gendoc.v1_0.BM103(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log,dependentRequired).execute();
				break;
			case Service.MS01d1:
				result = new com.service.impl.gendoc.v1_0.MS01d1(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log, dependentRequired).execute();
				break;
			default:
				result = new com.service.impl.gendoc.v1_0.GenDoc_Service(super.service, super.data, super.isMergeData, super.siVersion, super.processDataSource, super.logDataSource, serviceInfo.getServiceConfig(), log).execute();
				break;
			}
			
			break;

		default:
			throw new SIException(Error.VER_99, String.format("Service [%s] has no version [%s]", super.service, serviceInfo.getVersion()));
		}
		return result;
	}

}