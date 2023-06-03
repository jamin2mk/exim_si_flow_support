package com.model.add_limit_node_teller.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddLimitNodeOutputVO", namespace = "http://www.finacle.com/fixml", propOrder = {"limitPrefix","limitSuffix"})
public class AddLimitNodeOutputVO {

	@XmlElement(name = "limitPrefix", namespace = "http://www.finacle.com/fixml")
	protected String limitPrefix;
	@XmlElement(name = "limitSuffix", namespace = "http://www.finacle.com/fixml")
	protected String limitSuffix;

	public AddLimitNodeOutputVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddLimitNodeOutputVO(String limitPrefix, String limitSuffix) {
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
