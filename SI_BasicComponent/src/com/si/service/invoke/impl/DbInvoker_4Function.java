package com.si.service.invoke.impl;

import Handler.Auto_Mapping;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.si.consts.Error;
import com.si.exception.SIException;
import com.si.helper.JDBC_Helper;
import com.si.helper.ServiceHelper;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.DBConfig;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.invoke.Invoker;

public class DbInvoker_4Function implements Invoker {

	DBConfig dbConfig;
	String dataSourceName;
	SI_Log log;
	Auto_Mapping mapper = new Auto_Mapping();

	public DbInvoker_4Function(ServiceConfig serviceConfig, String dataSourceName, SI_Log log) {
		this.dbConfig = serviceConfig.getDb();
		this.dataSourceName = dataSourceName;
		this.log = log;
	}

	@Override
	public JsonElement invoke(JsonElement input) throws SIException {

		this.log.setToInput(input.toString());

		// invoke DB
		String result = JDBC_Helper.invoke_CallableStmt_4Funcion(input.getAsJsonArray(), this.dataSourceName, this.dbConfig);

		// set value to [this.output]
		JsonElement invoked_output = new Gson().fromJson(result, JsonElement.class);

		/** Write log [OutInput] **/
		this.log.setToOutput(result);
		return invoked_output;
	}

	@Override
	public JsonElement automap_input(JsonElement data) throws SIException {

		JsonObject fromData = data.getAsJsonObject();
		JsonObject toData = new JsonObject();

		JsonArray inputs;
		try {
			String subjectCode = this.dbConfig.getInput().getSubjectCode();
			JsonArray map_info = ServiceHelper.loadMapInfo(subjectCode, this.dataSourceName);
			if (map_info.size() > 0) {
				String input = this.mapper.start_mapping_bo(fromData, toData, map_info);

				JsonObject input_JO = new Gson().fromJson(input, JsonObject.class);
				inputs = input_JO.get("data").getAsJsonArray();
			} else {
				inputs = new JsonArray();
			}

		} catch (SIException e) {
			throw e;
		} catch (Exception e) {
			throw new SIException(Error.MAP_99, "Error in mapping input", e);
		}
		return inputs;
	}

	@Override
	public JsonElement automap_output(JsonElement output, JsonElement data) throws SIException {

		if (output.isJsonPrimitive() || output.getAsJsonArray().size() > 0) {
			JsonObject toData = data.getAsJsonObject();

			try {
				String subjectCode = this.dbConfig.getOutput().getSubjectCode();

				JsonArray map_info = ServiceHelper.loadMapInfo(subjectCode, this.dataSourceName);
				if (map_info.size() > 0) {

					JsonObject fromData = new JsonObject();
					fromData.add("data", output);

					this.mapper.start_mapping_bo(fromData, toData, map_info);
				} else {
					data = output;
				}

			} catch (Exception e) {
				throw new SIException(Error.MAP_99, "Error in mapping output", e);
			}
		}
		return data;
	}
}
