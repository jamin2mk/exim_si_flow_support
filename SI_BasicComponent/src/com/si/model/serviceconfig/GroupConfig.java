package com.si.model.serviceconfig;

import java.util.List;

public class GroupConfig {
	List<String> services;
	String input;
	String output;

	public GroupConfig() {
		// TODO Auto-generated constructor stub
	}

	public List<String> getServices() {
		return services;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}
