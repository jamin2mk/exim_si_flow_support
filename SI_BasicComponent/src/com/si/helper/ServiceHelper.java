package com.si.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.si.cache.CData;
import com.si.config.SI;
import com.si.consts.DATE_PATTERN;
import com.si.consts.Error;
import com.si.consts.Log;
import com.si.exception.SIException;
import com.si.model.ServiceInfo;
import com.si.model.sql.SqlStatement;

public class ServiceHelper {

	public static String invokeService(String wsURL, String xmlInput) throws Exception {

		String result = null;

		URL url = null;
		URLConnection connection = null;
		HttpURLConnection httpConn = null;
		String responseString = null;
		String outputString = "";
		OutputStream out = null;
		InputStreamReader isr = null;
		BufferedReader in = null;

		url = new URL(wsURL);
		connection = url.openConnection();
		httpConn = (HttpURLConnection) connection;

		byte[] buffer = new byte[xmlInput.length()];
		buffer = xmlInput.getBytes();

		// Set the appropriate HTTP parameters.
		httpConn.setRequestProperty("Content-Length", String.valueOf(buffer.length));
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpConn.setReadTimeout(5000);
//		httpConn.setRequestProperty("Accept-Encoding", "gzip,deflate");
//		httpConn.setRequestProperty("SOAPAction", "http://www.alsb.com/eTellerAppService/CreditCardPublish");
		httpConn.setRequestProperty("Connection", "Keep-Alive");
//		httpConn.setRequestProperty("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
		httpConn.setReadTimeout(60000);

		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		out = httpConn.getOutputStream();
		out.write(buffer);
		out.close();

		// Read the response and write it to standard out.
		isr = new InputStreamReader(httpConn.getInputStream(),"UTF-8");
		in = new BufferedReader(isr);

		while ((responseString = in.readLine()) != null) {
			outputString = outputString + responseString;
		}
		result = outputString;
		return result;
	}

	public static String generateRequest(String username, String password, String request_body) {

		String result = null;

		String preMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Header xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:alsb=\"http://www.alsb.com/\"><alsb:HeaderIn><UserName>{0}</UserName><Password>{1}</Password></alsb:HeaderIn></soap:Header><soapenv:Body>{2}</soapenv:Body></soapenv:Envelope>";
		result = MessageFormat.format(preMessage, username, password, request_body);

		return result;
	}

	public static String generateFIXMLRequest(String serviceRequestId, String serviceRequestVersion, String channelId, String requestData) {

		String result = null;
		String preMessage = "<FIXML xsi:schemaLocation=\"http://www.finacle.com/fixml CAAcctAdd.xsd\" xmlns=\"http://www.finacle.com/fixml\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Header><RequestHeader><MessageKey><RequestUUID>{0}</RequestUUID><ServiceRequestId>{1}</ServiceRequestId><ServiceRequestVersion>{2}</ServiceRequestVersion><ChannelId>{3}</ChannelId><LanguageId></LanguageId></MessageKey><RequestMessageInfo><BankId></BankId><TimeZone></TimeZone><EntityId></EntityId><EntityType></EntityType><ArmCorrelationId></ArmCorrelationId><MessageDateTime>{4}</MessageDateTime></RequestMessageInfo><Security><Token><PasswordToken><UserId></UserId><Password></Password></PasswordToken></Token><FICertToken></FICertToken><RealUserLoginSessionId></RealUserLoginSessionId><RealUser></RealUser><RealUserPwd></RealUserPwd><SSOTransferToken></SSOTransferToken></Security></RequestHeader></Header><Body>{5}</Body></FIXML>";

		String requestUUID = MessageFormat.format("CAA{0}", DateHelper.convertDateToString(new Date(), DATE_PATTERN.YYYYMMDD_HH_MM_SS));
		String messageDateTime = DateHelper.getCurrentDate(DATE_PATTERN.HYPHEN_LONG_T_S3);
		result = MessageFormat.format(preMessage, requestUUID, serviceRequestId, serviceRequestVersion, channelId, messageDateTime, requestData);

		return result;
	}

