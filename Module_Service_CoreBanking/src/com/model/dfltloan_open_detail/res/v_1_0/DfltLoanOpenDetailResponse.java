package com.model.dfltloan_open_detail.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DfltLoanOpenDetailResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class DfltLoanOpenDetailResponse {

	@XmlElement(name = "executeFinacleScript_CustomData", namespace = "http://www.finacle.com/fixml")
	protected ExecuteFinacleScriptCustomData executeFinacleScriptCustomData;

	public DfltLoanOpenDetailResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DfltLoanOpenDetailResponse(
			ExecuteFinacleScriptCustomData executeFinacleScriptCustomData) {
		super();
		this.executeFinacleScriptCustomData = executeFinacleScriptCustomData;
	}

	public ExecuteFinacleScriptCustomData getExecuteFinacleScriptCustomData() {
		return executeFinacleScriptCustomData;
	}

	public void setExecuteFinacleScriptCustomData(
			ExecuteFinacleScriptCustomData executeFinacleScriptCustomData) {
		this.executeFinacleScriptCustomData = executeFinacleScriptCustomData;
	}

}
