package com.model.createdepositcoltrl.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CreateDepositColtrlResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateDepositColtrlResponse {

	@XmlElement(name = "ResutlResponse")
	protected String resultResponse;

	public CreateDepositColtrlResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateDepositColtrlResponse(String resultResponse) {
		super();
		this.resultResponse = resultResponse;
	}

	public String getResultResponse() {
		return resultResponse;
	}

	public void setResultResponse(String resultResponse) {
		this.resultResponse = resultResponse;
	}

}
