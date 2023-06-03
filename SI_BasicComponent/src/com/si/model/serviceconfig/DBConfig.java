package com.si.model.serviceconfig;

public class DBConfig {
	protected String sp;
	protected IOInfo input;
	protected IOInfo output;
	protected boolean isCached = false;

	public DBConfig() {
		// TODO Auto-generated constructor stub
	}

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public IOInfo getInput() {
		return input;
	}

	public void setInput(IOInfo input) {
		this.input = input;
	}

	public IOInfo getOutput() {
		return output;
	}

	public void setoutput(IOInfo output) {
		this.output = output;
	}

	public void setInput(String procedure, String type) {
		this.input = new IOInfo();
		this.input.setSubjectCode(String.format("%s.IN", procedure));
		this.input.setType(type);
	}

	public void setOutput(String procedure, String type) {
		this.output = new IOInfo();
		this.output.setSubjectCode(String.format("%s.OUT", procedure));
		this.output.setType(type);
	}
}
