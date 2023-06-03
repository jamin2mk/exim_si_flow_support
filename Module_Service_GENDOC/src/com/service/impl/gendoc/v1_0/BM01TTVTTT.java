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

public class BM01TTVTTT extends SI_Service {

	private GenDocInvoker genDocInvoker = null;
	private String docName = null;
	private String dependentRequired;
	public BM01TTVTTT(String docName, String data, boolean isMergeData, String siVersion, String processDataSource, String logDataSource, ServiceConfig serviceConfig, SI_Log log, String dependentRequired) {
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
		
         /////////////////MASTER DATA-END///////////////////////////	
		
		//////////////CHECK-START////////////////////
		///////////////CHECK-END/////////////////////////
		
		////////////////RADIO BUTTON START///////////////////////		

        ////////////////RADIO BUTTON END///////////////////////
		
   ///////////////TABLE START////////////////////////////
		map.put("PROFILE_NAME", "TEN_HOSO");
		map.put("PROFILE_NUMBER", "SO");
		map.put("PROFILE_DATE", "NGAY_CAP");
		map.put("PROFILE_GRANTING_AGENCIES", "COQUAN_CAP");
		map.put("PROFILE_ORIGINAL", "BAN_CHINH");
		map.put("PROFILE_COPY", "BAN_SAO");
		map.put("PROFILE_NO_PAGES", "SO_TRANG");
		JsonArray TABLE_PROFILE = support.getTable(map,dirParent+ ".DS_HOSO_GIAONHAN", BO);
		BO.addProperty("TABLE_PROFILE", TABLE_PROFILE.toString());
		map.clear();
    ///////////////TABLE END//////////////////////////////
		
		//////////////CUSTOME FIELD-START////////////////////
		
//		BO = motaBDS(BO);
//		BO = motaPTVT(BO);
//
//		JsonElement jsTSBD = get_value_byDir(BO, "#.TT_SOANTHAO.TT_YC_HDTSBD[0].DS_PTVT[0].GIA_TRI");
//		String tsbdText = MoneyHelper.readMoneyToText(jsTSBD.getAsString());
//		BO.addProperty("TSBD_VALUE_TEXT", tsbdText);
//		
//		JsonElement jsASSET = get_value_byDir(BO, "#.TT_SOANTHAO.TT_YC_HDTSBD[0].DS_BDS[0].TSTD_CCGCN.GIA_TRI");
//		String assetText = MoneyHelper.readMoneyToText(jsASSET.getAsString());
//		BO.addProperty("BY_ASSET_VALUE", assetText);
		BO = BM01_QSDD(dirParent,BO);
		BO = BM01_CHCC(dirParent,BO);
		BO = BM01_QSDD_TSTD(dirParent,BO);
		BO = BM01_PTVT(dirParent,BO);
		BO = CN_GRANDPARENTS_FULLNAME(dirParent, BO);
        //////////////CUSTOME FIELD-END////////////////////
	}
	
	private JsonObject BM01_QSDD(String dirParent,JsonObject BO) throws SIException{
		String result = "";
		JsonElement hdbd150JE = null;
		JsonElement hdbd152JE = null;
		try {
			hdbd150JE=   get_value_byDir(BO,dirParent+ ".DS_BDS[0].QSSD.THUA_DAT_SO");
			 hdbd152JE = get_value_byDir(BO,dirParent+ ".DS_BDS[0].QSSD.DIA_CHI");
		} catch (Exception e) {
			BO.addProperty("BM01_QSDD", "");
			return BO;
		}
		
		String hdbd150="";
		String hdbd152="";
		if(hdbd150JE.isJsonPrimitive())
			hdbd150 = hdbd150JE.getAsString();		
		if(hdbd152JE.isJsonPrimitive())
			hdbd152 = hdbd152JE.getAsString();		
		
		result = String.format("Bất động sản tại Thửa đất số  %s,  %s ", hdbd150,hdbd152);
		
		BO.addProperty("BM01_QSDD", result);
		return BO;
	}
	private JsonObject BM01_CHCC(String dirParent,JsonObject BO) throws SIException{
		String result = "";
		JsonElement hdbd177JE =null;
		JsonElement hdbd182JE =null;
				try {
					hdbd177JE = get_value_byDir(BO,dirParent+ ".DS_BDS[0].TS_CHCC.CANHO_SO");
					 hdbd182JE = get_value_byDir(BO,dirParent+ ".DS_BDS[0].TS_CHCC.DIA_CHI");
				} catch (Exception e) {
					BO.addProperty("BM01_CHCC", "");
					return BO;
				}
		 
		String hdbd177="";
		String hdbd182="";
		if(hdbd177JE.isJsonPrimitive())
			hdbd177 = hdbd177JE.getAsString();		
		if(hdbd182JE.isJsonPrimitive())
			hdbd182 = hdbd182JE.getAsString();		
		
		result = String.format("Bất động sản tại %s %s", hdbd177,hdbd182);
		
		BO.addProperty("BM01_CHCC", result);
		return BO;
	}
	
