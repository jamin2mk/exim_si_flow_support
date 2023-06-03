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

public class BM102 extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public BM102(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
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
		////////////////////////////////	
		map.put("NOICAPID", "NOI_CAP, BTC_DN_BUSINESSES_NUMBER_ISSUED_BY");
		BO = support.getMasterData(map,dirParent+".TT_BEN_BD.KH_DN" , BO);
		map.clear();
		
		map.put("ONGBA", "ONG_BA, BTC_DN_GRANDPARENTS");
		BO = support.getMasterData(map,dirParent+".TT_BEN_BD.KH_DN" , BO);
		map.clear();
		
		map.put("ONGBA", "ONG_BA, BTC_CN_GRANDPARENTS");
		BO = support.getMasterData(map,dirParent+".TT_BEN_BD.KH_CN" , BO);
		map.clear();
		//////////////////////////////////
		map.put("LOAIID", "LOAI_ID, BTC_CN_ID_TYPE");
		map.put("NOICAPID", "NOI_CAP, BTC_CN_COUPLE_ISSUED_BY");
		BO = support.getMasterData(map,dirParent+".TT_BEN_BD.TT_DINHDANH[0]" , BO);
		map.clear();
		//////////////////////////////////
		map.put("ONGBA", "ONG_BA, NHP_GRANDPARENTS");
		BO = support.getMasterData(map,dirParent+".TT_BEN_BD.TT_NGUOIHONPHOI" , BO);
		map.clear();
		///////////////////////////////////
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
		BO = support.getAddress(BO,dirParent+ ".TT_BEN_BD.TT_LIENLAC", "BTC_CN_COUPLE_PER_ADDRESS", "BTC_CN_COUPLE_CONT_ADDRESS");		   
		// //////////////////////////////
		BO = support.getAddress(BO,dirParent+ ".TT_BEN_BD.TT_NGUOIHONPHOI.TT_LIENLAC", "NHP_PER_ADDRESS", "NHP_CONT_ADDRESS");	
		/////////////////////////////////////////////////////////////
		BO = support.getAddress(BO, "#.TT_SOANTHAO.TT_CTD_KH.TT_LIENLAC", "CTD_CN_PER_ADDRESS", "CTD_CN_CONT_ADDRESS");	
         /////////////////MASTER DATA-END///////////////////////////	
		
		//////////////CHECK-START////////////////////
		BO = support.toCheck(dirParent+".TT_BEN_BD.YC_NGUOIHONPHOI", "MARRIAGE_SIGNING", BO);
		BO = support.toCheck(dirParent+".TT_BEN_BD.UYQUYEN_KY_HS", "BBD_KWNN", BO);
		BO = support.toCheck("#.TT_DENGHI.TT_CACBEN_KI_HD.YC_HONPHOI", "IS_MARRY", BO);
		///////////////CHECK-END/////////////////////////
		
		////////////////RADIO BUTTON START///////////////////////		

        ////////////////RADIO BUTTON END///////////////////////
		
        ///////////////TABLE START////////////////////////////
		
        ///////////////TABLE END//////////////////////////////
		
		//////////////CUSTOME FIELD-START////////////////////
       try {
    	   JsonElement js = get_value_byDir(BO,dirParent+ ".DS_PTVT[0].GIA_TRI");
   		String moneyText = MoneyHelper.readMoneyToText(js.getAsString());
   		BO.addProperty("TSBD_VALUE_TEXT", moneyText);
        } catch (Exception e) {
        	BO.addProperty("TSBD_VALUE_TEXT", "");
       }
		

        //////////////CUSTOME FIELD-END////////////////////
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
