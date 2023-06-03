package com.model.collateralcreatecustom.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.model.error.res.v_1_0.Error;

@XmlRootElement(name = "Body")
@XmlAccessorType(XmlAccessType.FIELD)
public class Body {

	@XmlElement(name = "executeFinacleScriptResponse")
	protected ExecuteFinacleScriptResponse executeFinacleScriptResponse;

	@XmlElement(name = "Error")
	protected Error error;

	public Body() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Body(ExecuteFinacleScriptResponse executeFinacleScriptResponse,
			Error error) {
		super();
		this.executeFinacleScriptResponse = executeFinacleScriptResponse;
		this.error = error;
	}

	public ExecuteFinacleScriptResponse getExecuteFinacleScriptResponse() {
		return executeFinacleScriptResponse;
	}

	public void setExecuteFinacleScriptResponse(
			ExecuteFinacleScriptResponse executeFinacleScriptResponse) {
		this.executeFinacleScriptResponse = executeFinacleScriptResponse;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

}