	private JsonObject BM01_QSDD_TSTD(String dirParent,JsonObject BO) throws SIException{
		String result = "";
		JsonElement hdbd169JE =null;
		try {
			hdbd169JE = get_value_byDir(BO,dirParent+ ".DS_BDS[0].TS_TRENDAT.DIA_CHI");
		} catch (Exception e) {
			BO.addProperty("BM01_QSDD_TSTD", "");
			return BO;
		}
		
		String hdbd169="";
		if(hdbd169JE.isJsonPrimitive())
			hdbd169 = hdbd169JE.getAsString();				
		result = String.format("Bất động sản tại %s", hdbd169);
		
		BO.addProperty("BM01_QSDD_TSTD", result);
		return BO;
	}
	
	private JsonObject BM01_PTVT(String dirParent,JsonObject BO) throws SIException{
		String result = "";
		JsonElement hdbd220JE =null;
				JsonElement hdbd223JE = null;
						JsonElement hdbd221JE =null;
						try {
							 hdbd220JE = get_value_byDir(BO,dirParent+ ".DS_PTVT[0].PTVT_DB[0].TEN_PTVT_MOTA");
							 hdbd223JE = get_value_byDir(BO,dirParent+ ".DS_PTVT[0].PTVT_DB[0].BIEN_SO");
							 hdbd221JE = get_value_byDir(BO,dirParent+ ".DS_PTVT[0].PTVT_DB[0].SO_KHUNG");
						} catch (Exception e) {
							BO.addProperty("BM01_PTVT", result);
							return BO;
						}
	
		String hdbd220="";
		String hdbd223="";
		String hdbd221="";
		if(hdbd220JE.isJsonPrimitive())
			hdbd220 = hdbd220JE.getAsString();		
		if(hdbd223JE.isJsonPrimitive())
			hdbd223 = hdbd223JE.getAsString();	
		if(hdbd221JE.isJsonPrimitive())
			hdbd221 = hdbd221JE.getAsString();
		
		result = String.format("%s biển số %s, số khung %s", hdbd220,hdbd223,hdbd221);
		
		BO.addProperty("BM01_PTVT", result);
		return BO;
	}
	
