package com.helper;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.script.ScriptException;

import Handler.Auto_Mapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.si.consts.Error;
import com.si.consts.TYPE;
import com.si.exception.SIException;
import com.si.helper.ServiceHelper;


public class SupportBMHelper {
	
	private Auto_Mapping mapper;
	private String processDataSource;
	
	
	
	public SupportBMHelper(Auto_Mapping mapper, String processDataSource) {
		super();
		this.mapper = mapper;
		this.processDataSource = processDataSource;
	}

	public JsonElement get_value_byDir(JsonObject BO, String dir) throws SIException {

		try {
			JsonObject result = this.mapper.get_value_byDir(BO, "#", dir);
			if (result.get(TYPE.GET_HASVALUE).getAsBoolean()) {
				return result.get(TYPE.GET_RES);
			} else {
				throw new SIException(Error.MAP_99, String.format("Has no properties from dir: [%s] ", dir));
			}
		} catch (NumberFormatException | ScriptException e) {
			// TODO Auto-generated catch block
			throw new SIException(Error.MAP_99, String.format("Can't get value by dir: [%s]", dir), e);
		}
	}
	
	public String convertMasterdataCodeToName(String groupCode, String code) throws SIException {

		String result = null;
		try {
			JsonArray masterData_Info = ServiceHelper.loadMasterdata(groupCode, code, this.processDataSource);
			if(masterData_Info != null && masterData_Info.size() > 0){
				for (JsonElement masterData : masterData_Info) {
					if(code.equalsIgnoreCase(masterData.getAsJsonObject().get("code").getAsString())){
						result = masterData.getAsJsonObject().get("name").getAsString();
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new SIException(Error.MAP_99, "Error in converting masterdata-code-to-name", e);				
		}
		return result;
	}
	
 
	public JsonObject getMasterData(Map<String,String> map, String dir, JsonObject BO) throws SIException{
		
		JsonElement jsonElement= null;		
		try {
			jsonElement = get_value_byDir(BO,dir);	
		} catch (Exception e) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String[] values = entry.getValue().split(",");
				String[] twoKey = values[1].split("\\|");
				for(String tk : twoKey)
					BO.addProperty(values.length==2 ? tk.trim() : values[0].trim(), "");
	        }
			return BO;
		}		
		if(jsonElement == null || !jsonElement.isJsonObject())
			return BO;
		
		JsonObject jsonObject= jsonElement.getAsJsonObject();
				if(jsonObject != null && !jsonObject.isJsonNull()){
					for (Map.Entry<String, String> entry : map.entrySet()) {
//						String value = entry.getValue();
					String[] values = entry.getValue().split(",");
					String name="";
					if(jsonObject.get(values[0].trim()) != null && !jsonObject.get(values[0].trim()).isJsonNull()){
						try {
							name=	convertMasterdataCodeToName(entry.getKey(),jsonObject.get(values[0].trim()).getAsString());
						} catch (Exception e) {
							
						}
					}
					String[] twoKey = values[1].split("\\|");
					for(String tk : twoKey)
					BO.addProperty(values.length==2 ? tk.trim() : values[0].trim(), name);
			        }
		}
		return BO;
	}
   
	public List<Map<String, String>> getMasterData1(Map<String,String> map, String dir, JsonObject BO) throws SIException{
		
		  List<Map<String, String>> listMap = new ArrayList<>();
		 
			JsonElement jsonElement=  get_value_byDir(BO,dir);	
			if(!jsonElement.isJsonArray())
				return listMap;
			
			JsonArray jsonArr= jsonElement.getAsJsonArray();
			
			if(jsonArr !=null && jsonArr.size()>0){
				
				for(JsonElement je: jsonArr){
					if(!je.isJsonObject()) continue;
					 Map<String, String> mapList= new HashMap<>();
					if(je.getAsJsonObject() != null && !je.getAsJsonObject().isJsonNull()){					
						for (Map.Entry<String, String> entry : map.entrySet()) {			            
							JsonElement valueJsonElement = je.getAsJsonObject().get(entry.getValue());
							if(valueJsonElement== null || !valueJsonElement.isJsonPrimitive()) continue;						
							String value = valueJsonElement.getAsString();
							if(!entry.getKey().substring(0, 1).equals("_")){
								String name = "";
								try {
									  name=	convertMasterdataCodeToName(entry.getKey(),value);
								} catch (Exception e) {
										name = value;
								}
						   
						    mapList.put(entry.getValue(), name);
							}else{
								mapList.put(entry.getValue(), value);
							}
				        }
					}
					if(!mapList.isEmpty())
					listMap.add(mapList);
					
				}		
			}
			return listMap;
		}

	public JsonObject getAddress(JsonObject BO,String dir ,String field_dctt, String field_dcll) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("_DIA_CHI_CUTHE", "DIA_CHI_CUTHE");
		map.put("_LOAI_DIA_CHI", "LOAI_DIA_CHI");
		map.put("QUOCGIA", "QUOC_GIA");
		map.put("QUAN", "QUAN_HUYEN");
		map.put("XA", "XA_PHUONG");
		map.put("TINH", "TINH_TP");
		BO.addProperty(field_dctt, "");
		BO.addProperty(field_dcll, "");
		List<Map<String, String>> listMap1 = null;
		try {
			listMap1=	getMasterData1(map,dir, BO);
		} catch (Exception e) {
			return BO;
		}	
		System.out.println(listMap1);
		for (Map<String, String> mapDC : listMap1) {
			if (mapDC.get("LOAI_DIA_CHI")!= null && mapDC.get("LOAI_DIA_CHI").equalsIgnoreCase("HKTHUONGTRU")) {
				String dctt = String.format("%s, %s, %s, %s, %s. ",
						mapDC.get("DIA_CHI_CUTHE"), mapDC.get("XA_PHUONG"),
						mapDC.get("QUAN_HUYEN"), mapDC.get("TINH_TP"),
						mapDC.get("QUOC_GIA"));
				BO.addProperty(field_dctt, dctt.replace(", null", ""));
			}
			if (mapDC.get("LOAI_DIA_CHI")!= null && mapDC.get("LOAI_DIA_CHI").equalsIgnoreCase("DCLIENHE")) {
				String dctt = String.format("%s, %s, %s, %s, %s. ",
						mapDC.get("DIA_CHI_CUTHE"), mapDC.get("XA_PHUONG"),
						mapDC.get("QUAN_HUYEN"), mapDC.get("TINH_TP"),
						mapDC.get("QUOC_GIA"));
				BO.addProperty(field_dcll, dctt.replace(", null", ""));
			}
		}
		map.clear();
		return BO;
	}
	
