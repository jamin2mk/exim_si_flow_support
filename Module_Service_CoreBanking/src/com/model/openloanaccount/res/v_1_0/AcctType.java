package com.model.openloanaccount.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcctType", propOrder = {"schmCode","schmType"})
public class AcctType {

	@XmlElement(name = "SchmCode")
	protected String schmCode;
	@XmlElement(name = "SchmType")
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
