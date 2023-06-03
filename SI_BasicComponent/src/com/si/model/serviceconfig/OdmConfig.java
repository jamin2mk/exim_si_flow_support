package com.si.model.serviceconfig;

public class OdmConfig {
	protected String rule;
	
	protected IOInfo input;
	protected IOInfo ouput;

	public OdmConfig() {
		// TODO Auto-generated constructor stub
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public IOInfo getInput() {
		return input;
	}

	public void setInput(IOInfo input) {
		this.input = input;
	}

	public IOInfo getOuput() {
		return ouput;
	}

	public void setOuput(IOInfo ouput) {
		this.ouput = ouput;
	}

}
