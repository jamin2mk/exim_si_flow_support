package com.helper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.si.cache.CData;
import com.si.config.SI;
import com.si.consts.Error;
import com.si.consts.Log;
import com.si.exception.SIException;
import com.si.helper.LogHelper;
import com.si.model.sql.SqlStatement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Handler.Auto_Mapping;

public class LGIssuesHandling {
	
	//EXCHANGERADIOBUTTON(CHECK-UNCHECK) METHOD
	public static void RadioButtonExchange(JsonArray inputData, String... fieldslist) throws SIException {
		JsonObject inputDataCFG = inputData.get(0).getAsJsonObject();
		for(int i=0; i<fieldslist.length; i++){
			if((!inputDataCFG.get(fieldslist[i]).isJsonNull() && inputDataCFG.get(fieldslist[i]).getAsString().equalsIgnoreCase(""))
					|| inputDataCFG.get(fieldslist[i]).isJsonNull()
					|| inputDataCFG.get(fieldslist[i]).getAsString().trim().length() > 2) {
				inputData.get(0).getAsJsonObject().addProperty(fieldslist[i], "UNCHECK");
			}else {
				if(inputDataCFG.get(fieldslist[i]).getAsInt()==i+1){
					inputData.get(0).getAsJsonObject().addProperty(fieldslist[i], "CHECK");
				} else {
					inputData.get(0).getAsJsonObject().addProperty(fieldslist[i], "UNCHECK");
				}
			}			
		}
	}
	
	//EXCHANGERADIOBUTTON METHOD
	public static void CheckBoxExchange(JsonArray inputData, String fieldslist) throws SIException {
		JsonObject inputDataCFG = inputData.get(0).getAsJsonObject();
		if((!inputDataCFG.get(fieldslist).isJsonNull() && inputDataCFG.get(fieldslist).getAsString().equalsIgnoreCase(""))
				|| inputDataCFG.get(fieldslist).isJsonNull()) {
			inputData.get(0).getAsJsonObject().addProperty(fieldslist, "UNCHECK");
		} else {
			if(inputDataCFG.get(fieldslist).getAsInt()==1){
				inputData.get(0).getAsJsonObject().addProperty(fieldslist, "CHECK");
			} else {
				inputData.get(0).getAsJsonObject().addProperty(fieldslist, "UNCHECK");
			}
		}			
	}
	
	//NUMBER-FORMATER
	public static String NumberFormater(String strCurrency, String localeType, int decimalNum) throws SIException {

        String result = null;
        double doubleNumber = Double.parseDouble(strCurrency);
        String pattern0 = "###,###";
        String pattern2 = "###,###.##";

        if (localeType=="VN") {
            DecimalFormat dcfVN = (DecimalFormat) NumberFormat.getNumberInstance(new Locale("vi", "VN"));
            if (decimalNum == 0) {
                dcfVN.applyPattern(pattern0);
                result = dcfVN.format(doubleNumber);
            } else {
                dcfVN.applyPattern(pattern2);
                result= dcfVN.format(doubleNumber);
            }

        } else if (localeType=="EN") {
            DecimalFormat dcfEN = (DecimalFormat) NumberFormat.getNumberInstance(new Locale("en", "EN"));
            if(decimalNum == 0) {
                dcfEN.applyPattern(pattern0);
                result= dcfEN.format(doubleNumber);
            } else {
                dcfEN.applyPattern(pattern2);
                result= dcfEN.format(doubleNumber);
            }
        }
        return result;
    }
	
	//FULL DATE FORMATER (day...month...year...)
	public static void FullDateFormater(JsonArray inputData, String fieldDate) throws SIException {
		JsonObject inputDataCFG = inputData.get(0).getAsJsonObject();
		String[] dateSpliter = inputDataCFG.get(fieldDate).getAsString().split("/");
		inputData.get(0).getAsJsonObject().addProperty(fieldDate, String.format("Ngày %s tháng %s năm %s ", dateSpliter[0], dateSpliter[1], dateSpliter[2]));
	}

