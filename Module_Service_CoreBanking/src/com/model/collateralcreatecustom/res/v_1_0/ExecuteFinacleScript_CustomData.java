package com.model.collateralcreatecustom.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "executeFinacleScript_CustomData")
public class ExecuteFinacleScript_CustomData {

	@XmlElement(name = "SuccessOrFailure")
	protected String successOrFailure;

	public ExecuteFinacleScript_CustomData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExecuteFinacleScript_CustomData(String successOrFailure) {
		super();
		this.successOrFailure = successOrFailure;
	}

	public String getSuccessOrFailure() {
		return successOrFailure;
	}

	public void setSuccessOrFailure(String successOrFailure) {
		this.successOrFailure = successOrFailure;
	}

}
