package com.si.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.si.consts.Log;
import com.si.helper.JDBC_Helper;
import com.si.helper.LogHelper;
import com.si.model.sql.SqlProcedure;
import com.si.model.sql.SqlStatement;

public class CData {

	public static LoadingCache<String, JsonElement> LOADING = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build(new CacheLoader<String, JsonElement>() {

		public JsonElement load(String key) throws Exception {

			SqlStatement sqlStatement = new Gson().fromJson(key, SqlStatement.class);
			JsonElement result = JDBC_Helper.queryByPrepareStatement_3(sqlStatement.getStatement(), sqlStatement.getParams(), sqlStatement.getDataSourceName());

			LogHelper.writeLog(Log.INFO, Log.CDATA_CALL_FROM_DB, "");
			return result;
		}
	});

	public static LoadingCache<String, JsonElement> MAP_DATA = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build(new CacheLoader<String, JsonElement>() {

		public JsonElement load(String key) throws Exception {

			SqlStatement sqlStatement = new Gson().fromJson(key, SqlStatement.class);
			JsonElement result = JDBC_Helper.queryByPrepareStatement_3(sqlStatement.getStatement(), sqlStatement.getParams(), sqlStatement.getDataSourceName());
			LogHelper.writeLog(Log.INFO, Log.CDATA_CALL_FROM_DB, "");
			return result;
		}
	});
	
	/*
	 * String result = JDBC_Helper.invoke_CallableStmt(input.getAsJsonArray(), this.dataSourceName, this.dbConfig);

		// set value to [this.output]
		JsonElement invoked_output = new Gson().fromJson(result, JsonElement.class);
	 */
	
	public static LoadingCache<String, JsonElement> SERVICE_DATA = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES).build(new CacheLoader<String, JsonElement>() {

		public JsonElement load(String key) throws Exception {
			
			SqlProcedure pro = new Gson().fromJson(key, SqlProcedure.class);
			String result = JDBC_Helper.invoke_CallableStmt(pro.getDataInputs(), pro.getDataSourceName(), pro.getProtocol());

			// set value to [this.output]
			JsonElement invoked_output = new Gson().fromJson(result, JsonElement.class);

			LogHelper.writeLog(Log.INFO, Log.CDATA_CALL_FROM_DB, "");
			return invoked_output;
		}
	});
}