	public String getPerContAddress(JsonObject jo, String dir) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("_DIA_CHI_CUTHE", "DIA_CHI_CUTHE");
		map.put("_LOAI_DIA_CHI", "LOAI_DIA_CHI");
		map.put("QUOCGIA", "QUOC_GIA");
		map.put("QUAN", "QUAN_HUYEN");
		map.put("XA", "XA_PHUONG");
		map.put("TINH", "TINH_TP");
		String PER_CONT_ADDRESS = "";
		List<Map<String, String>> listMap1 = null;
		try {
			listMap1=	getMasterData1(map,dir, jo);
		} catch (Exception e) {
			return PER_CONT_ADDRESS;
		}
		
		for (Map<String, String> mapDC : listMap1) {
			if (mapDC.get("LOAI_DIA_CHI")!= null &&mapDC.get("LOAI_DIA_CHI").equalsIgnoreCase("HKTHUONGTRU")) {
				String dctt = String.format("%s, %s, %s, %s, %s. ",
						mapDC.get("DIA_CHI_CUTHE"), mapDC.get("XA_PHUONG"),
						mapDC.get("QUAN_HUYEN"), mapDC.get("TINH_TP"),
						mapDC.get("QUOC_GIA"));
				//target.addProperty("NDV_PER_ADDRESS", dctt);
				PER_CONT_ADDRESS += String.format(";Địa chỉ thường chú: %s", dctt.replace(", null", "")) ;
			}
			if (mapDC.get("LOAI_DIA_CHI")!= null && mapDC.get("LOAI_DIA_CHI").equalsIgnoreCase("DCLIENHE")) {
				String dctt = String.format("%s, %s, %s, %s, %s. ",
						mapDC.get("DIA_CHI_CUTHE"), mapDC.get("XA_PHUONG"),
						mapDC.get("QUAN_HUYEN"), mapDC.get("TINH_TP"),
						mapDC.get("QUOC_GIA"));
				//target.addProperty("NDV_CONT_ADDRESS", dctt);
				PER_CONT_ADDRESS += String.format(";Địa chỉ liên lạc: %s", dctt.replace(", null", ""));
			}
		}
		if(PER_CONT_ADDRESS.startsWith(";"))
			PER_CONT_ADDRESS = PER_CONT_ADDRESS.substring(1);
		if(PER_CONT_ADDRESS.endsWith(";"))
			PER_CONT_ADDRESS = PER_CONT_ADDRESS.substring(0, PER_CONT_ADDRESS.length() -1);
		map.clear();
		
