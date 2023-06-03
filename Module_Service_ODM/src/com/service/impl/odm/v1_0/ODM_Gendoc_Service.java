package com.service.impl.odm.v1_0;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.si.exception.SIException;
import com.si.model.SI_Log;
import com.si.model.env.OdmEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.OdmInvoker_v2;

public class ODM_Gendoc_Service extends SI_Service{
	
	protected JsonObject input = null;

	public ODM_Gendoc_Service(String rule, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource, serviceConfig, log);
		
		OdmEnv odmEnv = new Gson().fromJson(super.data.getAsJsonObject().get("env"), OdmEnv.class);
		OdmInvoker_v2 odmInvoker = new OdmInvoker_v2(rule, odmEnv, processDataSource, log);
		super.isManualMapping_Input = true;
		super.isManualMapping_Output = true;
		odmInvoker.setData(super.data.getAsJsonObject());
		super.invoker = odmInvoker;	
	}

	@Override
	public void businessMapping_Input() throws SIException {
		// TODO Auto-generated method stub
	}

	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub
		this.invoked_input = new JsonObject();
		input = this.invoked_input.getAsJsonObject();
		
		//-- LOS BPM TT_DENGHI.TT_CACBEN_KI_HD.LOAI_KH
		JsonObject LOS_BPM_INFORMATION = this.data.getAsJsonObject().get("LOS_BPM_INFORMATION").getAsJsonObject();
		input.addProperty("MasterProcessType", LOS_BPM_INFORMATION.get("MASTER_PROCESS_TYPE").getAsString());
		input.addProperty("Role", LOS_BPM_INFORMATION.get("ROLE_UPDATED").getAsString());
