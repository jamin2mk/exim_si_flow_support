package com.model.getlosaddbusrul.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileStatus", namespace = "http://www.finacle.com/fixml")
public class FileStatus {

	@XmlElement(name = "tongHMThe", namespace = "http://www.finacle.com/fixml")
	protected String tongHMThe;
	@XmlElement(name = "soTheActive", namespace = "http://www.finacle.com/fixml")
	protected String soTheActive;
	@XmlElement(name = "DNHM", namespace = "http://www.finacle.com/fixml")
	protected String dnhm;
	@XmlElement(name = "theQuaHan", namespace = "http://www.finacle.com/fixml")
	protected String theQuaHan;
	@XmlElement(name = "DNHM_KTSBD", namespace = "http://www.finacle.com/fixml")
	protected String dnhmktsbd;
	@XmlElement(name = "DNHM_CC", namespace = "http://www.finacle.com/fixml")
	protected String dnhmcc;
	@XmlElement(name = "flgChkSol", namespace = "http://www.finacle.com/fixml")
	protected String flgChkSol;
	@XmlElement(name = "errcode", namespace = "http://www.finacle.com/fixml")
	protected String errcode;
	@XmlElement(name = "errDesc", namespace = "http://www.finacle.com/fixml")
	protected String errDesc;

	public FileStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileStatus(String tongHMThe, String soTheActive, String dnhm,
			String theQuaHan, String dnhmktsbd, String dnhmcc,
			String flgChkSol, String errcode, String errDesc) {
		super();
		this.tongHMThe = tongHMThe;
		this.soTheActive = soTheActive;
		this.dnhm = dnhm;
		this.theQuaHan = theQuaHan;
		this.dnhmktsbd = dnhmktsbd;
		this.dnhmcc = dnhmcc;
		this.flgChkSol = flgChkSol;
		this.errcode = errcode;
		this.errDesc = errDesc;
	}

	public String getTongHMThe() {
		return tongHMThe;
	}

	public void setTongHMThe(String tongHMThe) {
		this.tongHMThe = tongHMThe;
	}

	public String getSoTheActive() {
		return soTheActive;
	}

	public void setSoTheActive(String soTheActive) {
		this.soTheActive = soTheActive;
	}

	public String getDnhm() {
		return dnhm;
	}

	public void setDnhm(String dnhm) {
		this.dnhm = dnhm;
	}

	public String getTheQuaHan() {
		return theQuaHan;
	}

	public void setTheQuaHan(String theQuaHan) {
		this.theQuaHan = theQuaHan;
	}

	public String getDnhmktsbd() {
		return dnhmktsbd;
	}

	public void setDnhmktsbd(String dnhmktsbd) {
		this.dnhmktsbd = dnhmktsbd;
	}

	public String getDnhmcc() {
		return dnhmcc;
	}

	public void setDnhmcc(String dnhmcc) {
		this.dnhmcc = dnhmcc;
	}

	public String getFlgChkSol() {
		return flgChkSol;
	}

	public void setFlgChkSol(String flgChkSol) {
		this.flgChkSol = flgChkSol;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

}
