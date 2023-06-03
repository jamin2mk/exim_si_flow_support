package com.model.check_aml.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CheckAML", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class CheckAMLRequest {

	protected String arg0;

	public CheckAMLRequest() {
		// TODO Auto-generated constructor stub
	}

	public CheckAMLRequest(String arg0) {
		super();
		this.arg0 = arg0;
	}

	public String getArg0() {
		return arg0;
	}

	public void setArg0(String arg0) {
		this.arg0 = arg0;
	}

}
