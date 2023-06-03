package com.si.model;

public class FromInput {
	
	protected String target;
	protected Boolean isMergeData;
	protected Object data;
	protected String siVersion;
	protected String serviceVersion;
	protected String dataSourceName;

	public FromInput() {
		// TODO Auto-generated constructor stub
	}

	public FromInput(Object data, String siVersion, String dataSourceName) {
		this.data = data;
		this.siVersion = siVersion;
		this.dataSourceName = dataSourceName;
	}
	
	public FromInput(String target, Boolean isMergeData, Object data, String siVersion, String dataSourceName) {
		
		this.target = target;
		this.isMergeData = isMergeData;
			
		this.data = data;
		this.siVersion = siVersion;
		this.dataSourceName = dataSourceName;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getSiVersion() {
		return siVersion;
	}

	public void setSiVersion(String siVersion) {
		this.siVersion = siVersion;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

}
