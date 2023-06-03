package com.model.getloscif.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "GetLosCifResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetLosCifResponse", namespace = "http://www.alsb.com/", propOrder = { "cifDetail" })
public class GetLosCifResponse {

	@XmlElement(name = "cifDetail", namespace = "http://www.finacle.com/fixml")
	protected CifDetail cifDetail;

	public CifDetail getCifDetail() {
		return cifDetail;
	}

	public void setCifDetail(CifDetail cifDetail) {
		this.cifDetail = cifDetail;
	}
}
