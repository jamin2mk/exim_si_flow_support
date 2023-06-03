package com.si.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.si.model.serviceconfig.DBConfig;
import com.si.model.serviceconfig.ServiceConfig;

public class ServiceInfo {

	@SerializedName("SERVICE_VERSION")
	protected String version;

	@SerializedName("SERVICE_CONFIG")
	protected ServiceConfig serviceConfig;

	public ServiceInfo() {
		// TODO Auto-generated constructor stub
	}

	public ServiceInfo(String version, ServiceConfig serviceConfig) {
		this.version = version;
		this.serviceConfig = serviceConfig;
	}

	public ServiceInfo(String version, String serviceConfig) {
		this.version = version;
		this.serviceConfig = new Gson().fromJson(serviceConfig, ServiceConfig.class);
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

	public void setServiceConfig(ServiceConfig serviceConfig) {
		this.serviceConfig = serviceConfig;
	}

	public void setDbConfig(String procedure, String inputType, String outputType) {
		
		DBConfig dbConfig = new DBConfig();
		dbConfig.setSp(procedure);
		dbConfig.setInput(procedure, inputType);
		dbConfig.setOutput(procedure, outputType);
		
		this.getServiceConfig().setDb(dbConfig);
	}
}
