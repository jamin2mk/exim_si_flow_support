package com.model.caacctadd.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TellerAppResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class TellerAppResponse {

	private String outputresponse;

	public String getOutputresponse() {
		return outputresponse;
	}

	public void setOutputresponse(String outputresponse) {
		this.outputresponse = outputresponse;
	}

}
