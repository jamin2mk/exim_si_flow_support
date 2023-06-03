package com.service.impl.gendoc.v1_0;


import java.util.HashMap;
import java.util.Map;

import Handler.Auto_Mapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.helper.SupportBMHelper;
import com.si.exception.SIException;
import com.si.helper.DateHelper;
import com.si.helper.MoneyHelper;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class BM35 extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired = null; 
	public BM35(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log ,String dependentRequired) {
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
//		JsonObject BO = super.data.getAsJsonObject().get("data").getAsJsonObject();
//		if(BO.getAsJsonObject().get("LOS_BPM_INFORMATION").getAsJsonObject().get("IS_ADJUST").getAsString().equalsIgnoreCase("0")){
//			BO.addProperty("TABLE_DBM_BPBD", "2");
//		}
		JsonObject BO = super.data.getAsJsonObject().get("data").getAsJsonObject();
		BO.addProperty("GENDOC_CODE", docName);
		BO.addProperty("DATE_FM", DateHelper.getCurrentDate("YYYMMdd"));
		String PCA = BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE") != null ? BO.getAsJsonObject("LOS_BPM_INFORMATION").get("CODE").getAsString() : null;
		String[] split = PCA.split("\\.");
		BO.addProperty("PCA", split != null ? String.join("_", split):"");
		
		
		SupportBMHelper support = new SupportBMHelper(super.mapper, super.processDataSource);
		int indexOfN = support.findIndexOfN(BO, this.dependentRequired, "ID_YEUCAU_GN");
		super.invoker= ((GenDocInvoker)super.invoker).setMapper(indexOfN);
		super.mapper = new Auto_Mapping(indexOfN);
		String dirParent = String.format("#.TT_BAO_CAO.TT_YC_GIAINGAN[%s]", indexOfN);
		
		/////////////////MASTER DATA///////////////////////////
		JsonObject TT_DEXUAT = BO.get("TT_DEXUAT").getAsJsonObject();		
		String cust_type = TT_DEXUAT.get("LOAI_KH").getAsString();		
		Map<String, String> map = new HashMap<String, String>();
		
		if(cust_type.equalsIgnoreCase("CN")){
		map.put("LOAIID", "LOAI_ID, ID_TYPE_DN");
		BO = support.getMasterData(map,"#.TT_BAO_CAO.TT_CTD_KH.TT_DINHDANH[0]" , BO);
		BO.addProperty("ID_TYPE_CN", "");
		map.clear();
		}else if(cust_type.equalsIgnoreCase("DN")){
			map.put("LOAIID", "LOAI_ID, ID_TYPE_CN");
			BO = support.getMasterData(map,"#.TT_BAO_CAO.TT_CTD_KH.TT_DINHDANH[0]" , BO);
			BO.addProperty("ID_TYPE_DN", "");
			map.clear();
		}

		////////////////////////////////
		map.put("NOICAPID", "NOI_CAP, CTD_DN_BUSINESSES_NUMBER_ISSUED_BY");
		BO = support.getMasterData(map,"#.TT_BAO_CAO.TT_CTD_KH" , BO);
		map.clear();
		////////////////////////////////	
		map.put("ONGBA", "ONG_BA, CTD_DN_GRANDPARENTS");
		BO = support.getMasterData(map,"#.TT_BAO_CAO.TT_CTD_KH" , BO);
		map.clear();
		//////////////////////////////////
		map.put("ONGBA", "ONG_BA, CTD_CN_GRANDPARENTS");
		BO = support.getMasterData(map,"#.TT_BAO_CAO.TT_CTD_KH" , BO);
		map.clear();
		//////////////////////////////////
		map.put("NOICAPID", "NOI_CAP, CTD_CN_ISSUED_BY");
		BO = support.getMasterData(map,"#.TT_BAO_CAO.TT_CTD_KH.TT_DINHDANH[0]" , BO);
		map.clear();
		////////////////////////////////
		map.put("ONGBA", "ONG_BA, NDV_GRANDPARENTS");
		BO = support.getMasterData(map,"#.TT_BAO_CAO.TT_CTD_KH.TT_VC_KH" , BO);
		map.clear();
		////////////////////////////////
		map.put("NOICAPID", "NOI_CAP, NDV_ISSUED_BY");
		BO = support.getMasterData(map,"#.TT_BAO_CAO.TT_CTD_KH.TT_VC_KH.TT_DINHDANH[0]" , BO);
		map.clear();
		// //////////////////////////////
		map.put("LOAITIEN", "LOAI_TIEN, DXGN_AMOUT_CUR_T");
		BO = support.getMasterData(map,dirParent , BO);
		map.clear();
		////////////////////////////////
		BO =support.getAddress(BO, "#.TT_BAO_CAO.TT_CTD_KH.TT_LIENLAC", "CTD_CN_PER_ADDRESS", "CTD_CN_CONT_ADDRESS");
		// //////////////////////////////
		BO =support.getAddress(BO, "#.TT_BAO_CAO.TT_CTD_KH.TT_VC_KH.TT_DIACHI", "NDV_PER_ADDRESS", "NDV_CONT_ADDRESS");
         /////////////////MASTER DATA-END///////////////////////////	
		
		//////////////CHECK-START////////////////////
		BO = support.toCheck("#.TT_DEXUAT.YC_HONPHOI_KY", "IS_MARRY", BO);
		BO = support.toCheck("#.TT_BAO_CAO.TT_CTD_KH.TT_VC_KH.BEN_UQ", "KWNN", BO);
		BO = support.toCheck(dirParent+".PHUTHUC_GN_TM", "PAY_IN_CASH", BO,"CHECK");
		BO = support.toCheck(dirParent+".PHTHUC_GN_CK", "DBM_TRANSFER", BO,"CHECK");
		///////////////CHECK-END/////////////////////
		
		////////////////RADIO BUTTON START///////////	
		String[] radio_KY_TNGOC= {"A_LOT_TIMES","END_OF_TERM","OTHER_PRINCP_PAY_TERM"};
		BO = support.toRadio(dirParent+".KYHAN_TRANO_GOC",radio_KY_TNGOC, BO, "CHECK");
		
		String[] radio_KYHAN_TRALAI= {"MONTHLY_ON_DAY","OTHER_PAYMENT_TERMS"};
		BO = support.toRadio(dirParent+".KYHAN_TRALAI",radio_KYHAN_TRALAI, BO, "CHECK");
		
		String[] radio_CHUKY_TD_LSUAT={"RULE_1","RULE_2","REGULARLY_ADJUSTMENT"};
		BO = support.toRadio(dirParent+".CHUKY_TD_LSUAT",radio_CHUKY_TD_LSUAT, BO,"CHECK");
		
		String[] radio_NGTAC_DIEUCHINH_LS={"GOVERNMENT_PRINCIPLES_1","GOVERNMENT_PRINCIPLES_2","GOVERNMENT_ORTHER_1"};
		BO = support.toRadio(dirParent+".NGTAC_DIEUCHINH_LS",radio_NGTAC_DIEUCHINH_LS, BO,"CHECK");
		
		String[] radio_LOAI_YC_PHAT={"BANKING_RIGHTS","BANKING_PUNISH"};
		BO = support.toRadio(dirParent+".LOAI_YC_PHAT",radio_LOAI_YC_PHAT, BO,"CHECK");
		
		String[] radio_PHUONGTHUC_PHAT={"BANKING_PUNISH_FREE_CHECK","BANKING_PUNISH_NO"};
		BO = support.toRadio(dirParent+".PHUONGTHUC_PHAT",radio_PHUONGTHUC_PHAT, BO,"CHECK");
        ////////////////RADIO BUTTON END//////////////
		
		///////////////TABLE START///////////////////
		map.put("_TRANSFER_CHECK", getTrueOrCheck(dirParent+".PHTHUC_GN_CK", BO, "CHECK"));
		map.put("TRANSFER_NAME", "TEN_NG_THUHUONG");
		map.put("TRANSFER_STK", "STK_THUHUONG");
		map.put("*TRANSFER_SITE", "TCTD, TAI_TCTD");
		map.put("TRANSFER_MONEY", "SOTIEN");
		JsonArray TABLE_INFO_BENEFICIARYS = support.getTable(map, dirParent+".DS_PHUONGTHUC_GN", BO);
		BO.addProperty("TABLE_INFO_BENEFICIARYS", TABLE_INFO_BENEFICIARYS.toString());
		map.clear();
		///////////////TABLE END//////////////////////////////
		
		//////////////CUSTOME FIELD-START////////////////////
		try {
			JsonElement js = get_value_byDir(BO, dirParent+".SOTIEN_GN");
			String moneyText = MoneyHelper.readMoneyToText(js.getAsString());
			BO.addProperty("BY_MONEY_DBM_AMOUNT", moneyText + " " );
		} catch (Exception e) {
			BO.addProperty("BY_MONEY_DBM_AMOUNT", "");
		}	
        //////////////CUSTOME FIELD-END////////////////////
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
		// Handle business
		JsonArray inputData = super.data.getAsJsonObject().get("InputData").getAsJsonArray();
		
		if(inputData != null && inputData.size() > 0){
			JsonObject input = inputData.get(0).getAsJsonObject();
			// prop handle 
			//WF_TYPE
			input.addProperty("WF_TYPE", "10");
			
//			input.addProperty("ADDRESS_CHECK", "CHECK");
		}
		String stringInputData = inputData.toString();
		super.data.getAsJsonObject().addProperty("InputData", stringInputData);
		JsonElement ID = genDocInvoker.invoke(super.data);
		super.data = ID;
	}
	
	private String getTrueOrCheck(String dir, JsonObject BO, String condition) throws SIException{
		String result = "";
		JsonElement jsonElement = null;
		try {
			 jsonElement = get_value_byDir(BO, dir);
		} catch (Exception e) {
			if(condition.equals("CHECK")){						
					result= "UNCHECK";
			}else if(condition.equals("TRUE")){				
					result= "FALSE";
			}
		}
		if(jsonElement == null ||!jsonElement.isJsonPrimitive()){
			return result;
		}		
		String status = jsonElement.getAsString();
		if(condition.equals("CHECK")){
			if(status.equalsIgnoreCase("1") ){
				result= "CHECK";
			}else if(status.equalsIgnoreCase("0")){
				result= "UNCHECK";
			}
		}else if(condition.equals("TRUE")){
			if(status.equalsIgnoreCase("1") ){
				result= "TRUE";
			}else if(status.equalsIgnoreCase("0")){
				result= "FALSE";
			}
		}
		
		return result;
	}
}
