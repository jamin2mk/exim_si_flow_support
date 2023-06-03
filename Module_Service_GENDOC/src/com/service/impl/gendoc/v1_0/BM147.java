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

public class BM147 extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public BM147(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log,String dependentRequired) {
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
		JsonObject TT_SOANTHAO =  BO.has("TT_SOANTHAO") ? BO.get("TT_SOANTHAO").getAsJsonObject() : null;
		JsonObject TT_CTD_EXIM = null;
		JsonObject TT_CTD_KH  = null;
		if(TT_SOANTHAO != null && !TT_SOANTHAO.isEmpty()){
			TT_CTD_EXIM = TT_SOANTHAO.has("TT_CTD_EXIM") ?  TT_SOANTHAO.get("TT_CTD_EXIM").getAsJsonObject() : null;
			TT_CTD_KH = TT_SOANTHAO.has("TT_CTD_KH") ? TT_SOANTHAO.get("TT_CTD_KH").getAsJsonObject() : null;
		}
		String BANK_GRANDPARENTS= null;
		if(TT_CTD_EXIM!= null && !TT_CTD_EXIM.isEmpty()){
			String BANK_POSITION =  ( TT_CTD_EXIM.has("CHUCVU") && !TT_CTD_EXIM.get("CHUCVU").isJsonNull()) ? TT_CTD_EXIM.get("CHUCVU").getAsString() : null;
			BO.addProperty("BANK_POSITION", BANK_POSITION);
			String DVKD =  (TT_CTD_EXIM.has("DVKD") && !TT_CTD_EXIM.get("DVKD").isJsonNull()) ? TT_CTD_EXIM.get("DVKD").getAsString() : null;
			BO.addProperty("DVKD", DVKD);
			String BANK_OFFICE_ADDRESS = ( TT_CTD_EXIM.has("DIACHI") && !TT_CTD_EXIM.get("DIACHI").isJsonNull())? TT_CTD_EXIM.get("DIACHI").getAsString() : null;
			BO.addProperty("BANK_OFFICE_ADDRESS", BANK_OFFICE_ADDRESS);
			BANK_GRANDPARENTS = convertMasterdataCodeToName("ONGBA",(TT_CTD_EXIM.has("ONG_BA") && !TT_CTD_EXIM.get("ONG_BA").isJsonNull()) ?TT_CTD_EXIM.get("ONG_BA").getAsString() : null);
			BO.addProperty("BANK_GRANDPARENTS", BANK_GRANDPARENTS);
		}
		
