package com.si.model;

import com.google.gson.annotations.Expose;

public class InstanceInfo {
	
	@Expose
	String instanceId;
	@Expose
	String taskId;
	@Expose
	String userClaim;
	@Expose
	String workFlow;
	@Expose
	String refInfo;
	@Expose
	BpmInfo bpmInfo;

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getUserClaim() {
		return userClaim;
	}

	public void setUserClaim(String userClaim) {
		this.userClaim = userClaim;
	}

	public String getWorkFlow() {
		return workFlow;
	}

	public void setWorkFlow(String workFlow) {
		this.workFlow = workFlow;
	}

	public String getRefInfo() {
		return refInfo;
	}

	public void setRefInfo(String refInfo) {
		this.refInfo = refInfo;
	}

	public BpmInfo getBpmInfo() {
		return bpmInfo;
	}

	public void setBpmInfo(BpmInfo bpmInfo) {
		this.bpmInfo = bpmInfo;
	}
}
