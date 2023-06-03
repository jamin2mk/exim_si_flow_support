package com.si.model.serviceconfig;

public class SibsConfig {

	protected String serviceVersion;
	protected String sourceAppID;
	protected String queueName;
	protected String operation;
	protected String soapActionWSDL;
	protected String businessDomain;
	protected String url;
	protected String username;
	protected String password;
	protected int timeout;

	protected IOInfo input;
	protected IOInfo output;

	public SibsConfig() {
		// TODO Auto-generated constructor stub
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public String getSourceAppID() {
		return sourceAppID;
	}

	public void setSourceAppID(String sourceAppID) {
		this.sourceAppID = sourceAppID;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getSoapActionWSDL() {
		return soapActionWSDL;
	}

	public void setSoapActionWSDL(String soapActionWSDL) {
		this.soapActionWSDL = soapActionWSDL;
	}

	public String getBusinessDomain() {
		return businessDomain;
	}

	public void setBusinessDomain(String businessDomain) {
		this.businessDomain = businessDomain;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public IOInfo getInput() {
		return input;
	}

	public void setInput(IOInfo input) {
		this.input = input;
	}

	public IOInfo getOutput() {
		return output;
	}

	public void setOutput(IOInfo output) {
		this.output = output;
	}

}
