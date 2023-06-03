package com.service.impl.gendoc.v1_0;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.consts.Gendoc;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.helper.SupportBMHelper;
import com.si.consts.Error;
import com.si.exception.SIException;
import com.si.helper.DateHelper;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class YCST extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public YCST(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log,String dependentRequired) {
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
//		JsonObject TT_SOANTHAO = BO.get("TT_SOANTHAO").getAsJsonObject();
		//-----------------------------------Start check-------------------------------------------
		JsonObject TT_YC_HDTINDUNG = null;
		SupportBMHelper support = new SupportBMHelper(super.mapper, super.processDataSource);
		TT_YC_HDTINDUNG = support.getObjectByDepedenRequired(BO, dependentRequired);
		//-----------------------------------end check-------------------------------------------
		String DEN_CONTACT_TYPE = null;
		String DEN_LOAN_MATURITY_DATE_1 = null;
		String DEN_LOAN_MATURITY_DATE_2 = null;

		int PTHUC_GN_TIENMAT = 0;
		int PTHUC_GN_CK = 0;
		String DEN_FROM_DATE = null;
		String DEN_ORIGINAL_PAYMENT_DATE = null;
		String DEN_INTEREST_PAYMENT_DAY = null;
		String DEN_INTEREST_RATES = null;
		String DEN_CASH = null;
		String DEN_TRANSFER = null;
		String DEN_ST_CTD = null;
		String DEN_CURRENCY = null;
		String DEN_MO_CREDIT_SUPPLY = null;
		String DEN_RULE = null;
		String DEN_OTHER_1 = null;
		String DEN_OTHER_2 = null;
		String DEN_FO_DISCHARGE = null;
		String DEN_OTHER_FEES_1 = null;
		String DEN_OTHER_FEES_2 = null;
		String DEN_VIOLATIONS = "UNCHECK";
		String DEN_RULE_ESCROW = "UNCHECK";
		String DEN_YCKH = "UNCHECK";
		String DEN_CHARGES_1 = "UNCHECK";
		String DEN_CHARGES_2 = "UNCHECK";
		String DEN_PAYMENT_AMOUNT_1 = null;
		String DEN_PAYMENT_AMOUNT_2 = null;
		String DEN_PAYMENT_AMOUNT_3 = null;
		String DEN_AT_SUGGESTION = null;
		String DEN_MO_TRANSFER = null;
		String DEN_LETTER_OF_GUARANTEE = null;
		String DEN_GUARANTEE_BENEFITS = null;
		String DEN_GUARANTEE_TIME_SMALL = null;
		String DEN_GUARANTEE_FEE = null;
		String DEN_DEPOSIT_AMOUNT = null;
		if(TT_YC_HDTINDUNG != null && !TT_YC_HDTINDUNG.isEmpty()){
			DEN_CONTACT_TYPE = convertMasterdataCodeToName("LOAIHDTD",(TT_YC_HDTINDUNG.has("LOAI_HD") && !TT_YC_HDTINDUNG.get("LOAI_HD").isJsonNull()) ? TT_YC_HDTINDUNG.get("LOAI_HD").getAsString() : null);
			DEN_LOAN_MATURITY_DATE_1 = (TT_YC_HDTINDUNG.has("THOIGIAN_CHOVAY_THANG")) ? TT_YC_HDTINDUNG.get("THOIGIAN_CHOVAY_THANG").getAsString() : null;
			DEN_LOAN_MATURITY_DATE_2 = (TT_YC_HDTINDUNG.has("THOIGIAN_CHOVAY_NGAY")) ? TT_YC_HDTINDUNG.get("THOIGIAN_CHOVAY_NGAY").getAsString() : null;
			PTHUC_GN_TIENMAT =(TT_YC_HDTINDUNG.has("PTHUC_GIAINGAN_TM") && !TT_YC_HDTINDUNG.get("PTHUC_GIAINGAN_TM").isJsonNull()) ? TT_YC_HDTINDUNG.get("PTHUC_GIAINGAN_TM").getAsInt() : 0;
			try {
				DEN_FROM_DATE = DateHelper.convertDateString((TT_YC_HDTINDUNG.has("NGAY_VAY") && !TT_YC_HDTINDUNG.get("NGAY_VAY").isJsonNull()) ?TT_YC_HDTINDUNG.get("NGAY_VAY").getAsString() : null,"dd-MM-YYY");
			} catch (ParseException e) {
				throw new SIException(Error.EX_99,
						"Error in convertBpmDateString", e);
			}
			DEN_ORIGINAL_PAYMENT_DATE = (TT_YC_HDTINDUNG.has("NGAY_TRA_GOC") && !TT_YC_HDTINDUNG.get("NGAY_TRA_GOC").isJsonNull()) ? TT_YC_HDTINDUNG.get("NGAY_TRA_GOC").getAsString() : null;
			DEN_INTEREST_PAYMENT_DAY = ( TT_YC_HDTINDUNG.has("NGAY_TRA_LAI") && !TT_YC_HDTINDUNG.get("NGAY_TRA_LAI").isJsonNull()) ? TT_YC_HDTINDUNG.get("NGAY_TRA_LAI").getAsString() : null;
			DEN_INTEREST_RATES = (TT_YC_HDTINDUNG.has("LAI_SUAT") && !TT_YC_HDTINDUNG.get("LAI_SUAT").isJsonNull()) ? TT_YC_HDTINDUNG.get("LAI_SUAT").getAsString() : null;
			PTHUC_GN_CK = (TT_YC_HDTINDUNG.has("PTHUC_GIAINGAN_CK")&& !TT_YC_HDTINDUNG.get("PTHUC_GIAINGAN_CK").isJsonNull()) ? TT_YC_HDTINDUNG.get("PTHUC_GIAINGAN_CK").getAsInt() : 0;
			DEN_ST_CTD = (TT_YC_HDTINDUNG.has("SOTIEN_CTD") && !TT_YC_HDTINDUNG.get("SOTIEN_CTD").isJsonNull()) ? TT_YC_HDTINDUNG.get("SOTIEN_CTD").getAsString() : null;
			DEN_CURRENCY = convertMasterdataCodeToName("LOAITIEN",(TT_YC_HDTINDUNG.has("LOAITIEN") && !TT_YC_HDTINDUNG.get("LOAITIEN").isJsonNull()) ? TT_YC_HDTINDUNG.get("LOAITIEN").getAsString() : null);
			DEN_MO_CREDIT_SUPPLY = convertMasterdataCodeToName("PHUONGTHUCCTD",(TT_YC_HDTINDUNG.has("PHUONGTHUC") && !TT_YC_HDTINDUNG.get("PHUONGTHUC").isJsonNull()) ? TT_YC_HDTINDUNG.get("PHUONGTHUC").getAsString() : null);
			DEN_FO_DISCHARGE = convertMasterdataCodeToName("HINHTHUCGIAINGAN", (TT_YC_HDTINDUNG.has("HINHTHUC_GIAINGAN") && !TT_YC_HDTINDUNG.get("HINHTHUC_GIAINGAN").isJsonNull()) ? TT_YC_HDTINDUNG.get("HINHTHUC_GIAINGAN").getAsString() : null);
			DEN_RULE = (TT_YC_HDTINDUNG.has("LOAI_PHI") && !TT_YC_HDTINDUNG.get("LOAI_PHI").isJsonNull()) ? TT_YC_HDTINDUNG.get("LOAI_PHI").getAsString().equalsIgnoreCase("1") ? "CHECK" : "UNCHECK" : "UNCHECK" ;
			DEN_OTHER_1 = (TT_YC_HDTINDUNG.has("LOAI_PHI") && !TT_YC_HDTINDUNG.get("LOAI_PHI").isJsonNull()) ? TT_YC_HDTINDUNG.get("LOAI_PHI").getAsString().equalsIgnoreCase("2") ? "CHECK" : "UNCHECK" : "UNCHECK";
			DEN_OTHER_2 = (TT_YC_HDTINDUNG.has("PHI_CAMKET_RUTVON_KHAC") && !TT_YC_HDTINDUNG.get("PHI_CAMKET_RUTVON_KHAC").isJsonNull()) ? TT_YC_HDTINDUNG.get("PHI_CAMKET_RUTVON_KHAC").getAsString() : null;
			DEN_OTHER_FEES_1 = (TT_YC_HDTINDUNG.has("PHI_KHAC") && !TT_YC_HDTINDUNG.get("PHI_KHAC").isJsonNull()) ? TT_YC_HDTINDUNG.get("PHI_KHAC").getAsString().equalsIgnoreCase("1") ? "CHECK" : "UNCHECK" : "UNCHECK";
			DEN_OTHER_FEES_2 = (TT_YC_HDTINDUNG.has("PHI_RUTVON_KHAC") && !TT_YC_HDTINDUNG.get("PHI_RUTVON_KHAC").isJsonNull()) ? TT_YC_HDTINDUNG.get("PHI_RUTVON_KHAC").getAsString() : null;
			String PHUONGTHUC_PHAT =(TT_YC_HDTINDUNG.has("PHUONGTHUC_PHAT") && !TT_YC_HDTINDUNG.get("PHUONGTHUC_PHAT").isJsonNull()) ? TT_YC_HDTINDUNG.get("PHUONGTHUC_PHAT").getAsString() : null;
			if("1".equalsIgnoreCase(PHUONGTHUC_PHAT)){
				DEN_RULE_ESCROW = "CHECK";
			}else if("2".equalsIgnoreCase(PHUONGTHUC_PHAT)){
				DEN_VIOLATIONS = "CHECK";
			}else if ("3".equalsIgnoreCase(PHUONGTHUC_PHAT)){
				DEN_YCKH = "CHECK";
			}
			String LOAI_YC_PHAT =(TT_YC_HDTINDUNG.has("LOAI_YC_PHAT") && !TT_YC_HDTINDUNG.get("LOAI_YC_PHAT").isJsonNull()) ? TT_YC_HDTINDUNG.get("LOAI_YC_PHAT").getAsString() : null;
			if("1".equalsIgnoreCase(LOAI_YC_PHAT)){
				DEN_CHARGES_1 = "CHECK";
			} 
			DEN_CHARGES_2 =(TT_YC_HDTINDUNG.has("MUCPHI_PHAT") && !TT_YC_HDTINDUNG.get("MUCPHI_PHAT").isJsonNull()) ? TT_YC_HDTINDUNG.get("MUCPHI_PHAT").getAsString() : null;
			String NGTAC_THUNOGOC_TH  = (TT_YC_HDTINDUNG.has("NGTAC_THUNOGOC_TH") && !TT_YC_HDTINDUNG.get("NGTAC_THUNOGOC_TH").isJsonNull()) ? TT_YC_HDTINDUNG.get("NGTAC_THUNOGOC_TH").getAsString() : null;
			if("1".equalsIgnoreCase(NGTAC_THUNOGOC_TH)){
				DEN_PAYMENT_AMOUNT_1 = "CHECK";
			}else if("2".equalsIgnoreCase(NGTAC_THUNOGOC_TH)){
				DEN_PAYMENT_AMOUNT_2= "CHECK";
			}else if("3".equalsIgnoreCase(NGTAC_THUNOGOC_TH)){
				DEN_PAYMENT_AMOUNT_3 = "CHECK";
			}else if("4".equalsIgnoreCase(NGTAC_THUNOGOC_TH)){
				DEN_AT_SUGGESTION = "CHECK";
			}
			
			DEN_CASH =(TT_YC_HDTINDUNG.has("SOTIEN_GIAINGAN_TM") && !TT_YC_HDTINDUNG.get("SOTIEN_GIAINGAN_TM").isJsonNull()) ? TT_YC_HDTINDUNG.get("SOTIEN_GIAINGAN_TM").getAsString() : null;
			JsonArray DS_PTHUC_CHUYENKHOAN = (TT_YC_HDTINDUNG.has("DS_PTHUC_CHUYENKHOAN") && !TT_YC_HDTINDUNG.get("DS_PTHUC_CHUYENKHOAN").isJsonNull()) ? TT_YC_HDTINDUNG.get("DS_PTHUC_CHUYENKHOAN").getAsJsonArray() : null;
			if(DS_PTHUC_CHUYENKHOAN != null && !DS_PTHUC_CHUYENKHOAN.isEmpty() && DS_PTHUC_CHUYENKHOAN.size() > 0){
				
				String STK_THUHUONG = "";
				String TCTD = "";
				String SOTIEN = "";
				List<String> DEN_TABLE_TRANSFER_ARR = new ArrayList<String>();
				String fm = "";
				for (JsonElement e : DS_PTHUC_CHUYENKHOAN) {
					STK_THUHUONG = e.getAsJsonObject().has("STK_THUHUONG") && !e.getAsJsonObject().get("STK_THUHUONG").isJsonNull() ?  e.getAsJsonObject().get("STK_THUHUONG").getAsString() : "";
					TCTD = convertMasterdataCodeToName("TCTD",e.getAsJsonObject().has("TCTD") && !e.getAsJsonObject().get("TCTD").isJsonNull() ?  e.getAsJsonObject().get("TCTD").getAsString() : "");
					SOTIEN = e.getAsJsonObject().has("SOTIEN") && !e.getAsJsonObject().get("SOTIEN").isJsonNull() ?  e.getAsJsonObject().get("SOTIEN").getAsString() : "";
					fm = String.format("số tài khoản: %s tại: %s, số tiền %s",STK_THUHUONG,TCTD,SOTIEN);
					DEN_TABLE_TRANSFER_ARR.add(fm);
				}
				String DEN_TABLE_TRANSFER = String.join(", ", DEN_TABLE_TRANSFER_ARR);
				BO.addProperty("DEN_TABLE_TRANSFER", DEN_TABLE_TRANSFER);
				String PTHUC_CHUYEN = DS_PTHUC_CHUYENKHOAN.get(0).getAsJsonObject().has("PTHUC_CHUYEN") &&  !DS_PTHUC_CHUYENKHOAN.get(0).getAsJsonObject().get("PTHUC_CHUYEN").isJsonNull() ? DS_PTHUC_CHUYENKHOAN.get(0).getAsJsonObject().get("PTHUC_CHUYEN").getAsString() : null;
				DEN_MO_TRANSFER = convertMasterdataCodeToName("PTCHUYENKHOAN", PTHUC_CHUYEN);
			}
			DEN_LETTER_OF_GUARANTEE = convertMasterdataCodeToName("LOAITHUBL",(TT_YC_HDTINDUNG.has("THU_BAOLANH") && !TT_YC_HDTINDUNG.get("THU_BAOLANH").isJsonNull()) ? TT_YC_HDTINDUNG.get("THU_BAOLANH").getAsString() : null);
			DEN_GUARANTEE_BENEFITS = (TT_YC_HDTINDUNG.has("NGUOI_THUHUONG_BL") && !TT_YC_HDTINDUNG.get("NGUOI_THUHUONG_BL").isJsonNull()) ? TT_YC_HDTINDUNG.get("NGUOI_THUHUONG_BL").getAsString() : null;
			DEN_GUARANTEE_TIME_SMALL = (TT_YC_HDTINDUNG.has("THOIGIAN_BAOLANH") && !TT_YC_HDTINDUNG.get("THOIGIAN_BAOLANH").isJsonNull()) ? TT_YC_HDTINDUNG.get("THOIGIAN_BAOLANH").getAsString() : null;
			DEN_GUARANTEE_FEE = (TT_YC_HDTINDUNG.has("PHI_BAOLANH") && !TT_YC_HDTINDUNG.get("PHI_BAOLANH").isJsonNull()) ? TT_YC_HDTINDUNG.get("PHI_BAOLANH").getAsString() : null;
			DEN_DEPOSIT_AMOUNT = (TT_YC_HDTINDUNG.has("TYLE_KYQUY") && !TT_YC_HDTINDUNG.get("TYLE_KYQUY").isJsonNull()) ? TT_YC_HDTINDUNG.get("TYLE_KYQUY").getAsString() : null;
		}
		BO.addProperty("DEN_DEPOSIT_AMOUNT", DEN_DEPOSIT_AMOUNT);
		BO.addProperty("DEN_GUARANTEE_FEE", DEN_GUARANTEE_FEE);
		BO.addProperty("DEN_GUARANTEE_TIME_SMALL", DEN_GUARANTEE_TIME_SMALL);
		BO.addProperty("DEN_GUARANTEE_BENEFITS", DEN_GUARANTEE_BENEFITS);
		BO.addProperty("DEN_LETTER_OF_GUARANTEE", DEN_LETTER_OF_GUARANTEE);
		BO.addProperty("DEN_MO_TRANSFER", DEN_MO_TRANSFER);
		BO.addProperty("DEN_PAYMENT_AMOUNT_1", DEN_PAYMENT_AMOUNT_1);
		BO.addProperty("DEN_PAYMENT_AMOUNT_2", DEN_PAYMENT_AMOUNT_2);
		BO.addProperty("DEN_PAYMENT_AMOUNT_3", DEN_PAYMENT_AMOUNT_3);
		BO.addProperty("DEN_AT_SUGGESTION", DEN_AT_SUGGESTION);
		BO.addProperty("DEN_CHARGES_1", DEN_CHARGES_1);
		BO.addProperty("DEN_CHARGES_2", DEN_CHARGES_2);
		BO.addProperty("DEN_RULE_ESCROW", DEN_RULE_ESCROW);
		BO.addProperty("DEN_VIOLATIONS", DEN_VIOLATIONS);
		BO.addProperty("DEN_YCKH", DEN_YCKH);
		BO.addProperty("DEN_CONTACT_TYPE", DEN_CONTACT_TYPE);
		BO.addProperty("DEN_LOAN_MATURITY_DATE_1", DEN_LOAN_MATURITY_DATE_1);
		BO.addProperty("DEN_LOAN_MATURITY_DATE_2", DEN_LOAN_MATURITY_DATE_2);
	
	
		BO.addProperty("DEN_FROM_DATE", DEN_FROM_DATE);
		BO.addProperty("DEN_ORIGINAL_PAYMENT_DATE", DEN_ORIGINAL_PAYMENT_DATE);
		BO.addProperty("DEN_INTEREST_PAYMENT_DAY", DEN_INTEREST_PAYMENT_DAY);
		BO.addProperty("DEN_INTEREST_RATES", DEN_INTEREST_RATES);
		DEN_TRANSFER = (PTHUC_GN_TIENMAT == 1) ?  "CHECK" :  "UNCHECK";
		DEN_CASH = (PTHUC_GN_CK == 1) ?  "CHECK" :  "UNCHECK";
		BO.addProperty("DEN_CASH", DEN_CASH);
		BO.addProperty("DEN_TRANSFER", DEN_TRANSFER);
		BO.addProperty("DEN_ST_CTD", DEN_ST_CTD);
		BO.addProperty("DEN_CURRENCY", DEN_CURRENCY);
		BO.addProperty("DEN_MO_CREDIT_SUPPLY", DEN_MO_CREDIT_SUPPLY);
		BO.addProperty("DEN_FO_DISCHARGE", DEN_FO_DISCHARGE);
		BO.addProperty("DEN_RULE", DEN_RULE);
		BO.addProperty("DEN_OTHER_1", DEN_OTHER_1);
		BO.addProperty("DEN_OTHER_2", DEN_OTHER_2);
		BO.addProperty("DEN_OTHER_FEES_1", DEN_OTHER_FEES_1);
		BO.addProperty("DEN_OTHER_FEES_2", DEN_OTHER_FEES_2);
		JsonObject TT_DENGHI = BO.has("TT_DENGHI") ? BO.get("TT_DENGHI").getAsJsonObject() : null;
		if(TT_DENGHI!= null && !TT_DENGHI.isEmpty()){
			JsonObject TT_CACBEN_KI_HD = TT_DENGHI.has("TT_CACBEN_KI_HD") ? TT_DENGHI.getAsJsonObject("TT_CACBEN_KI_HD") : null;
			String DEN_NHP = (TT_CACBEN_KI_HD.has("YC_HONPHOI") && !TT_CACBEN_KI_HD.get("YC_HONPHOI").isJsonNull()) ? (TT_CACBEN_KI_HD.get("YC_HONPHOI").getAsString().equalsIgnoreCase("1") ? "CHECK" : "UNCHECK") : null;
			BO.addProperty("DEN_NHP", DEN_NHP);			
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
