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
import com.si.config.SI;
import com.si.consts.Error;
import com.si.exception.SIException;
import com.si.helper.DateHelper;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class BM128 extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public BM128(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log,String dependentRequired) {
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
		//TT_BAO_CAO
		JsonObject TT_BAO_CAO = BO.has("TT_BAO_CAO") ?  BO.get("TT_BAO_CAO").getAsJsonObject() : null;
		//TT_CTD_KH
		JsonObject TT_CTD_KH  = null;
		JsonObject TAISAN_BAODAM = null;
		if(TT_BAO_CAO != null && !TT_BAO_CAO.isEmpty()){			
			TT_CTD_KH = TT_BAO_CAO.has("TT_CTD_KH") ? TT_BAO_CAO.get("TT_CTD_KH").getAsJsonObject() : null;
			TAISAN_BAODAM = TT_BAO_CAO.has("TAISAN_BAODAM") ? TT_BAO_CAO.get("TAISAN_BAODAM").getAsJsonObject() : null;
		}
		//TT_LIENLAC
		JsonArray  TT_LIENLAC= null;
		if(TT_CTD_KH != null && !TT_CTD_KH.isEmpty()){
			TT_LIENLAC = TT_CTD_KH.has("TT_LIENLAC") ? TT_CTD_KH.get("TT_LIENLAC").getAsJsonArray() : null ;
			String CTD_CN_GRANDPARENTS = convertMasterdataCodeToName("ONGBA",(TT_CTD_KH.has("ONG_BA") && !TT_CTD_KH.get("ONG_BA").isJsonNull()) ?TT_CTD_KH.get("ONG_BA").getAsString() : null);
			BO.addProperty("CTD_CN_GRANDPARENTS", CTD_CN_GRANDPARENTS);
			JsonArray TT_DINHDANH = TT_CTD_KH.has("TT_DINHDANH") && !TT_CTD_KH.get("TT_DINHDANH").isJsonNull() ? TT_CTD_KH.get("TT_DINHDANH").getAsJsonArray() : null;
			if(TT_DINHDANH!= null && !TT_DINHDANH.isEmpty() && TT_DINHDANH.size() > 0){
				String NOI_CAP = TT_DINHDANH.get(0).getAsJsonObject().has("NOI_CAP") && !TT_DINHDANH.get(0).getAsJsonObject().get("NOI_CAP").isJsonNull() ? TT_DINHDANH.get(0).getAsJsonObject().get("NOI_CAP").getAsString() : null;
				BO.addProperty("CTD_CN_ISSUED_BY", convertMasterdataCodeToName("NOICAPID", NOI_CAP));
				
			}
			JsonObject TT_VC_KH = TT_CTD_KH.has("TT_VC_KH") ? TT_CTD_KH.getAsJsonObject("TT_VC_KH") : null;
			if (TT_VC_KH != null && !TT_VC_KH.isEmpty()){
				String ONG_BA = TT_VC_KH.has("ONG_BA") && !TT_VC_KH.get("ONG_BA").isJsonNull() ? TT_VC_KH.get("ONG_BA").getAsString() : null;
				BO.addProperty("NDV_FULLNAME_INDIVIDUAL", convertMasterdataCodeToName("ONGBA", ONG_BA));
				JsonArray TT_DINHDANH_VC = TT_VC_KH.has("TT_DINHDANH") ? TT_VC_KH.getAsJsonArray("TT_DINHDANH") : null;
				if(TT_DINHDANH_VC!= null && !TT_DINHDANH_VC.isEmpty() && TT_DINHDANH_VC.size() > 0){
					String NOI_CAP = TT_DINHDANH_VC.get(0).getAsJsonObject().has("NOI_CAP") && !TT_DINHDANH_VC.get(0).getAsJsonObject().get("NOI_CAP").isJsonNull() ? TT_DINHDANH_VC.get(0).getAsJsonObject().get("NOI_CAP").getAsString(): null;
					BO.addProperty("NDV_ISSUED_BY", convertMasterdataCodeToName("NOICAPID", NOI_CAP));
 				}
				JsonArray TT_DIACHI = TT_VC_KH.has("TT_DIACHI") ? TT_VC_KH.getAsJsonArray("TT_DIACHI") : null;
				BO.addProperty("NDV_PER_ADDRESS", AddressHelper.getMergerAddress(TT_DIACHI, "HKTHUONGTRU", this.processDataSource));
				BO.addProperty("NDV_CONT_ADDRESS", AddressHelper.getMergerAddress(TT_DIACHI, "DCLIENHE", this.processDataSource));
			}  
		}
		List<String> address = new ArrayList<String>();
		List<String> address_LH = new ArrayList<String>();
		if(TT_LIENLAC != null && !TT_LIENLAC.isEmpty() && TT_LIENLAC.size()>0){
			String QUOC_GIA= null;
			String QUAN_HUYEN= null;
			String XA_PHUONG= null;
			String TINH_TP= null;
			String DIA_CHI_CUTHE = null;
			
			String QUOC_GIA_LH= null;
			String QUAN_HUYEN_LH= null;
			String XA_PHUONG_LH= null;
			String TINH_TP_LH= null;
			String DIA_CHI_CUTHE_LH = null;
			for (JsonElement tt_ll : TT_LIENLAC) {
				if(tt_ll.getAsJsonObject() != null && !tt_ll.getAsJsonObject().isJsonNull()){
					if("HKTHUONGTRU".equalsIgnoreCase(tt_ll.getAsJsonObject().get("LOAI_DIA_CHI").getAsString())){
						QUOC_GIA = convertMasterdataCodeToName("QUOCGIA",(tt_ll.getAsJsonObject().has("QUOC_GIA")&& !tt_ll.getAsJsonObject().get("QUOC_GIA").isJsonNull()) ? tt_ll.getAsJsonObject().get("QUOC_GIA").getAsString() : null);
						QUAN_HUYEN = convertMasterdataCodeToName("QUAN",(tt_ll.getAsJsonObject().has("QUAN_HUYEN") && !tt_ll.getAsJsonObject().get("QUAN_HUYEN").isJsonNull()) ? tt_ll.getAsJsonObject().get("QUAN_HUYEN").getAsString() : null);
						XA_PHUONG = convertMasterdataCodeToName("XA",(tt_ll.getAsJsonObject().has("XA_PHUONG")  && !tt_ll.getAsJsonObject().get("XA_PHUONG").isJsonNull()) ? tt_ll.getAsJsonObject().get("XA_PHUONG").getAsString() : null);
						TINH_TP = convertMasterdataCodeToName("TINH",(tt_ll.getAsJsonObject().has("TINH_TP") && !tt_ll.getAsJsonObject().get("TINH_TP").isJsonNull()) ? tt_ll.getAsJsonObject().get("TINH_TP").getAsString() : null);
						DIA_CHI_CUTHE = (tt_ll.getAsJsonObject().has("DIA_CHI_CUTHE") && !tt_ll.getAsJsonObject().get("DIA_CHI_CUTHE").isJsonNull()) ? tt_ll.getAsJsonObject().get("DIA_CHI_CUTHE").getAsString() : null;
					}
					if("DCLIENHE".equalsIgnoreCase(tt_ll.getAsJsonObject().get("LOAI_DIA_CHI").getAsString())){
						QUOC_GIA_LH = convertMasterdataCodeToName("QUOCGIA",(tt_ll.getAsJsonObject().has("QUOC_GIA") && !tt_ll.getAsJsonObject().get("QUOC_GIA").isJsonNull()) ? tt_ll.getAsJsonObject().get("QUOC_GIA").getAsString() : null);
						QUAN_HUYEN_LH = convertMasterdataCodeToName("QUAN",(tt_ll.getAsJsonObject().has("QUAN_HUYEN") && !tt_ll.getAsJsonObject().get("QUAN_HUYEN").isJsonNull()) ? tt_ll.getAsJsonObject().get("QUAN_HUYEN").getAsString() : null);
						XA_PHUONG_LH = convertMasterdataCodeToName("XA",(tt_ll.getAsJsonObject().has("XA_PHUONG") && !tt_ll.getAsJsonObject().get("XA_PHUONG").isJsonNull()) ? tt_ll.getAsJsonObject().get("XA_PHUONG").getAsString() : null);
						TINH_TP_LH = convertMasterdataCodeToName("TINH",(tt_ll.getAsJsonObject().has("TINH_TP") && !tt_ll.getAsJsonObject().get("TINH_TP").isJsonNull()) ? tt_ll.getAsJsonObject().get("TINH_TP").getAsString() : null);
						DIA_CHI_CUTHE_LH = (tt_ll.getAsJsonObject().has("DIA_CHI_CUTHE") && !tt_ll.getAsJsonObject().get("DIA_CHI_CUTHE").isJsonNull()) ? tt_ll.getAsJsonObject().get("DIA_CHI_CUTHE").getAsString() : null;
					}
				}
			}
			
			if(DIA_CHI_CUTHE != null) { address.add(DIA_CHI_CUTHE);}
			if(XA_PHUONG != null) { address.add(XA_PHUONG);}
			if(QUAN_HUYEN != null) { address.add(QUAN_HUYEN);}
			if(TINH_TP != null) { address.add(TINH_TP);}
			if(QUOC_GIA != null) { address.add(QUOC_GIA);}
			
			if(DIA_CHI_CUTHE_LH != null) { address_LH.add(DIA_CHI_CUTHE_LH);}
			if(XA_PHUONG_LH != null) { address_LH.add(XA_PHUONG_LH);}
			if(QUAN_HUYEN_LH != null) { address_LH.add(QUAN_HUYEN_LH);}
			if(TINH_TP_LH != null) { address_LH.add(TINH_TP_LH);}
			if(QUOC_GIA_LH != null) { address_LH.add(QUOC_GIA_LH);}
			
			
		}
		// CTD_CN_PER_ADDRESS
		BO.addProperty("CTD_CN_PER_ADDRESS", String.join(", ", address));
		//CTD_CN_CONT_ADDRESS
		BO.addProperty("CTD_CN_CONT_ADDRESS", String.join(", ", address_LH));
		// TT_DEXUAT
		JsonObject TT_DEXUAT = BO.get("TT_DEXUAT").getAsJsonObject();
		//IS_MARRY
		String YC_HONPHOI_KY = (TT_DEXUAT.has("YC_HONPHOI_KY") && !TT_DEXUAT.get("YC_HONPHOI_KY").isJsonNull()) ? (TT_DEXUAT.get("YC_HONPHOI_KY").getAsString().equalsIgnoreCase("1") ? "CHECK" : "UNCHECK") : null;
		BO.addProperty("IS_MARRY", YC_HONPHOI_KY);
		//TT_HD
		if(TT_BAO_CAO != null && !TT_BAO_CAO.isEmpty()){			
			JsonObject TT_HD = TT_BAO_CAO.get("TT_HD").getAsJsonObject();
			String CURRENT_DEBT_GROUP = TT_HD.has("NHOM_NO") && !TT_HD.get("NHOM_NO").isJsonNull() ? convertMasterdataCodeToName("NHOMNO", TT_HD.get("NHOM_NO").getAsString()) : null;
			BO.addProperty("CURRENT_DEBT_GROUP", CURRENT_DEBT_GROUP);
			//BIENPHAP_BAODAM
			JsonArray BIENPHAP_BAODAM =  TT_HD.has("BIENPHAP_BAODAM") ? TT_HD.get("BIENPHAP_BAODAM").getAsJsonArray() : null;
			//TABLE_DBM_BPBD
			JsonArray TABLE_DBM_BPBD_ARRAY = new JsonArray();
			if(BIENPHAP_BAODAM != null && !BIENPHAP_BAODAM.isEmpty() && BIENPHAP_BAODAM.size() > 0){
				int stt = 1;
				for (JsonElement table_ : BIENPHAP_BAODAM) {
					JsonObject TABLE_DBM_BPBD = new JsonObject();
					TABLE_DBM_BPBD.addProperty("BPBD_STT", stt);
					TABLE_DBM_BPBD.addProperty("TSBD_NAME", (table_.getAsJsonObject().has("TEN_TSBD") && !table_.getAsJsonObject().get("TEN_TSBD").isJsonNull()) ? table_.getAsJsonObject().get("TEN_TSBD").getAsString() : null);
					TABLE_DBM_BPBD.addProperty("VALUATION_VALUE", (table_.getAsJsonObject().has("GIATRI_DINHGIA") && ! table_.getAsJsonObject().get("GIATRI_DINHGIA").isJsonNull()) ? table_.getAsJsonObject().get("GIATRI_DINHGIA").getAsString() : null);
					TABLE_DBM_BPBD.addProperty("GUARANTEED_VALUE", (table_.getAsJsonObject().has("GIATRI_TSBD") && !table_.getAsJsonObject().get("GIATRI_TSBD").isJsonNull()) ? table_.getAsJsonObject().get("GIATRI_TSBD").getAsString() : null);
					TABLE_DBM_BPBD.addProperty("NOTARIZATION", (table_.getAsJsonObject().has("DK_BPBD") && !table_.getAsJsonObject().get("DK_BPBD").isJsonNull()) ? table_.getAsJsonObject().get("DK_BPBD").getAsString() : null);
					stt++;
					TABLE_DBM_BPBD_ARRAY.add(TABLE_DBM_BPBD);
				}
			}
			BO.addProperty("TABLE_DBM_BPBD", TABLE_DBM_BPBD_ARRAY.toString());
			//TT_DANHGIA_HDONG
			JsonObject TT_DANHGIA_HDONG = TT_BAO_CAO.has("TT_DANHGIA_HDONG") ? TT_BAO_CAO.get("TT_DANHGIA_HDONG").getAsJsonObject() : null;
			if(TT_DANHGIA_HDONG != null && !TT_DANHGIA_HDONG.isEmpty()){
				//1
				String MUA_BHIEM = (TT_DANHGIA_HDONG.has("MUA_BHIEM") && !TT_DANHGIA_HDONG.get("MUA_BHIEM").isJsonNull())  ? TT_DANHGIA_HDONG.get("MUA_BHIEM").getAsString(): null;
				if(MUA_BHIEM != null && MUA_BHIEM.equalsIgnoreCase("1")){
					BO.addProperty("COMPLY","CHECK");
					
				}else if(MUA_BHIEM != null && MUA_BHIEM.equalsIgnoreCase("2")){
					BO.addProperty("NON_COMPLIANCE","CHECK");
				}
				//2
				String DGIA_TSBD = (TT_DANHGIA_HDONG.has("DGIA_TSBD") && !TT_DANHGIA_HDONG.get("DGIA_TSBD").isJsonNull())  ? TT_DANHGIA_HDONG.get("DGIA_TSBD").getAsString(): null;
				if(DGIA_TSBD!=null && DGIA_TSBD.equalsIgnoreCase("1")){
					BO.addProperty("TSBD_FOLLOW", "CHECK");
				}else if(DGIA_TSBD!=null && DGIA_TSBD.equalsIgnoreCase("2")){
					BO.addProperty("TSBD_DO_NOT_OBEY", "CHECK");
				}
				// 3
				String DK_TRC_GN = (TT_DANHGIA_HDONG.has("DK_TRC_GN") && !TT_DANHGIA_HDONG.get("DK_TRC_GN").isJsonNull()) ? TT_DANHGIA_HDONG.get("DK_TRC_GN").getAsString() : null;
				if(DK_TRC_GN!= null && DK_TRC_GN.equalsIgnoreCase("1")){
					BO.addProperty("DBM_COMPLY","CHECK");
				}else if(DK_TRC_GN!= null && DK_TRC_GN.equalsIgnoreCase("2")){
					BO.addProperty("DBM_NON_COMPLIANCE","CHECK");
				}
				// 4
				String DK_KHAC = (TT_DANHGIA_HDONG.has("DK_KHAC") && !TT_DANHGIA_HDONG.get("DK_KHAC").isJsonNull())  ? TT_DANHGIA_HDONG.get("DK_KHAC").getAsString(): null;
				if(DK_KHAC!= null && DK_KHAC.equalsIgnoreCase("1")){					
					BO.addProperty("OTHER_COMPLY","CHECK");
				}else if(DK_KHAC!= null && DK_KHAC.equalsIgnoreCase("2")){
	
					BO.addProperty("OTHER_NON_COMPLIANCE","CHECK");
				}
				//5
				String LS_TRANO = (TT_DANHGIA_HDONG.has("LS_TRANO") && TT_DANHGIA_HDONG.get("LS_TRANO").isJsonNull()) ? TT_DANHGIA_HDONG.get("LS_TRANO").getAsString() :  null;
				if(LS_TRANO	!=null && LS_TRANO.equalsIgnoreCase("1")){
					BO.addProperty("REPUTABLE","CHECK");
				}else if(LS_TRANO	!=null && LS_TRANO.equalsIgnoreCase("2")){
					BO.addProperty("ARISING","CHECK");
				}
			}
			
		}
		JsonObject  TT_YC_GIAINGAN = null;
		SupportBMHelper support = new SupportBMHelper(super.mapper,
				super.processDataSource);
		TT_YC_GIAINGAN = support
				.getObjectByDepedenRequired(BO, dependentRequired);
		if(TT_YC_GIAINGAN != null && !TT_YC_GIAINGAN.isJsonNull()){
		
			String GRACE_PERIOD = TT_YC_GIAINGAN.has("THOIGIAN_AN_HAN") && !TT_YC_GIAINGAN.get("THOIGIAN_AN_HAN").isJsonNull() ? TT_YC_GIAINGAN.get("THOIGIAN_AN_HAN").getAsString() : null;
			BO.addProperty("GRACE_PERIOD", GRACE_PERIOD);
			String LOAN_DATE_DAY = TT_YC_GIAINGAN.has("TG_CHOVAY_NGAY") && !TT_YC_GIAINGAN.get("TG_CHOVAY_NGAY").isJsonNull() ? TT_YC_GIAINGAN.get("TG_CHOVAY_NGAY").getAsString() : null;
			BO.addProperty("LOAN_DATE_DAY", LOAN_DATE_DAY);
			String LOAN_DATE_MONTH = TT_YC_GIAINGAN.has("TG_CHOVAY_THANG") && !TT_YC_GIAINGAN.get("TG_CHOVAY_THANG").isJsonNull() ? TT_YC_GIAINGAN.get("TG_CHOVAY_THANG").getAsString() : null;
			BO.addProperty("LOAN_DATE_MONTH", LOAN_DATE_MONTH);
			String DXGN_AMOUNT = TT_YC_GIAINGAN.has("SOTIEN_GN") && !TT_YC_GIAINGAN.get("SOTIEN_GN").isJsonNull() ? TT_YC_GIAINGAN.get("SOTIEN_GN").getAsString() : null;
			BO.addProperty("DXGN_AMOUNT", DXGN_AMOUNT);
			String DXGN_EXCHANGE_RATE = TT_YC_GIAINGAN.has("TY_GIA") && !TT_YC_GIAINGAN.get("TY_GIA").isJsonNull() ? TT_YC_GIAINGAN.get("TY_GIA").getAsString() : null;
			BO.addProperty("DXGN_EXCHANGE_RATE", DXGN_EXCHANGE_RATE);
			String DXGN_AMOUT_CUR = TT_YC_GIAINGAN.has("LOAI_TIEN") && !TT_YC_GIAINGAN.get("LOAI_TIEN").isJsonNull() ? TT_YC_GIAINGAN.get("LOAI_TIEN").getAsString() : null;
			BO.addProperty("DXGN_AMOUT_CUR", DXGN_AMOUT_CUR);
			JsonArray DS_DANHGIA = TT_YC_GIAINGAN.has("DS_DANHGIA") ?  TT_YC_GIAINGAN.getAsJsonArray("DS_DANHGIA") : null;
			if(DS_DANHGIA != null && !DS_DANHGIA.isEmpty() && DS_DANHGIA.size() > 0){
				String APPROPRIATE_ASSESSMENT_1 = (DS_DANHGIA.get(0).getAsJsonObject().has("DANH_GIA") && !DS_DANHGIA.get(0).getAsJsonObject().get("DANH_GIA").isJsonNull()) ? "1".equalsIgnoreCase(DS_DANHGIA.get(0).getAsJsonObject().get("DANH_GIA").getAsString()) ? "CHECK": "UNCHECK" : "UNCHECK";
				BO.addProperty("APPROPRIATE_ASSESSMENT_1", APPROPRIATE_ASSESSMENT_1);
				String APPROPRIATE_ASSESSMENT_2 = (DS_DANHGIA.get(1).getAsJsonObject().has("DANH_GIA") && !DS_DANHGIA.get(1).getAsJsonObject().get("DANH_GIA").isJsonNull()) ? "1".equalsIgnoreCase(DS_DANHGIA.get(1).getAsJsonObject().get("DANH_GIA").getAsString()) ? "CHECK": "UNCHECK" : "UNCHECK";
				BO.addProperty("APPROPRIATE_ASSESSMENT_2", APPROPRIATE_ASSESSMENT_2);
				String APPROPRIATE_ASSESSMENT_3 = (DS_DANHGIA.get(2).getAsJsonObject().has("DANH_GIA") && !DS_DANHGIA.get(2).getAsJsonObject().get("DANH_GIA").isJsonNull()) ? "1".equalsIgnoreCase(DS_DANHGIA.get(2).getAsJsonObject().get("DANH_GIA").getAsString()) ? "CHECK": "UNCHECK" : "UNCHECK";
				BO.addProperty("APPROPRIATE_ASSESSMENT_3", APPROPRIATE_ASSESSMENT_3);
				String APPROPRIATE_ASSESSMENT_4 = (DS_DANHGIA.get(3).getAsJsonObject().has("DANH_GIA") && !DS_DANHGIA.get(3).getAsJsonObject().get("DANH_GIA").isJsonNull()) ? "1".equalsIgnoreCase(DS_DANHGIA.get(3).getAsJsonObject().get("DANH_GIA").getAsString()) ? "CHECK": "UNCHECK" : "UNCHECK";
				BO.addProperty("APPROPRIATE_ASSESSMENT_4", APPROPRIATE_ASSESSMENT_4);
				String APPROPRIATE_ASSESSMENT_5 = (DS_DANHGIA.get(4).getAsJsonObject().has("DANH_GIA") && !DS_DANHGIA.get(4).getAsJsonObject().get("DANH_GIA").isJsonNull()) ? "1".equalsIgnoreCase(DS_DANHGIA.get(4).getAsJsonObject().get("DANH_GIA").getAsString()) ? "CHECK": "UNCHECK" : "UNCHECK";
				BO.addProperty("APPROPRIATE_ASSESSMENT_5", APPROPRIATE_ASSESSMENT_5);
				String APPROPRIATE_ASSESSMENT_6 = (DS_DANHGIA.get(5).getAsJsonObject().has("DANH_GIA") && !DS_DANHGIA.get(5).getAsJsonObject().get("DANH_GIA").isJsonNull()) ? "1".equalsIgnoreCase(DS_DANHGIA.get(5).getAsJsonObject().get("DANH_GIA").getAsString()) ? "CHECK": "UNCHECK" : "UNCHECK";
				BO.addProperty("APPROPRIATE_ASSESSMENT_6", APPROPRIATE_ASSESSMENT_6);
				String APPROPRIATE_ASSESSMENT_7 = (DS_DANHGIA.get(6).getAsJsonObject().has("DANH_GIA") && !DS_DANHGIA.get(6).getAsJsonObject().get("DANH_GIA").isJsonNull()) ? "1".equalsIgnoreCase(DS_DANHGIA.get(6).getAsJsonObject().get("DANH_GIA").getAsString()) ? "CHECK": "UNCHECK" : "UNCHECK";
				BO.addProperty("APPROPRIATE_ASSESSMENT_7", APPROPRIATE_ASSESSMENT_7);
				String APPROPRIATE_ASSESSMENT_8 = (DS_DANHGIA.get(7).getAsJsonObject().has("DANH_GIA") && !DS_DANHGIA.get(7).getAsJsonObject().get("DANH_GIA").isJsonNull()) ? "1".equalsIgnoreCase(DS_DANHGIA.get(7).getAsJsonObject().get("DANH_GIA").getAsString()) ? "CHECK": "UNCHECK" : "UNCHECK";
				BO.addProperty("APPROPRIATE_ASSESSMENT_8", APPROPRIATE_ASSESSMENT_8);
				String APPROPRIATE_ASSESSMENT_9 = (DS_DANHGIA.get(8).getAsJsonObject().has("DANH_GIA") && !DS_DANHGIA.get(8).getAsJsonObject().get("DANH_GIA").isJsonNull()) ? "1".equalsIgnoreCase(DS_DANHGIA.get(8).getAsJsonObject().get("DANH_GIA").getAsString()) ? "CHECK": "UNCHECK" : "UNCHECK";
				BO.addProperty("APPROPRIATE_ASSESSMENT_9", APPROPRIATE_ASSESSMENT_9);
				String APPROPRIATE_ASSESSMENT_10 = (DS_DANHGIA.get(9).getAsJsonObject().has("DANH_GIA") && !DS_DANHGIA.get(9).getAsJsonObject().get("DANH_GIA").isJsonNull()) ? "1".equalsIgnoreCase(DS_DANHGIA.get(9).getAsJsonObject().get("DANH_GIA").getAsString()) ? "CHECK": "UNCHECK" : "UNCHECK";
				BO.addProperty("APPROPRIATE_ASSESSMENT_10", APPROPRIATE_ASSESSMENT_10);
				String APPROPRIATE_ASSESSMENT_11 = (DS_DANHGIA.get(10).getAsJsonObject().has("DANH_GIA") && !DS_DANHGIA.get(10).getAsJsonObject().get("DANH_GIA").isJsonNull()) ? "1".equalsIgnoreCase(DS_DANHGIA.get(10).getAsJsonObject().get("DANH_GIA").getAsString()) ? "CHECK": "UNCHECK" : "UNCHECK";
				BO.addProperty("APPROPRIATE_ASSESSMENT_11", APPROPRIATE_ASSESSMENT_11);
				String APPROPRIATE_ASSESSMENT_12 = (DS_DANHGIA.get(11).getAsJsonObject().has("DANH_GIA") && !DS_DANHGIA.get(11).getAsJsonObject().get("DANH_GIA").isJsonNull()) ? "1".equalsIgnoreCase(DS_DANHGIA.get(11).getAsJsonObject().get("DANH_GIA").getAsString()) ? "CHECK": "UNCHECK" : "UNCHECK";
				BO.addProperty("APPROPRIATE_ASSESSMENT_12", APPROPRIATE_ASSESSMENT_12);
				
			}
			String DBM_PURPOSE =  TT_YC_GIAINGAN.has("MUCDICH_GN") && !TT_YC_GIAINGAN.get("MUCDICH_GN").isJsonNull() ? TT_YC_GIAINGAN.get("MUCDICH_GN").getAsString() : null;
			BO.addProperty("DBM_PURPOSE", DBM_PURPOSE);
			JsonArray DS_CODE_MUCDICH = TT_YC_GIAINGAN.has("DS_CODE_MUCDICH")  ? TT_YC_GIAINGAN.getAsJsonArray("DS_CODE_MUCDICH") : null;
			if(DS_CODE_MUCDICH != null && !DS_CODE_MUCDICH.isEmpty() && DS_CODE_MUCDICH.size() > 0){
				JsonObject MUC_DICH = DS_CODE_MUCDICH.get(0).getAsJsonObject();
				String CODE_NAME_1_CODE = MUC_DICH.has("TEN_CODE_CAP1") && !MUC_DICH.get("TEN_CODE_CAP1").isJsonNull() ? MUC_DICH.get("TEN_CODE_CAP1").getAsString() : null;
				String CODE_NAME_2_CODE = MUC_DICH.has("TEN_CODE_CAP2") && !MUC_DICH.get("TEN_CODE_CAP2").isJsonNull() ? MUC_DICH.get("TEN_CODE_CAP2").getAsString() : null;
				String CODE_NAME_3_CODE = MUC_DICH.has("TEN_CODE_CAP3") && !MUC_DICH.get("TEN_CODE_CAP3").isJsonNull() ? MUC_DICH.get("TEN_CODE_CAP3").getAsString() : null;
				String CODE_NAME_4_CODE = MUC_DICH.has("TEN_CODE_CAP4") && !MUC_DICH.get("TEN_CODE_CAP4").isJsonNull() ? MUC_DICH.get("TEN_CODE_CAP4").getAsString() : null;
				String CODE_NAME_5_CODE = MUC_DICH.has("TEN_CODE_CAP5") && !MUC_DICH.get("TEN_CODE_CAP5").isJsonNull() ? MUC_DICH.get("TEN_CODE_CAP5").getAsString() : null;
				String CODE_NAME_6_CODE = MUC_DICH.has("TEN_CODE_CAP6") && !MUC_DICH.get("TEN_CODE_CAP6").isJsonNull() ? MUC_DICH.get("TEN_CODE_CAP6").getAsString() : null;
				try {
					JsonArray DS_MUCDICH = queryByPrepareStatement(SI.QUERY_MUCDICHVAY, CODE_NAME_1_CODE,CODE_NAME_2_CODE,CODE_NAME_3_CODE,CODE_NAME_4_CODE,CODE_NAME_5_CODE);
					JsonArray DS_MUCDICH_CODE_6 = queryByPrepareStatement(SI.QUERY_CODE_6,CODE_NAME_6_CODE);
					String CODE_NAME_6 = null;
					if(DS_MUCDICH_CODE_6 != null && !DS_MUCDICH_CODE_6.isEmpty() && DS_MUCDICH_CODE_6.size() > 0){
						CODE_NAME_6 = DS_MUCDICH_CODE_6.get(0).getAsJsonObject().has("muc_dich_vay_cap_6") && !DS_MUCDICH_CODE_6.get(0).getAsJsonObject().get("muc_dich_vay_cap_6").isJsonNull() ? DS_MUCDICH_CODE_6.get(0).getAsJsonObject().get("muc_dich_vay_cap_6").getAsString() : null;
					}
					if(DS_MUCDICH != null && !DS_MUCDICH.isEmpty() && DS_MUCDICH.size() > 0){
						for (JsonElement e : DS_MUCDICH) {
							
								String CODE_NAME_1 = e.getAsJsonObject().has("muc_dich_vay_cap_1") && !e.getAsJsonObject().get("muc_dich_vay_cap_1").isJsonNull() ? e.getAsJsonObject().get("muc_dich_vay_cap_1").getAsString() : null;
								String CODE_NAME_2 = e.getAsJsonObject().has("muc_dich_vay_cap_2") && !e.getAsJsonObject().get("muc_dich_vay_cap_2").isJsonNull() ? e.getAsJsonObject().get("muc_dich_vay_cap_2").getAsString() : null;
								String CODE_NAME_3 = e.getAsJsonObject().has("muc_dich_vay_cap_3") && !e.getAsJsonObject().get("muc_dich_vay_cap_3").isJsonNull() ? e.getAsJsonObject().get("muc_dich_vay_cap_3").getAsString() : null;
								String CODE_NAME_4 = e.getAsJsonObject().has("muc_dich_vay_cap_4") && !e.getAsJsonObject().get("muc_dich_vay_cap_4").isJsonNull() ? e.getAsJsonObject().get("muc_dich_vay_cap_4").getAsString() : null;
								String CODE_NAME_5 = e.getAsJsonObject().has("muc_dich_vay_cap_5") && !e.getAsJsonObject().get("muc_dich_vay_cap_5").isJsonNull() ? e.getAsJsonObject().get("muc_dich_vay_cap_5").getAsString() : null;
								
								BO.addProperty("CODE_NAME_1", CODE_NAME_1);
								BO.addProperty("CODE_NAME_2", CODE_NAME_2);
								BO.addProperty("CODE_NAME_3", CODE_NAME_3);
								BO.addProperty("CODE_NAME_4", CODE_NAME_4);
								BO.addProperty("CODE_NAME_5", CODE_NAME_5);
								BO.addProperty("CODE_NAME_6", CODE_NAME_6);
							
						}
					}
				} catch (Exception e) {
					throw new SIException(Error.EX_99, "Error in queryByPrepareStatement", e);	
				}
			}
			// CT_DA_CUNGCAP
			String PROVIDED_DOCUMENT  = TT_YC_GIAINGAN.has("CT_DA_CUNGCAP") && !TT_YC_GIAINGAN.get("CT_DA_CUNGCAP").isJsonNull() ?  "1".equalsIgnoreCase(TT_YC_GIAINGAN.get("CT_DA_CUNGCAP").getAsString()) ? "CHECK" : "UNCHECK" : "UNCHECK";
			BO.addProperty("PROVIDED_DOCUMENT", PROVIDED_DOCUMENT);
			String PROVIDED_DOC_DETAIL = TT_YC_GIAINGAN.has("CHITIET_CT_CUNGCAP") && !TT_YC_GIAINGAN.get("CHITIET_CT_CUNGCAP").isJsonNull() ? TT_YC_GIAINGAN.get("CHITIET_CT_CUNGCAP").getAsString() : null;
			BO.addProperty("PROVIDED_DOC_DETAIL", PROVIDED_DOC_DETAIL);
			String DOC_AFTER_DBM  = TT_YC_GIAINGAN.has("CT_BOSUNG") && !TT_YC_GIAINGAN.get("CT_BOSUNG").isJsonNull() ?  "1".equalsIgnoreCase(TT_YC_GIAINGAN.get("CT_BOSUNG").getAsString()) ? "CHECK" : "UNCHECK" : "UNCHECK";
			BO.addProperty("DOC_AFTER_DBM", DOC_AFTER_DBM);
			String DOC_AFTER_DBM_DETAIL = TT_YC_GIAINGAN.has("CHITIET_CT_BOSUNG") && !TT_YC_GIAINGAN.get("CHITIET_CT_BOSUNG").isJsonNull() ? TT_YC_GIAINGAN.get("CHITIET_CT_BOSUNG").getAsString() : null;
			BO.addProperty("DOC_AFTER_DBM_DETAIL", DOC_AFTER_DBM_DETAIL);
			String PAY_IN_CASH =  TT_YC_GIAINGAN.has("PHUTHUC_GN_TM") && !TT_YC_GIAINGAN.get("PHUTHUC_GN_TM").isJsonNull() ?  "1".equalsIgnoreCase(TT_YC_GIAINGAN.get("PHUTHUC_GN_TM").getAsString()) ? "CHECK" : "UNCHECK" : "UNCHECK";
			BO.addProperty("PAY_IN_CASH", PAY_IN_CASH);
			String PAY_IN_CASH_AMOUNT  = TT_YC_GIAINGAN.has("SOTIEN_GN_TIENMAT") && !TT_YC_GIAINGAN.get("SOTIEN_GN_TIENMAT").isJsonNull() ? TT_YC_GIAINGAN.get("SOTIEN_GN_TIENMAT").getAsString() : null;
			BO.addProperty("PAY_IN_CASH_AMOUNT", PAY_IN_CASH_AMOUNT );
			String DBM_TRANSFER = TT_YC_GIAINGAN.has("PHTHUC_GN_CK") && !TT_YC_GIAINGAN.get("PHTHUC_GN_CK").isJsonNull() ?  "1".equalsIgnoreCase(TT_YC_GIAINGAN.get("PHTHUC_GN_CK").getAsString()) ? "CHECK" : "UNCHECK" : "UNCHECK";
			BO.addProperty("DBM_TRANSFER", DBM_TRANSFER );
			JsonArray TABLE_INFO_BENEFICIARYS_ARR = new JsonArray();
			JsonArray DS_PHUONGTHUC_GN = TT_YC_GIAINGAN.has("DS_PHUONGTHUC_GN") ? TT_YC_GIAINGAN.getAsJsonArray("DS_PHUONGTHUC_GN") : null;
			if(DS_PHUONGTHUC_GN != null && !DS_PHUONGTHUC_GN.isEmpty() && DS_PHUONGTHUC_GN.size() > 0){
				for (JsonElement e : DS_PHUONGTHUC_GN) {
					JsonObject  PT_GN = e.getAsJsonObject();
					JsonObject INFO_BENEFICIARYS  = new JsonObject();
					String TEN_NG_THUHUONG =  PT_GN.has("TEN_NG_THUHUONG") && !PT_GN.get("TEN_NG_THUHUONG").isJsonNull() ?  PT_GN.get("TEN_NG_THUHUONG").getAsString(): null;
					String STK_THUHUONG =  PT_GN.has("STK_THUHUONG") && !PT_GN.get("STK_THUHUONG").isJsonNull() ?  PT_GN.get("STK_THUHUONG").getAsString(): null;
					String TAI_TCTD =  PT_GN.has("TAI_TCTD") && !PT_GN.get("TAI_TCTD").isJsonNull() ?  convertMasterdataCodeToName("TCTD",PT_GN.get("TAI_TCTD").getAsString()): null;
					String SOTIEN =  PT_GN.has("SOTIEN") && !PT_GN.get("SOTIEN").isJsonNull() ?  PT_GN.get("SOTIEN").getAsString(): null;
					INFO_BENEFICIARYS.addProperty("BENEFICIARYS_NAME", TEN_NG_THUHUONG);
					INFO_BENEFICIARYS.addProperty("BENEFICIARYS_ACC", STK_THUHUONG);
					INFO_BENEFICIARYS.addProperty("BENEFICIARYS_ACC_PLACE", TAI_TCTD);
					INFO_BENEFICIARYS.addProperty("BENEFICIARYS_LOAN", SOTIEN);
					TABLE_INFO_BENEFICIARYS_ARR.add(INFO_BENEFICIARYS);
				}
				BO.addProperty("TABLE_INFO_BENEFICIARYS", TABLE_INFO_BENEFICIARYS_ARR.toString());
			}
			try {
				String LOAN_FROM_DATE = (TT_YC_GIAINGAN.has("TU_NGAY") && !TT_YC_GIAINGAN.get("TU_NGAY").isJsonNull()) ? DateHelper.convertDateString(TT_YC_GIAINGAN.get("TU_NGAY").getAsString(),"dd-MM-YYY") : null ;
				String LOAN_TO_DATE = (TT_YC_GIAINGAN.has("DEN_NGAY") && !TT_YC_GIAINGAN.get("DEN_NGAY").isJsonNull()) ? DateHelper.convertDateString(TT_YC_GIAINGAN.get("DEN_NGAY").getAsString(),"dd-MM-YYY") : null ;
				BO.addProperty("LOAN_FROM_DATE", LOAN_FROM_DATE);
				BO.addProperty("LOAN_TO_DATE", LOAN_TO_DATE);
				String F_PRINCIPAL_REPAY_PERIOD = (TT_YC_GIAINGAN.has("KY_TRAGOC_DAUTIEN") && !TT_YC_GIAINGAN.get("KY_TRAGOC_DAUTIEN").isJsonNull()) ? DateHelper.convertDateString(TT_YC_GIAINGAN.get("KY_TRAGOC_DAUTIEN").getAsString(),"dd-MM-YYY") : null ;
				BO.addProperty("F_PRINCIPAL_REPAY_PERIOD", F_PRINCIPAL_REPAY_PERIOD);
				String FINAL_PAYMENT_DATE = (TT_YC_GIAINGAN.has("KY_TRAGOC_CUOI") && !TT_YC_GIAINGAN.get("KY_TRAGOC_CUOI").isJsonNull()) ? DateHelper.convertDateString(TT_YC_GIAINGAN.get("KY_TRAGOC_CUOI").getAsString(),"dd-MM-YYY") : null ;
				BO.addProperty("FINAL_PAYMENT_DATE", FINAL_PAYMENT_DATE);
			} catch (ParseException e) {
				throw new SIException(Error.EX_99,
						"Error in convertBpmDateString", e);
			}
			
			String A_LOT_TIMES =  TT_YC_GIAINGAN.has("KYHAN_TRANO_GOC") && !TT_YC_GIAINGAN.get("KYHAN_TRANO_GOC").isJsonNull() ?  "1".equalsIgnoreCase(TT_YC_GIAINGAN.get("KYHAN_TRANO_GOC").getAsString()) ? "CHECK" : "UNCHECK" : "UNCHECK";
			String END_OF_TERM =  TT_YC_GIAINGAN.has("KYHAN_TRANO_GOC") && !TT_YC_GIAINGAN.get("KYHAN_TRANO_GOC").isJsonNull() ?  "2".equalsIgnoreCase(TT_YC_GIAINGAN.get("KYHAN_TRANO_GOC").getAsString()) ? "CHECK" : "UNCHECK" : "UNCHECK";
			String OTHER_PRINCP_PAY_TERM =  TT_YC_GIAINGAN.has("KYHAN_TRANO_GOC") && !TT_YC_GIAINGAN.get("KYHAN_TRANO_GOC").isJsonNull() ?  "3".equalsIgnoreCase(TT_YC_GIAINGAN.get("KYHAN_TRANO_GOC").getAsString()) ? "CHECK" : "UNCHECK" : "UNCHECK";
			BO.addProperty("A_LOT_TIMES", A_LOT_TIMES);
			BO.addProperty("END_OF_TERM", END_OF_TERM);
			BO.addProperty("OTHER_PRINCP_PAY_TERM", OTHER_PRINCP_PAY_TERM);
			String OTHER_PRINCP_PAY_TERM_T  =  TT_YC_GIAINGAN.has("CHITIET_TRAGOC_KHAC") && !TT_YC_GIAINGAN.get("CHITIET_TRAGOC_KHAC").isJsonNull() ? TT_YC_GIAINGAN.get("CHITIET_TRAGOC_KHAC").getAsString() : null;
			BO.addProperty("OTHER_PRINCP_PAY_TERM_T", OTHER_PRINCP_PAY_TERM_T);
			String DEBT_REPAYMENT_PERIOD = TT_YC_GIAINGAN.has("DINHKY_TRANO_GOC") && !TT_YC_GIAINGAN.get("DINHKY_TRANO_GOC").isJsonNull() ? TT_YC_GIAINGAN.get("DINHKY_TRANO_GOC").getAsString() : null;
			BO.addProperty("DEBT_REPAYMENT_PERIOD", DEBT_REPAYMENT_PERIOD);
			String FIRST_ORIGINAL_PAYMENT_DAY = TT_YC_GIAINGAN.has("KY_TRAGOC_DAUTIEN") && !TT_YC_GIAINGAN.get("KY_TRAGOC_DAUTIEN").isJsonNull() ? TT_YC_GIAINGAN.get("KY_TRAGOC_DAUTIEN").getAsString() : null;
			BO.addProperty("FIRST_ORIGINAL_PAYMENT_DAY", FIRST_ORIGINAL_PAYMENT_DAY);		
			String  KYHAN_TRALAI = TT_YC_GIAINGAN.has("KYHAN_TRALAI") && !TT_YC_GIAINGAN.get("KYHAN_TRALAI").isJsonNull() ? TT_YC_GIAINGAN.get("KYHAN_TRALAI").getAsString() : null;
			String MONTH_INTEREST_PAY = null;
			String O_INS_INTEREST_PAY = null;
			if(KYHAN_TRALAI != null && "1".equalsIgnoreCase(KYHAN_TRALAI)){
				MONTH_INTEREST_PAY = "CHECK";
			}else if(KYHAN_TRALAI != null && "2".equalsIgnoreCase(KYHAN_TRALAI)){
				String DATE_IN_MONTH = TT_YC_GIAINGAN.has("NGAYTRANO_LAI_DAY") && !TT_YC_GIAINGAN.get("NGAYTRANO_LAI_DAY").isJsonNull() ? TT_YC_GIAINGAN.get("NGAYTRANO_LAI_DAY").getAsString() : null;
				BO.addProperty("DATE_IN_MONTH", DATE_IN_MONTH);
				O_INS_INTEREST_PAY = "CHECK";
			}
			BO.addProperty("MONTH_INTEREST_PAY", MONTH_INTEREST_PAY);
			String F_INS_INTEREST_PAY = TT_YC_GIAINGAN.has("NGAY_TRALAI_DAUTIEN") && !TT_YC_GIAINGAN.get("NGAY_TRALAI_DAUTIEN").isJsonNull() ? TT_YC_GIAINGAN.get("NGAY_TRALAI_DAUTIEN").getAsString() : null;
			BO.addProperty("F_INS_INTEREST_PAY", F_INS_INTEREST_PAY);	
			String F_INS_INTEREST_PAY_DATE = TT_YC_GIAINGAN.has("NGAYTRALAI_CUOIKY") && !TT_YC_GIAINGAN.get("NGAYTRALAI_CUOIKY").isJsonNull() ? TT_YC_GIAINGAN.get("NGAYTRALAI_CUOIKY").getAsString() : null;
			BO.addProperty("F_INS_INTEREST_PAY_DATE", F_INS_INTEREST_PAY_DATE);
			BO.addProperty("O_INS_INTEREST_PAY", O_INS_INTEREST_PAY);
			String  O_INS_INTEREST_PAY_DATE = TT_YC_GIAINGAN.has("CHITIET_KYTRALAI_KHAC") && !TT_YC_GIAINGAN.get("CHITIET_KYTRALAI_KHAC").isJsonNull() ? TT_YC_GIAINGAN.get("CHITIET_KYTRALAI_KHAC").getAsString() : null;
			BO.addProperty("O_INS_INTEREST_PAY_DATE", O_INS_INTEREST_PAY_DATE);
			String DBM_INTEREST_RATE = TT_YC_GIAINGAN.has("LS_CHOVAY") && !TT_YC_GIAINGAN.get("LS_CHOVAY").isJsonNull() ? TT_YC_GIAINGAN.get("LS_CHOVAY").getAsString() : null;
			BO.addProperty("DBM_INTEREST_RATE", DBM_INTEREST_RATE);
			String SCHEME_CODE =  TT_YC_GIAINGAN.has("SCHEME_CODE") && !TT_YC_GIAINGAN.get("SCHEME_CODE").isJsonNull() ? TT_YC_GIAINGAN.get("SCHEME_CODE").getAsString() : null;
			BO.addProperty("SCHEME_CODE", SCHEME_CODE);
			String DBM_FEE = TT_YC_GIAINGAN.has("PHI") && !TT_YC_GIAINGAN.get("PHI").isJsonNull() ? TT_YC_GIAINGAN.get("PHI").getAsString() : null;
			BO.addProperty("DBM_FEE", DBM_FEE);
			String  PETITION = TT_YC_GIAINGAN.has("KIEN_NGHI_KHAC") && !TT_YC_GIAINGAN.get("KIEN_NGHI_KHAC").isJsonNull() ? TT_YC_GIAINGAN.get("KIEN_NGHI_KHAC").getAsString() : null;
			BO.addProperty("PETITION", PETITION);
		} 
		if(TAISAN_BAODAM!= null && !TAISAN_BAODAM.isEmpty()){
			JsonArray TT_TIENGUI =TAISAN_BAODAM.has("TT_TIENGUI") ? TAISAN_BAODAM.getAsJsonArray("TT_TIENGUI") : null;
			JsonArray TT_VANG =TAISAN_BAODAM.has("TT_VANG") ? TAISAN_BAODAM.getAsJsonArray("TT_VANG") : null;
			JsonArray TT_TAISAN_KHAC =TAISAN_BAODAM.has("TT_TAISAN_KHAC") ? TAISAN_BAODAM.getAsJsonArray("TT_TAISAN_KHAC") : null;
			if(TT_TIENGUI!= null && !TT_TIENGUI.isEmpty() && TT_TIENGUI.size() > 0){
				JsonArray TABLE_DBM_TTK_ARR = new JsonArray();
				int STT = 1;
				for (JsonElement e : TT_TIENGUI) {
					JsonObject TIENGUI= e.getAsJsonObject();
					JsonObject DBM_TTK = new JsonObject();
					String TOCHUC_PHATHANH = TIENGUI.has("TOCHUC_PHATHANH") && !TIENGUI.get("TOCHUC_PHATHANH").isJsonNull() ? convertMasterdataCodeToName("TCTD",TIENGUI.get("TOCHUC_PHATHANH").getAsString()) : null;
					String SO_TAIKHOAN = TIENGUI.has("SO_TAIKHOAN") && !TIENGUI.get("SO_TAIKHOAN").isJsonNull() ? TIENGUI.get("SO_TAIKHOAN").getAsString() : null;
					String NGAY_PHATHANH = null;
					String NGAY_DAOHAN  =  null;
					try {
						NGAY_PHATHANH = TIENGUI.has("NGAY_PHATHANH") && !TIENGUI.get("NGAY_PHATHANH").isJsonNull() ? DateHelper.convertDateString(TIENGUI.get("NGAY_PHATHANH").getAsString(), "dd-MM-YYYY") : null;
						NGAY_DAOHAN = TIENGUI.has("NGAY_DAOHAN") && !TIENGUI.get("NGAY_DAOHAN").isJsonNull() ? DateHelper.convertDateString(TIENGUI.get("NGAY_DAOHAN").getAsString(), "dd-MM-YYYY") : null;
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String LOAITIEN = TIENGUI.has("LOAITIEN") && !TIENGUI.get("LOAITIEN").isJsonNull() ? TIENGUI.get("LOAITIEN").getAsString() : null;
					String GIATRI = TIENGUI.has("GIATRI") && !TIENGUI.get("GIATRI").isJsonNull() ? TIENGUI.get("GIATRI").getAsString() : null;
					String SOTIEN_BAODAM = TIENGUI.has("SOTIEN_BAODAM") && !TIENGUI.get("SOTIEN_BAODAM").isJsonNull() ? TIENGUI.get("SOTIEN_BAODAM").getAsString() : null;
					String TINHTRANG_PHONGTOA = TIENGUI.has("TINHTRANG_PHONGTOA") && !TIENGUI.get("TINHTRANG_PHONGTOA").isJsonNull() ? TIENGUI.get("TINHTRANG_PHONGTOA").getAsString() : null;
					DBM_TTK.addProperty("TTK_STT", STT);
					DBM_TTK.addProperty("TTK_TCPH", TOCHUC_PHATHANH);
					DBM_TTK.addProperty("TTK_STK", SO_TAIKHOAN);
					DBM_TTK.addProperty("TTK_NPH", NGAY_PHATHANH);
					DBM_TTK.addProperty("TTK_NDH", NGAY_DAOHAN);
					DBM_TTK.addProperty("TTK_CURRENCY", LOAITIEN);
					DBM_TTK.addProperty("TTK_VALUE", GIATRI);
					DBM_TTK.addProperty("TTK_GUARANTEE_AMOUNT", SOTIEN_BAODAM);
					DBM_TTK.addProperty("TTK_COURT_STATUS", TINHTRANG_PHONGTOA);
					TABLE_DBM_TTK_ARR.add(DBM_TTK);
					STT++;
				}
				BO.addProperty("TABLE_DBM_TTK", TABLE_DBM_TTK_ARR.toString());
			}
			if(TT_VANG != null && !TT_VANG.isEmpty() && TT_VANG.size()>0){
				JsonArray TABLE_DBM_VANG_ARR = new JsonArray();
				int STT = 1;
				for (JsonElement e : TT_VANG) {
					JsonObject VANG = e.getAsJsonObject();
					JsonObject DBM_VANG = new JsonObject();
					String LOAITAISAN = VANG.has("LOAITAISAN") && !VANG.get("LOAITAISAN").isJsonNull() ? VANG.get("LOAITAISAN").getAsString() : null;
					String SOLUONG = VANG.has("SOLUONG") && !VANG.get("SOLUONG").isJsonNull() ? VANG.get("SOLUONG").getAsString() : null;
					String LOAITIEN = VANG.has("LOAITIEN") && !VANG.get("LOAITIEN").isJsonNull() ? VANG.get("LOAITIEN").getAsString() : null;
					String GIATRI = VANG.has("GIATRI") && !VANG.get("GIATRI").isJsonNull() ? VANG.get("GIATRI").getAsString() : null;
					String SOTIEN_BAODAM = VANG.has("SOTIEN_BAODAM") && !VANG.get("SOTIEN_BAODAM").isJsonNull() ? VANG.get("SOTIEN_BAODAM").getAsString() : null;
					String TINHTRANG_PHONGTOA = VANG.has("TINHTRANG_PHONGTOA") && !VANG.get("TINHTRANG_PHONGTOA").isJsonNull() ? VANG.get("TINHTRANG_PHONGTOA").getAsString() : null;
					DBM_VANG.addProperty("VANG_STT", STT);
					DBM_VANG.addProperty("VANG_ASSET_TYPE", LOAITAISAN);
					DBM_VANG.addProperty("VANG_SL", SOLUONG);
					DBM_VANG.addProperty("VANG_CUR", LOAITIEN);
					DBM_VANG.addProperty("VANG_VA", GIATRI);
					DBM_VANG.addProperty("VANG_GUARANTEE_AMOUNT", SOTIEN_BAODAM);
					DBM_VANG.addProperty("VANG_TTK_COURT_STATUS", TINHTRANG_PHONGTOA);
					STT++;
					TABLE_DBM_VANG_ARR.add(DBM_VANG);
				}
				BO.addProperty("TABLE_DBM_VANG", TABLE_DBM_VANG_ARR.toString());
			}
			if(TT_TAISAN_KHAC != null && !TT_TAISAN_KHAC.isEmpty() && TT_TAISAN_KHAC.size()>0){
				JsonArray TABLE_DBM_KHAC = new JsonArray(); 
				int STT =1;
				for (JsonElement e : TT_TAISAN_KHAC) {
					JsonObject TAISAN_KHAC = e.getAsJsonObject();
					JsonObject DBM_KHAC = new JsonObject();
					String TEN_TSBD = TAISAN_KHAC.has("TEN_TSBD") && !TAISAN_KHAC.get("TEN_TSBD").isJsonNull() ? TAISAN_KHAC.get("TEN_TSBD").getAsString() : null;
					String GIATRI = TAISAN_KHAC.has("GIATRI") && !TAISAN_KHAC.get("GIATRI").isJsonNull() ? TAISAN_KHAC.get("GIATRI").getAsString() : null;
					String GIATRI_BAODAM = TAISAN_KHAC.has("GIATRI_BAODAM") && !TAISAN_KHAC.get("GIATRI_BAODAM").isJsonNull() ? TAISAN_KHAC.get("GIATRI_BAODAM").getAsString() : null;
					String CONGCHUNG = TAISAN_KHAC.has("CONGCHUNG") && !TAISAN_KHAC.get("CONGCHUNG").isJsonNull() ? TAISAN_KHAC.get("CONGCHUNG").getAsString() : null;
					DBM_KHAC.addProperty("KHAC_STT", STT);
					DBM_KHAC.addProperty("KHAC_TSBD_NAME", TEN_TSBD);
					DBM_KHAC.addProperty("KHAC_VALUE", GIATRI);
					DBM_KHAC.addProperty("KHAC_VALUEBD", GIATRI_BAODAM);
					DBM_KHAC.addProperty("KHAC_DKBPBD", CONGCHUNG);
					TABLE_DBM_KHAC.add(DBM_KHAC);
					STT++;
				}
				BO.addProperty("TABLE_DBM_KHAC", TABLE_DBM_KHAC.toString());
			}
		}
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
