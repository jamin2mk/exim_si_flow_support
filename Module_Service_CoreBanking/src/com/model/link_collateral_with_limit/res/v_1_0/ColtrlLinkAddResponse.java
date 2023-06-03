package com.model.link_collateral_with_limit.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ColtrlLinkAddResponse", propOrder = { "" })
public class ColtrlLinkAddResponse {

	@XmlElement(name = "ColtrlLinkAddRs")
	protected ColtrlLinkAddRs coltrlLinkAddRs;

	public ColtrlLinkAddResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ColtrlLinkAddResponse(ColtrlLinkAddRs coltrlLinkAddRs) {
		super();
		this.coltrlLinkAddRs = coltrlLinkAddRs;
	}

	public ColtrlLinkAddRs getColtrlLinkAddRs() {
		return coltrlLinkAddRs;
	}

	public void setColtrlLinkAddRs(ColtrlLinkAddRs coltrlLinkAddRs) {
		this.coltrlLinkAddRs = coltrlLinkAddRs;
	}

}
