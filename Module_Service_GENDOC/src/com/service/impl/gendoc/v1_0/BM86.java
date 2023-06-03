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

public class BM86 extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public BM86(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
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
		int indexOfN = support.findIndexOfN(BO, this.dependentRequired, "ID_HD");
		super.invoker= ((GenDocInvoker)super.invoker).setMapper(indexOfN);
		super.mapper = new Auto_Mapping(indexOfN);
		String dirParent = String.format("#.TT_SOANTHAO.TT_YC_HDTSBD[%s]", indexOfN);
		/////////////////MASTER DATA///////////////////////////
		
		Map<String, String> map = new HashMap<String, String>();
				////////////////////////////////	
		map.put("ONGBA", "ONG_BA, BANK_GRANDPARENTS");
		BO = support.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_EXIM" , BO);
		map.clear();
		//////////////////////////////////
		map.put("ONGBA", "ONG_BA, BTC_DN_GRANDPARENTS");
		map.put("NOICAPID", "NOI_CAP, BTC_DN_BUSINESSES_NUMBER_ISSUED_BY");
		BO = support.getMasterData(map,dirParent+".TT_BEN_BD.KH_DN" , BO);
		map.clear();
		
		map.put("ONGBA", "ONG_BA, BTC_CN_GRANDPARENTS");
		BO = support.getMasterData(map,dirParent+".TT_BEN_BD.KH_CN" , BO);
		map.clear();
		//////////////////////////////////
		map.put("NOICAPID", "NOI_CAP, BTC_CN_COUPLE_ISSUED_BY");
		map.put("LOAIID", "LOAI_ID, BTC_CN_ID_TYPE");
		BO = support.getMasterData(map,dirParent+".TT_BEN_BD.TT_DINHDANH[0]" , BO);
		map.clear();
		////////////////////////////////
		map.put("ONGBA", "ONG_BA, NHP_GRANDPARENTS");
		BO = support.getMasterData(map,dirParent+".TT_BEN_BD.TT_NGUOIHONPHOI" , BO);
		map.clear();
		////////////////////////////////
		map.put("LOAIID", "LOAI_ID, NHP_ID_TYPE");
		map.put("NOICAPID", "NOI_CAP, NHP_ISSUED_BY");
		BO = support.getMasterData(map,dirParent+".TT_BEN_BD.TT_NGUOIHONPHOI.TT_DINHDANH[0]" , BO);
		map.clear();
        ////////////////////////////////
		map.put("NOICAPID", "NOI_CAP, CTD_DN_BUSINESSES_NUMBER_ISSUED_BY");
		map.put("ONGBA", "ONG_BA, CTD_DN_GRANDPARENTS|CTD_CN_GRANDPARENTS");
		map.put("CHUCVU", "CHUC_VU, CTD_DN_POSITION_ENTERPRISE");
		BO = support.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_KH" , BO);
		map.clear();	
		 ////////////////////////////////
		map.put("NOICAPID", "NOI_CAP, CTD_CN_ISSUED_BY");
		BO = support.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_KH.TT_DINHDANH[0]" , BO);
		map.clear();
		 ////////////////////////////////
		map.put("ONGBA", "ONG_BA, NDV_GRANDPARENTS");
		BO = support.getMasterData(map,"#.TT_SOANTHAO.TT_CTD_KH.TT_VC_KH" , BO);
		map.clear();
        ////////////////////////////////
        map.put("TCTD", "TCPH, TIEN_ISSUERS");
        BO = support.getMasterData(map,dirParent+".DS_TV_GTCG[0]" , BO);
        map.clear();
		////////////////////////////////
        BO = support.getAddress(BO,dirParent+ ".TT_BEN_BD.TT_LIENLAC", "BTC_CN_COUPLE_PER_ADDRESS", "BTC_CN_COUPLE_CONT_ADDRESS");		   
		// //////////////////////////////
        BO = support.getAddress(BO,dirParent+ ".TT_BEN_BD.TT_NGUOIHONPHOI.TT_LIENLAC", "NHP_PER_ADDRESS", "NHP_CONT_ADDRESS");		
		// //////////////////////////////
        BO = support.getAddress(BO,"#.TT_SOANTHAO.TT_CTD_KH.TT_LIENLAC", "CTD_CN_PER_ADDRESS", "CTD_CN_CONT_ADDRESS");
		// //////////////////////////////
        BO = support.getAddress(BO,"#.TT_SOANTHAO.TT_CTD_KH.TT_VC_KH.TT_DIACHI", "NDV_PER_ADDRESS", "NDV_CONT_ADDRESS");
         /////////////////MASTER DATA-END///////////////////////////	
		
		//////////////CHECK-START////////////////////
		BO = support.toCheck(dirParent+".TT_BEN_BD.YC_NGUOIHONPHOI", "MARRIAGE_SIGNING", BO);
		BO = support.toCheck(dirParent+".TT_BEN_BD.UYQUYEN_KY_HS", "BBD_KWNN", BO);
		BO = support.toCheck("#.TT_DENGHI.TT_CACBEN_KI_HD.YC_HONPHOI", "IS_MARRY", BO);
		BO = support.toCheck(dirParent+".TT_BEN_BD.DONG_SO_HUU", "IS_DSH", BO);
		///////////////CHECK-END/////////////////////////
		
		////////////////RADIO BUTTON START///////////////////////		
		String[] radio_NV_BAODAM= {"GUARANTEE_OBLIGATIONS_1","GUARANTEE_OBLIGATIONS_2"};
		BO = support.toRadio(dirParent+".NV_BAODAM",radio_NV_BAODAM, BO, "CHECK");
		
		String[] radio_TONG_NV_BAODAM= {"TOTAL_GUARANTEE_OBLIGATIONS_2"};
		BO = support.toRadio(dirParent+".TONG_NV_BAODAM",radio_TONG_NV_BAODAM, BO, "CHECK");
		
		String[] radio_COQUAN_GIAIQUYET= {"PREVAILING_REGULATIONS"};
		BO = support.toRadio(dirParent+".COQUAN_GIAIQUYET",radio_COQUAN_GIAIQUYET, BO, "CHECK");
        ////////////////RADIO BUTTON END///////////////////////
		
   ///////////////TABLE START////////////////////////////

		//////////////{}//////////////////
		map.put("TYPE_TSBD", "LOAI_TSBD");
		map.put("TIEN_MONEY", "SO_TIEN");
		map.put("TIEN_STK", "SO_TK");
		map.put("TIEN_ISSUERS", "TCPH");
		JsonObject condition1 = new JsonObject();
		condition1.addProperty("LOAI_TSBD", "LOAITSBD01");
		JsonArray TABLE_GTCG_T = support.getTable(map,dirParent+ ".DS_TV_GTCG", BO, condition1);
		if(TABLE_GTCG_T.size()>0){
			BO.addProperty("TYPE_TSBD_T", "LOAITSBD01");
		}else{
			BO.addProperty("TYPE_TSBD_T", "");
		}
		BO.addProperty("TABLE_GTCG_T", TABLE_GTCG_T.toString());
		map.clear();
		
		map.put("GTCG_NAME", "TCPH");
		map.put("GTCG_SERI", "SO_TK");
		map.put("GTCG_DELIVERY_DATE", "NGAY_PH");
		map.put("GTCG_DTAE", "NGAY_DAO_HAN");
		map.put("GTCG_CURRENCY", "LOAI_TIEN");
		map.put("GTCG_VALUE", "GIA_TRI");
		map.put("GTCG_MONEY", "SOTIEN_DB");
		JsonObject condition2 = new JsonObject();
		condition2.addProperty("LOAI_TSBD", "LOAITSBD02");
		JsonArray TABLE_GTCG_G = support.getTableInTable(map,dirParent+ ".DS_TV_GTCG", BO, "TT_TGTCG", condition2);
		if(TABLE_GTCG_G.size()>0){
			BO.addProperty("TYPE_TSBD_G", "LOAITSBD02");
		}else{
			BO.addProperty("TYPE_TSBD_G", "");
		}
		BO.addProperty("TABLE_GTCG_G", TABLE_GTCG_G.toString());
		map.clear();
		
		
		map.put("VANG_LTS", "LOAI_TS");
		map.put("VANG_QUANTITY", "SO_LUONG");
		map.put("VANG_CURRENCY", "LOAI_TIEN");
		map.put("VANG_VALUE", "GIA_TRI");
		map.put("VANG_DOCUMENT", "CHUNG_TU");
		JsonObject condition3 = new JsonObject();
		condition3.addProperty("LOAI_TSBD", "LOAITSBD03");
		JsonArray TABLE_GTCG_V = support.getTableInTable(map,dirParent+ ".DS_TV_GTCG", BO, "TT_V", condition3);
		if(TABLE_GTCG_V.size()>0){
			BO.addProperty("TYPE_TSBD_V", "LOAITSBD03");
		}else{
			BO.addProperty("TYPE_TSBD_V", "");
		}
		BO.addProperty("TABLE_GTCG_V", TABLE_GTCG_V.toString());
		map.clear();
        //////////////{}//////////////////
        //////////////{}//////////////////
		map.put("GTCG_SERI", "SO_TK");
		JsonArray TABLE_GTCG_1 = support.getTableInTable(map,dirParent+ ".DS_TV_GTCG", BO, "TT_TGTCG", condition2);
		BO.addProperty("TABLE_GTCG_1", TABLE_GTCG_1.toString());
		map.clear();
        //////////////{}//////////////////
		map.put("GTCG_SERI", "SO_TK");
		JsonArray TABLE_GTCG_2 = support.getTableInTable(map,dirParent+ ".DS_TV_GTCG", BO, "TT_TGTCG", condition2);
		BO.addProperty("TABLE_GTCG_2", TABLE_GTCG_2.toString());
		map.clear();
        //////////////{}//////////////////
		 map.put("*DSH_GRANDPARENTS", "ONGBA, ONG_BA");
		  map.put("DSH_FULLNAME_INDIVIDUAL", "TEN_KH_CODAU");
		  map.put("DSH_BIRTHDAY", "NGAY_SINH");
		  map.put("*DSH_ID_TYPE", "LOAIID, TT_DINHDANH[0].LOAI_ID");
	      map.put("DSH_IDNUMBER", "TT_DINHDANH[0].SO_ID");
	      map.put("DSH_DATE_RANGE", "TT_DINHDANH[0].NGAY_CAP");
	      map.put("*DSH_ISSUED_BY", "NOICAPID, TT_DINHDANH[0].NOI_CAP");
	      map.put("DSH_PER_ADDRESS", "TT_LIENLAC[0].DIA_CHI_CUTHE");
	      map.put("DSH_CONT_ADDRESS", "TT_LIENLAC[0].DIA_CHI_CUTHE");
	      map.put("DSH_PHONE_NUMBER", "SO_DT");
		  JsonArray TABLE_DSH = support.getTable(map, dirParent+ ".TT_BEN_BD.DS_DONGSOHUU", BO);
		  BO.addProperty("TABLE_DSH", TABLE_DSH.toString());
		  map.clear();
    ///////////////TABLE END//////////////////////////////
		
		//////////////CUSTOME FIELD-START////////////////////
		try {
			JsonElement js = get_value_byDir(BO,dirParent+ ".TONG_NV_BAODAM_TIEN");
			String moneyText = MoneyHelper.readMoneyToText(js.getAsString());
			BO.addProperty("BY_TOTAL_GUARANTEE_OBLIGATIONS_3", moneyText);
		} catch (Exception e) {
			BO.addProperty("BY_TOTAL_GUARANTEE_OBLIGATIONS_3", "");
		}
		
		//////////////////////////////////////
		map.put("GTCG_VALUE_number", "GTCG_SUM_VALUE");
		map.put("GTCG_MONEY_number", "GTCG_SUM_MONEY");
		BO = sum(map, TABLE_GTCG_G, BO);
		map.clear();
		//////////////////////////////////////
		BO= contractDate(dirParent+".DS_HDTD_SO", BO, "HDBD_NO_CONTRACT_DATE");

        //////////////CUSTOME FIELD-END////////////////////
	}
	
	private JsonObject contractDate(String dir, JsonObject BO, String keyMap) throws SIException{
		JsonElement jsonElement = null;
		try {
			jsonElement=	get_value_byDir(BO, dir);
		} catch (Exception e) {
			BO.addProperty(keyMap, "");
			return BO;
		}
		if(!jsonElement.isJsonArray()){
			BO.addProperty(keyMap, "");
			return BO;
		}
		JsonArray ja = jsonElement.getAsJsonArray();
		String HDBD_NO_CONTRACT_DATE = "";
		for(JsonElement je: ja){
			if (!je.isJsonObject())
				continue;
			JsonObject jo = je.getAsJsonObject();
			if (jo != null && !jo.isJsonNull()) {
				String HDBD_CONTRACT_TYPE = "";
				String HDBD_NO_CONTRACT="";
				String HDBD_CONTRACT_DATE="";

				if(jo.get("LOAI_HD").isJsonPrimitive())
					HDBD_CONTRACT_TYPE = jo.get("LOAI_HD").getAsString();
				if(jo.get("SO").isJsonPrimitive())
					HDBD_NO_CONTRACT = jo.get("SO").getAsString();
				if(jo.get("NGAY").isJsonPrimitive())
					HDBD_CONTRACT_DATE = jo.get("NGAY").getAsString();
				HDBD_NO_CONTRACT_DATE += String.format("%s số %s ngày %s và các văn bản, sửa đổi bổ sung của Hợp đồng này \n", HDBD_CONTRACT_TYPE,HDBD_NO_CONTRACT,HDBD_CONTRACT_DATE);
				
			}
		}
		BO.addProperty(keyMap, HDBD_NO_CONTRACT_DATE);
		
		return BO;	
	}
	private JsonObject sum(Map<String,String> map, JsonArray jsonArray, JsonObject BO){
		if(jsonArray.isEmpty()){
			for (Map.Entry<String, String> entry : map.entrySet()) {
			    BO.addProperty(entry.getValue(), "");
	        }
			return BO;
		}
		double[] sumArr = new double[map.size()];

		for (JsonElement je : jsonArray) {
			if (!je.isJsonObject())
				continue;

			JsonObject jo = je.getAsJsonObject();
			if (jo != null && !jo.isJsonNull()) {
				int count = 0;
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if (jo.get(entry.getKey()).isJsonPrimitive())
						sumArr[count] += jo.get(entry.getKey()).getAsDouble();
					count++;
				}

			}

		}

		int count = 0;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			BO.addProperty(entry.getValue(), sumArr[count]);
			count++;
		}

		return BO;
	}

