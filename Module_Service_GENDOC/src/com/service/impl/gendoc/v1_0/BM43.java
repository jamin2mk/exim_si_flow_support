package com.service.impl.gendoc.v1_0;

import com.consts.Gendoc;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.helper.LGIssuesHandling;
import com.si.exception.SIException;
import com.si.helper.DateHelper;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class BM43 extends SI_Service {
	
	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	
	public BM43(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
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
		
		// STARTING CHECKING MASTERDATA
		// ENDING CHECKING MASTERDATA
		
		// STARTING HANDLING MULTIPLE TEMPLATES
		if(dependentRequired!=null || "".equals(dependentRequired)){
			LGIssuesHandling.RequiredContactType(super.data.getAsJsonObject(), dependentRequired, "ID_HD");
		}
		// ENDING HANDLING MULTIPLE TEMPLATES
		
		// CONFIGURATING AVIABLE PATH
		JsonObject TT_SOANTHAO = BO.get("TT_SOANTHAO").getAsJsonObject();
		JsonArray TT_YC_HDTSBD = TT_SOANTHAO.get("TT_YC_HDTSBD").getAsJsonArray();
		JsonObject TT_BEN_BD = null;
		JsonArray TT_LIENLAC_CN = new JsonArray();
		JsonArray TT_LIENLAC_NHP = new JsonArray();
		
		if(!TT_YC_HDTSBD.isEmpty()) {
			TT_BEN_BD = TT_YC_HDTSBD.get(0).getAsJsonObject().get("TT_BEN_BD").getAsJsonObject();
			TT_LIENLAC_CN = TT_BEN_BD.has("TT_LIENLAC") ? TT_BEN_BD.get("TT_LIENLAC").getAsJsonArray() : null;
			TT_LIENLAC_NHP = TT_BEN_BD.has("TT_NGUOIHONPHOI") ? TT_BEN_BD.get("TT_NGUOIHONPHOI").getAsJsonObject().get("TT_LIENLAC").getAsJsonArray() : null;
		}
		
		// STARTING HANDLING CONTACT INFOMATION
		/* HOP DONG TAI SAN BAO DAM CA NHAN */
		if (!TT_LIENLAC_CN.isEmpty()) {
			LGIssuesHandling.GetAddressInfo(TT_LIENLAC_CN, BO, this.processDataSource, "BTC_CN_COUPLE_PER_ADDRESS", "BTC_CN_COUPLE_CONT_ADDRESS");
		} 
		else {
			BO.addProperty("BTC_CN_COUPLE_PER_ADDRESS", "");
			BO.addProperty("BTC_CN_COUPLE_CONT_ADDRESS", "");
		}
		
		/* HOP DONG TAI SAN BAO DAM NGUOI HON PHOI */
		if (!TT_LIENLAC_NHP.isEmpty()) {
			LGIssuesHandling.GetAddressInfo(TT_LIENLAC_NHP, BO, this.processDataSource, "NHP_PER_ADDRESS", "NHP_CONT_ADDRESS");
		} 
		else {
			BO.addProperty("NHP_PER_ADDRESS", "");
			BO.addProperty("NHP_CONT_ADDRESS", "");
		}
		// ENDING HANDLING CONTACT INFOMATION
		
		// STARTING HANDLING HDBD_NO_INFO
		if(!TT_YC_HDTSBD.isEmpty() && TT_YC_HDTSBD.get(0).getAsJsonObject().has("DS_HDTD_SO")) {
			JsonArray DS_HDTD_SO = TT_YC_HDTSBD.get(0).getAsJsonObject().get("DS_HDTD_SO").getAsJsonArray();
			JsonArray tempInfo = new JsonArray();
			
			for(JsonElement beInfoEle : DS_HDTD_SO) {
				String HDBD_CONTRACT_TYPE = beInfoEle.getAsJsonObject().get("LOAI_HD").getAsString();
				String HDBD_NO_CONTRACT = beInfoEle.getAsJsonObject().get("SO").getAsString();
				String HDBD_CONTRACT_DATE = beInfoEle.getAsJsonObject().get("NGAY").getAsString();
				
				tempInfo.add(String.format("%s số %s ngày %s và các văn bản, sửa đổi bổ sung của Hợp đồng này"
						, HDBD_CONTRACT_TYPE
						, HDBD_NO_CONTRACT
						, HDBD_CONTRACT_DATE));			
			}
			if(!tempInfo.isEmpty()) {
				StringBuilder strValueContactInfo = new StringBuilder("" + tempInfo.getAsJsonArray().get(0).getAsString());
				for(int i=1; i< tempInfo.size(); i++) {
					strValueContactInfo.append("\n- " + tempInfo.getAsJsonArray().get(i).getAsString());
				}		
				BO.addProperty("HDBD_NO_CONTRACT_DATE", strValueContactInfo.toString());
			} else {
				BO.addProperty("HDBD_NO_CONTRACT_DATE", "");
			}
		} else {
			BO.addProperty("HDBD_NO_CONTRACT_DATE", "");
		}
		// ENDING HANDLING HDBD_NO_INFO
		
		// STARTING HANDLING TABLE
		/* DONG SO HUU TABLE */
		if(!TT_YC_HDTSBD.isEmpty()) {
			TT_BEN_BD = TT_YC_HDTSBD.get(0).getAsJsonObject().get("TT_BEN_BD").getAsJsonObject();
			if(TT_BEN_BD.has("DS_DONGSOHUU") && !TT_BEN_BD.get("DS_DONGSOHUU").getAsJsonArray().isEmpty()){
				BO.add("TABLE_DSH", TT_BEN_BD.getAsJsonObject().get("DS_DONGSOHUU").getAsJsonArray());
			}
		} else {
			BO.add("TABLE_DSH", new JsonArray());
			
		}

		/* DANH SACH TAI SAN BAO DAM */
		if(!TT_YC_HDTSBD.isEmpty()) {
			JsonArray DS_BDS = TT_YC_HDTSBD.get(0).getAsJsonObject().has("DS_BDS")? 
							   TT_YC_HDTSBD.get(0).getAsJsonObject().get("DS_BDS").getAsJsonArray()
							   : new JsonArray();
			BO.add("TABLE_TSBD", DS_BDS);
		} else {
			BO.add("TABLE_TSBD", new JsonArray());
		}
		// ENDING HNADLING TABLE
		
		// ANOTHER PROPERTIES
		BO.addProperty("REFEREE_1", "hoặc Trung tâm Trọng tài quốc tế Việt Nam (VIAC) theo Quy tắc tố tụng trọng tài của Trung tâm này do Eximbank lựa chọn ");		
		BO.addProperty("REFEREE_2", "hoặc Trọng tài ");		
		BO.addProperty("REFEREE_3", "hoặc Trung tâm Trọng tài quốc tế Việt Nam do Eximbank lựa chọn ");

	}

	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub
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

		// STARTING HANDLING MASTER DATA FROM OUTPUT
		LGIssuesHandling.ConvertMsDataToName(inputData, "BANK_GRANDPARENTS", "ONGBA", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "BTC_DN_GRANDPARENTS", "ONGBA", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "BTC_CN_GRANDPARENTS", "ONGBA", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "NHP_GRANDPARENTS", "ONGBA", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "BTC_CN_ID_TYPE", "LOAIID", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "NHP_ID_TYPE", "LOAIID", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "BTC_CN_COUPLE_ISSUED_BY", "NOICAPID", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "NHP_ISSUED_BY", "NOICAPID", this.processDataSource);
		// ENDINGS HANDLING MASTER DATA FROM OUTPUT
		
		// LOADING NAME BY MA_NV
		LGIssuesHandling.ConvertUserCodeToName(inputData, "BANK_FULLNAME", this.processDataSource);
		
		// STARTING HANDLING OUTPUT RADIOBUTTON
		LGIssuesHandling.RadioButtonExchange(inputData, "GUARANTEE_OBLIGATIONS_1", "GUARANTEE_OBLIGATIONS_2");
		LGIssuesHandling.RadioButtonExchange(inputData, "TOTAL_GUARANTEE_OBLIGATIONS_1","TOTAL_GUARANTEE_OBLIGATIONS_2");
		LGIssuesHandling.RadioButtonExchange(inputData, "PREVAILING_REGULATIONS");
		// ENDING HANDLING OUTPUT RADIOBUTTON
		
		// STARTING HANDLING OUTPUT CHECKBOXBUTTON
		LGIssuesHandling.CheckBoxExchange(inputData, "MARRIAGE_SIGNING");
		LGIssuesHandling.CheckBoxExchange(inputData, "IS_DSH");
		LGIssuesHandling.CheckBoxExchange(inputData, "BBD_KWNN");
		// ENDING HANDLING OUTPUT CHECKBOXBUTTON
		
		// STARTING HANDLING NUMBER FORMAT
		LGIssuesHandling.GetNumberFormater(inputData, "TOTAL_GUARANTEE_OBLIGATIONS_3", "VN", 0);
		LGIssuesHandling.GetNumberFormater(inputData, "ASSET_VALUE", "VN", 0);
		// ENDING HANDLING NUMBER FORMAT
		
		// STARTING GET BY NUMBER IN WORLD
		LGIssuesHandling.GetByNumberInWords(inputData, "TOTAL_GUARANTEE_OBLIGATIONS_3", "BY_TOTAL_GUARANTEE_OBLIGATIONS_3");
		LGIssuesHandling.GetByNumberInWords(inputData, "ASSET_VALUE", "BY_ASSET_VALUE");
		// ENDING GET BY NUMBER IN WORLD
		
		/*DONG SO HUU TABLE*/
		JsonObject inputDataCFG = inputData.get(0).getAsJsonObject();
		
		if(!inputDataCFG.get("TABLE_DSH").isJsonNull() && !inputDataCFG.get("TABLE_DSH").getAsJsonArray().isEmpty()) {
			LGIssuesHandling.GetDSHList(inputData, this.processDataSource);
		} else {
			inputDataCFG.addProperty("TABLE_DSH", new JsonArray().toString());
		}
		
		/*DANH SACH TAI SAN BAO DAM*/
		if(!inputDataCFG.get("TABLE_TSBD").getAsJsonArray().isEmpty()){
			JsonObject TABLE_VALUE_TEMP = new JsonObject();
			JsonArray TABLE_ARRAY_TEMP = new JsonArray();
			int STT = 1;
			
			for(JsonElement BDSEle : inputDataCFG.get("TABLE_TSBD").getAsJsonArray()) {
				JsonObject QSSD = BDSEle.getAsJsonObject().has("QSSD") && !BDSEle.getAsJsonObject().get("QSSD").isJsonNull() ? 
								  BDSEle.getAsJsonObject().get("QSSD").getAsJsonObject()
								  : new JsonObject();
				JsonObject TS_TRENDAT = BDSEle.getAsJsonObject().has("TS_TRENDAT") && !BDSEle.getAsJsonObject().get("TS_TRENDAT").isJsonNull()? 
										BDSEle.getAsJsonObject().get("TS_TRENDAT").getAsJsonObject() 
										: new JsonObject();
				JsonObject TS_HTTTL = BDSEle.getAsJsonObject().has("TS_HTTTL") && !BDSEle.getAsJsonObject().get("TS_HTTTL").isJsonNull() ? 
										BDSEle.getAsJsonObject().get("TS_HTTTL").getAsJsonObject() 
										: new JsonObject();

				TABLE_VALUE_TEMP.addProperty("STT", STT++);
				TABLE_VALUE_TEMP.addProperty("LOTTERY_NUMBER", QSSD.has("THUADATSO") && !QSSD.get("THUADATSO").isJsonNull() ? QSSD.get("THUADATSO").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("NUMBER_MAP", QSSD.has("TOBANDOSO") && !QSSD.get("TOBANDOSO").isJsonNull() ? QSSD.get("TOBANDOSO").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("LAND_ADDRESS", QSSD.has("DIA_CHI") && !QSSD.get("DIA_CHI").isJsonNull() ? QSSD.get("DIA_CHI").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("ACREAGE", QSSD.has("DIEN_TICH") && !QSSD.get("DIEN_TICH").isJsonNull() ? QSSD.get("DIEN_TICH").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("BY_ACREAGE", QSSD.has("DIEN_TICH") && !QSSD.get("DIEN_TICH").isJsonNull() ? QSSD.get("DIEN_TICH").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("PRIVATE_USED_AREA", QSSD.has("DIEN_TICH_SDR") && !QSSD.get("DIEN_TICH_SDR").isJsonNull() ? QSSD.get("DIEN_TICH_SDR").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("GENERAL_USED_AREA", QSSD.has("DIEN_TICH_SDC") && !QSSD.get("DIEN_TICH_SDC").isJsonNull() ? QSSD.get("DIEN_TICH_SDC").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("USE_PURPOSE", QSSD.has("MUC_DICH") && !QSSD.get("MUC_DICH").isJsonNull() ? QSSD.get("MUC_DICH").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("EXPIRY_DATE", QSSD.has("THOIHANSUDUNG") && !QSSD.get("THOIHANSUDUNG").isJsonNull() ? QSSD.get("THOIHANSUDUNG").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("RESULTS_LAND_USE", QSSD.has("THOIHAN_SD_CONLAI") && !QSSD.get("THOIHAN_SD_CONLAI").isJsonNull() ? QSSD.get("THOIHAN_SD_CONLAI").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("ORIGINAL_USE", QSSD.has("NGUON_GOC") && !QSSD.get("NGUON_GOC").isJsonNull() ? QSSD.get("NGUON_GOC").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("LIMITATIONS_USE", QSSD.has("HANCHE_SDD") && !QSSD.get("HANCHE_SDD").isJsonNull() ? QSSD.get("HANCHE_SDD").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("CERTIFICATE_NUMBER", QSSD.has("SO_GCN") && !QSSD.get("SO_GCN").isJsonNull() ? QSSD.get("SO_GCN").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("SPECIFIC", QSSD.has("CHITIET_BIENDONG_TS") && !QSSD.get("CHITIET_BIENDONG_TS").isJsonNull() ? QSSD.get("CHITIET_BIENDONG_TS").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("PROPERTY_DOC_NUMBER", QSSD.has("SO_GCN") && !QSSD.get("SO_GCN").isJsonNull() ? QSSD.get("SO_GCN").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("NO_CERTIFICATE", QSSD.has("TEN_GIAY_CN") && !QSSD.get("TEN_GIAY_CN").isJsonNull() ? QSSD.get("TEN_GIAY_CN").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("NI_PAPER_BOOK", QSSD.has("SO_VAOSO") && !QSSD.get("SO_VAOSO").isJsonNull() ? QSSD.get("SO_VAOSO").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("NI_GRANTING_AGENCIES", QSSD.has("CO_QUAN_CAP") && !QSSD.get("CO_QUAN_CAP").isJsonNull() ? QSSD.get("CO_QUAN_CAP").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("NI_DATE_RANGE", QSSD.has("NGAY_CAP") && !QSSD.get("NGAY_CAP").isJsonNull() ? QSSD.get("NGAY_CAP").getAsString():"");
				
				TABLE_VALUE_TEMP.addProperty("TYPE_ASSETS", TS_TRENDAT.has("LOAI_TS") && !TS_TRENDAT.get("LOAI_TS").isJsonNull() ? TS_TRENDAT.get("LOAI_TS").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("CONSTRUCTION_AREA", TS_TRENDAT.has("DIENTICH_XD") && !TS_TRENDAT.get("DIENTICH_XD").isJsonNull() ? TS_TRENDAT.get("DIENTICH_XD").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("BY_CONSTRUCTION_AREA", TS_TRENDAT.has("DIENTICH_XD") && !TS_TRENDAT.get("DIENTICH_XD").isJsonNull() ? TS_TRENDAT.get("DIENTICH_XD").getAsString():"");
				
				TABLE_VALUE_TEMP.addProperty("DESCRIBE", TS_HTTTL.has("GIAY_TO") && !TS_HTTTL.get("GIAY_TO").isJsonNull() ? TS_HTTTL.get("GIAY_TO").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("QTS_DOCUMENT", TS_HTTTL.has("GIAYTO_LQ") && !TS_HTTTL.get("GIAYTO_LQ").isJsonNull() ? TS_HTTTL.get("GIAYTO_LQ").getAsString():"");
				
				TABLE_VALUE_TEMP.addProperty("RELATED_DOCUMENTS", BDSEle.getAsJsonObject().has("GIAYTO_LQ") && !BDSEle.getAsJsonObject().get("GIAYTO_LQ").isJsonNull() ? BDSEle.getAsJsonObject().get("GIAYTO_LQ").getAsString():"");
				
				TABLE_ARRAY_TEMP.add(TABLE_VALUE_TEMP.toString());
			}
			
			JsonArray TABLE_TSBD_VALUES = new JsonArray();
			for(JsonElement EleStr : TABLE_ARRAY_TEMP) {
				JsonObject convertedEleStr = new Gson().fromJson(EleStr.getAsString(), JsonObject.class);
				
				// Handle Number Format and NumberInWords
				LGIssuesHandling.GetNumberFormaterTSBD(convertedEleStr, "ACREAGE", "VN", 2);
				LGIssuesHandling.GetNumberFormaterTSBD(convertedEleStr, "CONSTRUCTION_AREA", "VN", 2);
				LGIssuesHandling.GetByNumberInWordsTSBD(convertedEleStr, "ACREAGE", "BY_ACREAGE");
				LGIssuesHandling.GetByNumberInWordsTSBD(convertedEleStr, "CONSTRUCTION_AREA", "BY_CONSTRUCTION_AREA");
				
				TABLE_TSBD_VALUES.add(convertedEleStr);
			}
			inputDataCFG.addProperty("TABLE_TSBD", TABLE_TSBD_VALUES.toString());
			
		} else {
			inputDataCFG.addProperty("TABLE_TSBD", new JsonArray().toString());
		}
		//convert InputData to JSON String
		super.data.getAsJsonObject().addProperty(Gendoc.inpuData, inputData.toString());
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;

		
	}

}
