package com.si.model;

import com.google.gson.annotations.Expose;

public class Timing {
	
	@Expose
	protected String fromStart;
	@Expose
	protected String fromFinish;
	@Expose
	protected String midStart;
	@Expose
	protected String midFinish;
	@Expose
	protected String toStart;
	@Expose
	protected String toFinish;

	public Timing() {
		// TODO Auto-generated constructor stub
	}

	public Timing(String fromStart, String fromFinish, String midStart, String midFinish, String toStart, String toFinish) {
		this.fromStart = fromStart;
		this.fromFinish = fromFinish;
		this.midStart = midStart;
		this.midFinish = midFinish;
		this.toStart = toStart;
		this.toFinish = toFinish;
	}

	public String getFromStart() {
		return fromStart;
	}

	public void setFromStart(String fromStart) {
		this.fromStart = fromStart;
	}

	public String getFromFinish() {
		return fromFinish;
	}

	public void setFromFinish(String fromFinish) {
		this.fromFinish = fromFinish;
	}

	public String getMidStart() {
		return midStart;
	}

	public void setMidStart(String midStart) {
		this.midStart = midStart;
	}

	public String getMidFinish() {
		return midFinish;
	}

	public void setMidFinish(String midFinish) {
		this.midFinish = midFinish;
	}

	public String getToStart() {
		return toStart;
	}

	public void setToStart(String toStart) {
		this.toStart = toStart;
	}

	public String getToFinish() {
		return toFinish;
	}

	public void setToFinish(String toFinish) {
		this.toFinish = toFinish;
	}

}
