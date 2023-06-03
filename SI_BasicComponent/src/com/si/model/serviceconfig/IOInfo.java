package com.si.model.serviceconfig;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class IOInfo {

	protected String subjectCode;
	protected String moduleCode;
	protected List<String> type;

	public IOInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public void setType(String ioType) {
		Type type = new TypeToken<List<String>>(){}.getType();
		this.type = new Gson().fromJson(ioType, type);
	}
}