		String CTD_CN_GRANDPARENTS = null;
		String CTD_CN_BIRTHDAY = null;
		if(TT_CTD_KH!= null && !TT_CTD_KH.isEmpty()){			
			String CTD_DN_POSITION_ENTERPRISE = convertMasterdataCodeToName("CHUCVU", ( TT_CTD_KH.has("CHUC_VU") && !TT_CTD_KH.get("CHUC_VU").isJsonNull()) ? TT_CTD_KH.get("CHUC_VU").getAsString() : null);
			BO.addProperty("CTD_DN_POSITION_ENTERPRISE", CTD_DN_POSITION_ENTERPRISE);
			String CTD_CN_IDNUMBER = null;
			String CTD_CN_DATE_RANGE = null;
			String CTD_CN_ISSUED_BY = null;
			CTD_CN_GRANDPARENTS = convertMasterdataCodeToName("ONGBA",(TT_CTD_KH.has("ONG_BA") && !TT_CTD_KH.get("ONG_BA").isJsonNull()) ?TT_CTD_KH.get("ONG_BA").getAsString() : null);
			try {
				CTD_CN_BIRTHDAY = DateHelper.convertDateString((TT_CTD_KH.getAsJsonObject().has("NGAY_SINH") && !TT_CTD_KH.getAsJsonObject().get("NGAY_SINH").isJsonNull()) ? TT_CTD_KH.getAsJsonObject().get("NGAY_SINH").getAsString() : null,"dd-MM-YYY");
			} catch (ParseException e) {
				throw new SIException(Error.EX_99, "Error in convertBpmDateString", e);	
			}
			JsonArray TT_DINHDANH = TT_CTD_KH.get("TT_DINHDANH").getAsJsonArray();
			if(TT_DINHDANH != null && !TT_DINHDANH.isEmpty() && TT_DINHDANH.size() > 0){
				for (JsonElement ttdd : TT_DINHDANH) {
						CTD_CN_IDNUMBER =  (ttdd.getAsJsonObject().has("SO_ID") && !ttdd.getAsJsonObject().get("SO_ID").isJsonNull()) ? ttdd.getAsJsonObject().get("SO_ID").getAsString() : null;
						try {
							CTD_CN_DATE_RANGE = DateHelper.convertDateString((ttdd.getAsJsonObject().has("NGAY_CAP") && !ttdd.getAsJsonObject().get("NGAY_CAP").isJsonNull()) ? ttdd.getAsJsonObject().get("NGAY_CAP").getAsString() : null,"dd-MM-YYY");
						} catch (ParseException e) {
							throw new SIException(Error.EX_99, "Error in convertBpmDateString", e);	
						}
						CTD_CN_ISSUED_BY = convertMasterdataCodeToName("NOICAPID",(ttdd.getAsJsonObject().has("NOI_CAP") && !ttdd.getAsJsonObject().get("NOI_CAP").isJsonNull()) ? ttdd.getAsJsonObject().get("NOI_CAP").getAsString() : null);
						break;
				}
			}
			BO.addProperty("CTD_CN_BIRTHDAY", CTD_CN_BIRTHDAY);
			BO.addProperty("CTD_CN_GRANDPARENTS", CTD_CN_GRANDPARENTS);
			BO.addProperty("CTD_CN_IDNUMBER", CTD_CN_IDNUMBER);
			BO.addProperty("CTD_CN_DATE_RANGE", CTD_CN_DATE_RANGE);
			BO.addProperty("CTD_CN_ISSUED_BY", CTD_CN_ISSUED_BY);
			JsonArray TT_LIENLAC = TT_CTD_KH.has("TT_LIENLAC") ? TT_CTD_KH.getAsJsonArray("TT_LIENLAC") : null;
			
			String HKTHUONGTRU = AddressHelper.getMergerAddress(TT_LIENLAC, "HKTHUONGTRU", this.processDataSource);
			BO.addProperty("CTD_CN_PER_ADDRESS", HKTHUONGTRU);
			String DCLIENHE = AddressHelper.getMergerAddress(TT_LIENLAC, "DCLIENHE", this.processDataSource);
			BO.addProperty("CTD_CN_CONT_ADDRESS", DCLIENHE);
			JsonObject TT_VC_KH = TT_CTD_KH.has("TT_VC_KH") ? TT_CTD_KH.getAsJsonObject("TT_VC_KH") : null;
			if(TT_VC_KH != null && !TT_VC_KH.isEmpty()){
				JsonArray TT_DINHDANH_VC = TT_VC_KH.getAsJsonArray("TT_DINHDANH");
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
						NDV_CN_ISSUED_BY = convertMasterdataCodeToName("NOICAPID",( ttdd.getAsJsonObject().has("NOI_CAP") && !ttdd.getAsJsonObject().get("NOI_CAP").isJsonNull()) ? ttdd.getAsJsonObject().get("NOI_CAP").getAsString() : null);
						break;
					}
				}
				JsonArray TT_DIACHI = TT_VC_KH.has("TT_DIACHI") ? TT_VC_KH.getAsJsonArray("TT_VC_KH") : null;
				BO.addProperty("NDV_PER_ADDRESS ", AddressHelper.getMergerAddress(TT_DIACHI, "HKTHUONGTRU", this.processDataSource));
				BO.addProperty("NDV_CONT_ADDRESS", AddressHelper.getMergerAddress(TT_DIACHI, "DCLIENHE", this.processDataSource));
				BO.addProperty("NDV_IDNUMBER", NDV_IDNUMBER);
				BO.addProperty("NDV_DATE_RANGE", NDV_DATE_RANGE);
				BO.addProperty("NDV_CN_ISSUED_BY", NDV_CN_ISSUED_BY);
			}
		}
	
