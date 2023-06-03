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
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class BM79 extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public BM79(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log,String dependentRequired) {
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
		
		JsonObject TT_SOANTHAO =  BO.has("TT_SOANTHAO") ? BO.get("TT_SOANTHAO").getAsJsonObject() : null;
		JsonObject TT_CTD_EXIM = null;
		JsonObject TT_CTD_KH  = null;
		if(TT_SOANTHAO != null && !TT_SOANTHAO.isEmpty()){
			
			TT_CTD_EXIM = (TT_SOANTHAO.has("TT_CTD_EXIM")) ? TT_SOANTHAO.get("TT_CTD_EXIM")
					.getAsJsonObject() : null;
					TT_CTD_KH = TT_SOANTHAO.get("TT_CTD_KH").getAsJsonObject();
		}
		JsonObject TT_YC_HDTSBD = null;
		SupportBMHelper support = new SupportBMHelper(super.mapper,
				super.processDataSource);
		TT_YC_HDTSBD = support
				.getObjectByDepedenRequired(BO, dependentRequired);	
				JsonObject TT_BEN_BD = null;
				String LOAN = null;
		if (TT_YC_HDTSBD != null && !TT_YC_HDTSBD.isEmpty()) {

			TT_BEN_BD = (TT_YC_HDTSBD.has("TT_BEN_BD")) ? TT_YC_HDTSBD.get("TT_BEN_BD").getAsJsonObject() : null;
			LOAN = (TT_YC_HDTSBD.has("SOTIEN_CTD") && !TT_YC_HDTSBD.get("SOTIEN_CTD").isJsonNull()) ?  TT_YC_HDTSBD.get("SOTIEN_CTD").getAsString() : null;
		}
		BO.addProperty("LOAN", LOAN);
		JsonObject TT_NGUOIHONPHOI = null;
		JsonObject KH_CN = null;
		JsonObject KH_DN = null;
		JsonArray TT_LIENLAC = null;
		if (TT_BEN_BD!= null && !TT_BEN_BD.isEmpty()) {
			KH_CN = TT_BEN_BD.getAsJsonObject("KH_CN");
			KH_DN = (TT_BEN_BD.has("KH_DN"))? TT_BEN_BD.getAsJsonObject("KH_DN") : null;
			TT_NGUOIHONPHOI = TT_BEN_BD.getAsJsonObject("TT_NGUOIHONPHOI");
			KH_DN = (TT_BEN_BD.has("KH_DN"))? TT_BEN_BD.getAsJsonObject("KH_DN") : null;
			TT_LIENLAC = (TT_BEN_BD.has("TT_LIENLAC")) ? TT_BEN_BD.getAsJsonArray("TT_LIENLAC") : null;
		}
			String CHUC_VU = null;
			String BTC_DN_OFFICE_ADDRESS = null;
			String BTC_DNT_BUSINESSES_NUMBER = null;
			String BTC_DN_BUSINESSES_NUMBER_DATE = null;
			String BTC_DN_BUSINESSES_NUMBER_ISSUED_BY = null;
			String BTC_DN_PHONE_NUMBER = null;
			String BTC_DN_GRANDPARENTS = null;
			String BTC_DN_FULLNAME_ENTERPRISE = null;
			String TEN_KH_CODAU= "";
			String BTC_DN_AUTHOR_NUMBER = null;
		if(KH_DN != null && !KH_DN.isEmpty()){
			BTC_DN_OFFICE_ADDRESS = (KH_DN.has("DIACHI") && !KH_DN.get("DIACHI").isJsonNull()) ? KH_DN.get("DIACHI").getAsString() : null;
			TEN_KH_CODAU = (KH_DN.has("TEN_KH_CODAU") && !KH_DN.get("TEN_KH_CODAU").isJsonNull()) ? KH_DN.get("TEN_KH_CODAU").getAsString() : null ;
			CHUC_VU = (KH_DN.has("CHUC_VU") && !KH_DN.get("CHUC_VU").isJsonNull()) ? KH_DN.get("CHUC_VU").getAsString() : null;
			BTC_DNT_BUSINESSES_NUMBER = ( KH_DN.has("MASO_DN") && !KH_DN.get("MASO_DN").isJsonNull())? KH_DN.get("MASO_DN").getAsString() : null;
			BTC_DN_BUSINESSES_NUMBER_ISSUED_BY = convertMasterdataCodeToName("NOICAPID",(KH_DN.has("NOI_CAP") && ! KH_DN.get("NOI_CAP").isJsonNull()) ? KH_DN.get("NOI_CAP").getAsString() : null);
			try {
				BTC_DN_BUSINESSES_NUMBER_DATE = DateHelper.convertDateString( (KH_DN.has("NGAY_CAP") && !KH_DN.get("NGAY_CAP").isJsonNull()) ? KH_DN.get("NGAY_CAP").getAsString() : null,"dd-MM-YYY");
			} catch (ParseException e) {
				throw new SIException(Error.EX_99,
						"Error in convertBpmDateString", e);
			}
			BTC_DN_PHONE_NUMBER = (KH_DN.has("SO_DT") && !KH_DN.get("SO_DT").isJsonNull()) ? KH_DN.get("SO_DT").getAsString() : null;
			BTC_DN_GRANDPARENTS = convertMasterdataCodeToName("ONGBA",(KH_DN.has("ONG_BA") && !KH_DN.get("ONG_BA").isJsonNull()) ? KH_DN.get("ONG_BA").getAsString() : null);
			BTC_DN_FULLNAME_ENTERPRISE = (KH_DN.has("NGDAIDIEN_KYHD")&&!KH_DN.get("NGDAIDIEN_KYHD").isJsonNull())?	KH_DN.get("NGDAIDIEN_KYHD").getAsString() :null;
			BTC_DN_AUTHOR_NUMBER = (KH_DN.has("DAIDIEN_KH")&&!KH_DN.get("DAIDIEN_KH").isJsonNull())?KH_DN.get("DAIDIEN_KH").getAsString() : null;
		}
		BO.addProperty("BTC_DN_COMPANY", TEN_KH_CODAU != null ?TEN_KH_CODAU.toUpperCase() : null);
		BO.addProperty("BTC_DN_FULLNAME_ENTERPRISE", BTC_DN_FULLNAME_ENTERPRISE);
		BO.addProperty("BTC_DN_GRANDPARENTS", BTC_DN_GRANDPARENTS);
		BO.addProperty("BTC_DN_PHONE_NUMBER", BTC_DN_PHONE_NUMBER);
		BO.addProperty("BTC_DN_OFFICE_ADDRESS", BTC_DN_OFFICE_ADDRESS);
		BO.addProperty("BTC_DNT_BUSINESSES_NUMBER", BTC_DNT_BUSINESSES_NUMBER);
		BO.addProperty("BTC_DN_BUSINESSES_NUMBER_ISSUED_BY", BTC_DN_BUSINESSES_NUMBER_ISSUED_BY);
		BO.addProperty("BTC_DN_BUSINESSES_NUMBER_DATE", BTC_DN_BUSINESSES_NUMBER_DATE);
		BO.addProperty("BTC_DN_AUTHOR_NUMBER", BTC_DN_AUTHOR_NUMBER);
		BO.addProperty("BTC_DN_POSITION_ENTERPRISE",
				convertMasterdataCodeToName("CHUCVU", CHUC_VU));
		String BTC_CN_GRANDPARENTS = null;
		String BTC_CN_FULLNAME = null;
		String BTC_CN_BIRTH = null;
		String BTC_CN_PHONE_NUMBER = null;
		if(KH_CN != null && !KH_CN.isEmpty()){
			BTC_CN_GRANDPARENTS = convertMasterdataCodeToName("ONGBA",(KH_CN.has("ONG_BA") && !KH_CN.get("ONG_BA").isJsonNull()) ?KH_CN.get("ONG_BA").getAsString() : null);
			BTC_CN_FULLNAME  =(KH_CN.has("TEN_KH_CODAU") && !KH_CN.get("TEN_KH_CODAU").isJsonNull()) ?KH_CN.get("TEN_KH_CODAU").getAsString() : null;
			try {
				BTC_CN_BIRTH = DateHelper.convertDateString((KH_CN.has("NAM_SINH") && !KH_CN.get("NAM_SINH").isJsonNull()) ? KH_CN.get("NAM_SINH").getAsString() : null,"dd-MM-YYY");
			} catch (ParseException e) {
				throw new SIException(Error.EX_99,
						"Error in convertBpmDateString", e);
			}
			BTC_CN_PHONE_NUMBER=  (KH_CN.has("SO_DT") && !KH_CN.get("SO_DT").isJsonNull()) ? KH_CN.get("SO_DT").getAsString() : null;
		}
		BO.addProperty("BTC_CN_BIRTH", BTC_CN_BIRTH);
		BO.addProperty("BTC_CN_GRANDPARENTS", BTC_CN_GRANDPARENTS);
		BO.addProperty("BTC_CN_FULLNAME", BTC_CN_FULLNAME);
		BO.addProperty("BTC_CN_PHONE_NUMBER", BTC_CN_PHONE_NUMBER);

//		#.data.NHAO_BUSINESSES_NUMBER
//		#.data.NHAO_OFFICE_ADDRESS
//		#.data.NHAO_FULLNAME
//		#.data.NHAO_POSITION
//		#.data.NHAO_AUTHOR_NUMBER
//		#.data.NHAO_AUTHOR_NUMBER_DATE
		BO.addProperty("NHAO_BUSINESSES_NUMBER", "");
		BO.addProperty("NHAO_OFFICE_ADDRESS", "");
		BO.addProperty("NHAO_FULLNAME", "");
		BO.addProperty("NHAO_POSITION", "");
		BO.addProperty("NHAO_AUTHOR_NUMBER", ""); 
		BO.addProperty("NHAO_AUTHOR_NUMBER_DATE", ""); 
		if(TT_CTD_EXIM != null && !TT_CTD_EXIM.isEmpty()){
			
			String BANK_OFFICE_ADDRESS = (TT_CTD_EXIM.has("DIACHI") && !TT_CTD_EXIM.get("DIACHI").isJsonNull()) ? TT_CTD_EXIM.get("DIACHI").getAsString() : null;
			BO.addProperty("BANK_OFFICE_ADDRESS", BANK_OFFICE_ADDRESS);
			String BANK_POSITION = convertMasterdataCodeToName("CHUCVU", (TT_CTD_EXIM.has("CHUC_VU") && !TT_CTD_EXIM.get("CHUC_VU").isJsonNull())?  TT_CTD_EXIM.get("CHUC_VU").getAsString() : null);
			BO.addProperty("BANK_POSITION", BANK_POSITION);
		}
		String BTC_CN_COUPLE_IDNUMBER = null;
		String BTC_CN_COUPLE_DATE_RANGE = null;
		String BTC_CN_COUPLE_ISSUED_BY = null;
		String BTC_CN_ID_TYPE = null;
		JsonArray TT_DINHDANH = null;
		if(TT_CTD_KH !=null && !TT_CTD_KH.isEmpty()){
			String TEN_KH_CODAU_CTD = (TT_CTD_KH.has("TEN_KH_CODAU")&& !TT_CTD_KH.get("TEN_KH_CODAU").isJsonNull())  ? TT_CTD_KH.get("TEN_KH_CODAU").getAsString() : null;
			String ONG_BA =  (TT_CTD_KH.has("ONG_BA") && !TT_CTD_KH.get("ONG_BA").isJsonNull()) ? convertMasterdataCodeToName("ONGBA",TT_CTD_KH.get("ONG_BA").getAsString()) : null;
			String CTD_GRANDPARENTS_FULLNAME  = String.format("%s %s",ONG_BA,TEN_KH_CODAU_CTD);
			BO.addProperty("CTD_GRANDPARENTS_FULLNAME", CTD_GRANDPARENTS_FULLNAME);
			TT_DINHDANH  = TT_CTD_KH.get("TT_DINHDANH").getAsJsonArray();
		}
		
		if (TT_DINHDANH!= null && !TT_DINHDANH.isEmpty() && TT_DINHDANH.size() > 0) {
			for (JsonElement e : TT_DINHDANH) {
				BTC_CN_ID_TYPE = (e.getAsJsonObject().has("LOAI_ID") && !e
						.getAsJsonObject().get("LOAI_ID").isJsonNull()) ? e
						.getAsJsonObject().get("LOAI_ID").getAsString() : null;
					BTC_CN_COUPLE_IDNUMBER =  (e.getAsJsonObject().has("SO_ID")&&!e.getAsJsonObject().get("SO_ID").isJsonNull()) ? e.getAsJsonObject().get("SO_ID")
							.getAsString() : null;
					BTC_CN_COUPLE_ISSUED_BY = convertMasterdataCodeToName("NOICAPID",(e.getAsJsonObject()
							.has("NOI_CAP") && !e.getAsJsonObject()
							.get("NOI_CAP").isJsonNull())?  e.getAsJsonObject()
							.get("NOI_CAP").getAsString() : null);
					try {
						BTC_CN_COUPLE_DATE_RANGE = DateHelper
								.convertDateString(
										(e.getAsJsonObject().has("NGAY_CAP")&&! e.getAsJsonObject().get("NGAY_CAP").isJsonNull()) ? e.getAsJsonObject().get("NGAY_CAP")
												.getAsString() : null, "dd-MM-YYY");
					} catch (ParseException e1) {
						throw new SIException(Error.EX_99,
								"Error in convertBpmDateString", e1);
					}
					break;
				
			}
		}

		BO.addProperty("BTC_CN_COUPLE_IDNUMBER", BTC_CN_COUPLE_IDNUMBER);
		BO.addProperty("BTC_CN_COUPLE_DATE_RANGE", BTC_CN_COUPLE_DATE_RANGE);
		BO.addProperty("BTC_CN_COUPLE_ISSUED_BY", BTC_CN_COUPLE_ISSUED_BY);
		BO.addProperty("BTC_CN_ID_TYPE", BTC_CN_ID_TYPE);
		String BTC_CN_COUPLE_PER_ADDRESS = null;
		String BTC_CN_COUPLE_CONT_ADDRESS = null;
		if (TT_LIENLAC != null && !TT_LIENLAC.isEmpty()
				&& TT_LIENLAC.size() > 0) {
			BTC_CN_COUPLE_PER_ADDRESS = AddressHelper.getMergerAddress(
					TT_LIENLAC, "HKTHUONGTRU", this.processDataSource);
			BTC_CN_COUPLE_CONT_ADDRESS = AddressHelper.getMergerAddress(
					TT_LIENLAC, "DCLIENHE", this.processDataSource);
		}
		BO.addProperty("BTC_CN_COUPLE_PER_ADDRESS", BTC_CN_COUPLE_PER_ADDRESS);
		BO.addProperty("BTC_CN_COUPLE_CONT_ADDRESS", BTC_CN_COUPLE_CONT_ADDRESS);
	
		
		JsonArray TT_DINHDANH_NHP = null;
		JsonArray TT_LIENLAC_NHP = null;
		String NHP_PHONE_NUMBER = null;
		String NHP_GRANDPARENTS = null;
		String NHP_FULLNAME_INDIVIDUAL = null;
		String NHP_BIRTHDAY = null;
		if (TT_NGUOIHONPHOI != null && !TT_NGUOIHONPHOI.isEmpty()) {
			TT_DINHDANH_NHP = TT_NGUOIHONPHOI.getAsJsonArray("TT_DINHDANH");
			TT_LIENLAC_NHP = TT_NGUOIHONPHOI.getAsJsonArray("TT_LIENLAC");
			NHP_GRANDPARENTS =convertMasterdataCodeToName("ONGBA",(TT_NGUOIHONPHOI.has("ONG_BA") && ! TT_NGUOIHONPHOI.get("ONG_BA").isJsonNull()) ? TT_NGUOIHONPHOI.get("ONG_BA").getAsString() :null);
			NHP_FULLNAME_INDIVIDUAL = (TT_NGUOIHONPHOI.has("TEN_KH_CODAU")&&!TT_NGUOIHONPHOI.get("TEN_KH_CODAU").isJsonNull()) ? TT_NGUOIHONPHOI.get("TEN_KH_CODAU").getAsString() : null;
			try {
				NHP_BIRTHDAY = DateHelper.convertDateString( (TT_NGUOIHONPHOI.has("NGAY_SINH") && !TT_NGUOIHONPHOI.get("NGAY_SINH").isJsonNull()) ? TT_NGUOIHONPHOI.get("NGAY_SINH").getAsString() : null,"dd-MM-YYY");
			} catch (ParseException e) {
				throw new SIException(Error.EX_99,
						"Error in convertBpmDateString", e);
			}
			NHP_PHONE_NUMBER = (TT_NGUOIHONPHOI.has("SO_DT")&& !TT_NGUOIHONPHOI.get("SO_DT").isJsonNull()) ? TT_NGUOIHONPHOI.get("SO_DT").getAsString() : null;
		}
		BO.addProperty("NHP_GRANDPARENTS", NHP_GRANDPARENTS);
		BO.addProperty("NHP_FULLNAME_INDIVIDUAL", NHP_FULLNAME_INDIVIDUAL);
		BO.addProperty("NHP_BIRTHDAY", NHP_BIRTHDAY);
		BO.addProperty("NHP_PHONE_NUMBER", NHP_PHONE_NUMBER);
		String NHP_ID_TYPE = null;
		String NHP_IDNUMBER = null;
		String NHP_DATE_RANGE = null;
		String NHP_ISSUED_BY = null;
		if (TT_DINHDANH_NHP != null && !TT_DINHDANH_NHP.isEmpty() && TT_DINHDANH_NHP.size() > 0) {
			for (JsonElement e : TT_DINHDANH_NHP) {
					NHP_ID_TYPE = (e.getAsJsonObject().has("LOAI_ID") && !e
							.getAsJsonObject().isJsonNull()) ? e
						.getAsJsonObject().get("LOAI_ID").getAsString() : null;
					NHP_IDNUMBER = (e.getAsJsonObject().has("SO_ID")&&!e.getAsJsonObject().get("SO_ID").isJsonNull())  ? e.getAsJsonObject().get("SO_ID")
							.getAsString() : null;
					try {
						NHP_DATE_RANGE = DateHelper.convertDateString((e
								.getAsJsonObject().has("NGAY_CAP") && !e
								.getAsJsonObject().get("NGAY_CAP").isJsonNull())? e
								.getAsJsonObject().get("NGAY_CAP")
								.getAsString() : null, "dd-MM-YYY");
					} catch (ParseException e1) {
						throw new SIException(Error.EX_99,
								"Error in convertBpmDateString", e1);
					}
					NHP_ISSUED_BY = convertMasterdataCodeToName("NOICAPID",(e.getAsJsonObject().has("NOI_CAP")&&! e.getAsJsonObject().get("NOI_CAP").isJsonNull()) ? e.getAsJsonObject().get("NOI_CAP")
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
		String MARRIAGE_SIGNING = null;
		JsonObject TT_DEXUAT = BO.getAsJsonObject("TT_DEXUAT");
		if (TT_DEXUAT != null && !TT_DEXUAT.isEmpty()) {
			MARRIAGE_SIGNING = TT_DEXUAT.has("YC_HONPHOI_KY") && !TT_DEXUAT.get("YC_HONPHOI_KY").isJsonNull() ? (TT_DEXUAT
							.get("YC_HONPHOI_KY").getAsString().equalsIgnoreCase("1") ? "CHECK"
									: "UNCHECK")
									: "UNCHECK";
		}
		BO.addProperty("MARRIAGE_SIGNING", MARRIAGE_SIGNING);
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
