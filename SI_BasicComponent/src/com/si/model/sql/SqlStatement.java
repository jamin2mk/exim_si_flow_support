package com.si.model.sql;

import com.google.gson.Gson;

public class SqlStatement {

	String statement;
	String[] params;
	String dataSourceName;

	public SqlStatement(String statement, String[] params, String dataSourceName) {

		this.statement = statement;
		this.params = params;
		this.dataSourceName = dataSourceName;
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
