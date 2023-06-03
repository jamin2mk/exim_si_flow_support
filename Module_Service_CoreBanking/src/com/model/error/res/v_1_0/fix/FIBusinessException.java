package com.model.error.res.v_1_0.fix;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FIBusinessException", namespace = "http://www.finacle.com/fixml")
public class FIBusinessException {

	@XmlElement(name = "ErrorDetail", namespace = "http://www.finacle.com/fixml")
	protected List<ErrorDetail> errorDetail;

	public FIBusinessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ErrorDetail> getErrorDetail() {
		return errorDetail;
	}

	public void setErrorDetail(List<ErrorDetail> errorDetail) {
		this.errorDetail = errorDetail;
	}

}
