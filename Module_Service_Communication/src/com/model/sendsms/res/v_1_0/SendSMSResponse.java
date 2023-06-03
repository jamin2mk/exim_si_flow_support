package com.model.sendsms.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "SendSMSResponse", namespace = "http://tempuri.org/")
public class SendSMSResponse {

	@XmlElement(name = "SendSMSResult")
	protected String sendSMSResult;

	public SendSMSResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SendSMSResponse(String sendSMSResult) {
		super();
		this.sendSMSResult = sendSMSResult;
	}

	public String getSendSMSResult() {
		return sendSMSResult;
	}

	public void setSendSMSResult(String sendSMSResult) {
		this.sendSMSResult = sendSMSResult;
	}

}
