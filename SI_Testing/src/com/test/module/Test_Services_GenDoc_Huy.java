package com.test.module;

import com.consts.gendoc.Service;
import com.helper.TestHelper;
import com.pre.impl.gendoc.v1_0.Pre_GenDoc_Service;
import com.si.pre.SIPre;

public class Test_Services_GenDoc_Huy {

	public static void main(String[] args) throws Exception {

		String data = null;
		String bpmVersion = "RLOSv1.0";
		String processInfo = null;
		String dataSourceName = "SAALEM_51";
		String logDataSource = "SAALEM_51";

		SIPre pre;
		boolean isMergeData = false;
		String service = Service.DXGN_BM130;

		switch (service) {
		case Service.GR_BIDV_VB05:

			isMergeData = false;
			data = TestHelper.loadLosProcess("resources/los_process_VB05.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.DXGN_BM128:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/dxgn_process.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		
		case Service.DXGN_BM35:

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
		case Service.DXGN_BM153:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/mb130.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM52:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/bo_ycst.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM54:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/bo_ycst.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM56:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/bo_ycst.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM58:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/bo_ycst.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM60:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/bo_ycst.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM62:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/bo_ycst.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM64:
			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/bo_ycst.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM66:
			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/bo_ycst.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM68:
			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/bo_ycst.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM99:
			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/bo_ycst.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.DXGN_BM130:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/mb130.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM26:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/bo_ycst.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM27:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/bo_ycst.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM29:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/bo_ycst.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		case Service.BM30:

			isMergeData = true;
			data = TestHelper.loadLosProcess("resources/bo_ycst.json").toString();
			pre = new Pre_GenDoc_Service(service, isMergeData, data, bpmVersion, processInfo, dataSourceName, logDataSource);

			break;
		default:
			throw new Exception("Invalid service");
		}
		System.out.println(pre.invoke());
//		pre.invoke();
	}
		
}
