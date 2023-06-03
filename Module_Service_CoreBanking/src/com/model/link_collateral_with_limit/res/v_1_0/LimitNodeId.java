package com.model.link_collateral_with_limit.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LimitNodeId", propOrder = {"limitPrefix","limitSuffix"})
public class LimitNodeId {

	@XmlElement(name = "LimitPrefix")
	protected String limitPrefix;
	@XmlElement(name = "LimitSuffix")
	protected String limitSuffix;

	public LimitNodeId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LimitNodeId(String limitPrefix, String limitSuffix) {
		super();
		this.limitPrefix = limitPrefix;
		this.limitSuffix = limitSuffix;
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
