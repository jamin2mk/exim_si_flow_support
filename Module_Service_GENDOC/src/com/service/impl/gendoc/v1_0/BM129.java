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

public class BM129 extends SI_Service {
	
	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	
	public BM129(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
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
		
		// STARTING HANDLING MULTIPLE TEMPLATES
		if(dependentRequired!=null || "".equals(dependentRequired)){
			LGIssuesHandling.RequiredContactType(super.data.getAsJsonObject(), dependentRequired, "ID_YEUCAU_GN");
		}
		// ENDING HANDLING MULTIPLE TEMPLATES

		// CONFIGURATING AVIABLE PATH 
		JsonObject TT_BAO_CAO = BO.get("TT_BAO_CAO").getAsJsonObject();
		JsonObject TT_CTD_KH = TT_BAO_CAO.get("TT_CTD_KH").getAsJsonObject();
		JsonObject DS_YCAU_GN = BO.get("TT_DEXUAT").getAsJsonObject().get("DS_YCAU_GN").getAsJsonArray().get(0).getAsJsonObject();
		JsonObject TT_HD = TT_BAO_CAO.get("TT_HD").getAsJsonObject();
		
		// STARTING HANDLING CONTACT INFOMATION
		JsonArray TT_LIENLAC = TT_CTD_KH.get("TT_LIENLAC").getAsJsonArray();
		JsonArray TT_DIACHI = TT_CTD_KH.get("TT_VC_KH").getAsJsonObject().get("TT_DIACHI").getAsJsonArray();
		
		if (!TT_LIENLAC.isEmpty()) {
			LGIssuesHandling.GetAddressInfo(TT_LIENLAC, BO, this.processDataSource, "CTD_CN_PER_ADDRESS", "CTD_CN_CONT_ADDRESS");
		} 
		else {
			BO.addProperty("CTD_CN_PER_ADDRESS", "");
			BO.addProperty("CTD_CN_CONT_ADDRESS", "");
		}
		
		if (!TT_DIACHI.isEmpty()) {
			LGIssuesHandling.GetAddressInfo(TT_DIACHI, BO, this.processDataSource, "NDV_PER_ADDRESS", "NDV_CONT_ADDRESS");
		} 
		else {
			BO.addProperty("NDV_PER_ADDRESS", "");
			BO.addProperty("NDV_CONT_ADDRESS", "");
		}
		// ENDING HANDLING CONTACT INFOMATION
		
		// STARTING HANDLING MASTERDATA
		String NHOMNO = convertMasterdataCodeToName("NHOMNO",TT_HD.getAsJsonObject().has("NHOM_NO") ? TT_HD.getAsJsonObject().get("NHOM_NO").getAsString():null);
		TT_HD.addProperty("NHOM_NO", NHOMNO);
		String LOAITIEN = convertMasterdataCodeToName("LOAITIEN",DS_YCAU_GN.getAsJsonObject().has("LOAITIEN") ? DS_YCAU_GN.getAsJsonObject().get("LOAITIEN").getAsString():null);
		DS_YCAU_GN.addProperty("LOAITIEN", LOAITIEN);
		// ENDING HANDLING MASTERDATA
		
		//TABLE_BM129_1
		JsonArray BIENPHAP_BAODAM = TT_HD.get("BIENPHAP_BAODAM").getAsJsonArray();
		if(BIENPHAP_BAODAM != null && BIENPHAP_BAODAM.size() > 0){
			JsonObject TABLE_VALUE_TEMP = new JsonObject();
			JsonArray TABLE_ARRAY_TEMP = new JsonArray();
			int STT = 1;
			
			for(JsonElement item : BIENPHAP_BAODAM){
				TABLE_VALUE_TEMP.addProperty("STT", STT++);
				TABLE_VALUE_TEMP.addProperty("TSBD_NAME", item.getAsJsonObject().has("TEN_TSBD") && !item.getAsJsonObject().get("TEN_TSBD").isJsonNull() ? item.getAsJsonObject().get("TEN_TSBD").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("VALUATION_VALUE", item.getAsJsonObject().has("GIATRI_DINHGIA") && !item.getAsJsonObject().get("GIATRI_DINHGIA").isJsonNull() ? item.getAsJsonObject().get("GIATRI_DINHGIA").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("GUARANTEED_VALUE", item.getAsJsonObject().has("GIATRI_TSBD") && !item.getAsJsonObject().get("GIATRI_TSBD").isJsonNull() ? item.getAsJsonObject().get("GIATRI_TSBD").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("NOTARIZATION", item.getAsJsonObject().has("DK_BPBD") && !item.getAsJsonObject().get("DK_BPBD").isJsonNull() ? item.getAsJsonObject().get("DK_BPBD").getAsString():"");
			
				TABLE_ARRAY_TEMP.add(TABLE_VALUE_TEMP.toString());
			}
			JsonArray TABLE_VALUES = new JsonArray();
			for(JsonElement EleStr : TABLE_ARRAY_TEMP) {
				JsonObject convertedEleStr = new Gson().fromJson(EleStr.getAsString(), JsonObject.class);
				TABLE_VALUES.add(convertedEleStr);
			}
			BO.addProperty("TABLE_BM129_1", TABLE_VALUES.toString());
			
		}else {
			BO.addProperty("TABLE_BM129_1", new JsonArray().toString());
		}
		
		//TABLE_BM129_2
		JsonArray TINHHINH_SDVON = TT_HD.get("TINHHINH_SDVON").getAsJsonArray();
		if(TINHHINH_SDVON != null && TINHHINH_SDVON.size() > 0){
			JsonObject TABLE_VALUE_TEMP = new JsonObject();
			JsonArray TABLE_ARRAY_TEMP = new JsonArray();
			int STT = 1;
			
			for(JsonElement item : TINHHINH_SDVON){
				TABLE_VALUE_TEMP.addProperty("STT", STT++);
				TABLE_VALUE_TEMP.addProperty("DATE", item.getAsJsonObject().has("NGAY") && !item.getAsJsonObject().get("NGAY").isJsonNull() ? item.getAsJsonObject().get("NGAY").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("INDENTURE", item.getAsJsonObject().has("KHE_UOC") && !item.getAsJsonObject().get("KHE_UOC").isJsonNull() ? item.getAsJsonObject().get("KHE_UOC").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("MONEY_DBM", item.getAsJsonObject().has("SOTIEN_GIAINGAN") && !item.getAsJsonObject().get("SOTIEN_GIAINGAN").isJsonNull() ? item.getAsJsonObject().get("SOTIEN_GIAINGAN").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("BENEFICIARY", item.getAsJsonObject().has("NGUOI_THUHUONG") && !item.getAsJsonObject().get("NGUOI_THUHUONG").isJsonNull() ? item.getAsJsonObject().get("NGUOI_THUHUONG").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("DEBT", item.getAsJsonObject().has("DU_NO") && !item.getAsJsonObject().get("DU_NO").isJsonNull() ? item.getAsJsonObject().get("DU_NO").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("EXCHANGE", item.getAsJsonObject().has("QUY_DOI") && !item.getAsJsonObject().get("QUY_DOI").isJsonNull() ? item.getAsJsonObject().get("QUY_DOI").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("USING_PURPOSE", item.getAsJsonObject().has("MUCDICH_SDVON") && !item.getAsJsonObject().get("MUCDICH_SDVON").isJsonNull() ? item.getAsJsonObject().get("MUCDICH_SDVON").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("USES", item.getAsJsonObject().has("CHUNG_TU") && !item.getAsJsonObject().get("CHUNG_TU").isJsonNull() ? item.getAsJsonObject().get("CHUNG_TU").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("NOTE", item.getAsJsonObject().has("GHI_CHU") && !item.getAsJsonObject().get("GHI_CHU").isJsonNull() ? item.getAsJsonObject().get("GHI_CHU").getAsString():"");
			
				TABLE_ARRAY_TEMP.add(TABLE_VALUE_TEMP.toString());
			}
			JsonArray TABLE_VALUES = new JsonArray();
			for(JsonElement EleStr : TABLE_ARRAY_TEMP) {
				JsonObject convertedEleStr = new Gson().fromJson(EleStr.getAsString(), JsonObject.class);
				
				// Handling Master Data
				String USES_MSDATA = convertMasterdataCodeToName("CHUNGMINHMDSDV", convertedEleStr.has("USES") && !convertedEleStr.get("USES").isJsonNull() ? convertedEleStr.get("USES").getAsString() : "");
				convertedEleStr.addProperty("USES", USES_MSDATA);
				
				TABLE_VALUES.add(convertedEleStr);
			}
			BO.addProperty("TABLE_BM129_2", TABLE_VALUES.toString());
			
		}else {
			BO.addProperty("TABLE_BM129_2", new JsonArray().toString());
		}
				
		// STARTING HANDLING DS_CHUYENKHOAN
		
		JsonArray DS_CHUYENKHOAN = DS_YCAU_GN.get("DS_CHUYENKHOAN").getAsJsonArray();
		JsonArray tempInfo = new JsonArray();
		
		String BENEFICIARYS_NAME = "";
		String BENEFICIARYS_ACC = "";
		String BENEFICIARYS_ACC_PLACE = "";
		String BENEFICIARYS_LOAN = "";
		
		if(!DS_CHUYENKHOAN.isEmpty()) {
			for(JsonElement beInfoEle : DS_CHUYENKHOAN) {
				
				BENEFICIARYS_NAME = beInfoEle.getAsJsonObject().has("TENTHUHUONG") ? beInfoEle.getAsJsonObject().get("TENTHUHUONG").getAsString(): "................";
				BENEFICIARYS_ACC = beInfoEle.getAsJsonObject().has("STK_THU_HUONG") ? beInfoEle.getAsJsonObject().get("STK_THU_HUONG").getAsString(): "................";
				BENEFICIARYS_ACC_PLACE = beInfoEle.getAsJsonObject().has("TCTD") ? convertMasterdataCodeToName("TCTD", beInfoEle.getAsJsonObject().get("TCTD").getAsString()): "................";
				BENEFICIARYS_LOAN = beInfoEle.getAsJsonObject().has("SOTIEN") ? beInfoEle.getAsJsonObject().get("SOTIEN").getAsString(): "................";
				
				tempInfo.add(String.format("%s, số tài khoản: %s tại %s, số tiền %s"
						, BENEFICIARYS_NAME
						, BENEFICIARYS_ACC
						, BENEFICIARYS_ACC_PLACE
						, BENEFICIARYS_LOAN));
			}
			if(!tempInfo.isEmpty()){
				StringBuilder strValueBenefit = new StringBuilder("- " + tempInfo.getAsJsonArray().get(0).getAsString());
				for(int i=1; i< tempInfo.size(); i++) {
					strValueBenefit.append("\n- " + tempInfo.getAsJsonArray().get(i).getAsString());
				}		
				BO.addProperty("TRANSFER_BENEFICIARYS_INFO", strValueBenefit.toString());
			}
		} else {
			BO.addProperty("TRANSFER_BENEFICIARYS_INFO", "");
		}
		
		// ENDING HANDLING DS_CHUYENKHOAN
		
		JsonObject TAISAN_BAODAM = TT_BAO_CAO.get("TAISAN_BAODAM").getAsJsonObject();
		// TABLE_DBM_TTK
		JsonArray TT_TIENGUI = TAISAN_BAODAM.get("TT_TIENGUI").getAsJsonArray();
		
		if(TT_TIENGUI != null && TT_TIENGUI.size() > 0){
			JsonObject TABLE_VALUE_TEMP = new JsonObject();
			JsonArray TABLE_ARRAY_TEMP = new JsonArray();
			int STT = 1;
			
			for(JsonElement item : TT_TIENGUI){
				TABLE_VALUE_TEMP.addProperty("TTK_STT", STT++);
				TABLE_VALUE_TEMP.addProperty("TTK_TCPH", item.getAsJsonObject().has("TOCHUC_PHATHANH") && !item.getAsJsonObject().get("TOCHUC_PHATHANH").isJsonNull() ? item.getAsJsonObject().get("TOCHUC_PHATHANH").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("TTK_STK", item.getAsJsonObject().has("SO_TAIKHOAN") && !item.getAsJsonObject().get("SO_TAIKHOAN").isJsonNull() ? item.getAsJsonObject().get("SO_TAIKHOAN").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("TTK_NPH", item.getAsJsonObject().has("NGAY_PHATHANH") && !item.getAsJsonObject().get("NGAY_PHATHANH").isJsonNull() ? item.getAsJsonObject().get("NGAY_PHATHANH").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("TTK_NDH", item.getAsJsonObject().has("NGAY_DAOHAN") && !item.getAsJsonObject().get("NGAY_DAOHAN").isJsonNull() ? item.getAsJsonObject().get("NGAY_DAOHAN").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("TTK_CURRENCY", item.getAsJsonObject().has("LOAITIEN") && !item.getAsJsonObject().get("LOAITIEN").isJsonNull() ? item.getAsJsonObject().get("LOAITIEN").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("TTK_VALUE", item.getAsJsonObject().has("GIATRI") && !item.getAsJsonObject().get("GIATRI").isJsonNull() ? item.getAsJsonObject().get("GIATRI").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("TTK_GUARANTEE_AMOUNT", item.getAsJsonObject().has("SOTIEN_BAODAM") && !item.getAsJsonObject().get("SOTIEN_BAODAM").isJsonNull() ? item.getAsJsonObject().get("SOTIEN_BAODAM").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("TTK_COURT_STATUS", item.getAsJsonObject().has("TINHTRANG_PHONGTOA") && !item.getAsJsonObject().get("TINHTRANG_PHONGTOA").isJsonNull() ? item.getAsJsonObject().get("TINHTRANG_PHONGTOA").getAsString():"");
			
				TABLE_ARRAY_TEMP.add(TABLE_VALUE_TEMP.toString());
			}
			JsonArray TABLE_VALUES = new JsonArray();
			for(JsonElement EleStr : TABLE_ARRAY_TEMP) {
				JsonObject convertedEleStr = new Gson().fromJson(EleStr.getAsString(), JsonObject.class);
				
				// Handling Master Data
				String TTK_TCPH_MSDATA = convertMasterdataCodeToName("TCTD", convertedEleStr.has("TTK_TCPH") && !convertedEleStr.get("TTK_TCPH").isJsonNull() ? convertedEleStr.get("TTK_TCPH").getAsString() : "");
				convertedEleStr.addProperty("TTK_TCPH", TTK_TCPH_MSDATA);
				
				TABLE_VALUES.add(convertedEleStr);
			}
			BO.addProperty("TABLE_DBM_TTK", TABLE_VALUES.toString());
			
		}else {
			BO.addProperty("TABLE_DBM_TTK", new JsonArray().toString());
		}
		
		// TABLE_DBM_VANG
		JsonArray TT_VANG = TAISAN_BAODAM.get("TT_VANG").getAsJsonArray();
		
		if(TT_VANG != null && TT_VANG.size() > 0){
			JsonObject TABLE_VALUE_TEMP = new JsonObject();
			JsonArray TABLE_ARRAY_TEMP = new JsonArray();
			int STT = 1;
			
			for(JsonElement item : TT_VANG){
				TABLE_VALUE_TEMP.addProperty("VANG_STT", STT++);
				TABLE_VALUE_TEMP.addProperty("VANG_ASSET_TYPE", item.getAsJsonObject().has("LOAITAISAN") && !item.getAsJsonObject().get("LOAITAISAN").isJsonNull() ? item.getAsJsonObject().get("LOAITAISAN").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("VANG_SL", item.getAsJsonObject().has("SOLUONG") && !item.getAsJsonObject().get("SOLUONG").isJsonNull() ? item.getAsJsonObject().get("SOLUONG").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("VANG_CUR", item.getAsJsonObject().has("LOAITIEN") && !item.getAsJsonObject().get("LOAITIEN").isJsonNull() ? item.getAsJsonObject().get("LOAITIEN").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("VANG_VA", item.getAsJsonObject().has("GIATRI") && !item.getAsJsonObject().get("GIATRI").isJsonNull() ? item.getAsJsonObject().get("GIATRI").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("VANG_GUARANTEE_AMOUNT", item.getAsJsonObject().has("SOTIEN_BAODAM") && !item.getAsJsonObject().get("SOTIEN_BAODAM").isJsonNull() ? item.getAsJsonObject().get("SOTIEN_BAODAM").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("VANG_TTK_COURT_STATUS", item.getAsJsonObject().has("TINHTRANG_PHONGTOA") && !item.getAsJsonObject().get("TINHTRANG_PHONGTOA").isJsonNull() ? item.getAsJsonObject().get("TINHTRANG_PHONGTOA").getAsString():"");
			
				TABLE_ARRAY_TEMP.add(TABLE_VALUE_TEMP.toString());
			}
			JsonArray TABLE_VALUES = new JsonArray();
			for(JsonElement EleStr : TABLE_ARRAY_TEMP) {
				JsonObject convertedEleStr = new Gson().fromJson(EleStr.getAsString(), JsonObject.class);
				TABLE_VALUES.add(convertedEleStr);
			}
			BO.addProperty("TABLE_DBM_VANG", TABLE_VALUES.toString());
			
		}else {
			BO.addProperty("TABLE_DBM_VANG", new JsonArray().toString());
		}
		
		// TABLE_DBM_KHAC
		JsonArray TT_TAISAN_KHAC = TAISAN_BAODAM.get("TT_TAISAN_KHAC").getAsJsonArray();
		
		if(TT_TAISAN_KHAC != null && TT_TAISAN_KHAC.size() > 0){
			JsonObject TABLE_VALUE_TEMP = new JsonObject();
			JsonArray TABLE_ARRAY_TEMP = new JsonArray();
			int STT = 1;
			
			for(JsonElement item : TT_TAISAN_KHAC){
				TABLE_VALUE_TEMP.addProperty("KHAC_STT", STT++);
				TABLE_VALUE_TEMP.addProperty("KHAC_TSBD_NAME", item.getAsJsonObject().has("TEN_TSBD") && !item.getAsJsonObject().get("TEN_TSBD").isJsonNull() ? item.getAsJsonObject().get("TEN_TSBD").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("KHAC_VALUE", item.getAsJsonObject().has("GIATRI") && !item.getAsJsonObject().get("GIATRI").isJsonNull() ? item.getAsJsonObject().get("GIATRI").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("KHAC_VALUEBD", item.getAsJsonObject().has("GIATRI_BAODAM") && !item.getAsJsonObject().get("GIATRI_BAODAM").isJsonNull() ? item.getAsJsonObject().get("GIATRI_BAODAM").getAsString():"");
				TABLE_VALUE_TEMP.addProperty("KHAC_DKBPBD", item.getAsJsonObject().has("CONGCHUNG") && !item.getAsJsonObject().get("CONGCHUNG").isJsonNull() ? item.getAsJsonObject().get("CONGCHUNG").getAsString():"");
			
				TABLE_ARRAY_TEMP.add(TABLE_VALUE_TEMP.toString());
			}
			JsonArray TABLE_VALUES = new JsonArray();
			for(JsonElement EleStr : TABLE_ARRAY_TEMP) {
				JsonObject convertedEleStr = new Gson().fromJson(EleStr.getAsString(), JsonObject.class);
				TABLE_VALUES.add(convertedEleStr);
			}
			BO.addProperty("TABLE_DBM_KHAC", TABLE_VALUES.toString());
			
		}else {
			BO.addProperty("TABLE_DBM_KHAC", new JsonArray().toString());
		}
		
		// NEED TO CONFIGURE
		BO.addProperty("CA_CANCEL", "");
		BO.addProperty("CA_CANCEL_REASON", "");
		BO.addProperty("CA_AGREE_CANCEL", "");
		
		BO.addProperty("QR_TRADING_CODE", "");
		BO.addProperty("QR_USER", "");
		BO.addProperty("QR_DATE", "");
		BO.addProperty("QR_HOUR", "");
		BO.addProperty("QR_DVKD", "");
		
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
		LGIssuesHandling.ConvertMsDataToName(inputData, "CTD_DN_GRANDPARENTS", "ONGBA", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "CTD_CN_GRANDPARENTS", "ONGBA", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "NDV_GRANDPARENTS", "ONGBA", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "CTD_CN_ISSUED_BY", "NOICAPID", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "NDV_ISSUED_BY", "NOICAPID", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "GNBL_TCPH", "TCTD", this.processDataSource);
		LGIssuesHandling.ConvertMsDataToName(inputData, "CTD_DN_BUSINESSES_NUMBER_ISSUED_BY", "NOICAPID", this.processDataSource);
		// ENDINGS HANDLING MASTER DATA FROM OUTPUT

		// STARTING HANDLING OUTPUT RADIOBUTTON
		LGIssuesHandling.RadioButtonExchange(inputData, "COMPLY", "NON_COMPLIANCE");
		LGIssuesHandling.RadioButtonExchange(inputData, "TSBD_FOLLOW", "TSBD_DO_NOT_OBEY");
		LGIssuesHandling.RadioButtonExchange(inputData, "DBM_COMPLY", "DBM_NON_COMPLIANCE");
		LGIssuesHandling.RadioButtonExchange(inputData, "REPUTABLE", "ARISING");
		
		LGIssuesHandling.RadioButtonExchange(inputData, "OTHER_COMPLY", "OTHER_NON_COMPLIANCE");
		LGIssuesHandling.RadioButtonExchange(inputData, "A_LOT_TIMES", "END_OF_TERM", "OTHER_PRINCP_PAY_TERM");
		LGIssuesHandling.RadioButtonExchange(inputData, "MONTH_INTEREST_PAY", "O_INS_INTEREST_PAY");
		// ENDING HANDLING OUTPUT RADIOBUTTON
		
		// STARTING HANDLING OUTPUT CHECKBOXBUTTON
		LGIssuesHandling.CheckBoxExchange(inputData, "IS_MARRY");
		LGIssuesHandling.CheckBoxExchange(inputData, "PROVIDED_DOCUMENT");
		LGIssuesHandling.CheckBoxExchange(inputData, "DOC_AFTER_DBM");
		LGIssuesHandling.CheckBoxExchange(inputData, "PAY_IN_CASH");
		LGIssuesHandling.CheckBoxExchange(inputData, "DBM_TRANSFER");
		// ENDING HANDLING OUTPUT CHECKBOXBUTTON
		
		// ĐÁNH GIÁ ĐỀ NGHỊ GIẢI NGÂN CỦA PHÒNG TABLE
		for(int i=1; i<13; i++) {
			if(!inputData.get(0).getAsJsonObject().get(String.format("APPROPRIATE_ASSESSMENT_%d",i)).isJsonNull() && inputData.get(0).getAsJsonObject().get(String.format("APPROPRIATE_ASSESSMENT_%d",i)).getAsInt()==1) {
				inputData.get(0).getAsJsonObject().addProperty(String.format("APPROPRIATE_ASSESSMENT_%d",i), "CHECK");
			} else {
				inputData.get(0).getAsJsonObject().addProperty(String.format("APPROPRIATE_ASSESSMENT_%d",i), "UNCHECK");
			}
		}
		
		//convert InputData to JSON String
		super.data.getAsJsonObject().addProperty(Gendoc.inpuData, inputData.toString());
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;
		
	}

}
