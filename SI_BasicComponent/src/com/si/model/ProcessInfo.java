package com.si.model;

import com.google.gson.annotations.Expose;

public class ProcessInfo {

	@Expose
	InstanceInfo instanceInfo;
	@Expose
	String caseID;
	@Expose
	String caseID_Ref;

	public InstanceInfo getInstanceInfo() {
		return instanceInfo;
	}

	public void setInstanceInfo(InstanceInfo instanceInfo) {
		this.instanceInfo = instanceInfo;
	}

	public String getCaseID() {
		return caseID;
	}

	public void setCaseID(String caseID) {
		this.caseID = caseID;
	}

	public String getCaseID_Ref() {
		return caseID_Ref;
	}

	public void setCaseID_Ref(String caseID_Ref) {
		this.caseID_Ref = caseID_Ref;
	}

}