//	private JsonObject toRadio(String dir, String[] keys, JsonObject BO) throws SIException {
//		JsonElement jsonElement = get_value_byDir(BO, dir);
//		if(!jsonElement.isJsonPrimitive()){
//			return BO;
//		}
//			String value = jsonElement.getAsString();
//			try {
//				Integer index = Integer.parseInt(value);
//				for(String key: keys){
//					if(index != null && keys.length - 1 >= index -1 && index >= 1 && key.equals(keys[index - 1])){
//						BO.addProperty(key, "TRUE");
//					}else{
//						BO.addProperty(key, "FALSE");
//					}
//				}
//				
//			} catch (NumberFormatException e) {
//				for(String key: keys){	
//						BO.addProperty(key, "FALSE");
//				}
//			}
//	
//		return BO;
//	}

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
	
//	private String getTrueOrCheck(String dir, JsonObject BO, String condition) throws SIException{
//		JsonElement jsonElement = get_value_byDir(BO, dir);
//		String result = "";
//		if(!jsonElement.isJsonPrimitive()){
//			return result;
//		}		
//		String status = jsonElement.getAsString();
//		if(condition.equals("CHECK")){
//			if(status.equalsIgnoreCase("1") ){
//				result= "CHECK";
//			}else if(status.equalsIgnoreCase("0")){
//				result= "UNCHECK";
//			}
//		}else if(condition.equals("TRUE")){
//			if(status.equalsIgnoreCase("1") ){
//				result= "TRUE";
//			}else if(status.equalsIgnoreCase("0")){
//				result= "FALSE";
//			}
//		}
//		
//		return result;
//	}
	
//	private JsonObject toCheck(String dir, String key, JsonObject BO, String condition) throws SIException{
//		JsonElement jsonElement = get_value_byDir(BO, dir);
//		if(!jsonElement.isJsonPrimitive()){
//			return BO;
//		}		
//		String status = jsonElement.getAsString();
//
//		if (condition.equals("CHECK")) {
//			if (status.equalsIgnoreCase("1")) {
//				BO.addProperty(key, "CHECK");
//			} else if (status.equalsIgnoreCase("0")) {
//				BO.addProperty(key, "UNCHECK");
//			}
//
//		} else if (condition.equals("TRUE")) {
//			if (status.equalsIgnoreCase("1")) {
//				BO.addProperty(key, "TRUE");
//			} else if (status.equalsIgnoreCase("0")) {
//				BO.addProperty(key, "FALSE");
//			}
//		}
//		return BO;
//	}
}
