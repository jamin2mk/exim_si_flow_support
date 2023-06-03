package com.model.odacctaddrequest.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;

@XmlType(name = "AcctType", namespace = "http://www.finacle.com/fixml", propOrder = {"schmCode","schmType"})
@XmlAccessorType(XmlAccessType.FIELD)
public class AcctType {

	@SerializedName("SchmCode")
	@XmlElement(name = "SchmCode", namespace = "http://www.finacle.com/fixml")
	protected String schmCode;
	@SerializedName("SchmType")
	@XmlElement(name = "SchmType", namespace = "http://www.finacle.com/fixml")
	protected String schmType;

	public AcctType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AcctType(String schmCode, String schmType) {
		super();
		this.schmCode = schmCode;
		this.schmType = schmType;
	}

	public String getSchmCode() {
		return schmCode;
	}

	public void setSchmCode(String schmCode) {
		this.schmCode = schmCode;
	}

	public String getSchmType() {
		return schmType;
	}

	public void setSchmType(String schmType) {
		this.schmType = schmType;
	}

}
