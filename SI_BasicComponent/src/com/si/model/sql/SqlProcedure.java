package com.si.model.sql;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.si.model.serviceconfig.DBConfig;

public class SqlProcedure {

	private String dataSourceName;
	private DBConfig protocol;
	private JsonArray dataInputs;

	public SqlProcedure() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SqlProcedure(String dataSourceName, DBConfig protocol,
			JsonArray dataInputs) {
		super();
		this.dataSourceName = dataSourceName;
		this.protocol = protocol;
		this.dataInputs = dataInputs;
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public DBConfig getProtocol() {
		return protocol;
	}

	public void setProtocol(DBConfig protocol) {
		this.protocol = protocol;
	}

	public JsonArray getDataInputs() {
		return dataInputs;
	}

	public void setDataInputs(JsonArray dataInputs) {
		this.dataInputs = dataInputs;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
