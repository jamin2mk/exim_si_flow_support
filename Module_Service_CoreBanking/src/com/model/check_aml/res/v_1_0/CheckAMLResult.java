package com.model.check_aml.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckAMLResult", propOrder = { "return_0" })
public class CheckAMLResult {

	@XmlElement(name = "return")
	protected String return_0;

	public CheckAMLResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CheckAMLResult(String return_0) {
		super();
		this.return_0 = return_0;
	}

	public String getReturn_0() {
		return return_0;
	}

	public void setReturn_0(String return_0) {
		this.return_0 = return_0;
	}

}