	//GETING-NUMBER-FORMATER
	public static void GetNumberFormater(JsonArray inputData, String fieldNumber, String localeType, int decimalNum) throws SIException {
		JsonObject inputDataCFG = inputData.get(0).getAsJsonObject();
		String resultFormater;
		
		if(!inputDataCFG.get(fieldNumber).isJsonNull() && inputDataCFG.get(fieldNumber).getAsString().equalsIgnoreCase("") 
				|| inputDataCFG.get(fieldNumber).isJsonNull()) {
			resultFormater = "";
		} else {
			String strNumber = inputDataCFG.get(fieldNumber).getAsString();
			resultFormater = NumberFormater(strNumber, localeType, decimalNum);
		}
		inputData.get(0).getAsJsonObject().addProperty(fieldNumber, resultFormater);
	}
	
	//GETING-NUMBER-FORMATER-TSBD
	public static void GetNumberFormaterTSBD(JsonObject inputDataCFG, String fieldNumber, String localeType, int decimalNum) throws SIException {
		String resultFormater;
		
		if(!inputDataCFG.get(fieldNumber).isJsonNull() && inputDataCFG.get(fieldNumber).getAsString().equalsIgnoreCase("") 
				|| inputDataCFG.get(fieldNumber).isJsonNull()) {
			resultFormater = "";
		} else {
			String strNumber = inputDataCFG.get(fieldNumber).getAsString();
			resultFormater = NumberFormater(strNumber, localeType, decimalNum);
		}
		inputDataCFG.addProperty(fieldNumber, resultFormater);
	}
	
	//GETING-NUMBER-IN-WORDS
	public static void GetByNumberInWords(JsonArray inputData, String fieldNumber, String filedGetWords) throws SIException {
		JsonObject inputDataCFG = inputData.get(0).getAsJsonObject();
		String inputNumber = inputDataCFG.get(fieldNumber).getAsString();
		String resultByWords = LGSuportHandler.NumberToWords(inputNumber);
		inputData.get(0).getAsJsonObject().addProperty(filedGetWords, resultByWords);
	}
	
	//GETING-NUMBER-IN-WORDS
	public static void GetByNumberInWordsTSBD(JsonObject inputDataCFG, String fieldNumber, String filedGetWords) throws SIException {
		String inputNumber = inputDataCFG.get(fieldNumber).getAsString();
		String resultByWords = LGSuportHandler.NumberToWords(inputNumber);
		inputDataCFG.addProperty(filedGetWords, resultByWords);
	}
	
	//CHECKING FIELD IN JSON-OBJECT
	public static boolean CheckElementInJsonObject(JsonObject inputJson, String key) {
        Set<Map.Entry<String, JsonElement>> entries = inputJson.entrySet();
        for (Map.Entry<String, JsonElement> entry : entries) {
            if (entry.getKey().equalsIgnoreCase(key)) {
                return true;
            }
        }
        return false;
    }
	
