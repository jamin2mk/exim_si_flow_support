package com.model.caacctadd.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcctStmtFreq", propOrder = { "type", "startDt", "holStat" })
public class AcctStmtFreq {

	@SerializedName("StartDt")
	@XmlElement(name = "StartDt")
	private Integer startDt;

	@SerializedName("Type")
	@XmlElement(name = "Type")
	private String type;

	@SerializedName("HolStat")
	@XmlElement(name = "HolStat")
	private String holStat;

	public Integer getStartDt() {
		return startDt;
	}

	public void setStartDt(Integer startDt) {
		this.startDt = startDt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHolStat() {
		return holStat;
	}

	public void setHolStat(String holStat) {
		this.holStat = holStat;
	}
}