package com.model.collateralcreatecustom.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "executeFinacleScriptResponse")
public class ExecuteFinacleScriptResponse {

	@XmlElement(name = "ExecuteFinacleScriptOutputVO")
	protected String executeFinacleScriptOutputVO;

	@XmlElement(name = "executeFinacleScript_CustomData")
	protected ExecuteFinacleScript_CustomData executeFinacleScriptCustomData;

	public ExecuteFinacleScriptResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExecuteFinacleScriptResponse(String executeFinacleScriptOutputVO,
			ExecuteFinacleScript_CustomData executeFinacleScriptCustomData) {
		super();
		this.executeFinacleScriptOutputVO = executeFinacleScriptOutputVO;
		this.executeFinacleScriptCustomData = executeFinacleScriptCustomData;
	}

	public String getExecuteFinacleScriptOutputVO() {
		return executeFinacleScriptOutputVO;
	}

	public void setExecuteFinacleScriptOutputVO(
			String executeFinacleScriptOutputVO) {
		this.executeFinacleScriptOutputVO = executeFinacleScriptOutputVO;
	}

	public ExecuteFinacleScript_CustomData getExecuteFinacleScriptCustomData() {
		return executeFinacleScriptCustomData;
	}

	public void setExecuteFinacleScriptCustomData(
			ExecuteFinacleScript_CustomData executeFinacleScriptCustomData) {
		this.executeFinacleScriptCustomData = executeFinacleScriptCustomData;
	}

}