	// CONVERTING ADDRESS INFORMATION
	public static void GetAddressInfo(JsonArray arrayAddressBase, JsonObject BaseBO, String processDataSource, String... listAddressFields) throws SIException{
		
		JsonObject tempObject = new JsonObject();
		
		for(JsonElement addressElement : arrayAddressBase) {
			String TypeOfAddress = addressElement.getAsJsonObject().get("LOAI_DIA_CHI").getAsString();
			String DIA_CHI_CUTHE = addressElement.getAsJsonObject().has("DIA_CHI_CUTHE") && !addressElement.getAsJsonObject().get("DIA_CHI_CUTHE").isJsonNull() ? addressElement.getAsJsonObject().get("DIA_CHI_CUTHE").getAsString() : "";
			String XA_PHUONG = LGSuportHandler.GetNameFromMsDataCode("XA", addressElement.getAsJsonObject().has("XA_PHUONG") && !addressElement.getAsJsonObject().get("XA_PHUONG").isJsonNull() ? addressElement.getAsJsonObject().get("XA_PHUONG").getAsString() : "", processDataSource);
			String QUAN_HUYEN = LGSuportHandler.GetNameFromMsDataCode("QUAN", addressElement.getAsJsonObject().has("QUAN_HUYEN") && !addressElement.getAsJsonObject().get("QUAN_HUYEN").isJsonNull() ? addressElement.getAsJsonObject().get("QUAN_HUYEN").getAsString() : "", processDataSource);
			String TINH_TP = LGSuportHandler.GetNameFromMsDataCode("TINH", addressElement.getAsJsonObject().has("TINH_TP") && !addressElement.getAsJsonObject().get("TINH_TP").isJsonNull() ? addressElement.getAsJsonObject().get("TINH_TP").getAsString() : "", processDataSource);
			String QUOC_GIA = LGSuportHandler.GetNameFromMsDataCode("QUOCGIA", addressElement.getAsJsonObject().has("QUOC_GIA") && !addressElement.getAsJsonObject().get("QUOC_GIA").isJsonNull() ? addressElement.getAsJsonObject().get("QUOC_GIA").getAsString() : "", processDataSource);
			
			// Joining address type in JsonObject
			String joinedAddress = 
				    Stream.of(DIA_CHI_CUTHE, XA_PHUONG, QUAN_HUYEN, TINH_TP, QUOC_GIA)
				          .filter(str -> str != null && !str.isEmpty())
				          .collect(Collectors.joining(", "));
			
			tempObject.addProperty(TypeOfAddress, joinedAddress);
		}
		BaseBO.addProperty(listAddressFields[0], tempObject.get("HKTHUONGTRU").getAsString()); 
		BaseBO.addProperty(listAddressFields[1], tempObject.get("DCLIENHE").getAsString());

	}
	