	private JsonObject CN_GRANDPARENTS_FULLNAME(String dirParent,JsonObject BO) throws SIException{
		String result = "";
		JsonElement hdbd273JE =null;
				JsonElement hdbd276JE =null;
						JsonElement hdbd278JE =null;
								JsonElement hdbd045JE = null;
										JsonElement hdbd008JE = null;
				try {
					 hdbd273JE = get_value_byDir(BO,dirParent+ ".DS_TV_GTCG[0].TT_TGTCG[0].TCPH");
					 hdbd276JE = get_value_byDir(BO,dirParent+ ".DS_TV_GTCG[0].TT_TGTCG[0].NGAY_DAO_HAN");
					 hdbd278JE = get_value_byDir(BO,dirParent+ ".DS_TV_GTCG[0].TT_TGTCG[0].GIA_TRI");
					 hdbd045JE = get_value_byDir(BO,dirParent+ ".TT_BEN_BD.NGAY_SINH");
					 hdbd008JE = get_value_byDir(BO, "#.TT_DENGHI.TT_CACBEN_KI_HD.YC_HONPHOI");

				} catch (Exception e) {
					BO.addProperty("CN_GRANDPARENTS_FULLNAME", "");
					return BO;
				}
	
		String hdbd273="";
		String hdbd276="";
		String hdbd278="";
		String hdbd045="";
		String hdbd008="";
		String nhpTrue = "";
		
	
		if(hdbd273JE.isJsonPrimitive())
			hdbd273 = hdbd273JE.getAsString();		
		if(hdbd276JE.isJsonPrimitive())
			hdbd276 = hdbd276JE.getAsString();
		if(hdbd278JE.isJsonPrimitive())
			hdbd278 = hdbd278JE.getAsString();		
		if(hdbd045JE.isJsonPrimitive())
			hdbd045 = hdbd045JE.getAsString();
		if(hdbd008JE.isJsonPrimitive())
			hdbd008 = hdbd008JE.getAsString();		
		
		if(hdbd008.equals("1")){
			 nhpTrue = "Khách hàng: %s %s, vợ chồng: %s %s";
			 result = String.format(nhpTrue, hdbd273,hdbd276,hdbd278,hdbd045);
		}else{
			nhpTrue = "Khách hàng: %s %s";
			result = String.format(nhpTrue, hdbd273, hdbd276);
		}
		
		
		BO.addProperty("CN_GRANDPARENTS_FULLNAME", result);
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
			
	//		input.addProperty("ADDRESS_CHECK", "CHECK");
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
	
//	private JsonObject getMasterData(Map<String,String> map, String dir, JsonObject BO) throws SIException{
//		
//		JsonElement jsonElement=  get_value_byDir(BO,dir);	
//		if(!jsonElement.isJsonObject())
//			return BO;
//		
//		JsonObject jsonObject= jsonElement.getAsJsonObject();
//				if(jsonObject != null && !jsonObject.isJsonNull()){
//					for (Map.Entry<String, String> entry : map.entrySet()) {
////						String value = entry.getValue();
//						String[] values = entry.getValue().split(",");
//					String name=	convertMasterdataCodeToName(entry.getKey(),jsonObject.get(values[0].trim()).getAsString());
//					BO.addProperty(values.length==2 ? values[1].trim() : values[0].trim(), name);
//			        }
//		}
//		return BO;
//	}

//  private List<Map<String, String>> getMasterData1(Map<String,String> map, String dir, JsonObject BO) throws SIException{
//		
//	  List<Map<String, String>> listMap = new ArrayList<>();
//	 
//		JsonElement jsonElement=  get_value_byDir(BO,dir);	
//		if(!jsonElement.isJsonArray())
//			return listMap;
//		
//		JsonArray jsonArr= jsonElement.getAsJsonArray();
//		
//		if(jsonArr !=null && jsonArr.size()>0){
//			
//			for(JsonElement je: jsonArr){
//				 Map<String, String> mapList= new HashMap<>();
//				if(je.getAsJsonObject() != null && !je.getAsJsonObject().isJsonNull()){					
//					for (Map.Entry<String, String> entry : map.entrySet()) {			            
//						JsonElement valueJsonElement = je.getAsJsonObject().get(entry.getValue());
//						if(!valueJsonElement.isJsonPrimitive()) continue;						
//						String value = valueJsonElement.getAsString();
//						if(!entry.getKey().substring(0, 1).equals("_")){
//					    String name=	convertMasterdataCodeToName(entry.getKey(),value);
//					    mapList.put(entry.getValue(), name);
//						}else{
//							mapList.put(entry.getValue(), value);
//						}
//			        }
//				}
//				if(!mapList.isEmpty())
//				listMap.add(mapList);
//				
//			}		
//		}
//		return listMap;
//	}
  
  
 
  
}
