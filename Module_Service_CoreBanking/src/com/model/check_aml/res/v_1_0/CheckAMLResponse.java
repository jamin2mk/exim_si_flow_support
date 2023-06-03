package com.model.check_aml.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "CheckAMLResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckAMLResponse", namespace = "http://www.alsb.com/", propOrder = { "checkAMLResult" })
public class CheckAMLResponse {

	@XmlElement(name = "CheckAMLResult")
	protected CheckAMLResult checkAMLResult;

	public CheckAMLResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckAMLResponse(CheckAMLResult checkAMLResult) {
		super();
		this.checkAMLResult = checkAMLResult;
	}

	public CheckAMLResult getCheckAMLResult() {
		return checkAMLResult;
	}

	public void setCheckAMLResult(CheckAMLResult checkAMLResult) {
		this.checkAMLResult = checkAMLResult;
	}

}
