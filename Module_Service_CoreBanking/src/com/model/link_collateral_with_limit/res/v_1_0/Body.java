package com.model.link_collateral_with_limit.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.model.error.res.v_1_0.Error;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Body")
public class Body {

	@XmlElement(name = "ColtrlLinkAddResponse")
	protected ColtrlLinkAddResponse coltrlLinkAddResponse;
	@XmlElement(name = "Error")
	protected Error error;
	public Body() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Body(ColtrlLinkAddResponse coltrlLinkAddResponse, Error error) {
		super();
		this.coltrlLinkAddResponse = coltrlLinkAddResponse;
		this.error = error;
	}
	public ColtrlLinkAddResponse getColtrlLinkAddResponse() {
		return coltrlLinkAddResponse;
	}
	public void setColtrlLinkAddResponse(ColtrlLinkAddResponse coltrlLinkAddResponse) {
		this.coltrlLinkAddResponse = coltrlLinkAddResponse;
	}
	public Error getError() {
		return error;
	}
	public void setError(Error error) {
		this.error = error;
	}
	
	
	
}