	// EDITING BO BASE ON DEPENDEN-REQUIRED 
	public static JsonObject RequiredContactType(JsonObject BO, String dependentRequired, String ID_YC_HD) throws SIException{
		
		Auto_Mapping autoMapping = new Auto_Mapping();
		JsonObject objDependentRequired = new Gson().fromJson(dependentRequired, JsonObject.class);
		String DIRVal = "";
		String replacedValue = "";
		
		// REPLACE DIR
		if (objDependentRequired != null) {
			DIRVal = objDependentRequired.get("DIR").getAsString();
			replacedValue = DIRVal.replaceAll("#.", "#.data.");
			objDependentRequired.addProperty("DIR", replacedValue);
		}
		
		try {
			JsonArray jsonArray = (JsonArray) autoMapping.get_value_byDir(BO, objDependentRequired.get("DIR").getAsString());
			if(!jsonArray.isEmpty() && jsonArray.size() > 1){
				
				for(int i=0; i < jsonArray.size(); i++){
					if(jsonArray.get(i).getAsJsonObject().has(ID_YC_HD) && 
							!jsonArray.get(i).getAsJsonObject().get(ID_YC_HD).getAsString()
							.equalsIgnoreCase(objDependentRequired.get("ID_HOPDONG").getAsString())) {
						jsonArray.remove(jsonArray.get(i));
					}
				}
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return BO;
	};
	
	// CONVERT MASTER DATA FROM OUTPUT DATA TO NAME BY CODE
	public static void ConvertMsDataToName(JsonArray inputData, String fieldCode, String GroupMsCode, String processDataSource) throws SIException{
		JsonObject inputDataCFG = inputData.get(0).getAsJsonObject();
		
		String groupCode = !inputDataCFG.get(fieldCode).isJsonNull() ? inputDataCFG.get(fieldCode).getAsString() : null;
		String result = LGSuportHandler.GetNameFromMsDataCode(GroupMsCode, groupCode, processDataSource);
		inputDataCFG.addProperty(fieldCode, result);
	}
	
	// CONVERT MA_NV TO NAME BY CODE
	public static void ConvertUserCodeToName(JsonArray inputData, String fieldCode, String processDataSource) throws SIException {
		JsonObject inputDataCFG = inputData.get(0).getAsJsonObject();
		
		String result = LGSuportHandler.ConvertCodeToUserName(
				inputDataCFG.has(fieldCode) && !inputDataCFG.get(fieldCode).isJsonNull() 
				? inputDataCFG.get(fieldCode).getAsString():""
					, processDataSource);
		inputDataCFG.addProperty(fieldCode, result);
		
//		String userCode = inputDataCFG.get(fieldCode).getAsString();
//		String userID = "";
//		String userName = "";
//		String[] userIDSplit = userCode.split("-");
//		
//		if((userCode != null || "".equals(userCode)) && userCode.contains("-")){
//			userName = userIDSplit[1].trim();
//			inputDataCFG.addProperty(fieldCode, userName);
//		} else {
//			userID = userIDSplit[0].trim();
//			String result = LGSuportHandler.ConvertCodeToUserName(userID, processDataSource);
//			inputDataCFG.addProperty(fieldCode, result);
//		}
		
	}
	
	// GET DANH SACH DONG SO HUU
	public static void GetDSHList(JsonArray inputData, String processDataSource) throws SIException {
		JsonObject inputDataCFG = inputData.get(0).getAsJsonObject();
		JsonArray TABLE_DSH = inputDataCFG.get("TABLE_DSH").getAsJsonArray();
		JsonObject DS_DSH = new JsonObject();
		JsonArray TABLE_DSH_TEMP = new JsonArray();
		JsonArray TABLE_DSH_VALUE = new JsonArray();
		
		for(JsonElement DSHItem : TABLE_DSH){
			
			String ONG_BA = DSHItem.getAsJsonObject().has("ONG_BA") && !DSHItem.getAsJsonObject().get("ONG_BA").isJsonNull() ? DSHItem.getAsJsonObject().get("ONG_BA").getAsString() : "";
			DS_DSH.addProperty("DSH_GRANDPARENTS", LGSuportHandler.GetNameFromMsDataCode("ONGBA", ONG_BA, processDataSource));
			
			DS_DSH.addProperty("DSH_FULLNAME_INDIVIDUAL", DSHItem.getAsJsonObject().has("TEN_KH_CODAU") && !DSHItem.getAsJsonObject().get("TEN_KH_CODAU").isJsonNull() ? DSHItem.getAsJsonObject().get("TEN_KH_CODAU").getAsString():""); 
			DS_DSH.addProperty("DSH_BIRTHDAY", DSHItem.getAsJsonObject().has("NGAY_SINH") && !DSHItem.getAsJsonObject().get("NGAY_SINH").isJsonNull() ? DSHItem.getAsJsonObject().get("NGAY_SINH").getAsString():"");
			DS_DSH.addProperty("DSH_PHONE_NUMBER", DSHItem.getAsJsonObject().has("SO_DT") && !DSHItem.getAsJsonObject().get("SO_DT").isJsonNull() ? DSHItem.getAsJsonObject().get("SO_DT").getAsString():"");
			
			if(DSHItem.getAsJsonObject().has("TT_DINHDANH") && !DSHItem.getAsJsonObject().get("TT_DINHDANH").getAsJsonArray().isEmpty()) {
				JsonObject TT_DINHDANH = DSHItem.getAsJsonObject().get("TT_DINHDANH").getAsJsonArray().get(0).getAsJsonObject();
				String LOAI_ID = TT_DINHDANH.getAsJsonObject().has("LOAI_ID") && !TT_DINHDANH.getAsJsonObject().get("LOAI_ID").isJsonNull() ? TT_DINHDANH.getAsJsonObject().get("LOAI_ID").getAsString(): "";
				String NOI_CAP = TT_DINHDANH.getAsJsonObject().has("NOI_CAP") && !TT_DINHDANH.getAsJsonObject().get("NOI_CAP").isJsonNull() ? TT_DINHDANH.getAsJsonObject().get("NOI_CAP").getAsString(): "";
				
				DS_DSH.addProperty("DSH_ID_TYPE", LGSuportHandler.GetNameFromMsDataCode("LOAIID", LOAI_ID, processDataSource));
				DS_DSH.addProperty("DSH_IDNUMBER", TT_DINHDANH.has("SO_ID") && !TT_DINHDANH.get("SO_ID").isJsonNull() ? TT_DINHDANH.get("SO_ID").getAsString() : "");
				DS_DSH.addProperty("DSH_DATE_RANGE", TT_DINHDANH.has("NGAY_CAP") && !TT_DINHDANH.get("NGAY_CAP").isJsonNull() ? TT_DINHDANH.get("NGAY_CAP").getAsString() : "");
				DS_DSH.addProperty("DSH_ISSUED_BY", LGSuportHandler.GetNameFromMsDataCode("NOICAPID", NOI_CAP, processDataSource));
			} else {
				DS_DSH.addProperty("DSH_ID_TYPE", "");
				DS_DSH.addProperty("DSH_IDNUMBER", "");
				DS_DSH.addProperty("DSH_DATE_RANGE", "");
				DS_DSH.addProperty("DSH_ISSUED_BY", "");
			}
			
			if (DSHItem.getAsJsonObject().has("TT_LIENLAC") && !DSHItem.getAsJsonObject().get("TT_LIENLAC").getAsJsonArray().isEmpty()) {
				JsonArray TT_LIENLAC = DSHItem.getAsJsonObject().get("TT_LIENLAC").getAsJsonArray();
				LGIssuesHandling.GetAddressInfo(TT_LIENLAC, DS_DSH, processDataSource, "DSH_PER_ADDRESS", "DSH_CONT_ADDRESS");
			} 
			else {
				DS_DSH.addProperty("DSH_PER_ADDRESS", "");
				DS_DSH.addProperty("DSH_CONT_ADDRESS", "");
			}
			
			TABLE_DSH_TEMP.add(DS_DSH.toString());
		}
		
		for(JsonElement ele : TABLE_DSH_TEMP) {
			TABLE_DSH_VALUE.add(new Gson().fromJson(ele.getAsString(), JsonObject.class));
		}
		
		inputDataCFG.addProperty("TABLE_DSH", TABLE_DSH_VALUE.toString());
	}
	
	// CHECKING MASTER DATA (BO) BEFORE USING AUTO_MAPPING
	public static void GetFieldsOnAutoMapping(JsonObject BaseBO, String subjectCode, String dataSourceName) throws SIException {
		JsonArray resultCollection = LGSuportHandler.LoadFromFieldOnSiAutoMapping(subjectCode,dataSourceName);
		
		for (JsonElement fieldEle : resultCollection) {
			if(fieldEle.getAsJsonObject().has("from_field")){
//			String fieldValue = fieldEle.getAsJsonObject().get("from_field").getAsString();
//			String[] splitedField = fieldValue.substring(7, fieldValue.length()).split("\\.");
			
//			System.err.println("Values of: " + Arrays.toString(splitedField));
			
//			System.err.println("SubString: " + fieldValue.substring(7, fieldValue.length()));
//			System.err.println(Arrays.toString(fieldValue.split("\\.")));
			}
		}
	}
}

class LGSuportHandler {
	
	//NUMBER TO WORDS CONVERTING
	public static String LongNumbersToWords(long inputNumber) throws SIException {
        String result = "";
		long NumberLimit = 1000000000000L;
		long CurrentLimit = 0;
		long Multiplier = 0;

		final String[] HundredTOKENS = {"", "nghìn tỷ", "tỷ", "triệu", "nghìn"};
		final String[] UnitTOKEN = {"không", "một", "hai",  "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín", "mười", "mười một",
									"mười hai", "mười ba", "mười bốn", "mười lăm", "mười sáu", "mười bảy", "mười tám", "mười chín"};
		final String[] TensTOKENS = {"", "", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"};
        
        if(inputNumber < 20L) {
        	return (UnitTOKEN[(int)inputNumber]);
        }

        for(long element = inputNumber; element > 0; element %= NumberLimit, NumberLimit /= 1000) {
        	CurrentLimit = element / NumberLimit;

        	while(CurrentLimit == 0){
        		element %= NumberLimit;
        		NumberLimit /= 1000;
        		CurrentLimit = element / NumberLimit;
        		
        		++Multiplier;	
        	}

        	if(CurrentLimit > 99) {
        		result += (UnitTOKEN[(int)CurrentLimit/100] + " trăm ");
        	}
        	
        	// Bring the current hundred to tens
        	CurrentLimit %= 100;
        	
        	if (CurrentLimit > 0 && CurrentLimit < 10) {
        		result += ("lẻ " + UnitTOKEN[(int)CurrentLimit]);
        	
        	} else if (CurrentLimit >= 10 && CurrentLimit < 20) {
        		result += (UnitTOKEN[(int)CurrentLimit]);
        		
        	} else if (CurrentLimit % 10 == 0 && CurrentLimit != 0){
        		result += (TensTOKENS[(int)CurrentLimit/10]);
        		
        	} else if (CurrentLimit > 20 && CurrentLimit < 100) {
        		if (CurrentLimit %10==1) {
        			result += (TensTOKENS[(int)CurrentLimit/10] + " " + "mốt ");
        		} else if (CurrentLimit %10==5) {
        			result += (TensTOKENS[(int)CurrentLimit/10] + " " + "lăm ");
        		} else {        			
        			result += (TensTOKENS[(int)CurrentLimit/10] + " " + UnitTOKEN[(int)CurrentLimit %10] + " ");
        		}
        	}

        	if(Multiplier < 4) {
        		result += (HundredTOKENS[(int)++Multiplier] + " ");
        	}
        }		
		return result;
	}
	
	public static String NumberToWords(String inputNumber) throws SIException {
		
		String result = "";
		final String UNION_AND = " phẩy ";
		
		if (inputNumber.equalsIgnoreCase("")) {
			return result;
		}
		
		// Parsing inputNumber and replacing dot point 
        String valReplace = inputNumber.replaceAll("\\.", "");
		int decimals = valReplace.indexOf(",");
		String floatValue = null;
		String integerValue = null;
		
		// Checking decimal number or integer number		
		if(decimals >= 0) {
			floatValue = valReplace.substring(decimals + 1);
			integerValue = valReplace.substring(0, decimals);
			
			if (floatValue.startsWith("0")) {
				result += LongNumbersToWords(Long.valueOf(integerValue)) + UNION_AND + "không "
						+ LongNumbersToWords(Long.valueOf(floatValue));
			} else {
				result += LongNumbersToWords(Long.valueOf(integerValue)) + UNION_AND 
						+ LongNumbersToWords(Long.valueOf(floatValue));
			}
			
		} else {
			result += LongNumbersToWords(Long.valueOf(valReplace));
		}
		
		return result;
	}
	
	// LOADING NAME FROM MASTER-DATA BASE ON CODE
	public static JsonArray loadMasterdata(String groupCode, String code, String dataSourceName) throws SIException {

		JsonArray result = null;

		String[] params = { groupCode, groupCode,code };
		String key = new SqlStatement(SI.QUERY_MASTERDATA_BYCODE, params, dataSourceName).toString();
		try {
			result = (JsonArray) CData.MAP_DATA.get(key);
			LogHelper.writeLog(Log.INFO, Log.CONVERTED_INFO, result.toString());
		} catch (ExecutionException e) {
			throw new SIException(Error.MAP_99, "Error in load converted-info");
		}
		return result;
	}
	
	public static String GetNameFromMsDataCode(String groupCode, String code, String processDataSource) throws SIException {
		String result = null;
		try {
			JsonArray masterData_Info = loadMasterdata(groupCode, code, processDataSource);
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
	
	public static String ConvertCodeToUserName(String userCode, String processDataSource) throws SIException {
		String result = null;
		
		try {
			JsonArray userCode_Info = LoadRepresentativeData(userCode, processDataSource);
			if(userCode_Info != null && userCode_Info.size() > 0) {
				result = userCode_Info.get(0).getAsJsonObject().get("nguoi_dai_dien").getAsString();
			}
			
		} catch (Exception e) {
			throw new SIException(Error.MAP_99, "Error in converting userCode-to-userName", e);				
		}
		
		return result;
	}
	
	public static JsonArray LoadRepresentativeData(String userCode, String dataSourceName) throws SIException {
		JsonArray result = null;
		String[] params = {userCode, userCode};
		String QUERY_USERNAME_BYCODE = "SELECT NGUOI_DAI_DIEN FROM AUTH_REPRESENTATIVE WHERE TRIM(UPPER(MA_NV)) IN (SELECT TRIM(UPPER(regexp_substr(?,'[^$]+', 1, level))) AS MA_NV_TYPE FROM dual CONNECT BY regexp_substr(?, '[^$]+', 1, level) IS NOT NULL ) AND STATUS=1";
		String key = new SqlStatement(QUERY_USERNAME_BYCODE, params, dataSourceName).toString();
		
		try {
			result = (JsonArray) CData.MAP_DATA.get(key);
			LogHelper.writeLog(Log.INFO, Log.CONVERTED_INFO, result.toString());
		} catch (ExecutionException e) {
			throw new SIException(Error.MAP_99, "Error in load converted-info");
		}
		
		return result;
	}
	
	public static JsonArray LoadFromFieldOnSiAutoMapping(String subjectCode, String dataSourceName) throws SIException {
		JsonArray result = null;
		String QUERY_FIELDS_BY_CODE= "SELECT FROM_FIELD, TO_FIELD, IS_CALCULA, IS_DATE, DEFAULT_DATA, CONDITION_FIELD, CONDITION, CTR_NAME, FORMULA, IS_REQUIRED FROM SI_AUTO_MAPPING WHERE TRIM(UPPER(SUBJECT_CODE)) IN (SELECT TRIM(UPPER(regexp_substr(?,'[^$]+', 1, level))) AS GROUP_SUBJECT_CODE FROM dual CONNECT BY regexp_substr(?, '[^$]+', 1, level) IS NOT NULL ) AND STATUS=1";
		String subject_code = String.join(".",subjectCode,"OUT");
		String[] params = {subject_code, subject_code};
		String key = new SqlStatement(QUERY_FIELDS_BY_CODE, params, dataSourceName).toString();
		
		try {
			result = (JsonArray) CData.MAP_DATA.get(key);
			LogHelper.writeLog(Log.INFO, Log.CONVERTED_INFO, result.toString());
		} catch (ExecutionException e) {
			throw new SIException(Error.MAP_99, "Error in load converted-info");
		}
		
		return result;
	}
	
	public static String ConvertDateTime(String receivedDate) throws SIException {

        if (receivedDate == null || receivedDate.equals("")) {
            return "";
        }
        String[] splitedFormat = receivedDate.split("T");
        String[] splitedDate = splitedFormat[0].split("-");
        String result = String.join("/", splitedDate[2], splitedDate[1], splitedDate[0]);

        return result;
    }
	
	public static String convertDateTime(String receivedDate) throws ParseException {
        DateFormat userDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        DateFormat dateFormatNeeded = new SimpleDateFormat("dd/MM/yyyy");
        Date date = userDateFormat.parse(receivedDate);
        String convertedDate = dateFormatNeeded.format(date);
        return convertedDate;
    }
}
