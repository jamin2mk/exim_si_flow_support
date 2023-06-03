package com.si.helper;


import com.google.gson.JsonArray;
import com.si.model.serviceconfig.DBConfig;
import com.si.model.sql.SqlProcedure;
import com.si.model.sql.SqlStatement;

public class CacheHelper {

	public static String generateKey(String statement, String dataSourceName, String... params) {
		return new SqlStatement(statement, params, dataSourceName).toString();
	}
	
	public static String generateKeyProcedure(JsonArray inputs, String dataSourceName, DBConfig dbConfig) {
		return new SqlProcedure(dataSourceName, dbConfig, inputs).toString();
	}
}
