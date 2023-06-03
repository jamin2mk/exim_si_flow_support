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

public class BM47 extends SI_Service {
	
	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	
	public BM47(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
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
		LGIssuesHandling.GetNumberFormater(inputData, "GTTS_EIB", "VN", 0);
		// ENDING HANDLING NUMBER FORMAT
		
		// STARTING GET BY NUMBER IN WORLD
		LGIssuesHandling.GetByNumberInWords(inputData, "TOTAL_GUARANTEE_OBLIGATIONS_3", "BY_TOTAL_GUARANTEE_OBLIGATIONS_3");
		LGIssuesHandling.GetByNumberInWords(inputData, "GTTS_EIB", "BY_GTTS_EIB");
		// ENDING GET BY NUMBER IN WORLD
		
		/*DONG SO HUU TABLE*/
		JsonObject inputDataCFG = inputData.get(0).getAsJsonObject();
		
		if(!inputDataCFG.get("TABLE_DSH").isJsonNull() && !inputDataCFG.get("TABLE_DSH").getAsJsonArray().isEmpty()) {
			LGIssuesHandling.GetDSHList(inputData, this.processDataSource);
		} else {
			inputDataCFG.addProperty("TABLE_DSH", new JsonArray().toString());
		}
		
		//convert InputData to JSON String
		super.data.getAsJsonObject().addProperty(Gendoc.inpuData, inputData.toString());
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;

		
	}

}