		return PER_CONT_ADDRESS;
	}
	public JsonObject getAddress(JsonObject BO,String dir ,String field_dctt, String field_dcll, JsonObject target) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("_DIA_CHI_CUTHE", "DIA_CHI_CUTHE");
		map.put("_LOAI_DIA_CHI", "LOAI_DIA_CHI");
		map.put("QUOCGIA", "QUOC_GIA");
		map.put("QUAN", "QUAN_HUYEN");
		map.put("XA", "XA_PHUONG");
		map.put("TINH", "TINH_TP");
		target.addProperty(field_dctt, "");
		target.addProperty(field_dcll, "");
		List<Map<String, String>> listMap1 = null;
		try {
			listMap1=	getMasterData1(map,dir, BO);
		} catch (Exception e) {
			return target;
		}	
		for (Map<String, String> mapDC : listMap1) {
			if (mapDC.get("LOAI_DIA_CHI")!= null && mapDC.get("LOAI_DIA_CHI").equalsIgnoreCase("HKTHUONGTRU")) {
				String dctt = String.format("%s, %s, %s, %s, %s. ",
						mapDC.get("DIA_CHI_CUTHE"), mapDC.get("XA_PHUONG"),
						mapDC.get("QUAN_HUYEN"), mapDC.get("TINH_TP"),
						mapDC.get("QUOC_GIA"));
				target.addProperty(field_dctt, dctt.replace(", null", ""));
			}
			if (mapDC.get("LOAI_DIA_CHI")!= null && mapDC.get("LOAI_DIA_CHI").equalsIgnoreCase("DCLIENHE")) {
				String dctt = String.format("%s, %s, %s, %s, %s. ",
						mapDC.get("DIA_CHI_CUTHE"), mapDC.get("XA_PHUONG"),
						mapDC.get("QUAN_HUYEN"), mapDC.get("TINH_TP"),
						mapDC.get("QUOC_GIA"));
				target.addProperty(field_dcll, dctt.replace(", null", ""));
			}
		}
		map.clear();
		return target;
	}
	
	public JsonObject toCheck(String dir, String key, JsonObject BO) throws SIException{
		String status = "0";
		try {
			JsonElement je = get_value_byDir(BO, dir);
			if(je.isJsonPrimitive())
				if(je.getAsString().equals("0") || je.getAsString().equals("1"))
			             status=	je.getAsString();
		} catch (Exception e) {
			status = "0";
		}
		if(status.equalsIgnoreCase("1")){
			String[] keys = key.split("\\|") ;
			for(String k: keys){
				BO.addProperty(k, "CHECK");
			}
		}else if(status.equalsIgnoreCase("0")){
			String[] keys = key.split("\\|") ;
			for(String k: keys)
			BO.addProperty(k, "UNCHECK");
		}
		return BO;
	}
	public JsonObject toCheck(String dir, String key, JsonObject BO, String condition) throws SIException{
		
		String condition1 = "CHECK";
		String condition2 = "UNCHECK";
		if(condition.equalsIgnoreCase("TRUE")){
			condition1 = "TRUE";
			condition2 = "FALSE";
		}	
		String status = "0";
		try {
			JsonElement je = get_value_byDir(BO, dir);
			if(je.isJsonPrimitive())
				if(je.getAsString().equals("0") || je.getAsString().equals("1"))
			             status=	je.getAsString();
		} catch (Exception e) {
			status = "0";
		}

			if (status.equalsIgnoreCase("1")) {
				BO.addProperty(key, condition1);
			} else if (status.equalsIgnoreCase("0")) {
				BO.addProperty(key, condition2);
			}

		return BO;
	}
	
