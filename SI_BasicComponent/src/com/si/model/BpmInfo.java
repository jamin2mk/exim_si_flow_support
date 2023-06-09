package com.si.model;

import com.google.gson.annotations.Expose;

public class BpmInfo {

	@Expose
	protected String url;
	@Expose
	protected String username;
	@Expose
	protected String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
