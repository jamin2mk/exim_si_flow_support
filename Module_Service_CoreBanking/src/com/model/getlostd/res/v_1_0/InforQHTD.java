package com.model.getlostd.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InforQHTD", namespace = "http://www.finacle.com/fixml", propOrder = { "details", "lavlist", "errorDetail" })
public class InforQHTD {

	@XmlElement(name = "Details", namespace = "http://www.finacle.com/fixml")
	protected Details details;

	@XmlElement(name = "LAVLIST", namespace = "http://www.finacle.com/fixml")
	protected LAVLIST lavlist;

	@XmlElement(name = "ERRORDETAIL", namespace = "http://www.finacle.com/fixml")
	protected ErrorDetail errorDetail;

	public InforQHTD() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InforQHTD(Details details, LAVLIST lavlist, ErrorDetail errorDetail) {
		super();
		this.details = details;
		this.lavlist = lavlist;
		this.errorDetail = errorDetail;
	}

	public Details getDetails() {
		return details;
	}

	public void setDetails(Details details) {
		this.details = details;
	}

	public LAVLIST getLavlist() {
		return lavlist;
	}

	public void setLavlist(LAVLIST lavlist) {
		this.lavlist = lavlist;
	}

	public ErrorDetail getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(ErrorDetail errorDetail) {
		this.errorDetail = errorDetail;
	}

}
