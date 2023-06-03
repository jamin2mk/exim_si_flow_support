package com.model.sendsms.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SendSMS", namespace = "http://tempuri.org/")
public class SendSMSRequest {

	@XmlElement(name = "xmlData", namespace = "http://tempuri.org/")
	protected String xmlData;

	public SendSMSRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SendSMSRequest(String xmlData) {
		super();
		this.xmlData = xmlData;
	}

	public String getXmlData() {
		return xmlData;
	}

	public void setXmlData(String xmlData) {
		this.xmlData = xmlData;
	}

}
