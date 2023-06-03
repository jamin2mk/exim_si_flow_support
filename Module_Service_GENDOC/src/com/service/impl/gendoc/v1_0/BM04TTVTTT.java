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
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.GenDocInvoker;

public class BM04TTVTTT extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String tableKH = "";
	private String dependentRequired;
	public BM04TTVTTT(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
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
		//TT_DEXUAT
//		JsonObject TT_DEXUAT = BO.get("TT_DEXUAT").getAsJsonObject();		
//		String cust_type = TT_DEXUAT.get("LOAI_KH").getAsString();		
		Map<String, String> map = new HashMap<String, String>();
	     ////////////////////////////////
        map.put("ONGBA", "ONG_BA, BTC_DN_GRANDPARENTS");
        BO = support.getMasterData(map,dirParent+".TT_BEN_BD.KH_CN" , BO);
        map.clear();
        
        map.put("ONGBA", "ONG_BA, BTC_CN_GRANDPARENTS");
        BO = support.getMasterData(map,dirParent+".TT_BEN_BD.KH_DN" , BO);
        map.clear();
        //////////////////////////////////////
        map.put("ONGBA", "ONG_BA, NHP_GRANDPARENTS");
        BO = support.getMasterData(map,dirParent+".TT_BEN_BD.TT_NGUOIHONPHOI" , BO);
        map.clear();
        
         /////////////////MASTER DATA-END///////////////////////////	
		
		//////////////CHECK-START////////////////////
		///////////////CHECK-END/////////////////////////
		
		////////////////RADIO BUTTON START///////////////////////		

        ////////////////RADIO BUTTON END///////////////////////
		
   ///////////////TABLE START////////////////////////////
        map.put("*DSH_GRANDPARENTS", "ONGBA, ONG_BA");
		map.put("DSH_FULLNAME_INDIVIDUAL", "TEN_KH_CODAU");
		map.put("DSH_IDNUMBER", "TT_DINHDANH[0].SO_ID");
		map.put("DSH_PHONE_NUMBER", "SO_DT");
		JsonArray TABLE_KH = getTable(map,dirParent+ ".TT_BEN_BD.DS_DONGSOHUU", BO);
		this.tableKH = TABLE_KH.toString();
		map.clear();
    ///////////////TABLE END//////////////////////////////
		
		//////////////CUSTOME FIELD-START////////////////////
		

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
			JsonArray ja = input.get("TABLE_KH").getAsJsonArray();
			JsonArray tableDSH = new Gson().fromJson(this.tableKH, JsonArray.class);
			
			for(JsonElement je : tableDSH){
				if(je.isJsonObject() && je.getAsJsonObject() != null)
				ja.add(je.getAsJsonObject());
			}
			
			int count = 0;
			for(JsonElement je: ja){
				if(je.isJsonObject() && je.getAsJsonObject() != null)
					je.getAsJsonObject().addProperty("STT", count);
			}
			input.addProperty("TABLE_KH", ja.toString());
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
//	private JsonObject toCheck(String dir, String key, JsonObject BO) throws SIException{
//		JsonElement jsonElement = get_value_byDir(BO, dir);
//		if(!jsonElement.isJsonPrimitive()){
//			return BO;
//		}		
//		String status = jsonElement.getAsString();
//		if(status.equalsIgnoreCase("1")){
//			BO.addProperty(key, "CHECK");
//		}else if(status.equalsIgnoreCase("0")){
//			BO.addProperty(key, "UNCHECK");
//		}
//		return BO;
//	}
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
	
	  
  private JsonArray getTable(Map<String,String> map, String dir, JsonObject BO) throws SIException{
		
	  JsonArray jsonArrReturn = new JsonArray();
		 JsonElement jsonElement= null;
		 try {
			  jsonElement=  get_value_byDir(BO,dir);	
		} catch (Exception e) {
			return jsonArrReturn;
		}	
		if(!jsonElement.isJsonArray())
			return jsonArrReturn;
		
		JsonArray jsonArr= jsonElement.getAsJsonArray();
		
		if(jsonArr !=null && jsonArr.size()>0){
			//int count = 1;
			for(JsonElement je: jsonArr){
				 JsonObject jo= new JsonObject();
				 //jo.addProperty("STT", count);
				if(je.getAsJsonObject() != null && !je.getAsJsonObject().isJsonNull()){					
					for (Map.Entry<String, String> entry : map.entrySet()) {					
						if (entry.getKey().startsWith("_")) {
							jo.addProperty(entry.getKey().substring(1), entry.getValue());
						}else if(entry.getKey().startsWith("*")){
							String[] masterData= entry.getValue().split(","); 
							String group = masterData[0].trim();
							JsonElement valueJsonElement = je.getAsJsonObject().get(masterData[1].trim());
							if(valueJsonElement== null || !valueJsonElement.isJsonPrimitive()) continue;
							String code = valueJsonElement.getAsString();									
							String name=	convertMasterdataCodeToName(group,code);
							jo.addProperty(entry.getKey().substring(1), name);
						}else if(entry.getValue().equals("TT_DINHDANH[0].SO_ID")){
							if(je.isJsonObject())
								continue;
							JsonElement soID = get_value_byDir(je.getAsJsonObject(), "#.TT_DINHDANH[0].SO_ID");
							if(soID.isJsonPrimitive())
								jo.addProperty(entry.getKey(), soID.getAsString());
						}else {							
							JsonElement valueJsonElement = je.getAsJsonObject().get(entry.getValue());
							if(valueJsonElement== null || !valueJsonElement.isJsonPrimitive()) continue;
							String value = valueJsonElement.getAsString();
							jo.addProperty(entry.getKey(), value);
						}
			        }
				}
				jsonArrReturn.add(jo);
				//count++;
			}		
		}
		return jsonArrReturn;
	}
}
