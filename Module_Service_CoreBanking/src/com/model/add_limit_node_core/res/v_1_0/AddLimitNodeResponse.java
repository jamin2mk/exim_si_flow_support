package com.model.add_limit_node_core.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AddLimitNodeResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddLimitNodeResponse {

	@XmlElement(name = "status")
	protected String status;
	@XmlElement(name = "limitPrefix")
	protected String limitPrefix;
	@XmlElement(name = "limitSuffix")
	protected String limitSuffix;

	public AddLimitNodeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddLimitNodeResponse(String status, String limitPrefix,
			String limitSuffix) {
		super();
		this.status = status;
		this.limitPrefix = limitPrefix;
		this.limitSuffix = limitSuffix;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLimitPrefix() {
		return limitPrefix;
	}

	public void setLimitPrefix(String limitPrefix) {
		this.limitPrefix = limitPrefix;
	}

	public String getLimitSuffix() {
		return limitSuffix;
	}

	public void setLimitSuffix(String limitSuffix) {
		this.limitSuffix = limitSuffix;
	}

}
