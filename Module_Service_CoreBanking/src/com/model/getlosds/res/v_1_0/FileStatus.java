package com.model.getlosds.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileStatus", namespace = "http://www.finacle.com/fixml")
public class FileStatus {

	@XmlElement(name = "traNoExim", namespace = "http://www.finacle.com/fixml")
	protected String traNoExim;
	@XmlElement(name = "NgayQhTD", namespace = "http://www.finacle.com/fixml")
	protected String ngayQhTD;
	@XmlElement(name = "errcode", namespace = "http://www.finacle.com/fixml")
	protected String errcode;
	@XmlElement(name = "errDesc", namespace = "http://www.finacle.com/fixml")
	protected String errDesc;

	public FileStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileStatus(String traNoExim, String ngayQhTD, String errcode,
			String errDesc) {
		super();
		this.traNoExim = traNoExim;
		this.ngayQhTD = ngayQhTD;
		this.errcode = errcode;
		this.errDesc = errDesc;
	}

	public String getTraNoExim() {
		return traNoExim;
	}

	public void setTraNoExim(String traNoExim) {
		this.traNoExim = traNoExim;
	}

	public String getNgayQhTD() {
		return ngayQhTD;
	}

	public void setNgayQhTD(String ngayQhTD) {
		this.ngayQhTD = ngayQhTD;
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
