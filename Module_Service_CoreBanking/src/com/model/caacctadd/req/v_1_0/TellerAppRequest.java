package com.model.caacctadd.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TellerAppRequest", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class TellerAppRequest {

	private String inputrequest;

	public String getInputRequest() {
		return inputrequest;
	}

	public TellerAppRequest() {
		// TODO Auto-generated constructor stub
	}

	public TellerAppRequest(String inputrequest) {
		super();
		this.inputrequest = inputrequest;
	}

	public void setInputRequest(String inputrequest) {
		this.inputrequest = inputrequest;
	}

}
