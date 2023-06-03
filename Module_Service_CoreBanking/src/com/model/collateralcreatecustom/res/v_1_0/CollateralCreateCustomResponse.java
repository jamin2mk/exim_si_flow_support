package com.model.collateralcreatecustom.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CollateralCreateCustomResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class CollateralCreateCustomResponse {

	@XmlElement(name = "ResutlResponse")
	protected String resutlResponse;

	public CollateralCreateCustomResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CollateralCreateCustomResponse(String resutlResponse) {
		super();
		this.resutlResponse = resutlResponse;
	}

	public String getResutlResponse() {
		return resutlResponse;
	}

	public void setResutlResponse(String resutlResponse) {
		this.resutlResponse = resutlResponse;
	}

}
