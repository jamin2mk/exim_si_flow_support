package com.service.impl.gendoc.v1_0;

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

public class BM73 extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public BM73(String docName, String data, boolean isMergeData,
			String siVersion, String processDataSource, String logDataSource,
			ServiceConfig serviceConfig, SI_Log log,String dependentRequired) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource,
				serviceConfig, log);
		GenDocEnv genDocEnv = new Gson().fromJson(super.data.getAsJsonObject()
				.get("env"), GenDocEnv.class);
		genDocInvoker = new GenDocInvoker(docName, genDocEnv,
				processDataSource, log);
		super.invoker = genDocInvoker;
		super.isMergeData = true;
		super.isManualMapping_Input = true;
		super.isManualInvoke = true;
		this.docName = docName;
		this.dependentRequired = dependentRequired;
	}

	@Override
	public void businessMapping_Input() throws SIException {
		JsonObject BO = super.data.getAsJsonObject().get("data")
				.getAsJsonObject();

		BO.addProperty("GENDOC_CODE", docName);
		BO.addProperty("DATE_FM", DateHelper.getCurrentDate("YYYMMdd"));
		String PCA = BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE") != null ? BO
				.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE")
				.getAsString() : null;
		String[] split = PCA.split("\\.");
		BO.addProperty("PCA", split != null ? String.join("_", split) : "");

		JsonObject TT_SOANTHAO =  BO.has("TT_SOANTHAO") ? BO.get("TT_SOANTHAO").getAsJsonObject() : null;
		JsonObject TT_CTD_EXIM = null;
		if(TT_SOANTHAO != null && !TT_SOANTHAO.isEmpty()){
			
			TT_CTD_EXIM = (TT_SOANTHAO.has("TT_CTD_EXIM") && !TT_SOANTHAO.get("TT_CTD_EXIM").isJsonNull()) ? TT_SOANTHAO.get("TT_CTD_EXIM")
					.getAsJsonObject() : null;
		}
				JsonObject TT_CTD_KH  = null;
				if(TT_SOANTHAO!= null && !TT_SOANTHAO.isEmpty()){
					
					TT_CTD_KH = TT_SOANTHAO.has("TT_CTD_KH") ? TT_SOANTHAO.get("TT_CTD_KH").getAsJsonObject() : null;
				}
				if(TT_CTD_EXIM!= null && !TT_CTD_EXIM.isEmpty()){					
					String DVKD = (TT_CTD_EXIM
							.has("DVKD")  && !TT_CTD_EXIM.get("DVKD").isJsonNull())? TT_CTD_EXIM
									.get("DVKD").getAsString() : null;
					BO.addProperty("DVKD", DVKD);
					
					String BANK_OFFICE_ADDRESS = (TT_CTD_EXIM.has("DIACHI") && !TT_CTD_EXIM.get("DIACHI").isJsonNull())?  TT_CTD_EXIM.get("DIACHI").getAsString() : null;
					BO.addProperty("BANK_OFFICE_ADDRESS", BANK_OFFICE_ADDRESS);
					String BANK_GRANDPARENTS = convertMasterdataCodeToName("ONGBA",(TT_CTD_EXIM.has("ONG_BA") && !TT_CTD_EXIM.get("ONG_BA").isJsonNull()) ?TT_CTD_EXIM.get("ONG_BA").getAsString() : null);
					BO.addProperty("BANK_GRANDPARENTS", BANK_GRANDPARENTS);
					String BANK_POSITION =  ( TT_CTD_EXIM.has("CHUCVU") && !TT_CTD_EXIM.get("CHUCVU").isJsonNull()) ? TT_CTD_EXIM.get("CHUCVU").getAsString() : null;
					BO.addProperty("BANK_POSITION", BANK_POSITION);
				}
		if(TT_CTD_KH != null && !TT_CTD_KH.isEmpty()){
			String BTC_CN_ID_TYPE = null;
			String BTC_CN_COUPLE_IDNUMBER = null;
			String BTC_CN_COUPLE_DATE_RANGE = null;
			String BTC_CN_COUPLE_ISSUED_BY = null;
			JsonArray TT_DINHDANH = TT_CTD_KH.get("TT_DINHDANH").getAsJsonArray();
			if (TT_DINHDANH != null && !TT_DINHDANH.isEmpty() && TT_DINHDANH.size() > 0) {
				for (JsonElement e : TT_DINHDANH) {
					
					BTC_CN_ID_TYPE = (e.getAsJsonObject().has("LOAI_ID") && !e.getAsJsonObject().get("LOAI_ID").isJsonNull()) ? convertMasterdataCodeToName("LOAIID", e.getAsJsonObject().get("LOAI_ID")
							.getAsString()) : null;
							BTC_CN_COUPLE_IDNUMBER = (e.getAsJsonObject().has("SO_ID") && ! e.getAsJsonObject().get("SO_ID").isJsonNull()) ? e.getAsJsonObject().get("SO_ID")
									.getAsString() : null;
									BTC_CN_COUPLE_ISSUED_BY = convertMasterdataCodeToName("NOICAPID",(e.getAsJsonObject().has("NOI_CAP") && !e.getAsJsonObject().get("NOI_CAP").isJsonNull()) ? e.getAsJsonObject().get("NOI_CAP")
											.getAsString() : null);
											try {
												BTC_CN_COUPLE_DATE_RANGE = DateHelper
														.convertDateString(
																(e.getAsJsonObject().has("NGAY_CAP") && !e.getAsJsonObject().get("NGAY_CAP").isJsonNull()) ? e.getAsJsonObject().get("NGAY_CAP")
																		.getAsString() : null, "dd-MM-YYY");
											} catch (ParseException e1) {
												throw new SIException(Error.EX_99,
														"Error in convertBpmDateString", e1);
											}
											break;
											
				}
			}
			BO.addProperty("BTC_CN_ID_TYPE", BTC_CN_ID_TYPE);
			BO.addProperty("BTC_CN_COUPLE_IDNUMBER", BTC_CN_COUPLE_IDNUMBER);
			BO.addProperty("BTC_CN_COUPLE_DATE_RANGE", BTC_CN_COUPLE_DATE_RANGE);
			BO.addProperty("BTC_CN_COUPLE_ISSUED_BY", BTC_CN_COUPLE_ISSUED_BY);
		}
		JsonObject TT_YC_HDTSBD = null;
		SupportBMHelper support = new SupportBMHelper(super.mapper,
				super.processDataSource);
		TT_YC_HDTSBD = support
				.getObjectByDepedenRequired(BO, dependentRequired);	
		JsonObject TT_BEN_BD = null;
		JsonObject TT_BEN_CN =  null; 
//		JsonObject TT_BEN_DN =  null; 
		JsonArray DS_BDS = null;
		JsonArray DS_QTS = null ;
		if (TT_YC_HDTSBD!= null && !TT_YC_HDTSBD.isEmpty()) {

			TT_BEN_BD = (TT_YC_HDTSBD.has("TT_BEN_BD")) ? TT_YC_HDTSBD.get("TT_BEN_BD").getAsJsonObject() : null;
			TT_BEN_CN = ( TT_YC_HDTSBD.has("TT_BEN_CN")) ? TT_YC_HDTSBD.getAsJsonObject("TT_BEN_CN") : null;
//			TT_BEN_DN = ( TT_YC_HDTSBD.has("TT_BEN_DN")) ? TT_YC_HDTSBD.getAsJsonObject("TT_BEN_DN") : null;
			DS_BDS = (TT_YC_HDTSBD.has("DS_BDS")) ? TT_YC_HDTSBD.getAsJsonArray("DS_BDS") : null;
			DS_QTS = (TT_YC_HDTSBD.has("DS_QTS")) ? TT_YC_HDTSBD.getAsJsonArray("DS_QTS") : null;
		}
		JsonArray TT_LIENLAC = null;
		JsonObject TT_NGUOIHONPHOI = null;
	
		JsonObject KH_CN = null;
		if (TT_BEN_BD != null && !TT_BEN_BD.isEmpty()) {
			TT_LIENLAC = TT_BEN_BD.getAsJsonArray("TT_LIENLAC");
			TT_NGUOIHONPHOI = TT_BEN_BD.getAsJsonObject("TT_NGUOIHONPHOI");
			KH_CN = TT_BEN_BD.getAsJsonObject("KH_CN");
			String MARRIAGE_SIGNING = (TT_BEN_BD.has("YC_NGUOIHONPHOI") &&!TT_BEN_BD.get("YC_NGUOIHONPHOI").isJsonNull()) ? (TT_BEN_BD
					.get("YC_NGUOIHONPHOI").getAsString().equalsIgnoreCase("1") ? "CHECK"
							: "UNCHECK")
							: "UNCHECK";
			BO.addProperty("MARRIAGE_SIGNING", MARRIAGE_SIGNING);
		}
		String BTC_CN_GRANDPARENTS = null;
		String BTC_CN_FULLNAME = null;
		String BTC_CN_PHONE_NUMBER = null;
		String BTC_CN_BIRTH = null;
		if(KH_CN != null && !KH_CN.isEmpty()){
			BTC_CN_GRANDPARENTS = convertMasterdataCodeToName("ONGBA", (KH_CN.has("ONG_BA") && !KH_CN.get("ONG_BA").isJsonNull()) ? KH_CN.get("ONG_BA").getAsString() : null);
			BTC_CN_FULLNAME  =  (KH_CN.has("TEN_KH_CODAU")&& !KH_CN.get("TEN_KH_CODAU").isJsonNull()) ? KH_CN.get("TEN_KH_CODAU").getAsString() : null;
			try {
				BTC_CN_BIRTH = DateHelper.convertDateString( (KH_CN.has("NAM_SINH") && !KH_CN.get("NAM_SINH").isJsonNull()) ? KH_CN.get("NAM_SINH").getAsString() : null,"dd-MM-YYY");
			} catch (ParseException e) {
				throw new SIException(Error.EX_99,
						"Error in convertBpmDateString", e);
			}
			BTC_CN_PHONE_NUMBER = (KH_CN.has("SO_DT")&& !KH_CN.get("SO_DT").isJsonNull()) ? KH_CN.get("SO_DT").getAsString() : null;
		}
		BO.addProperty("BTC_CN_PHONE_NUMBER", BTC_CN_PHONE_NUMBER);
		BO.addProperty("BTC_CN_BIRTH", BTC_CN_BIRTH);
		BO.addProperty("BTC_CN_FULLNAME", BTC_CN_FULLNAME);
		BO.addProperty("BTC_CN_GRANDPARENTS", BTC_CN_GRANDPARENTS);
		String BTC_CN_COUPLE_PER_ADDRESS = null;
		String BTC_CN_COUPLE_CONT_ADDRESS = null;
		if (TT_LIENLAC!= null && !TT_LIENLAC.isEmpty() && TT_LIENLAC.size() > 0) {
			BTC_CN_COUPLE_PER_ADDRESS = AddressHelper.getMergerAddress(
					TT_LIENLAC, "HKTHUONGTRU", this.processDataSource);
			BTC_CN_COUPLE_CONT_ADDRESS = AddressHelper.getMergerAddress(
					TT_LIENLAC, "DCLIENHE", this.processDataSource);
		}
		BO.addProperty("BTC_CN_COUPLE_PER_ADDRESS", BTC_CN_COUPLE_PER_ADDRESS);
		BO.addProperty("BTC_CN_COUPLE_CONT_ADDRESS", BTC_CN_COUPLE_CONT_ADDRESS);
		String NHP_ID_TYPE = null;
		String NHP_IDNUMBER = null;
		String NHP_DATE_RANGE = null;
		String NHP_ISSUED_BY = null;
		
		JsonArray TT_DINHDANH_NHP = null;
		JsonArray TT_LIENLAC_NHP = null;
		String NHP_GRANDPARENTS = null;
		String NHP_FULLNAME_INDIVIDUAL = null;
		String NHP_BIRTHDAY = null;
		String NHP_PHONE_NUMBER = null;
		if (TT_NGUOIHONPHOI != null && !TT_NGUOIHONPHOI.isEmpty()) {
			TT_DINHDANH_NHP = TT_NGUOIHONPHOI.getAsJsonArray("TT_DINHDANH");
			TT_LIENLAC_NHP = TT_NGUOIHONPHOI.getAsJsonArray("TT_LIENLAC");
			NHP_GRANDPARENTS  =  convertMasterdataCodeToName("ONGBA", (TT_NGUOIHONPHOI.has("ONG_BA") && !TT_NGUOIHONPHOI.get("ONG_BA").isJsonNull()) ? TT_NGUOIHONPHOI.get("ONG_BA").getAsString() : null);
			NHP_FULLNAME_INDIVIDUAL = (TT_NGUOIHONPHOI.has("TEN_KH_CODAU") && ! TT_NGUOIHONPHOI.get("TEN_KH_CODAU").isJsonNull()) ? TT_NGUOIHONPHOI.get("TEN_KH_CODAU").getAsString() : null;
			NHP_PHONE_NUMBER =  (TT_NGUOIHONPHOI.has("SO_DT") && ! TT_NGUOIHONPHOI.get("SO_DT").isJsonNull()) ? TT_NGUOIHONPHOI.get("SO_DT").getAsString() : null;
			try {
				NHP_BIRTHDAY = DateHelper.convertDateString((TT_NGUOIHONPHOI.has("NGAY_SINH")&& !TT_NGUOIHONPHOI.get("NGAY_SINH").isJsonNull()) ? TT_NGUOIHONPHOI.get("NGAY_SINH").getAsString() : null,"dd-MM-YYY");
			} catch (ParseException e) {
				throw new SIException(Error.EX_99,
						"Error in convertBpmDateString", e);
			}
		}
		BO.addProperty("NHP_PHONE_NUMBER", NHP_PHONE_NUMBER);
		BO.addProperty("NHP_BIRTHDAY", NHP_BIRTHDAY);
		BO.addProperty("NHP_GRANDPARENTS", NHP_GRANDPARENTS);
		BO.addProperty("NHP_FULLNAME_INDIVIDUAL", NHP_FULLNAME_INDIVIDUAL);
		if (TT_DINHDANH_NHP != null && !TT_DINHDANH_NHP.isEmpty() && TT_DINHDANH_NHP.size() > 0) {
			for (JsonElement e : TT_DINHDANH_NHP) {
				
					NHP_ID_TYPE =  (e.getAsJsonObject().has("LOAI_ID") && ! e.getAsJsonObject().get("LOAI_ID").isJsonNull()) ? convertMasterdataCodeToName("LOAIID", e.getAsJsonObject().get("LOAI_ID")
							.getAsString()) : null;
					NHP_IDNUMBER = (e.getAsJsonObject().has("SO_ID") && !e.getAsJsonObject().get("SO_ID").isJsonNull()) ? e.getAsJsonObject().get("SO_ID")
							.getAsString() : null;
					try {
						NHP_DATE_RANGE =DateHelper
								.convertDateString(
										(e.getAsJsonObject().has("NGAY_CAP") && !e.getAsJsonObject().get("NGAY_CAP").isJsonNull()) ? e.getAsJsonObject().get("NGAY_CAP")
												.getAsString() : null, "dd-MM-YYY");
					} catch (ParseException e1) {
						throw new SIException(Error.EX_99,
								"Error in convertBpmDateString", e1);
					}
					NHP_ISSUED_BY = convertMasterdataCodeToName("NOICAPID",(e.getAsJsonObject().has("NOI_CAP") && !e.getAsJsonObject().get("NOI_CAP").isJsonNull()) ? e.getAsJsonObject().get("NOI_CAP")
							.getAsString() : null);
					break;
				
			}
		}
		BO.addProperty("NHP_ID_TYPE", NHP_ID_TYPE);
		BO.addProperty("NHP_IDNUMBER", NHP_IDNUMBER);
		BO.addProperty("NHP_DATE_RANGE", NHP_DATE_RANGE);
		BO.addProperty("NHP_ISSUED_BY", NHP_ISSUED_BY);
		String NHP_PER_ADDRESS = null;
		String NHP_CONT_ADDRESS = null;
		if(TT_LIENLAC_NHP != null && !TT_LIENLAC_NHP.isEmpty() && TT_LIENLAC_NHP.size()>0){
			
			NHP_PER_ADDRESS = AddressHelper.getMergerAddress(
					TT_LIENLAC_NHP, "HKTHUONGTRU", this.processDataSource);
			NHP_CONT_ADDRESS = AddressHelper.getMergerAddress(
					TT_LIENLAC_NHP, "DCLIENHE", this.processDataSource);

		}
		BO.addProperty("NHP_PER_ADDRESS", NHP_PER_ADDRESS);
		BO.addProperty("NHP_CONT_ADDRESS", NHP_CONT_ADDRESS);
		String BCN_ID_TYPE = null;
		String BCN_IDNUMBER = null;
		String BCN_DATE_RANGE = null;
		String BCN_ISSUED_BY = null;
		JsonArray TT_DINHDANH_BEN_CN = null ;
		String BCN_GRANDPARENTS = null;
		String BCN_FULLNAME_INDIVIDUAL = null;
		String BCN_BIRTH = null;
		String DCN_FULLNAME_INDIVIDUAL = null;
		JsonArray TT_LIENLAC_BEN_CN  = null;
		JsonObject TT_DONGCN  = null;
		if(TT_BEN_CN != null && !TT_BEN_CN.isEmpty()){
			BCN_GRANDPARENTS = (TT_BEN_CN.has("ONG_BA") && !TT_BEN_CN.get("ONG_BA").isJsonNull()) ? convertMasterdataCodeToName("ONGBA",TT_BEN_CN.get("ONG_BA").getAsString()) : null;
			TT_DINHDANH_BEN_CN = (TT_BEN_CN.has("TT_DINHDANH")) ?  TT_BEN_CN.getAsJsonArray("TT_DINHDANH") : null;
			BCN_FULLNAME_INDIVIDUAL = TT_BEN_CN.has("TEN_CA_NHAN") && !TT_BEN_CN.get("TEN_CA_NHAN").isJsonNull() ? TT_BEN_CN.get("TEN_CA_NHAN").getAsString() : null;
			DCN_FULLNAME_INDIVIDUAL = TT_BEN_CN.has("TEN_KH_CODAU") && !TT_BEN_CN.get("TEN_KH_CODAU").isJsonNull() ? TT_BEN_CN.get("TEN_KH_CODAU").getAsString() : null;
			try {
				BCN_BIRTH =  DateHelper.convertDateString((TT_BEN_CN.has("NGAY_SINH")&& !TT_BEN_CN.get("NGAY_SINH").isJsonNull()) ? TT_BEN_CN.get("NGAY_SINH").getAsString() : null,"dd-MM-YYY");
			} catch (ParseException e) {
				throw new SIException(Error.EX_99,
						"Error in convertBpmDateString", e);
			} 
			TT_LIENLAC_BEN_CN = TT_BEN_CN.getAsJsonArray("TT_LIENLAC");
			TT_DONGCN = TT_BEN_CN.getAsJsonObject("TT_DONGCN");
		}
		BO.addProperty("DCN_FULLNAME_INDIVIDUAL", DCN_FULLNAME_INDIVIDUAL);
		BO.addProperty("BCN_BIRTH", BCN_BIRTH);
		BO.addProperty("BCN_GRANDPARENTS", BCN_GRANDPARENTS);
		BO.addProperty("BCN_FULLNAME_INDIVIDUAL", BCN_FULLNAME_INDIVIDUAL);
		if(TT_DINHDANH_BEN_CN != null && !TT_DINHDANH_BEN_CN.isEmpty() && TT_DINHDANH_BEN_CN.size() > 0){
			for (JsonElement e : TT_DINHDANH_BEN_CN) {
				
					BCN_ID_TYPE = (e.getAsJsonObject().has("LOAI_ID") && !e.getAsJsonObject().get("LOAI_ID").isJsonNull()) ? convertMasterdataCodeToName("LOAIID", e.getAsJsonObject().get("LOAI_ID")
							.getAsString()) : null;
					BCN_IDNUMBER = (e.getAsJsonObject().has("SO_ID") && !e.getAsJsonObject().get("SO_ID").isJsonNull()) ? e.getAsJsonObject().get("SO_ID")
							.getAsString() : null;
					BCN_ISSUED_BY = convertMasterdataCodeToName("NOICAPID",(e.getAsJsonObject().has("NOI_CAP") && !e.getAsJsonObject().get("NOI_CAP").isJsonNull()) ? e.getAsJsonObject().get("NOI_CAP")
							.getAsString() : null);
					try {
						BCN_DATE_RANGE = DateHelper
								.convertDateString(
										(e.getAsJsonObject().has("NGAY_CAP") && !e.getAsJsonObject().get("NGAY_CAP").isJsonNull()) ? e.getAsJsonObject().get("NGAY_CAP")
												.getAsString() : null, "dd-MM-YYY");
					} catch (ParseException e1) {
						throw new SIException(Error.EX_99,
								"Error in convertBpmDateString", e1);
					}
					break;
				
			}
		}
		
		BO.addProperty("BCN_ID_TYPE", BCN_ID_TYPE);
		BO.addProperty("BCN_IDNUMBER", BCN_IDNUMBER);
		BO.addProperty("BCN_DATE_RANGE", BCN_DATE_RANGE);
		BO.addProperty("BCN_ISSUED_BY", BCN_ISSUED_BY);
		
		String BCN_PER_ADDRESS = null;
		String BCN_CONT_ADDRESS = null;
		if(TT_LIENLAC_BEN_CN!= null && !TT_LIENLAC_BEN_CN.isEmpty() && TT_LIENLAC_BEN_CN.size() > 0 ){

			BCN_PER_ADDRESS = AddressHelper.getMergerAddress(
					TT_LIENLAC_BEN_CN, "HKTHUONGTRU", this.processDataSource);
			BCN_CONT_ADDRESS = AddressHelper.getMergerAddress(
					TT_LIENLAC_BEN_CN, "DCLIENHE", this.processDataSource);
		}
		BO.addProperty("BCN_PER_ADDRESS", BCN_PER_ADDRESS);
		BO.addProperty("BCN_CONT_ADDRESS", BCN_CONT_ADDRESS);
	
		JsonArray TT_DINHDANH_DONGCN = null;
		JsonArray TT_LIENLAC_DONGCN  = null;
		if(TT_DONGCN != null && !TT_DONGCN.isEmpty()){
			TT_DINHDANH_DONGCN = TT_DONGCN.getAsJsonArray("TT_DINHDANH"); 
			TT_LIENLAC_DONGCN = TT_DONGCN.getAsJsonArray("TT_LIENLAC");
		}
		String DCN_ID_TYPE = null;
		String DCN_IDNUMBER = null;
		String DCN_DATE_RANGE = null;
		String DCN_ISSUED_BY = null;
		

		if(TT_DINHDANH_DONGCN != null && !TT_DINHDANH_DONGCN.isEmpty() && TT_DINHDANH_DONGCN.size() > 0){
			for (JsonElement ttdd : TT_DINHDANH_DONGCN) {
				
					DCN_ID_TYPE = (ttdd.getAsJsonObject().has("LOAI_ID") && !ttdd.getAsJsonObject().get("LOAI_ID").isJsonNull()) ? convertMasterdataCodeToName("LOAIID",ttdd.getAsJsonObject().get("LOAI_ID").getAsString() ): null;
					DCN_IDNUMBER = (ttdd.getAsJsonObject().has("SO_ID")&& !ttdd.getAsJsonObject().get("SO_ID").isJsonNull())? ttdd.getAsJsonObject().get("SO_ID").getAsString() : null;
					try {
						DCN_DATE_RANGE = DateHelper.convertDateString((ttdd.getAsJsonObject().has("NGAY_CAP") && !ttdd.getAsJsonObject().get("NGAY_CAP").isJsonNull()) ? ttdd.getAsJsonObject().get("NGAY_CAP").getAsString() : null,"dd-MM-YYY");
					} catch (ParseException e) {
						throw new SIException(Error.EX_99, "Error in convertBpmDateString", e);	
					}
					DCN_ISSUED_BY = convertMasterdataCodeToName("NOICAPID",(ttdd.getAsJsonObject().has("NOI_CAP")&& !ttdd.getAsJsonObject().get("NOI_CAP").isJsonNull()) ? ttdd.getAsJsonObject().get("NOI_CAP").getAsString() : null);
					break;
				
			}
		}
		BO.addProperty("DCN_ID_TYPE", DCN_ID_TYPE);
		BO.addProperty("DCN_IDNUMBER", DCN_IDNUMBER);
		BO.addProperty("DCN_DATE_RANGE", DCN_DATE_RANGE);
		BO.addProperty("DCN_ISSUED_BY", DCN_ISSUED_BY);
		

		String DCN_PER_ADDRESS = null;
		String DCN_CONT_ADDRESS = null;
		if(TT_LIENLAC_DONGCN != null && !TT_LIENLAC_DONGCN.isEmpty() && TT_LIENLAC_DONGCN.size() > 0 ){

			DCN_PER_ADDRESS = AddressHelper.getMergerAddress(
					TT_LIENLAC_DONGCN, "HKTHUONGTRU", this.processDataSource);
			DCN_CONT_ADDRESS = AddressHelper.getMergerAddress(
					TT_LIENLAC_DONGCN, "DCLIENHE", this.processDataSource);
		}
		BO.addProperty("DCN_PER_ADDRESS", DCN_PER_ADDRESS);
		BO.addProperty("DCN_CONT_ADDRESS", DCN_CONT_ADDRESS);
		String LOTTERY_MAP_ADDRESS = null;
		if(DS_BDS != null && !DS_BDS.isEmpty() && DS_BDS.size() >0){
			JsonObject  QSSD = DS_BDS.get(0).getAsJsonObject().getAsJsonObject("QSSD");
			
			String THUA_DAT_SO = (QSSD.has("THUA_DAT_SO") && !QSSD.get("THUA_DAT_SO").isJsonNull())? QSSD.get("THUA_DAT_SO").getAsString() : null;
			String BAN_DO_SO = (QSSD.has("BAN_DO_SO") && !QSSD.get("BAN_DO_SO").isJsonNull())? QSSD.get("BAN_DO_SO").getAsString() : null;
			String DIA_CHI = (QSSD.has("DIA_CHI")&& !QSSD.get("DIA_CHI").isJsonNull()) ? QSSD.get("DIA_CHI").getAsString() : null;
			LOTTERY_MAP_ADDRESS = String.format("thửa đất số:%s, tờ bản đồ số:%s, địa chỉ thửa đất:%s", THUA_DAT_SO,BAN_DO_SO,DIA_CHI);
		}
		BO.addProperty("LOTTERY_MAP_ADDRESS", LOTTERY_MAP_ADDRESS);
		BO.addProperty("CERTIFICATE_NUMBER", "null");
		BO.addProperty("PROPERTY_DOC_NUMBER", "null");
		BO.addProperty("RELATED_DOCUMENTS", "null");
		JsonObject HDCN_BDS = null;

		// XỬ LẠI
		//dependentRequired
		JsonObject dependentRequiredOBJ = null;
		String CODE_PROPERTY = null;
		if(super.data.getAsJsonObject().has("dependentRequired") && !super.data.getAsJsonObject().get("dependentRequired").isJsonNull()){
			dependentRequiredOBJ = new Gson().fromJson(super.data.getAsJsonObject().get("dependentRequired").getAsString(),JsonObject.class);
			if(dependentRequiredOBJ != null && !dependentRequiredOBJ.isEmpty()){
				CODE_PROPERTY = dependentRequiredOBJ.has("CODE_PROPERTY") && !dependentRequiredOBJ.get("CODE_PROPERTY").isJsonNull() ? dependentRequiredOBJ.get("CODE_PROPERTY").getAsString() : null;
			}
		}
	
		if(DS_QTS != null && !DS_QTS.isEmpty() && DS_QTS.size() > 0 && CODE_PROPERTY != null){
			for (JsonElement e : DS_QTS) {
				JsonObject QTS =e.getAsJsonObject();
				if(QTS.has("ID_TS") && !QTS.get("ID_TS").isJsonNull() && CODE_PROPERTY.equalsIgnoreCase(QTS.get("ID_TS").getAsString())){
					HDCN_BDS = QTS.has("HDCN_BDS") ? QTS.getAsJsonObject("HDCN_BDS") : null;
					
					break;
				}
			}
		}

		if(HDCN_BDS != null && !HDCN_BDS.isEmpty()){
			String BDS_CONTRACT = HDCN_BDS.has("HD_SO")&& !HDCN_BDS.get("HD_SO").isJsonNull() ? HDCN_BDS.get("HD_SO").getAsString() : null;
			BO.addProperty("BDS_CONTRACT", BDS_CONTRACT);
			String  GENERAL_DESCRIPTION = HDCN_BDS.has("MOTA_KHAIQUAT")&& ! HDCN_BDS.get("MOTA_KHAIQUAT").isJsonNull() ? HDCN_BDS.get("MOTA_KHAIQUAT").getAsString() : null;
			BO.addProperty("GENERAL_DESCRIPTION", GENERAL_DESCRIPTION);
			String TIEN_THANHTOAN = (HDCN_BDS.has("TIEN_THANHTOAN")&& ! HDCN_BDS.get("TIEN_THANHTOAN").isJsonNull())? HDCN_BDS.get("TIEN_THANHTOAN").getAsString() : null;
			BO.addProperty("BY_BDS_NOTARIZED_MONEY", MoneyHelper.readMoneyToText(TIEN_THANHTOAN));
	
			try {
				String BDS_CONTRACT_DATE = DateHelper.convertDateString((HDCN_BDS.has("NGAY") && !HDCN_BDS.get("NGAY").isJsonNull()) ?HDCN_BDS.get("NGAY").getAsString() : null,"dd-MM-YYY");
				BO.addProperty("BDS_CONTRACT_DATE", BDS_CONTRACT_DATE);
				String BDS_CONTRACT_SITE_DATE = DateHelper.convertDateString((HDCN_BDS.has("NGAY_CONG_CHUNG") && !HDCN_BDS.get("NGAY_CONG_CHUNG").isJsonNull()) ?HDCN_BDS.get("NGAY_CONG_CHUNG").getAsString() : null,"dd-MM-YYY");
				BO.addProperty("BDS_CONTRACT_SITE_DATE", BDS_CONTRACT_SITE_DATE);
				String BDS_NOTARIZED_DATE =  DateHelper.convertDateString((HDCN_BDS.has("NGAY_THOA_THUAN") && !HDCN_BDS.get("NGAY_THOA_THUAN").isJsonNull()) ?HDCN_BDS.get("NGAY_THOA_THUAN").getAsString() : null,"dd-MM-YYY");
				BO.addProperty("BDS_NOTARIZED_DATE", BDS_NOTARIZED_DATE);
				String DBS_FROM_DATE = DateHelper.convertDateString((HDCN_BDS.has("TG_HTTT_TU") && !HDCN_BDS.get("TG_HTTT_TU").isJsonNull()) ?HDCN_BDS.get("TG_HTTT_TU").getAsString() : null,"dd-MM-YYY");
				BO.addProperty("DBS_FROM_DATE", DBS_FROM_DATE);
				String BDS_TO_DTAE  = DateHelper.convertDateString((HDCN_BDS.has("TG_HTTT_DEN") && !HDCN_BDS.get("TG_HTTT_DEN").isJsonNull()) ?HDCN_BDS.get("TG_HTTT_DEN").getAsString() : null,"dd-MM-YYY");
				BO.addProperty("BDS_TO_DTAE", BDS_TO_DTAE);
			} catch (ParseException e) {
				throw new SIException(Error.EX_99, "Error in convertBpmDateString", e);	
			}
			String BDS_CONTRACT_SITE = (HDCN_BDS.has("CONG_CHUNG_TAI")&& ! HDCN_BDS.get("CONG_CHUNG_TAI").isJsonNull())? HDCN_BDS.get("CONG_CHUNG_TAI").getAsString() : null;
			BO.addProperty("BDS_CONTRACT_SITE", BDS_CONTRACT_SITE);
			String BDS_NOTARIZED_NUMBER = (HDCN_BDS.has("SO_CONG_CHUNG")&& ! HDCN_BDS.get("SO_CONG_CHUNG").isJsonNull())? HDCN_BDS.get("SO_CONG_CHUNG").getAsString() : null;
			BO.addProperty("BDS_NOTARIZED_NUMBER", BDS_NOTARIZED_NUMBER);
			String BDS_NOTARIZED_MONEY = (HDCN_BDS.has("TIEN_THANHTOAN")&& ! HDCN_BDS.get("TIEN_THANHTOAN").isJsonNull())? HDCN_BDS.get("TIEN_THANHTOAN").getAsString() : null;
			BO.addProperty("BDS_NOTARIZED_MONEY", BDS_NOTARIZED_MONEY);
			String BY_BDS_NOTARIZED_MONEY = MoneyHelper.readMoneyToText(BDS_NOTARIZED_MONEY);
			BO.addProperty("BY_BDS_NOTARIZED_MONEY", BY_BDS_NOTARIZED_MONEY);
			String BDS_MONEY =  (HDCN_BDS.has("TIEN_VAY")&& ! HDCN_BDS.get("TIEN_VAY").isJsonNull())? HDCN_BDS.get("TIEN_VAY").getAsString() : null;
			BO.addProperty("BDS_MONEY", BDS_MONEY);
			String BY_BDS_MONEY = MoneyHelper.readMoneyToText(BDS_MONEY);
			BO.addProperty("BY_BDS_MONEY", BY_BDS_MONEY);
			
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
		JsonArray inputData = super.data.getAsJsonObject().get(Gendoc.inpuData)
				.getAsJsonArray();
		// convert InputData to JSON String
		super.data.getAsJsonObject().addProperty(Gendoc.inpuData,
				inputData.toString());
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;

	}

}