//		input.addProperty("LoaiKH", this.data.getAsJsonObject().get("TT_DENGHI").getAsJsonObject().get("TT_CACBEN_KI_HD").getAsJsonObject().get("LOAI_KH").getAsString());
		input.addProperty("ManHinh", "");
		
		//
		
		switch (input.get("MasterProcessType").getAsString()) {
		case "DXGN":
			
			JsonObject DEXUAT_STHAO_GN = this.data.getAsJsonObject().get("DEXUAT_STHAO_GN").getAsJsonObject();
			JsonObject HTCTD = new JsonObject();
			HTCTD.addProperty("isGN", DEXUAT_STHAO_GN.get("IS_GIAINGAN").getAsString());
			HTCTD.addProperty("isBL", DEXUAT_STHAO_GN.get("IS_BAOLANH").getAsString());
			HTCTD.addProperty("isLC", DEXUAT_STHAO_GN.get("IS_LC").getAsString());
			input.add("HTCTD", HTCTD);
			
			JsonArray DSBL = new JsonArray();
			try{
				JsonArray DS_YCAU_BAOLANH = this.data.getAsJsonObject().get("TT_DEXUAT").getAsJsonObject().get("DS_YCAU_BAOLANH").getAsJsonArray();
				for (JsonElement e : DS_YCAU_BAOLANH) {
					JsonObject obj = new JsonObject();
					obj.addProperty("Masp", e.getAsJsonObject().get("LOAITHU_BLANH").getAsString());
					DSBL.add(obj);
				}

			} catch (Exception e) {
				// TODO: handle exception
				JsonObject obj = new JsonObject();
				obj.addProperty("Masp", "");
				DSBL.add(obj);
			}
			input.add("DSBL", DSBL);
			
			JsonArray PTCTD = new JsonArray();
			try{
				String PHUONGTHUC_CTD = this.data.getAsJsonObject().get("TT_BAO_CAO").getAsJsonObject().get("TT_HD").getAsJsonObject().get("PHUONGTHUC_CTD").getAsString();
				JsonObject obj = new JsonObject();
				obj.addProperty("Mapt", PHUONGTHUC_CTD);
				PTCTD.add(obj);
				
			} catch (Exception e) {
				// TODO: handle exception
				JsonObject obj = new JsonObject();
				obj.addProperty("Mapt", "");
				PTCTD.add(obj);
			}
			input.add("PTCTD", PTCTD);
			break;
			
		case "YCST":
			JsonObject obj = new JsonObject();
			obj.add("ds", new JsonArray());
//			JsonArray ds = obj.get("ds").getAsJsonArray();
			JsonArray TT_YC_HDTINDUNG = this.data.getAsJsonObject().get("TT_SOANTHAO").getAsJsonObject().get("TT_YC_HDTINDUNG").getAsJsonArray();
			JsonArray dshd = new JsonArray();
			
			for (JsonElement e : TT_YC_HDTINDUNG) {
				JsonObject o = new JsonObject();
				
				o.addProperty("LoaiHD", e.getAsJsonObject().get("LOAI_HD").getAsString());
				o.addProperty("PhuongThucCTD", e.getAsJsonObject().get("PHUONGTHUC").getAsString());
				o.addProperty("HinhThucGN", e.getAsJsonObject().get("HINHTHUC_GIAINGAN").getAsString());
				dshd.add(o);
			}
			input.add("DSHD", dshd);
			
			//------
			JsonArray TT_YC_HDTSBD = this.data.getAsJsonObject().get("TT_SOANTHAO").getAsJsonObject().get("TT_YC_HDTSBD").getAsJsonArray();
			JsonArray dsBds = new JsonArray();
			JsonArray dsTsPhatsinh = new JsonArray();
			JsonArray dsTsGtcg = new JsonArray();
			JsonArray dsTsPtvt = new JsonArray();
			
			input.addProperty("LoaiKH",this.data.getAsJsonObject().get("TT_SOANTHAO").getAsJsonObject().get("TT_CTD_KH").getAsJsonObject().get("LOAI_KH").getAsString());
			for (JsonElement e : TT_YC_HDTSBD) {
				JsonObject hd = e.getAsJsonObject();
				JsonObject o = new JsonObject();
				JsonObject ts = null;
//				o.addProperty("LOAI_TSBD", hd.get("LOAI_TSBD").getAsString());
				
				switch (hd.get("LOAI_TSBD").getAsString()) {
				case "TSBD01":
//					o.remove("LOAI_TSBD");
					ts = hd.get("DS_BDS").getAsJsonArray().get(0).getAsJsonObject();
					o.addProperty("CHU_TSBD", ts.get("HINHTHUC_SOHUU").getAsString());
					o.addProperty("CHITIET_TSBD", ts.get("LOAI_BDS").getAsString());
					dsBds.add(o);
					break;
				case "TSBD07":
//					o.remove("LOAI_TSBD");
					ts = hd.get("DS_QTS").getAsJsonArray().get(0).getAsJsonObject();
					o.addProperty("CHU_TSBD", ts.get("HINHTHUC_SOHUU").getAsString());
					o.addProperty("CHITIET_TSBD", ts.get("LOAI_QTS").getAsString());
					dsTsPhatsinh.add(o);		
					break;
				case "TSBD09":
//					o.remove("LOAI_TSBD");
					ts = hd.get("DS_TV_GTCG").getAsJsonArray().get(0).getAsJsonObject();
					o.addProperty("TC_PHATHANH", ts.get("TCPH").getAsString());
					o.addProperty("CHU_TSBD", ts.get("HINHTHUC_SOHUU").getAsString());
					dsTsGtcg.add(o);
					break;
				case "TSBD03":
//					o.remove("LOAI_TSBD");
					ts = hd.get("DS_PTVT").getAsJsonArray().get(0).getAsJsonObject();
					o.addProperty("CHU_TSBD", ts.get("HINHTHUC_SOHUU").getAsString());
					dsTsPtvt.add(o);
					break;
				default:
					break;
				}
				
				
//				if(hd.has("DS_BDS")){
//					if(hd.get("DS_BDS").getAsJsonArray().size()>0){
//						ts = hd.get("DS_BDS").getAsJsonArray().get(0).getAsJsonObject();
//						o.addProperty("CHU_TSBD", ts.get("HINHTHUC_SOHUU").getAsString());
//						o.addProperty("CHITIET_TSBD", ts.get("LOAI_BDS").getAsString());
//					}
//				} else if(hd.has("DS_PTVT")){
//
//					if(hd.get("DS_PTVT").getAsJsonArray().size()>0){
//						ts = hd.get("DS_PTVT").getAsJsonArray().get(0).getAsJsonObject();
//						o.addProperty("CHU_TSBD", ts.get("HINHTHUC_SOHUU").getAsString());
//					}
//					
//				} else if(hd.has("DS_TV_GTCG")){
//					
//					if(hd.get("DS_TV_GTCG").getAsJsonArray().size()>0){
//						ts = hd.get("DS_TV_GTCG").getAsJsonArray().get(0).getAsJsonObject();
//						o.addProperty("TC_PHATHANH", ts.get("TCPH").getAsString());
//						o.addProperty("CHU_TSBD", ts.get("HINHTHUC_SOHUU").getAsString());
//					}
//				
//				} else if (hd.has("DS_QTS")){
//					
//					if(hd.get("DS_QTS").getAsJsonArray().size()>0){
//						ts = hd.get("DS_QTS").getAsJsonArray().get(0).getAsJsonObject();
//						o.addProperty("CHU_TSBD", ts.get("HINHTHUC_SOHUU").getAsString());
//						o.addProperty("CHITIET_TSBD", ts.get("LOAI_QTS").getAsString());
//					}
//				}
//				
//				ds.add(o);	
			}
			
			
//			
//			for (JsonElement e : ds) {
//				JsonObject o = e.getAsJsonObject();
//				switch (o.get("LOAI_TSBD").getAsString()) {
//				case "TSBD01":
//					o.remove("LOAI_TSBD");
//					dsBds.add(o);
//					break;
//				case "TSBD07":
//					o.remove("LOAI_TSBD");
//					dsTsPhatsinh.add(o);		
//					break;
//				case "TSBD09":
//					o.remove("LOAI_TSBD");
//					dsTsGtcg.add(o);
//					break;
//				case "TSBD03":
//					o.remove("LOAI_TSBD");
//					dsTsPtvt.add(o);
//					break;
//				default:
//					break;
//				}
//			}
//			JsonObject o = new JsonObject();
//			o.addProperty("CHU_TSBD", "");
//			o.addProperty("CHITIET_TSBD", "");
//			if(dsBds.isEmpty()){
//				dsBds.add(o);
//			}
//			if(dsTsPhatsinh.isEmpty()){
//				dsTsPhatsinh.add(o);
//			}
//			if(dsTsPtvt.isEmpty()){
//				o.remove("CHITIET_TSBD");
//				dsTsPtvt.add(o);
//			}
//			if(dsTsGtcg.isEmpty()){
//				o.addProperty("TC_PHATHANH", "");
//				dsTsGtcg.add(o);
//			}
			input.add("DS_BDS", dsBds);
			input.add("DS_TS_PHATSINH", dsTsPhatsinh);
			input.add("DS_TS_PTVT", dsTsPtvt);
			input.add("DS_TS_GTCG", dsTsGtcg);
			break;
			
		case "YCK":
	
			break;
		default:
			break;
		}
		
		this.invoked_input = input;

		
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
		this.data = this.invoked_output;
	}

	@Override
	public void businessMapping_Output() throws SIException {
		// TODO Auto-generated method stub
		
	}

	
}
