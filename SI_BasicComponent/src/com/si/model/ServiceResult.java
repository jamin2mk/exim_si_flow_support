package com.si.model;

import com.google.gson.JsonElement;

public class ServiceResult {

	JsonElement result;
	SI_Log log;

	public ServiceResult(JsonElement result, SI_Log log) {
		this.result = result;
		this.log = log;
	}

	public JsonElement getResult() {
		return result;
	}

	public void setResult(JsonElement result) {
		this.result = result;
	}

	public SI_Log getLog() {
		return log;
	}

	public void setLog(SI_Log log) {
		this.log = log;
	}

}
