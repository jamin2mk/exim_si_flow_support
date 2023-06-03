package com.si.service.invoke.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Handler.Auto_Mapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.si.consts.Error;
import com.si.consts.SYS;
import com.si.exception.SIException;
import com.si.helper.JDBC_Helper;
import com.si.helper.ServiceHelper;
import com.si.model.SI_Log;
import com.si.model.env.OdmEnv;
import com.si.service.invoke.Invoker;

public class OdmInvoker implements Invoker {

	String rule;
	String operation;
	String moduleCode = SYS.ODM;

	OdmEnv odmEnv;
	String dataSourceName;
	SI_Log log;
	Auto_Mapping mapper = new Auto_Mapping();

	JsonObject data;

	public OdmInvoker(String rule, OdmEnv odmEnv, String dataSourceName, SI_Log log) {
		this.rule = rule;
		this.odmEnv = odmEnv;
		this.dataSourceName = dataSourceName;
		this.log = log;
	}
	

	@Override
	public JsonElement invoke(JsonElement input) throws SIException {

		String result = null;
		String output = null;
		StringBuffer response = new StringBuffer();

		URL urlReq;
		try {
			this.operation = rule.substring(rule.indexOf(".") + 1, rule.length());
			urlReq = new URL(String.format("%s%s/%s", odmEnv.getUri(), odmEnv.getAppId(), this.operation));
			System.out.println("");
		} catch (MalformedURLException e2) {
			throw new SIException("", "");
		}
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) urlReq.openConnection();
		} catch (IOException e2) {
			throw new SIException(Error.ODM_99, "Can not connect to server");
		}
		conn.setDoOutput(true);
		try {
			conn.setRequestMethod("POST");
		} catch (ProtocolException e2) {
			throw new SIException("", "");
		}
		conn.setRequestProperty("Content-Type", "application/json");

		OutputStream os;
		try {
			os = conn.getOutputStream();
		} catch (IOException e1) {
			throw new SIException("", "");
		}

		JsonObject request = new JsonObject();
		request.addProperty("Input", input.toString());
		request.addProperty("__DecisionID__", UUID.randomUUID().toString());

		/** Write log [ToInput] **/
		this.log.setToInput(request.toString());

		try {
			os.write(request.toString().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new SIException("", "");
		}
		try {
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new SIException("", "");
		}
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			throw new SIException(Error.ODM_99, e1.getMessage());
		}

		int responseCode;
		try {
			responseCode = conn.getResponseCode();
		} catch (IOException e) {
			throw new SIException("", "");
		}

		try {
			while ((output = br.readLine()) != null) {
				response.append(output);
			}
		} catch (IOException e) {
			throw new SIException("", "");
		}

		result = response.toString();

		/** Write log [OutInput] **/
		this.log.setToOutput(result);

		conn.disconnect();

		/** writing log **/
		String status_result = String.format("%s: %s", "Status result", responseCode);
		String response_result = String.format("%s: %s", "Response result", result);

		System.out.println(status_result);
		System.out.println(response_result);
//		LogHelper.writeLog(Log.INFO, Log.CALLING_ODM_REST_API, status_result, response_result);

		/** insert transaction **/
		insertTransaction(input.toString(), result);

		return new Gson().fromJson(result, JsonElement.class);
	}

	@Override
	public JsonElement automap_input(JsonElement data) throws SIException {

		this.data = data.getAsJsonObject();

		JsonObject result = new JsonObject();
		String subjectCode = String.format("%s.IN", rule);

		JsonArray map_info = ServiceHelper.loadMapInfo(subjectCode, this.dataSourceName);

		try {
			this.mapper.start_mapping_bo(data.getAsJsonObject(), result, map_info);
		} catch (Exception e) {
			throw new SIException(Error.MAP_99, "Error in automap input", e);
		}
		return result;
	}

	@Override
	public JsonElement automap_output(JsonElement output, JsonElement data) throws SIException {

		JsonObject toData = new JsonObject();

		String subjectCode = String.format("%s.OUT", rule);
		JsonArray map_info = ServiceHelper.loadMapInfo(subjectCode, this.dataSourceName);

		try {
			String result = this.mapper.start_mapping_bo(output.getAsJsonObject(), toData, map_info);
			data = new Gson().fromJson(result, JsonElement.class);
		} catch (Exception e) {
			throw new SIException(Error.MAP_99, "Error in automap output", e);
		}
		return data;
	}

	private void insertTransaction(String input, String output) throws SIException {

//		JsonObject employee = this.data.get("dataObj_rule").getAsJsonObject().get("EMPLOYEE").getAsJsonObject();
		JsonObject los_bpm_information = this.data.get("LOS_BPM_INFORMATION").getAsJsonObject();

		String user = los_bpm_information.get("USER_UPDATED").getAsString();
		String role = los_bpm_information.get("ROLE_UPDATED").getAsString();
//		String taskId = los_bpm_information.get("BPM_TASK_ID").getAsString();
//		String caseId;
//		Pattern pattern = Pattern.compile("[A-Z]+\\d+");
//		Matcher matcher = pattern.matcher(taskId);
//		if (matcher.find()) {
//			caseId = matcher.group(0);
//		} else {
//			throw new SIException(Error.ODM_99, String.format("Error in fetch case-id from [%s]", taskId));
//		}
		String caseId = los_bpm_information.get("CODE").getAsString();

		List<String> inputs = new ArrayList<>();
		inputs.add(caseId);
		inputs.add(user);
		inputs.add(role);
		inputs.add(this.operation.replace("_Operation", ""));
		inputs.add(input);
		inputs.add(output);

		JDBC_Helper.invoke_CallableStmt(this.dataSourceName, String.format("%s.INSERT_LN_ODM_RULE_TRANSACTION", this.odmEnv.getPck()), inputs);
	}
}
