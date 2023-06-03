package com.test.module;

import com.consts.gendoc.Service;
import com.helper.TestHelper;
import com.pre.impl.gendoc.v1_0.Pre_GenDoc_Service;
import com.si.pre.SIPre;

public class Test_Services_GenDoc {

	public static void main(String[] args) throws Exception {

		String data = null;
		String bpmVersion = "RLOSv1.0";
		String processInfo = null;
		String dataSourceName = "SAALEM_51";
		String logDataSource = "SAALEM_51";

		SIPre pre;
		boolean isMergeData = false;
		String service = Service.BM124;

		switch (service) {
		case Service.GR_BIDV_VB05:

			isMergeData = false;
			data = TestHelper.loadLosProcess("resources/los_procesres_VB05.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.DXGN_BM128:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/dxgn_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
			
		case Service.BM129:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_dxgn_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
			
		case Service.BM124:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_dxgn_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		
		case Service.BM122:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_dxgn_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		
		case Service.DXGN_BM35:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/dxgn_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.DXGN_BM153:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/dxgn_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.DXGN_BM148:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/dxgn_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.DXGN_BM149:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/dxgn_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.DXGN_BM150:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/dxgn_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.DXGN_BM151:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/dxgn_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.DXGN_BM152:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/dxgn_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.DXGN_BM130:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/dxgn_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM39:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM40:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM41:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM42:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM43:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM44:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM45:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM46:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM47:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM49:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM10:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM11:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM12:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM13:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM17:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM18:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM19:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM20:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM21:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM22:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM74:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/pre_ycst_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		default:
			throw new Exception("Invalid service");
		}

		pre.invoke();
	}
		
}
