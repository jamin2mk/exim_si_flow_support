package com.model.unlink_collateral_details.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "UnlinkCollateralDetailsResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class UnlinkCollateralDetailsResponse {

	@XmlElement(name = "ResutlResponse")
	protected String resultResponse;

	public UnlinkCollateralDetailsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnlinkCollateralDetailsResponse(String resultResponse) {
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