//		JsonObject TT_DEXUAT = BO.has("TT_DEXUAT") ?  BO.get("TT_DEXUAT").getAsJsonObject() : null;
		if(TT_DENGHI!= null && !TT_DENGHI.isEmpty()){
			JsonObject TT_CACBEN_KI_HD = TT_DENGHI.has("TT_CACBEN_KI_HD") ? TT_DENGHI.getAsJsonObject("TT_CACBEN_KI_HD") : null;
			String YC_HONPHOI_KY = (TT_CACBEN_KI_HD.has("YC_HONPHOI") && !TT_CACBEN_KI_HD.get("YC_HONPHOI").isJsonNull()) ? (TT_CACBEN_KI_HD.get("YC_HONPHOI").getAsString().equalsIgnoreCase("1") ? "CHECK" : "UNCHECK") : null;
			BO.addProperty("IS_MARRY", YC_HONPHOI_KY);			
		}
	
		JsonObject TT_YC_HDTINDUNG = null;
		SupportBMHelper support = new SupportBMHelper(super.mapper,
				super.processDataSource);
		TT_YC_HDTINDUNG = support
				.getObjectByDepedenRequired(BO, dependentRequired);	
		String BY_MONEY_LOAN = null;
		String DV_BANHANH = null;
		JsonArray TT_DINHDANH_NTH = null;
		String TGT_TS_TT = null;
		String CURRENCY = null;
		String DESCRIPTION_IF_YES = null;
		String TIME_OF_USE_LIMIT = null;
		String BLTL_DESCRIBE = null;
		String LOAN= null;
		if(TT_YC_HDTINDUNG != null && !TT_YC_HDTINDUNG.isEmpty() && TT_YC_HDTINDUNG.size() > 0){
			
			BY_MONEY_LOAN = MoneyHelper.readMoneyToText((TT_YC_HDTINDUNG.has("SOTIEN_CTD") && !TT_YC_HDTINDUNG.get("SOTIEN_CTD").isJsonNull()) ?TT_YC_HDTINDUNG.get("SOTIEN_CTD").getAsString() : null);
			DV_BANHANH = (TT_YC_HDTINDUNG.has("DV_BANHANH")&&!TT_YC_HDTINDUNG.get("DV_BANHANH").isJsonNull() ) ? TT_YC_HDTINDUNG.get("DV_BANHANH").getAsString() : null;
			TT_DINHDANH_NTH = TT_YC_HDTINDUNG.getAsJsonArray("TT_DINHDANH_NTH");
			TGT_TS_TT = (TT_YC_HDTINDUNG.getAsJsonObject().has("TGT_TS_TT") && !TT_YC_HDTINDUNG.getAsJsonObject().get("TGT_TS_TT").isJsonNull()) ?TT_YC_HDTINDUNG.getAsJsonObject().get("TGT_TS_TT").getAsString() : null;
			CURRENCY = (TT_YC_HDTINDUNG.has("LOAITIEN") && !TT_YC_HDTINDUNG.get("LOAITIEN").isJsonNull()) ? TT_YC_HDTINDUNG.get("LOAITIEN").getAsString() : null;
			DESCRIPTION_IF_YES = (TT_YC_HDTINDUNG.has("MOTA") && !TT_YC_HDTINDUNG.get("MOTA").isJsonNull()) ? TT_YC_HDTINDUNG.get("MOTA").getAsString() : null;
			TIME_OF_USE_LIMIT = (TT_YC_HDTINDUNG.has("THOIHAN_BL_HANMUC") && !TT_YC_HDTINDUNG.get("THOIHAN_BL_HANMUC").isJsonNull()) ? TT_YC_HDTINDUNG.get("THOIHAN_BL_HANMUC").getAsString() : null;
			BLTL_DESCRIBE = (TT_YC_HDTINDUNG.has("MOTA_TSBD") && !TT_YC_HDTINDUNG.get("MOTA_TSBD").isJsonNull()) ? TT_YC_HDTINDUNG.get("MOTA_TSBD").getAsString() : null;
			LOAN = (TT_YC_HDTINDUNG.has("SOTIEN_CTD") && ! TT_YC_HDTINDUNG.get("SOTIEN_CTD").isJsonNull()) ? TT_YC_HDTINDUNG.get("SOTIEN_CTD").getAsString() : null;
		}
		BO.addProperty("LOAN", LOAN);
		BO.addProperty("BLTL_DESCRIBE", BLTL_DESCRIBE);
		BO.addProperty("TIME_OF_USE_LIMIT", TIME_OF_USE_LIMIT);
		BO.addProperty("DESCRIPTION_IF_YES", DESCRIPTION_IF_YES);
		BO.addProperty("CURRENCY", CURRENCY);
		BO.addProperty("BY_MONEY_LOAN", BY_MONEY_LOAN);
		String BLTL_LETTER_1 = (DV_BANHANH != null && DV_BANHANH.equalsIgnoreCase("DVBANHANHMAU01")) ? "CHECK" : "UNCHECK";
		String BLTL_LETTER_2 = (DV_BANHANH != null && DV_BANHANH.equalsIgnoreCase("DVBANHANHMAU02")) ? "CHECK" : "UNCHECK";
		BO.addProperty("BLTL_LETTER_1", BLTL_LETTER_1);
		BO.addProperty("BLTL_LETTER_2", BLTL_LETTER_2);
		String BLTL_NUMBER_ID = null;
		if(TT_DINHDANH_NTH != null && !TT_DINHDANH_NTH.isEmpty() && TT_DINHDANH_NTH.size() > 0){
			for (JsonElement ttdd : TT_DINHDANH_NTH) {
					BLTL_NUMBER_ID = (ttdd.getAsJsonObject().has("SO_ID") && !ttdd.getAsJsonObject().get("SO_ID").isJsonNull()) ? ttdd.getAsJsonObject().get("SO_ID").getAsString() : null;
					break;
			}
		}
		BO.addProperty("BLTL_NUMBER_ID", BLTL_NUMBER_ID);
		BO.addProperty("BLTL_BY_SUM_TSTT", MoneyHelper.readMoneyToText(TGT_TS_TT));
		
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