public JsonObject toRadio(String dir, String[] keys, JsonObject BO,  String option) throws SIException {
		
		
		String condition1 = "CHECK";
		String condition2 = "UNCHECK";
		if(option.equalsIgnoreCase("TRUE")){
			condition1 = "TRUE";
			condition2 = "FALSE";
		}
		
		JsonElement jsonElement = null;
		try {
			jsonElement= get_value_byDir(BO, dir);
			if(jsonElement == null || !jsonElement.isJsonPrimitive()){
				for(String key: keys){
					BO.addProperty(key, condition2);
				}
				return BO;
			}
		} catch (Exception e) {
			for(String key: keys){
				BO.addProperty(key, condition2);
			}
			return BO;
		}
				
			String value = jsonElement.getAsString();
			try {
				Integer index = Integer.parseInt(value);
				for(String key: keys){
					if(index != null && keys.length - 1 >= index -1 && index >= 1 && key.equals(keys[index - 1])){
						BO.addProperty(key, condition1);
					}else{
						BO.addProperty(key, condition2);
					}
				}
				
			} catch (NumberFormatException e) {
				for(String key: keys){	
						BO.addProperty(key, condition2);
				}
			}
	
		return BO;
	}

public JsonObject sum(Map<String,String> map, JsonArray jsonArray, JsonObject BO){
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
				if (jo.get(entry.getKey())!=null && jo.get(entry.getKey()).isJsonPrimitive())
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
	
	 public JsonArray getTable(Map<String,String> map, String dir, JsonObject BO) throws SIException{
			
		 JsonArray jsonArrReturn = new JsonArray();
		 JsonElement jsonElement= null;
		 try {
			  jsonElement=  get_value_byDir(BO,dir);	
			  if(jsonElement == null || jsonElement.isJsonNull()) return jsonArrReturn;
		} catch (Exception e) {
			return jsonArrReturn;
		}
		 			
			if(jsonElement.isJsonArray())
				return getTableHasDirArray(map, jsonArrReturn, jsonElement.getAsJsonArray(), null, null);
			
			return jsonArrReturn;
			
		}
	 public JsonArray getTable(Map<String,String> map, String dir, JsonObject BO, JsonObject condition) throws SIException{
			
		 JsonArray jsonArrReturn = new JsonArray();
		 JsonElement jsonElement= null;
		 try {
			  jsonElement=  get_value_byDir(BO,dir);	
			  if(jsonElement == null || jsonElement.isJsonNull()) return jsonArrReturn;
		} catch (Exception e) {
			return jsonArrReturn;
		}
		 			
			if(jsonElement.isJsonArray())
				return getTableHasDirArray(map, jsonArrReturn, jsonElement.getAsJsonArray(), null, condition);
			
			return jsonArrReturn;
			
		}
	 public JsonArray getTable(Map<String,String> map, String dir, JsonObject BO, String findToKey) throws SIException{
			
		 JsonArray jsonArrReturn = new JsonArray();
		 JsonElement jsonElement= null;
		 JsonElement jsonElementOfNewDir= null;
		 String[] dirArr = dir.split("\\.");
		 String newDir = "";
		 for(String key: dirArr){			 
			 if(key.equalsIgnoreCase(findToKey))
				 break;
			 newDir += key+".";
		 }		
		 newDir+= findToKey;
		 try {
			  jsonElement=  get_value_byDir(BO,dir);	
			  jsonElementOfNewDir=  get_value_byDir(BO,newDir);
			  if(jsonElement == null || jsonElement.isJsonNull() || jsonElementOfNewDir == null || jsonElementOfNewDir.isJsonNull()) 
				  return jsonArrReturn;
		} catch (Exception e) {
			return jsonArrReturn;
		}
			if(jsonElement.isJsonArray())
				return getTableHasDirArray(map, jsonArrReturn, jsonElement.getAsJsonArray(), jsonElementOfNewDir, null);
			
			return jsonArrReturn;
			
		}
	 
	 public JsonArray getTableInTable(Map<String,String> map, String dir, JsonObject BO, String keyArr, JsonObject condition) throws SIException{
			
		 JsonArray jsonArrReturn = new JsonArray();
		 JsonElement jsonElement= null;
		 try {
			  jsonElement=  get_value_byDir(BO,dir);	
			  if(jsonElement == null || jsonElement.isJsonNull()) 
				  return jsonArrReturn;
		} catch (Exception e) {
			return jsonArrReturn;
		}		 
			if(jsonElement.isJsonArray())
				for(JsonElement je: jsonElement.getAsJsonArray()){
					if(conditionField(je, condition))
						jsonArrReturn.addAll(getTable(map, "#."+keyArr, je.getAsJsonObject()));
				}
			
			int count = 1;
			for(JsonElement je: jsonArrReturn){
				if(!je.isJsonNull() && je.isJsonObject())
					je.getAsJsonObject().addProperty("STT", count++);
			}
			
			return jsonArrReturn;
			
		}
	 
	 private boolean conditionField(JsonElement je, JsonObject condition){
		 if (!(!je.isJsonNull() && je.isJsonObject())) return false;		 
		 if (condition != null && je.getAsJsonObject().has("LOAI_TSBD") && condition.has("LOAI_TSBD")) {
			 JsonElement jsonElement1 = je.getAsJsonObject().get("LOAI_TSBD");
			 JsonElement jsonElement2 = condition.get("LOAI_TSBD");
			 return (jsonElement1.isJsonPrimitive() && jsonElement2.isJsonPrimitive()&& jsonElement1.getAsString().equals(jsonElement2.getAsString()));			 
		 }		 
		 return true;
	 }
	  
	  private JsonArray getTableHasDirArray(Map<String, String> map, JsonArray jsonArrReturn, JsonArray jsonArrayBO , JsonElement jeBO, JsonObject condition) throws SIException {
		  
		  if(jsonArrayBO !=null && jsonArrayBO.size()>0){
				int count = 1;
				for(JsonElement je: jsonArrayBO){
					 if(!conditionField(je, condition)) continue;
					 JsonObject jo= new JsonObject();
					 jo.addProperty("STT", count);
					if(je.getAsJsonObject() != null && !je.getAsJsonObject().isJsonNull()){					
						for (Map.Entry<String, String> entry : map.entrySet()) {					
							if (entry.getKey().startsWith("_")) {
								jo.addProperty(entry.getKey().substring(1), entry.getValue());
							}else if(entry.getKey().startsWith("*")){
								String[] masterData= entry.getValue().split(","); 
								String group = masterData[0].trim();								
								JsonElement valueJsonElement = getValueFromDir(je, masterData[1].trim());
								if(valueJsonElement== null && jeBO != null) 
									valueJsonElement =getValueFromDir(jeBO, masterData[1].trim());								
								if(valueJsonElement== null || !valueJsonElement.isJsonPrimitive())
									    continue;
								String code = valueJsonElement.getAsString();									
								String name=	convertMasterdataCodeToName(group,code);
								jo.addProperty(entry.getKey().substring(1), name);							
							}else {	
								if(entry.getValue().startsWith("*") && jeBO != null){
									jo = format(entry.getValue().substring(1), jeBO , jo , entry.getKey());
								}else{									
								    jo = format(entry.getValue(), je , jo , entry.getKey());			
								}
							}
				        }
						
					}
					jsonArrReturn.add(jo);
					count++;
				}		
			}
		  
		  return jsonArrReturn;
		
	}
	  
	  private JsonElement getValueFromDir(JsonElement je, String dir){
		  JsonElement jsonElement = null;
		  if(dir.split("\\.").length>1){
				try {
					jsonElement=  get_value_byDir(je.getAsJsonObject(), "#."+dir);				    
			    } catch (Exception e) {
				    return null;
			    }
		 }else{
			 if(je.isJsonObject())
			 jsonElement = je.getAsJsonObject().get(dir);
		 }
		  return jsonElement;
	  }


	private String dateToString(String date){
		  	String formattedString="";
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			ZonedDateTime ldt = ZonedDateTime.parse(date);
		    formattedString = ldt.format(dateFormat); 
			return formattedString;
		}
	  
	  private  JsonObject format(String value, JsonElement je, JsonObject jo, String key){

			String u = "";
			JsonElement jsonElement = getValueFromDir(je, value); 
			switch (value) {
			case "GIA_TRI":
			case "SOTIEN_DB":
			case "SOTIEN":
				if(jsonElement!= null && jsonElement.isJsonPrimitive()){
				u = String.format(Locale.GERMANY, "%,.2f %s",
						jsonElement.getAsDouble(), "VND");
				if(u.substring(u.indexOf(",")).equals(",00 VND"))
					u = u.replace(",00", "");
				System.out.println("-------------"+ u);
				jo.addProperty(key+"_number", jsonElement.getAsDouble());
				}
				jo.addProperty(key, u);
				
				break;
			case "NGAY_PH":
			case "NGAY_DAO_HAN":
			case "NGAY_CAP":
			case "NGAY_SINH":
			case "TT_DINHDANH[0].NGAY_CAP":
			case "NGAY_PHATHANH":
			case "NGAY_DAOHAN":
			case "QSSD.NGAY_CAP":	
				if(jsonElement!= null && jsonElement.isJsonPrimitive())
					if(!jsonElement.getAsString().equals("0") && !jsonElement.getAsString().equals(""))
				        u= dateToString(jsonElement.getAsString());
				jo.addProperty(key, u);
				break;
			case "TT_DINHDANH[0].SO_ID":
			case "TT_DINHDANH[0].NOI_CAP":
				try {
					JsonElement DINH_DANH = get_value_byDir(je.getAsJsonObject(), "#."+value);
					if(DINH_DANH != null && DINH_DANH.isJsonPrimitive()){
						jo.addProperty(key, DINH_DANH.getAsString());
					}else{
						jo.addProperty(key, "");
					}
				} catch (Exception e) {
					jo.addProperty(key, "");
				}				
				break;
		    case "TT_LIENLAC[0].DIA_CHI_CUTHE":
		    		if(key.contains("PER_ADDRESS")){
					     jo = getAddress(je.getAsJsonObject(), "#.TT_LIENLAC", key, key+"_ll", jo);
		    		} else if(key.contains("CONT_ADDRESS")){
		    		     jo = getAddress(je.getAsJsonObject(), "#.TT_LIENLAC", key+"_tt", key, jo);
		    		}else{
		    			 jo.addProperty(key, "");
		    		}
				break;
		    case "TT_LIENLAC":
		    	String PER_CONT_ADDRESS= getPerContAddress(je.getAsJsonObject(),"#.TT_LIENLAC");
		    	 jo.addProperty(key, PER_CONT_ADDRESS);
		    	break;
			default : 
//				if(value.split("\\.").length>1){
//					try {
//					   JsonElement valueJE = 	get_value_byDir(je.getAsJsonObject(), "#."+value);
//					    if(valueJE != null && valueJE.isJsonPrimitive()){
//						     jo.addProperty(key, valueJE.getAsString());
//					   } else{
//						  jo.addProperty(key, "");
//					   }
//				    } catch (Exception e) {
//					    jo.addProperty(key, "");
//				    }
//				  break;
//				}
				if(jsonElement!= null && jsonElement.isJsonPrimitive())
					u = jsonElement.getAsString();
				jo.addProperty(key, u);
				break;
			}

			return jo;
		}
	  
		public int findIndexOfN(JsonObject BO, String dependentRequired, String keyIdElement) throws SIException{
			if(dependentRequired == null || dependentRequired.isEmpty()) return 0;
			JsonObject resource = new Gson().fromJson(dependentRequired, JsonObject.class);
			if(!resource.has("DIR") || !resource.has("ID_HOPDONG")) return 0;
			JsonElement jeDir = resource.get("DIR");
			JsonElement jeId = resource.get("ID_HOPDONG");
			if(!jeDir.isJsonPrimitive() || !jeId.isJsonPrimitive()) return 0;
			String dir = jeDir.getAsString();
			String id = jeId.getAsString();
			JsonElement jsonElement = null;
			try {
				 jsonElement = get_value_byDir(BO, dir);
			} catch (Exception e) {
				return 0;
			}
			if(jsonElement == null || !jsonElement.isJsonArray()) return 0;
			JsonArray ja = jsonElement.getAsJsonArray();			
			int count= 0;
			for (JsonElement je: ja){
				JsonObject jo = je.getAsJsonObject();
				if(jo.has(keyIdElement)&&jo.get(keyIdElement) != null && jo.get(keyIdElement).getAsString().equals(id)){
					return count;
				}
				count++;
			}
			return 0;
		}
		
		public String modifiedDIR(String dependentRequired, String newDir){
			if(dependentRequired == null || dependentRequired.isEmpty()) return dependentRequired;
			JsonObject resource = new Gson().fromJson(dependentRequired, JsonObject.class);
			if(resource.has("DIR")) resource.addProperty("DIR", newDir);
			return resource.toString();
		}
	
		public JsonArray addElementToInputData(JsonObject jo, JsonArray InputData){
			if(jo == null)
				return InputData;			
			for(JsonElement je: InputData){
				if(!je.isJsonObject() || je.isJsonNull())
					continue;
				for(String key: jo.keySet()){
					if(jo.get(key) != null || !jo.get(key).isJsonNull() || jo.get(key).isJsonPrimitive()) {
					  je.getAsJsonObject().addProperty(key, jo.get(key).getAsString());
					}
				}
			}
			return InputData;
		}
		public JsonObject getObjectByDepedenRequired(JsonObject BO,String dependentRequired) throws SIException{
			JsonObject rs =null;
			if(dependentRequired != null && dependentRequired != ""){
				JsonObject dR = new Gson().fromJson(dependentRequired, JsonObject.class);
				String DIR = (dR.has("DIR") &&  !dR.get("DIR").isJsonNull() ) ?  dR.get("DIR").getAsString() : null;
				String ID_HOPDONG =  (dR.has("ID_HOPDONG") && !dR.get("ID_HOPDONG").isJsonNull()) ? dR.get("ID_HOPDONG").getAsString() : null;
				JsonElement ele = get_value_byDir(BO, DIR);
				int count = 0;
				if(ele != null && ele.isJsonArray()){
					JsonArray  arr = ele.getAsJsonArray();
						if(!arr.isEmpty() && arr.size() > 0){
							for (JsonElement e : arr) {
								Set<Entry<String, JsonElement>> entrySet = e.getAsJsonObject().entrySet();
								if(entrySet != null && entrySet.size() > 0){
									for (Entry<String, JsonElement> entry : entrySet) {
										if(entry.getValue() != null && !entry.getValue().isJsonNull() && entry.getValue().isJsonPrimitive() && entry.getValue().getAsString().equalsIgnoreCase(ID_HOPDONG)){
											rs = e.getAsJsonObject();
											count++;
											break;
										}
									}
								}
								if(count  > 0){
									break;
								}
							}
						}
				}
			}
			return rs;
		}
		
	    public String getDirTSBD(JsonObject BO, String dirParent){
	    	
			JsonElement je = null;
			String resultDir = "";
	    	try {
				je=  get_value_byDir(BO, dirParent);				    
		    } catch (Exception e) {
			    return resultDir;
		    }
			
			if(je != null && !je.isJsonNull() & je.isJsonObject()){
				JsonObject jo = je.getAsJsonObject();
				if(jo.has("LOAI_TSBD")){
					String loai_tsbd = jo.get("LOAI_TSBD").getAsString();
					if(loai_tsbd.equalsIgnoreCase("TSBD01")){
						return dirParent+ ".DS_BDS";
					}else if(loai_tsbd.equalsIgnoreCase("TSBD07")){
						return dirParent+ ".DS_QTS";
					}else if(loai_tsbd.equalsIgnoreCase("TSBD09")){
						return dirParent+ ".DS_TV_GTCG";
					}else if(loai_tsbd.equalsIgnoreCase("TSBD03")){
						return dirParent+ ".DS_PTVT";
					}
				}				
			}
	    	return "";
		}
}
