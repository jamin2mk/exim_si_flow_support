package com.si.model;

import com.google.gson.annotations.Expose;

public class System {

	@Expose
	protected String from;
	@Expose
	protected String mid;
	@Expose
	protected String to;

	public System() {
		// TODO Auto-generated constructor stub
	}

	public System(String from, String mid, String to) {
		this.from = from;
		this.mid = mid;
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
