package com.si.service.invoke.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import Handler.Auto_Mapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.si.consts.Error;
import com.si.consts.SYS;
import com.si.exception.SIException;
import com.si.helper.ServiceHelper;
import com.si.model.SI_Log;
import com.si.model.env.GenDocEnv;
import com.si.service.invoke.Invoker;

public class GenDocInvoker implements Invoker {

	String docName;
	String moduleCode = SYS.EXF;

	GenDocEnv genDocEnv;
	String dataSourceName;
	SI_Log log;
	Auto_Mapping mapper = new Auto_Mapping();

	JsonObject data;

	public GenDocInvoker(String docName, GenDocEnv genDocEnv, String dataSourceName, SI_Log log) {
		this.docName = docName;
		this.genDocEnv = genDocEnv;
		this.dataSourceName = dataSourceName;
		this.log = log;
	}
	public GenDocInvoker(String docName, GenDocEnv genDocEnv, String dataSourceName, SI_Log log, int indexOfN) {
		this.docName = docName;
		this.genDocEnv = genDocEnv;
		this.dataSourceName = dataSourceName;
		this.log = log;
		this.mapper = new Auto_Mapping(indexOfN);
	}
	

	public GenDocInvoker setMapper(int indexOfN){
		this.mapper = new Auto_Mapping(indexOfN);
		return this;
	}

	@Override
	public JsonElement invoke(JsonElement input) throws SIException {
		String logInput = String.format("%s: %s", "Input Gendoc", input.toString());
		System.out.println(logInput);
		String result = null;
		String output = null;
		StringBuffer response = new StringBuffer();

		URL urlReq;
		try {
			urlReq = new URL(this.genDocEnv.getUrl());
		} catch (MalformedURLException e2) {
			throw new SIException("", "");
		}
		HttpURLConnection conn;
		try {
			conn = (HttpURLConnection) urlReq.openConnection();
		} catch (IOException e2) {
			throw new SIException(Error.GENDOC_99, "Can not connect to server");
		}
		conn.setDoOutput(true);
		try {
			conn.setRequestMethod("POST");
		} catch (ProtocolException e2) {
			throw new SIException("", "");
		}
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("charset", "UTF-8");

		OutputStream os;
		try {
			os = conn.getOutputStream();
		} catch (IOException e1) {
			throw new SIException("", "");
		}

		/** Write log [ToInput] **/
		this.log.setToInput(input.toString());

		try {
			os.write(input.toString().getBytes("UTF-8"));
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
			throw new SIException(Error.GD_99, e1.getMessage());
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

		return new Gson().fromJson(result, JsonElement.class);
	}

	@Override
	public JsonElement automap_input(JsonElement data) throws SIException {

		this.data = data.getAsJsonObject();

		JsonObject result = new JsonObject();
		String subjectCode = String.format("%s.IN", docName);

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

		String subjectCode = String.format("%s.OUT", docName);
		JsonArray map_info = ServiceHelper.loadMapInfo(subjectCode, this.dataSourceName);

		try {
			String result = this.mapper.start_mapping_bo(output.getAsJsonObject(), toData, map_info);
			data = new Gson().fromJson(result, JsonElement.class);
		} catch (Exception e) {
			throw new SIException(Error.MAP_99, "Error in automap output", e);
		}
		return data;
	}
}
