package com.model.error.res.v_1_0;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FIBusinessException")
public class FIBusinessException {

	@XmlElement(name = "ErrorDetail")
	protected List<ErrorDetail> errorDetail;

	public FIBusinessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FIBusinessException(List<ErrorDetail> errorDetail) {
		super();
		this.errorDetail = errorDetail;
	}

	public List<ErrorDetail> getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(List<ErrorDetail> errorDetail) {
		this.errorDetail = errorDetail;
	}

}
