package com.service.impl.gendoc.v1_0;

import java.math.BigDecimal;
import java.text.ParseException;

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
import com.si.helper.MoneyHelper;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class BM146 extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public BM146(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log,String dependentRequired) {
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
		BO.addProperty("QR_DATE", DateHelper.getCurrentDate("dd-MM-YYYY"));
		BO.addProperty("QR_HOUR", DateHelper.getCurrentDate("hh:mm:ss"));
		
		JsonObject TT_DENGHI = BO.has("TT_DENGHI") ? BO.get("TT_DENGHI").getAsJsonObject() : null;
		JsonObject TT_SOANTHAO = BO.has("TT_SOANTHAO")  ? BO.get("TT_SOANTHAO").getAsJsonObject() : null;
		JsonObject TT_CTD_EXIM = null;
		JsonObject TT_CTD_KH = null;
		JsonObject TT_YC_HDTINDUNG = null;
		SupportBMHelper support = new SupportBMHelper(super.mapper,
				super.processDataSource);
		TT_YC_HDTINDUNG = support
				.getObjectByDepedenRequired(BO, dependentRequired);	
		if(TT_SOANTHAO != null && !TT_SOANTHAO.isEmpty()){
			TT_CTD_EXIM = TT_SOANTHAO.has("TT_CTD_EXIM")  ? TT_SOANTHAO.get("TT_CTD_EXIM").getAsJsonObject() : null;
			TT_CTD_KH =  TT_SOANTHAO.has("TT_CTD_KH") ? TT_SOANTHAO.get("TT_CTD_KH").getAsJsonObject() : null;
		}
		String DVKD = null;
		String BANK_OFFICE_ADDRESS = null;
		String BANK_POSITION = null;
		if(TT_CTD_EXIM != null && !TT_CTD_EXIM.isEmpty()){
			
			DVKD =  (TT_CTD_EXIM.has("DVKD") && !TT_CTD_EXIM.get("DVKD").isJsonNull()) ? TT_CTD_EXIM.get("DVKD").getAsString() : null;
			BANK_OFFICE_ADDRESS = (TT_CTD_EXIM.has("DIACHI") && !TT_CTD_EXIM.get("DIACHI").isJsonNull()) ? TT_CTD_EXIM.get("DIACHI").getAsString() : null;
			BANK_POSITION =  (TT_CTD_EXIM.has("CHUCVU") && !TT_CTD_EXIM.get("CHUCVU").isJsonNull()) ? TT_CTD_EXIM.get("CHUCVU").getAsString() : null;
			String BANK_GRANDPARENTS = convertMasterdataCodeToName("ONGBA",(TT_CTD_EXIM.has("ONG_BA") && !TT_CTD_EXIM.get("ONG_BA").isJsonNull()) ?TT_CTD_EXIM.get("ONG_BA").getAsString() : null);
			BO.addProperty("BANK_GRANDPARENTS", BANK_GRANDPARENTS);
		}
		BO.addProperty("DVKD", DVKD);
		
		BO.addProperty("BANK_OFFICE_ADDRESS", BANK_OFFICE_ADDRESS);
		
		BO.addProperty("BANK_POSITION", BANK_POSITION);
		String CTD_DN_POSITION_ENTERPRISE  = null;
		JsonArray TT_DINHDANH = null;
		JsonArray TT_LIENLAC  = null;
		JsonObject TT_VC_KH = null;
		String CTD_CN_GRANDPARENTS = null;
		String CTD_CN_BIRTHDAY = null;
		if(TT_CTD_KH != null && !TT_CTD_KH.isEmpty()){			
			CTD_DN_POSITION_ENTERPRISE = convertMasterdataCodeToName("CHUCVU", (TT_CTD_KH.has("CHUC_VU") && !TT_CTD_KH.get("CHUC_VU").isJsonNull()) ? TT_CTD_KH.get("CHUC_VU").getAsString() : null);
			TT_DINHDANH = TT_CTD_KH.get("TT_DINHDANH").getAsJsonArray();
			TT_LIENLAC = TT_CTD_KH.getAsJsonArray("TT_LIENLAC");
			TT_VC_KH = TT_CTD_KH.getAsJsonObject("TT_VC_KH");
			CTD_CN_GRANDPARENTS = convertMasterdataCodeToName("ONGBA",(TT_CTD_KH.has("ONG_BA") && !TT_CTD_KH.get("ONG_BA").isJsonNull()) ?TT_CTD_KH.get("ONG_BA").getAsString() : null);
			try {
				CTD_CN_BIRTHDAY = DateHelper.convertDateString((TT_CTD_KH.getAsJsonObject().has("NGAY_SINH") && !TT_CTD_KH.getAsJsonObject().get("NGAY_SINH").isJsonNull()) ? TT_CTD_KH.getAsJsonObject().get("NGAY_SINH").getAsString() : null,"dd-MM-YYY");
			} catch (ParseException e) {
				throw new SIException(Error.EX_99, "Error in convertBpmDateString", e);	
			}
		}
		BO.addProperty("CTD_CN_GRANDPARENTS", CTD_CN_GRANDPARENTS);
		BO.addProperty("CTD_DN_POSITION_ENTERPRISE", CTD_DN_POSITION_ENTERPRISE);
		BO.addProperty("CTD_CN_BIRTHDAY", CTD_CN_BIRTHDAY);
		/*CTD_CN_IDNUMBER
		CTD_CN_DATE_RANGE
		CTD_CN_ISSUED_BY
		CTD_CN_PER_ADDRESS
		CTD_CN_CONT_ADDRESS*/
		String CTD_CN_IDNUMBER = null;
		String CTD_CN_DATE_RANGE = null;
		String CTD_CN_ISSUED_BY = null;
		
		if(TT_DINHDANH!= null && !TT_DINHDANH.isEmpty() && TT_DINHDANH.size() > 0){
			for (JsonElement ttdd : TT_DINHDANH) {
					CTD_CN_IDNUMBER = (ttdd.getAsJsonObject().has("SO_ID") && !ttdd.getAsJsonObject().get("SO_ID").isJsonNull())  ? ttdd.getAsJsonObject().get("SO_ID").getAsString() : null;
					try {
						CTD_CN_DATE_RANGE = DateHelper.convertDateString((ttdd.getAsJsonObject().has("NGAY_CAP") && !ttdd.getAsJsonObject().get("NGAY_CAP").isJsonNull()) ? ttdd.getAsJsonObject().get("NGAY_CAP").getAsString() : null,"dd-MM-YYY");
					} catch (ParseException e) {
						throw new SIException(Error.EX_99, "Error in convertBpmDateString", e);	
					}
					CTD_CN_ISSUED_BY = convertMasterdataCodeToName("NOICAPID",(ttdd.getAsJsonObject().has("NOI_CAP") && !ttdd.getAsJsonObject().get("NOI_CAP").isJsonNull()) ? ttdd.getAsJsonObject().get("NOI_CAP").getAsString() : null);
					break;
			}
		}
		BO.addProperty("CTD_CN_IDNUMBER", CTD_CN_IDNUMBER);
		BO.addProperty("CTD_CN_DATE_RANGE", CTD_CN_DATE_RANGE);
		BO.addProperty("CTD_CN_ISSUED_BY", CTD_CN_ISSUED_BY);
		
		String HKTHUONGTRU = AddressHelper.getMergerAddress(TT_LIENLAC, "HKTHUONGTRU", this.processDataSource);
		BO.addProperty("CTD_CN_PER_ADDRESS", HKTHUONGTRU);
		String DCLIENHE = AddressHelper.getMergerAddress(TT_LIENLAC, "DCLIENHE", this.processDataSource);
		BO.addProperty("CTD_CN_CONT_ADDRESS", DCLIENHE);
//		JsonObject TT_DEXUAT = BO.has("TT_DEXUAT")  ? BO.get("TT_DEXUAT").getAsJsonObject() : null;
		if(TT_DENGHI!= null && !TT_DENGHI.isEmpty()){
			JsonObject TT_CACBEN_KI_HD = TT_DENGHI.has("TT_CACBEN_KI_HD") ? TT_DENGHI.getAsJsonObject("TT_CACBEN_KI_HD") : null;
			String YC_HONPHOI_KY = (TT_CACBEN_KI_HD.has("YC_HONPHOI") && !TT_CACBEN_KI_HD.get("YC_HONPHOI").isJsonNull()) ? (TT_CACBEN_KI_HD.get("YC_HONPHOI").getAsString().equalsIgnoreCase("1") ? "CHECK" : "UNCHECK") : null;
			BO.addProperty("IS_MARRY", YC_HONPHOI_KY);			
		}
		JsonArray TT_DINHDANH_VC = null;
		if(TT_VC_KH != null && !TT_VC_KH.isEmpty()){
			
			TT_DINHDANH_VC = TT_VC_KH.has("TT_VC_KH") ? TT_VC_KH.getAsJsonArray("TT_DINHDANH") : null;
			JsonArray TT_DIACHI = TT_VC_KH.has("TT_DIACHI") ? TT_VC_KH.getAsJsonArray("TT_VC_KH") : null;
			BO.addProperty("NDV_PER_ADDRESS ", AddressHelper.getMergerAddress(TT_DIACHI, "HKTHUONGTRU", this.processDataSource));
			BO.addProperty("NDV_CONT_ADDRESS", AddressHelper.getMergerAddress(TT_DIACHI, "DCLIENHE", this.processDataSource));
		}
		String NDV_IDNUMBER =null;
		String NDV_DATE_RANGE =null;
		String NDV_CN_ISSUED_BY =null;
		if(TT_DINHDANH_VC != null && !TT_DINHDANH_VC.isEmpty() && TT_DINHDANH_VC.size() > 0){
			for (JsonElement ttdd : TT_DINHDANH_VC) {
					NDV_IDNUMBER = (ttdd.getAsJsonObject().has("SO_ID") && !ttdd.getAsJsonObject().get("SO_ID").isJsonNull()) ? ttdd.getAsJsonObject().get("SO_ID").getAsString() : null;
					try {
						NDV_DATE_RANGE = DateHelper.convertDateString((ttdd.getAsJsonObject().has("NGAY_CAP") && !ttdd.getAsJsonObject().get("NGAY_CAP").isJsonNull()) ? ttdd.getAsJsonObject().get("NGAY_CAP").getAsString() : null,"dd-MM-YYY");
					} catch (ParseException e) {
						throw new SIException(Error.EX_99, "Error in convertBpmDateString", e);	
					}
					NDV_CN_ISSUED_BY = convertMasterdataCodeToName("NOICAPID",( ttdd.getAsJsonObject().has("NOI_CAP") &&  !ttdd.getAsJsonObject().get("NOI_CAP").isJsonNull())? ttdd.getAsJsonObject().get("NOI_CAP").getAsString() : null);
					break;
			}
		}
		BO.addProperty("NDV_IDNUMBER", NDV_IDNUMBER);
		BO.addProperty("NDV_DATE_RANGE", NDV_DATE_RANGE);
		BO.addProperty("NDV_CN_ISSUED_BY", NDV_CN_ISSUED_BY);
		
		String BY_MONEY_LOAN = null;
		
		String TGT_TS_TT = null;
		String LOAN = null;
		String CURRENCY = null;
		String BLTL_DOCUMENT = null;
		String BLTL_TIME = null;
		String BLTL_ADDRESS = null;
		String BLTL_RULE = null;
		String BLTL_FRRE_1 = null;
		String BLTL_MONEY = null;
		String BLTL_RATIO = null;
		String BLTL_DESCRIBE = null;
		String BLTL_SUM_TSTT = null;
		String TO_LETTER_GUARANTEE = null;
		String BLTL_LETTER_1 = null;
		String BLTL_LETTER_2 = null;
		String BLTL_LANGUAGE = null;
		String BL_DAY = null;
		String BL_EFFECTIVE_HOUR_DAY = null;
		String BL_EXACT_DAY = null;
		String BL_EFFECTIVE_EVENT = null;
		String BL_EXACT_DAY_HOUR_DATE = null;
		String BLTL_NAME = null;
		String BLTL_NUMBER_ID = null;
		String BL_NO_DATE_1 = null;
		String BL_NO_DATE_2 = null; 
		String FROM_THE_TIME_OF_US = null; 
		String BL_SPECIFICALLY_1 = null; 
		String BL_SPECIFICALLY_2 = null; 
		String NO_DAYS_1 = null; 
		String NO_DAYS_2 = null; 
		String GNBL_EVENT_1 = null; 
		String GNBL_EVENT_2 = null; 
		if(TT_YC_HDTINDUNG != null && !TT_YC_HDTINDUNG.isEmpty() && TT_YC_HDTINDUNG.size() > 0){
			BY_MONEY_LOAN = MoneyHelper.readMoneyToText((TT_YC_HDTINDUNG.has("SOTIEN_CTD") && !TT_YC_HDTINDUNG.get("SOTIEN_CTD").isJsonNull()) ? TT_YC_HDTINDUNG.get("SOTIEN_CTD").getAsString() : null);
			TGT_TS_TT = (TT_YC_HDTINDUNG.has("TGT_TS_TT") && !TT_YC_HDTINDUNG.get("TGT_TS_TT").isJsonNull()) ? TT_YC_HDTINDUNG.get("TGT_TS_TT").getAsString() : null;
			LOAN = (TT_YC_HDTINDUNG.has("SOTIEN_CTD") && ! TT_YC_HDTINDUNG.get("SOTIEN_CTD").isJsonNull()) ? TT_YC_HDTINDUNG.get("SOTIEN_CTD").getAsString() : null;
			BLTL_DOCUMENT = (TT_YC_HDTINDUNG.has("CHUNGTU_LQ") &&  !TT_YC_HDTINDUNG.get("CHUNGTU_LQ").isJsonNull()) ? TT_YC_HDTINDUNG.get("CHUNGTU_LQ").getAsString() : null;
			BLTL_TIME = (TT_YC_HDTINDUNG.has("THOIHAN_BAOLANH") && !TT_YC_HDTINDUNG.get("THOIHAN_BAOLANH").isJsonNull())? TT_YC_HDTINDUNG.get("THOIHAN_BAOLANH").getAsString() : null;
			BLTL_ADDRESS = (TT_YC_HDTINDUNG.has("DIACHI_NGUOITH") && !TT_YC_HDTINDUNG.get("DIACHI_NGUOITH").isJsonNull()) ? TT_YC_HDTINDUNG.get("DIACHI_NGUOITH").getAsString() : null;
			BLTL_RULE = (TT_YC_HDTINDUNG.has("DK_NGHIAVU_BL") && !TT_YC_HDTINDUNG.get("DK_NGHIAVU_BL").isJsonNull()) ? TT_YC_HDTINDUNG.get("DK_NGHIAVU_BL").getAsString() : null;
			BLTL_FRRE_1 = (TT_YC_HDTINDUNG.has("PHI_BAOLANH") && !TT_YC_HDTINDUNG.get("PHI_BAOLANH").isJsonNull()) ? TT_YC_HDTINDUNG.get("PHI_BAOLANH").getAsString() : null;
			BLTL_MONEY = (TT_YC_HDTINDUNG.has("TIEN_KYQUY") && !TT_YC_HDTINDUNG.get("TIEN_KYQUY").isJsonNull()) ? TT_YC_HDTINDUNG.get("TIEN_KYQUY").getAsString() : null;
			BLTL_RATIO = (TT_YC_HDTINDUNG.has("TYLE_KYQUY")&& !TT_YC_HDTINDUNG.get("TYLE_KYQUY").isJsonNull()) ? TT_YC_HDTINDUNG.get("TYLE_KYQUY").getAsBigDecimal().setScale(2,BigDecimal.ROUND_HALF_EVEN).toString() : null;
			BLTL_DESCRIBE = (TT_YC_HDTINDUNG.has("MOTA_TSBD") && !TT_YC_HDTINDUNG.get("MOTA_TSBD").isJsonNull()) ? TT_YC_HDTINDUNG.get("MOTA_TSBD").getAsString() : null;
			BLTL_SUM_TSTT = (TT_YC_HDTINDUNG.has("TGT_TS_TT") && !TT_YC_HDTINDUNG.get("TGT_TS_TT").isJsonNull() )? TT_YC_HDTINDUNG.get("TGT_TS_TT").getAsString() : null;
			CURRENCY = (TT_YC_HDTINDUNG.has("LOAITIEN") && !TT_YC_HDTINDUNG.get("LOAITIEN").isJsonNull()) ? TT_YC_HDTINDUNG.get("LOAITIEN").getAsString() : null;
			TO_LETTER_GUARANTEE = convertMasterdataCodeToName("LOAITHUBL",(TT_YC_HDTINDUNG.has("THU_BAOLANH") && !TT_YC_HDTINDUNG.get("THU_BAOLANH").isJsonNull()) ? TT_YC_HDTINDUNG.get("THU_BAOLANH").getAsString() : null);
			BLTL_LETTER_1 = (TT_YC_HDTINDUNG.has("DV_BANHANH") && !TT_YC_HDTINDUNG.get("DV_BANHANH").isJsonNull()) ? (TT_YC_HDTINDUNG.get("DV_BANHANH").getAsString().equalsIgnoreCase("DVBANHANHMAU01"))?  "CHECK" : "UNCHECK" : "UNCHECK";
			BLTL_LETTER_2 = (TT_YC_HDTINDUNG.has("DV_BANHANH") && !TT_YC_HDTINDUNG.get("DV_BANHANH").isJsonNull()) ? (TT_YC_HDTINDUNG.get("DV_BANHANH").getAsString().equalsIgnoreCase("DVBANHANHMAU02"))?  "CHECK" : "UNCHECK" : "UNCHECK";
			BLTL_LANGUAGE = TT_YC_HDTINDUNG.has("NGONNGU_BL") && !TT_YC_HDTINDUNG.get("NGONNGU_BL").isJsonNull() ? TT_YC_HDTINDUNG.get("NGONNGU_BL").getAsString() : null;
			BL_DAY = TT_YC_HDTINDUNG.has("NGAY_HIEU_LUC") && !TT_YC_HDTINDUNG.get("NGAY_HIEU_LUC").isJsonNull() ? TT_YC_HDTINDUNG.get("NGAY_HIEU_LUC").getAsString() : null;
			BL_EXACT_DAY = TT_YC_HDTINDUNG.has("NGAY_HH_CU_THE") && !TT_YC_HDTINDUNG.get("NGAY_HH_CU_THE").isJsonNull() ? TT_YC_HDTINDUNG.get("NGAY_HH_CU_THE").getAsString().equalsIgnoreCase("1") ? "CHECK" :"UNCHECK" : "UNCHECK";
			BL_EFFECTIVE_EVENT = convertMasterdataCodeToName("NGAYHIEULUC",TT_YC_HDTINDUNG.has("NGAY_HIEU_LUC") && !TT_YC_HDTINDUNG.get("NGAY_HIEU_LUC").isJsonNull() ? TT_YC_HDTINDUNG.get("NGAY_HIEU_LUC").getAsString() : null);
			BL_EXACT_DAY_HOUR_DATE = TT_YC_HDTINDUNG.has("NGAY_HH_CU_THE_DD") && !TT_YC_HDTINDUNG.get("NGAY_HH_CU_THE_DD").isJsonNull() ? TT_YC_HDTINDUNG.get("NGAY_HH_CU_THE_DD").getAsString() : null;
			BLTL_NAME = TT_YC_HDTINDUNG.has("NGUOI_THUHUONG_BL") && !TT_YC_HDTINDUNG.get("NGUOI_THUHUONG_BL").isJsonNull() ? TT_YC_HDTINDUNG.get("NGUOI_THUHUONG_BL").getAsString() : null;
			JsonArray TT_DINHDANH_NTH = null;
			TT_DINHDANH_NTH = TT_YC_HDTINDUNG.has("TT_DINHDANH_NTH") && !TT_YC_HDTINDUNG.get("TT_DINHDANH_NTH").isJsonNull() ? TT_YC_HDTINDUNG.get("TT_DINHDANH_NTH").getAsJsonArray() : null;
			if(TT_DINHDANH_NTH !=null && !TT_DINHDANH_NTH.isEmpty() && TT_DINHDANH_NTH.size() > 0){
				BLTL_NUMBER_ID = (TT_DINHDANH_NTH.get(0).getAsJsonObject().has("SO_ID") && !TT_DINHDANH_NTH.get(0).getAsJsonObject().get("SO_ID").isJsonNull()) ? TT_DINHDANH_NTH.get(0).getAsJsonObject().get("SO_ID").getAsString(): null;
				BO.addProperty("BLTL_NUMBER_ID", BLTL_NUMBER_ID);
			}

			BL_NO_DATE_1 = TT_YC_HDTINDUNG.has("SONGAY") && !TT_YC_HDTINDUNG.get("SONGAY").isJsonNull() ? TT_YC_HDTINDUNG.get("SONGAY").getAsString().equalsIgnoreCase("1") ? "CHECK" :"UNCHECK" : "UNCHECK";;
			BL_NO_DATE_2 = TT_YC_HDTINDUNG.has("MOTA_SONGAY") && !TT_YC_HDTINDUNG.get("MOTA_SONGAY").isJsonNull() ? TT_YC_HDTINDUNG.get("MOTA_SONGAY").getAsString() : null;
			FROM_THE_TIME_OF_US = TT_YC_HDTINDUNG.has("KETU_THOIDIEM") && !TT_YC_HDTINDUNG.get("KETU_THOIDIEM").isJsonNull() ? TT_YC_HDTINDUNG.get("KETU_THOIDIEM").getAsString() : null;
			BL_SPECIFICALLY_1 = TT_YC_HDTINDUNG.has("CUTHE_NGAYHET_HL") && !TT_YC_HDTINDUNG.get("CUTHE_NGAYHET_HL").isJsonNull() ? TT_YC_HDTINDUNG.get("CUTHE_NGAYHET_HL").getAsString().equalsIgnoreCase("1") ? "CHECK" :"UNCHECK" : "UNCHECK";;
			try {
				BL_EFFECTIVE_HOUR_DAY = DateHelper.convertDateString(TT_YC_HDTINDUNG.has("NGAY_HL_CU_THE") && !TT_YC_HDTINDUNG.get("NGAY_HL_CU_THE").isJsonNull() ? TT_YC_HDTINDUNG.get("NGAY_HL_CU_THE").getAsString() : null,"hh:mm dd-MM-yyyy");
				BL_SPECIFICALLY_2 = DateHelper.convertDateString(TT_YC_HDTINDUNG.has("CUTHE_NGAYHET_HL_MOTA") && !TT_YC_HDTINDUNG.get("CUTHE_NGAYHET_HL_MOTA").isJsonNull() ? TT_YC_HDTINDUNG.get("CUTHE_NGAYHET_HL_MOTA").getAsString() : null,"dd-MM-yyyy");
			} catch (ParseException e) {
				throw new SIException(Error.EX_99, "Error in convertBpmDateString", e);	
			}
			NO_DAYS_1 = TT_YC_HDTINDUNG.has("SONGAY") && !TT_YC_HDTINDUNG.get("SONGAY").isJsonNull() ? TT_YC_HDTINDUNG.get("SONGAY").getAsString().equalsIgnoreCase("1") ? "CHECK" :"UNCHECK" : "UNCHECK";;
			NO_DAYS_2 = TT_YC_HDTINDUNG.has("MOTA_SONGAY") && !TT_YC_HDTINDUNG.get("MOTA_SONGAY").isJsonNull() ? TT_YC_HDTINDUNG.get("MOTA_SONGAY").getAsString() : null;

			GNBL_EVENT_1 = TT_YC_HDTINDUNG.has("NGAY_HH_SU_KIEN") && !TT_YC_HDTINDUNG.get("NGAY_HH_SU_KIEN").isJsonNull() ? TT_YC_HDTINDUNG.get("NGAY_HH_SU_KIEN").getAsString().equalsIgnoreCase("1") ? "CHECK" :"UNCHECK" : "UNCHECK";;
			GNBL_EVENT_2 = TT_YC_HDTINDUNG.has("NGAY_HH_SU_KIEN_MOTA") && !TT_YC_HDTINDUNG.get("NGAY_HH_SU_KIEN_MOTA").isJsonNull() ? TT_YC_HDTINDUNG.get("NGAY_HH_SU_KIEN_MOTA").getAsString() : null;
		}
		BO.addProperty("BL_EXACT_DAY_HOUR_DATE", BL_EXACT_DAY_HOUR_DATE);
		BO.addProperty("BL_NO_DATE_1", BL_NO_DATE_1);
		BO.addProperty("BL_NO_DATE_2", BL_NO_DATE_2);
		BO.addProperty("FROM_THE_TIME_OF_US", FROM_THE_TIME_OF_US);
		BO.addProperty("BL_SPECIFICALLY_1", BL_SPECIFICALLY_1);
		BO.addProperty("BL_SPECIFICALLY_2", BL_SPECIFICALLY_2);
		BO.addProperty("NO_DAYS_1", NO_DAYS_1);
		BO.addProperty("NO_DAYS_2", NO_DAYS_2);
		BO.addProperty("GNBL_EVENT_1", GNBL_EVENT_1);
		BO.addProperty("GNBL_EVENT_2", GNBL_EVENT_2);
		BO.addProperty("BL_EXACT_DAY", BL_EXACT_DAY);
		BO.addProperty("BLTL_NAME", BLTL_NAME);
		BO.addProperty("BL_EFFECTIVE_EVENT", BL_EFFECTIVE_EVENT);
		BO.addProperty("BL_DAY", BL_DAY);
		BO.addProperty("BL_EFFECTIVE_HOUR_DAY", BL_EFFECTIVE_HOUR_DAY);
		BO.addProperty("BLTL_LANGUAGE", BLTL_LANGUAGE);
		BO.addProperty("BY_MONEY_LOAN", BY_MONEY_LOAN);
		BO.addProperty("BLTL_LETTER_1", BLTL_LETTER_1);
		BO.addProperty("BLTL_LETTER_2", BLTL_LETTER_2);
		BO.addProperty("BLTL_BY_SUM_TSTT", MoneyHelper.readMoneyToText(TGT_TS_TT));
		BO.addProperty("TO_LETTER_GUARANTEE", TO_LETTER_GUARANTEE);
		BO.addProperty("LOAN", LOAN);
		BO.addProperty("CURRENCY", CURRENCY);
		BO.addProperty("BLTL_DOCUMENT", BLTL_DOCUMENT);
		BO.addProperty("BLTL_ADDRESS", BLTL_ADDRESS);
		BO.addProperty("BLTL_FRRE_1", BLTL_FRRE_1);
		BO.addProperty("BLTL_RULE", BLTL_RULE);
		BO.addProperty("BLTL_TIME", BLTL_TIME);
		BO.addProperty("BLTL_MONEY", BLTL_MONEY);
		BO.addProperty("BLTL_RATIO", BLTL_RATIO);
		BO.addProperty("BLTL_DESCRIBE", BLTL_DESCRIBE);
		BO.addProperty("BLTL_SUM_TSTT", BLTL_SUM_TSTT);
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