	public static ServiceInfo fetchServiceInfo(String bpmVersion, String service, boolean isLoadPckProtocol, String dataSourceName) throws Exception {

		ServiceInfo serviceInfo = null;
		
		String key_version = CacheHelper.generateKey(SI.SERVICE_CONFIG_QUERY_PSTMT, dataSourceName, bpmVersion, service);
		JsonArray records = (JsonArray) CData.LOADING.get(key_version);
		LogHelper.writeLog(Log.INFO, Log.CDATA_RESULT, records.toString());

		if (records.size() == 1) {
			String version = records.get(0).getAsJsonObject().get("service_version").getAsString();
			String serviceConfig = records.get(0).getAsJsonObject().get("service_config").getAsString();
			serviceInfo = new ServiceInfo(version, serviceConfig);

		} else {
			throw new Exception("Error in fetching version-service");
		}

		if (isLoadPckProtocol) {
			String key_pck = CacheHelper.generateKey(SI.SERVICE_PROCEDURE_QUERY_PSTMT, dataSourceName, service);
			JsonArray pck_records = (JsonArray) CData.LOADING.get(key_pck);

			if (pck_records.size() == 1) {
				String inputType = pck_records.get(0).getAsJsonObject().get("input").getAsString();
				String outputType = pck_records.get(0).getAsJsonObject().get("output").getAsString();
				serviceInfo.setDbConfig(service, inputType, outputType);

			} else {
				throw new Exception("Error in fetching target-service");
			}
		}

		return serviceInfo;
	}

	public static JsonArray loadMapInfo(String subjectCode, String dataSourceName) throws SIException {

		JsonArray result = null;

		String[] params = { subjectCode };
		String key = new SqlStatement(SI.MAP_INFO_QUERY_PSTMT, params, dataSourceName).toString();
		try {
			result = (JsonArray) CData.MAP_DATA.get(key);
			LogHelper.writeLog(Log.INFO, Log.MAP_INFO, result.toString());
		} catch (ExecutionException e) {
			throw new SIException(Error.MAP_99, "Error in load mapping-info");
		}
		return result;
	}

	public static JsonArray loadConvertInfo(String service, String convert_type, String dataSourceName) throws SIException {

		JsonArray result = null;

		String[] params = { service, convert_type };
		String key = new SqlStatement(SI.CONVERTED_INFO_QUERY_PSTMT, params, dataSourceName).toString();
		try {
			result = (JsonArray) CData.MAP_DATA.get(key);
			LogHelper.writeLog(Log.INFO, Log.CONVERTED_INFO, result.toString());
		} catch (ExecutionException e) {
			throw new SIException(Error.MAP_99, "Error in load converted-info");
		}
		return result;
	}
	
	public static JsonArray loadMasterdata(String groupCode,String code, String dataSourceName) throws SIException {

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
	
	// LOAD ROLE_NAME BY ROLE_CODE
	public static JsonArray loadRoleNamedata(String roleCode, String dataSourceName) throws SIException {

		JsonArray result = null;
		String[] params = {roleCode, roleCode};
		String key = new SqlStatement(SI.QUERY_ROLENAME_BYCODE, params, dataSourceName).toString();
		
		try {
			result = (JsonArray) CData.MAP_DATA.get(key);
			LogHelper.writeLog(Log.INFO, Log.CONVERTED_INFO, result.toString());
		} catch (ExecutionException e) {
			throw new SIException(Error.MAP_99, "Error in load converted-info");
		}
		
		return result;
	}
	
	public static String convertMasterdataCodeToName(String groupCode, String code,String dataSourceName) throws SIException {

		String result = null;
		try {
			JsonArray masterData_Info = loadMasterdata(groupCode, code,dataSourceName);
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
	
	public static JsonArray queryByPrepareStatement(String SQL,String dataSourceName, String... params) throws Exception{
		JsonArray rs = null;
		try {
			String value = CacheHelper.generateKey(SQL, dataSourceName, params);
			rs = (JsonArray) CData.LOADING.get(value);
			LogHelper.writeLog(Log.INFO, Log.CDATA_RESULT, rs.toString());
		} catch (Exception e) {
			throw new SIException(Error.EX_99, "Error in queryByPrepareStatement", e);	
		}
		
		return rs;
	}
}
