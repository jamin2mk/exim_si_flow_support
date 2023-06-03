package com.model.link_collateral_with_limit.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "LinkCollateralWithLimitResponse", namespace = "http://www.alsb.com/")
public class LinkCollateralWithLimitResponse {

	@XmlElement(name = "ResutlResponse")
	protected String resutlResponse;

	public LinkCollateralWithLimitResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LinkCollateralWithLimitResponse(String resutlResponse) {
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
