package com.service.impl.gendoc.v1_0;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import com.consts.Gendoc;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.helper.AddressHelper;
import com.helper.SupportBMHelper;
import com.si.consts.Error;
import com.si.exception.SIException;
import com.si.helper.DateHelper;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class MS01d1 extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public MS01d1(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log,String dependentRequired) {
			super(data, isMergeData, siVersion, processDataSource, logDataSource, serviceConfig, log);
			GenDocEnv genDocEnv = new Gson().fromJson(super.data.getAsJsonObject().get("env"), GenDocEnv.class);
			genDocInvoker  = new GenDocInvoker(docName, genDocEnv, processDataSource, log);
			super.invoker = genDocInvoker;
			super.isMergeData = true;
			super.isManualMapping_Input =true;
			super.isManualInvoke =true;
			this.docName = docName;		
			this.dependentRequired = dependentRequired;
	}

	@Override
	public void businessMapping_Input() throws SIException {
		JsonObject BO = super.data.getAsJsonObject().get("data").getAsJsonObject();
		
		BO.addProperty("GENDOC_CODE", docName);
		BO.addProperty("DATE_FM", DateHelper.getCurrentDate("YYYMMdd"));
		String PCA = BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE") != null ? BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE").getAsString() : null;
		String[] split = PCA.split("\\.");
		BO.addProperty("PCA", split != null ? String.join("_", split):"");
		//TT_SOANTHAO
		JsonObject TT_SOANTHAO = null;
		TT_SOANTHAO = BO.has("TT_SOANTHAO") ?  BO.get("TT_SOANTHAO").getAsJsonObject() : null;
		//-----------------------------------Start check-------------------------------------------
		JsonObject TT_YC_HDTSBD = null;
		SupportBMHelper support = new SupportBMHelper(super.mapper, super.processDataSource);
		TT_YC_HDTSBD = support.getObjectByDepedenRequired(BO, dependentRequired);
		//-----------------------------------end check-------------------------------------------
		String BTC_GRANDPARENTS_FULLNAME = null;
		
		//TT_BEN_BD
		JsonObject TT_BEN_BD = null;
		JsonArray DS_PTVT = null;
		if(TT_YC_HDTSBD!= null && !TT_YC_HDTSBD.isEmpty()){
			
			TT_BEN_BD = TT_YC_HDTSBD.get("TT_BEN_BD").getAsJsonObject();
			
			DS_PTVT = TT_YC_HDTSBD.get("DS_PTVT").getAsJsonArray();
		}
	
		String BTC_CN_COUPLE_PER_CONT_ADDRESS= null;
		JsonArray TT_DINHDANH= null;
		JsonObject TT_NGUOIHONPHOI = null;
		JsonObject KH_DN = null;
	
		if(TT_BEN_BD != null && !TT_BEN_BD.isEmpty()){
			
			JsonArray TT_LIENLAC = TT_BEN_BD.get("TT_LIENLAC").getAsJsonArray();
			String HKTHUONGTRU = AddressHelper.getMergerAddress(TT_LIENLAC, "HKTHUONGTRU", this.processDataSource);
			String DCLIENHE = AddressHelper.getMergerAddress(TT_LIENLAC, "DCLIENHE", this.processDataSource);
			BTC_CN_COUPLE_PER_CONT_ADDRESS = String.format("%s; %s", HKTHUONGTRU,DCLIENHE);
			TT_DINHDANH = TT_BEN_BD.get("TT_DINHDANH").getAsJsonArray();
			TT_NGUOIHONPHOI = TT_BEN_BD.get("TT_NGUOIHONPHOI").getAsJsonObject();
			KH_DN = TT_BEN_BD.getAsJsonObject("KH_DN");	
		}
		
	
		String BTC_DN_OFFICE_ADDRESS = null;
		String BTC_DNT_BUSINESSES_NUMBER = null;
		String BTC_DN_BUSINESSES_NUMBER_ISSUED_BY = null; 
		String BTC_DN_BUSINESSES_NUMBER_DATE = null; 
		String BTC_DN_PHONE_NUMBER = null;
		String BTC_DN_GRANDPARENTS = null;
		String BTC_DN_FULLNAME_ENTERPRISE = null;
		//TEN_KH_CODAU
		String TEN_KH_CODAU = "";
		if(KH_DN != null && !KH_DN.isEmpty()){
			String ONG_BA = convertMasterdataCodeToName("ONGBA",  (KH_DN.has("ONG_BA") && ! KH_DN.get("ONG_BA").isJsonNull()) ? KH_DN.get("ONG_BA").getAsString() : null);
			TEN_KH_CODAU = (KH_DN.has("TEN_KH_CODAU") && !KH_DN.get("TEN_KH_CODAU").isJsonNull()) ? KH_DN.get("TEN_KH_CODAU").getAsString() : null;
			BTC_GRANDPARENTS_FULLNAME = String.format("%s %s", ONG_BA,TEN_KH_CODAU).toUpperCase();
			BTC_DN_OFFICE_ADDRESS = ( KH_DN.has("DIACHI") && ! KH_DN.get("DIACHI").isJsonNull()) ?KH_DN.get("DIACHI").getAsString() : null;
			BTC_DN_BUSINESSES_NUMBER_ISSUED_BY = convertMasterdataCodeToName("NOICAPID",(KH_DN.has("NOI_CAP") && !KH_DN.get("NOI_CAP").isJsonNull())? KH_DN.get("NOI_CAP").getAsString() : null);
			BTC_DNT_BUSINESSES_NUMBER =( KH_DN.has("MASO_DN") &&  !KH_DN.get("MASO_DN").isJsonNull())? KH_DN.get("MASO_DN").getAsString() : null;
			try {
				BTC_DN_BUSINESSES_NUMBER_DATE = DateHelper.convertDateString((KH_DN.has("NGAY_CAP") && !KH_DN.get("NGAY_CAP").isJsonNull())?KH_DN.get("NGAY_CAP").getAsString() : null,"dd-MM-YYY");
			} catch (ParseException e) {
				throw new SIException(Error.EX_99,
						"Error in convertBpmDateString", e);
			}
			BTC_DN_PHONE_NUMBER = (KH_DN.has("SO_DT") && !KH_DN.get("SO_DT").isJsonNull() ) ? KH_DN.get("SO_DT").getAsString() : null;
			BTC_DN_GRANDPARENTS = convertMasterdataCodeToName("ONGBA",(KH_DN.has("ONG_BA") && !KH_DN.get("ONG_BA").isJsonNull()) ?KH_DN.get("ONG_BA").getAsString() : null);
			BTC_DN_FULLNAME_ENTERPRISE = (KH_DN.has("NGDAIDIEN_KYHD") && !KH_DN.get("NGDAIDIEN_KYHD").isJsonNull())	?KH_DN.get("NGDAIDIEN_KYHD").getAsString() : null;
		}
		BO.addProperty("BTC_GRANDPARENTS_FULLNAME", BTC_GRANDPARENTS_FULLNAME);
		BO.addProperty("BTC_DN_FULLNAME_ENTERPRISE", BTC_DN_FULLNAME_ENTERPRISE);
		BO.addProperty("BTC_DN_GRANDPARENTS", BTC_DN_GRANDPARENTS);
		BO.addProperty("BTC_DN_PHONE_NUMBER", BTC_DN_PHONE_NUMBER);
		BO.addProperty("BTC_DN_OFFICE_ADDRESS", BTC_DN_OFFICE_ADDRESS);
		BO.addProperty("BTC_DNT_BUSINESSES_NUMBER", BTC_DNT_BUSINESSES_NUMBER);
		BO.addProperty("BTC_CN_COUPLE_PER_CONT_ADDRESS", BTC_CN_COUPLE_PER_CONT_ADDRESS);
		BO.addProperty("BTC_DN_BUSINESSES_NUMBER_ISSUED_BY", BTC_DN_BUSINESSES_NUMBER_ISSUED_BY);
		BO.addProperty("BTC_DN_BUSINESSES_NUMBER_DATE", BTC_DN_BUSINESSES_NUMBER_DATE);
		String BTC_CN_COUPLE_DATE_RANGE = null;
		BO.addProperty("BTC_DN_COMPANY_1", TEN_KH_CODAU != null ? TEN_KH_CODAU.toUpperCase() : null);
		String BTC_CN_COUPLE_IDNUMBER = null;
		String BTC_CN_COUPLE_ISSUED_BY = null;
		if(TT_DINHDANH != null && !TT_DINHDANH.isEmpty() && TT_DINHDANH.size() > 0){
			try {
				BTC_CN_COUPLE_ISSUED_BY = convertMasterdataCodeToName("NOICAPID",(TT_DINHDANH.get(0).getAsJsonObject().has("NOI_CAP") && !TT_DINHDANH.get(0).getAsJsonObject().get("NOI_CAP").isJsonNull()) ? TT_DINHDANH.get(0).getAsJsonObject().get("NOI_CAP").getAsString() : null);
				BTC_CN_COUPLE_IDNUMBER = TT_DINHDANH.get(0).getAsJsonObject().has("SO_ID") && !TT_DINHDANH.get(0).getAsJsonObject().get("SO_ID").isJsonNull() ? TT_DINHDANH.get(0).getAsJsonObject().get("SO_ID").getAsString() : null;
				BTC_CN_COUPLE_DATE_RANGE = DateHelper.convertDateString((TT_DINHDANH.get(0).getAsJsonObject().get("NGAY_CAP") != null && !TT_DINHDANH.get(0).getAsJsonObject().get("NGAY_CAP").isJsonNull()) ? TT_DINHDANH.get(0).getAsJsonObject().get("NGAY_CAP").getAsString() : null,"dd-MM-YYYY");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				throw new SIException(Error.EX_99, "Error in convertBpmDateString", e);	
			}
		}
		BO.addProperty("BTC_CN_COUPLE_ISSUED_BY", BTC_CN_COUPLE_ISSUED_BY);
		BO.addProperty("BTC_CN_COUPLE_IDNUMBER", BTC_CN_COUPLE_IDNUMBER);
		BO.addProperty("BTC_CN_COUPLE_DATE_RANGE", BTC_CN_COUPLE_DATE_RANGE);
		String NHP_GRANDPARENTS_FULLNAME = null; 
		String NHP_PER_CONT_ADDRESS = null;
		if(TT_NGUOIHONPHOI != null && !TT_NGUOIHONPHOI.isEmpty()){
			String ONG_BA = convertMasterdataCodeToName("ONGBA",  (TT_NGUOIHONPHOI.has("ONG_BA") && ! TT_NGUOIHONPHOI.get("ONG_BA").isJsonNull()) ? TT_NGUOIHONPHOI.get("ONG_BA").getAsString() : null);
			String TEN_KH_CODAU_ = ( TT_NGUOIHONPHOI.has("TEN_KH_CODAU") && !TT_NGUOIHONPHOI.get("TEN_KH_CODAU").isJsonNull()) ? TT_NGUOIHONPHOI.get("TEN_KH_CODAU").getAsString() : null;
			NHP_GRANDPARENTS_FULLNAME =  String.format("%s %s", ONG_BA,TEN_KH_CODAU_).toUpperCase();
			JsonArray TT_LIENLAC = TT_NGUOIHONPHOI.get("TT_LIENLAC").getAsJsonArray();
			String HKTHUONGTRU = AddressHelper.getMergerAddress(TT_LIENLAC, "HKTHUONGTRU", this.processDataSource);
			String DCLIENHE = AddressHelper.getMergerAddress(TT_LIENLAC, "DCLIENHE", this.processDataSource);
			List<String> listAddress = new ArrayList<String>();
			if(HKTHUONGTRU!=null){
				listAddress.add(HKTHUONGTRU);
			}
			if(DCLIENHE!=null){
				listAddress.add(DCLIENHE);
			}
			NHP_PER_CONT_ADDRESS = String.join("; ", listAddress);
		}
		BO.addProperty("NHP_GRANDPARENTS_FULLNAME", NHP_GRANDPARENTS_FULLNAME);
		//NHP_PER_CONT_ADDRESS
		BO.addProperty("NHP_PER_CONT_ADDRESS",NHP_PER_CONT_ADDRESS);
		/*
		 * DSH_IDNUMBER
		 * DSH_ISSUED_BY
		 * DSH_DATE_RANGE_1
		 * DSH_GRANDPARENTS_FULLNAME
		 * DSH_PER_CONT_ADDRESS
		 * DSH_IDNUMBER
		 * DSH_ISSUED_BY
		 * DSH_PHONE_NUMBER
		 */
		BO.addProperty("DSH_ISSUED_BY", "");
		BO.addProperty("DS_DONGSOHUU", "");
		BO.addProperty("DSH_IDNUMBER", "");
		BO.addProperty("DSH_DATE_RANGE_1", "");
		BO.addProperty("DSH_PHONE_NUMBER", "");
		BO.addProperty("DSH_GRANDPARENTS_FULLNAME", "");
		BO.addProperty("DSH_PER_CONT_ADDRESS", "");
		JsonObject TT_CTD_EXIM = null;
		if(TT_SOANTHAO	!= null && !TT_SOANTHAO.isEmpty()){
			
			TT_CTD_EXIM = TT_SOANTHAO.get("TT_CTD_EXIM").getAsJsonObject();
		}
		String DVKD = (TT_CTD_EXIM.has("DVKD")  && !TT_CTD_EXIM.get("DVKD").isJsonNull()) ?  TT_CTD_EXIM.get("DVKD").getAsString() : null;
		BO.addProperty("DVKD", DVKD);
		JsonArray TABLE_PTVT_ARRAY = new JsonArray();
		JsonArray TABLE_PTGT_ARRAY = new JsonArray();
	
		if(DS_PTVT != null && !DS_PTVT.isEmpty() && DS_PTVT.size() > 0){
			int stt_PTVT_DB = 1;
			int stt_PTVT_KHAC = 1;
			for (JsonElement e : DS_PTVT) {
				JsonObject DS_PTVT_OBJ = e.getAsJsonObject();
				JsonArray PTVT_DB = (DS_PTVT_OBJ.has("PTVT_DB")) ? DS_PTVT_OBJ.get("PTVT_DB").getAsJsonArray() : null;
				JsonArray PTVT_KHAC = (DS_PTVT_OBJ.has("PTVT_KHAC"))? DS_PTVT_OBJ.get("PTVT_KHAC").getAsJsonArray() : null;
				if(PTVT_DB!= null && !PTVT_DB.isEmpty() && PTVT_DB.size() > 0){
					for (JsonElement ptvt_db : PTVT_DB) {
						JsonObject TABLE_PTVT = new JsonObject();
						TABLE_PTVT.addProperty("STT", stt_PTVT_DB);
						TABLE_PTVT.addProperty("NAME_PTVT", (ptvt_db.getAsJsonObject().get("TEN_PTVT_MOTA") != null && !ptvt_db.getAsJsonObject().get("TEN_PTVT_MOTA").isJsonNull() ) ? ptvt_db.getAsJsonObject().get("TEN_PTVT_MOTA").getAsString() : null);
						TABLE_PTVT.addProperty("FRAME_NO_PTVT", (ptvt_db.getAsJsonObject().get("SO_KHUNG") != null && !ptvt_db.getAsJsonObject().get("SO_KHUNG").isJsonNull())? ptvt_db.getAsJsonObject().get("SO_KHUNG").getAsString() : null);
						TABLE_PTVT.addProperty("LICENSE_PLATE_PTVT", (ptvt_db.getAsJsonObject().get("BIEN_SO") != null && !ptvt_db.getAsJsonObject().get("BIEN_SO").isJsonNull()) ?ptvt_db.getAsJsonObject().get("BIEN_SO").getAsString() : null);
						TABLE_PTVT.addProperty("PAPER_NOTICE", (ptvt_db.getAsJsonObject().get("YC_GIAY") != null && !ptvt_db.getAsJsonObject().get("YC_GIAY").isJsonNull()) ? ptvt_db.getAsJsonObject().get("YC_GIAY").getAsString() : null);
						TABLE_PTVT.addProperty("ELECTRONIC_NOTICE", (ptvt_db.getAsJsonObject().get("YC_DIENTU") != null && !ptvt_db.getAsJsonObject().get("YC_DIENTU").isJsonNull()) ? ptvt_db.getAsJsonObject().get("YC_DIENTU").getAsString() : null);
						TABLE_PTVT.addProperty("NAME_ADDRESS_NOTICE", (ptvt_db.getAsJsonObject().get("CQ_TIEPNHAN") != null && !ptvt_db.getAsJsonObject().get("CQ_TIEPNHAN").isJsonNull()) ?ptvt_db.getAsJsonObject().get("CQ_TIEPNHAN").getAsString() : null );
						TABLE_PTVT_ARRAY.add(TABLE_PTVT);
						stt_PTVT_DB++;
					} 
				}
				
				
				if(PTVT_KHAC!= null && !PTVT_KHAC.isEmpty() && PTVT_KHAC.size() > 0){
					for (JsonElement ptvt_khac : PTVT_KHAC) {
						JsonObject TABLE_PTGT = new JsonObject();
						TABLE_PTGT.addProperty("STT", stt_PTVT_KHAC);
						TABLE_PTGT.addProperty("NAME_NH_MS", (ptvt_khac.getAsJsonObject().get("TEN_PTVT_MOTA") != null && !ptvt_khac.getAsJsonObject().get("TEN_PTVT_MOTA").isJsonNull()) ? ptvt_khac.getAsJsonObject().get("TEN_PTVT_MOTA").getAsString() : null);
						TABLE_PTGT.addProperty("VEHICLE_OWNER", (ptvt_khac.getAsJsonObject().get("CHU_SH") != null && !ptvt_khac.getAsJsonObject().get("CHU_SH").isJsonNull()) ? ptvt_khac.getAsJsonObject().get("CHU_SH").getAsString() : null); 
						TABLE_PTGT.addProperty("REGISTRATION_NUMBER", (ptvt_khac.getAsJsonObject().get("SO_DANG_KY") != null && !ptvt_khac.getAsJsonObject().get("SO_DANG_KY").isJsonNull()) ? ptvt_khac.getAsJsonObject().get("SO_DANG_KY").getAsString() : null);
						TABLE_PTGT.addProperty("MEDIUM_LEVEL", (ptvt_khac.getAsJsonObject().get("CAP_PHUONG_TIEN") != null && !ptvt_khac.getAsJsonObject().get("CAP_PHUONG_TIEN").isJsonNull()) ? ptvt_khac.getAsJsonObject().get("CAP_PHUONG_TIEN").getAsString() : null);
						TABLE_PTGT.addProperty("PTGT_PAPER_NOTICE", (ptvt_khac.getAsJsonObject().get("YC_GIAY") != null && !ptvt_khac.getAsJsonObject().get("YC_GIAY").isJsonNull()) ? ptvt_khac.getAsJsonObject().get("YC_GIAY").getAsString() : null );
						TABLE_PTGT.addProperty("PTGT_ELECTRONIC_NOTICE", (ptvt_khac.getAsJsonObject().get("YC_DIENTU") != null && !ptvt_khac.getAsJsonObject().get("YC_DIENTU").isJsonNull() ) ?  ptvt_khac.getAsJsonObject().get("YC_DIENTU").getAsString() : null);
						TABLE_PTGT.addProperty("PTGT_NAME_ADDRESS_NOTICE", (ptvt_khac.getAsJsonObject().get("CQ_TIEPNHAN") != null && !ptvt_khac.getAsJsonObject().get("CQ_TIEPNHAN").isJsonNull()) ? ptvt_khac.getAsJsonObject().get("CQ_TIEPNHAN").getAsString() : null);
						stt_PTVT_KHAC++;
						TABLE_PTGT_ARRAY.add(TABLE_PTGT);
					} 
				}
			}
		}
		
		BO.addProperty("TABLE_PTVT", TABLE_PTVT_ARRAY.toString());
		BO.addProperty("TABLE_PTGT", TABLE_PTGT_ARRAY.toString());
		
	}
	@Override
	public void manualMapping_input() throws SIException {
		super.invoked_output = super.data;
		
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
		JsonArray inputData = super.data.getAsJsonObject().get(Gendoc.inpuData).getAsJsonArray();
		//convert InputData to JSON String
		super.data.getAsJsonObject().addProperty(Gendoc.inpuData, inputData.toString());
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;
		
	}

}
