package com.model.add_limit_node_core.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "AddLimitNode", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddLimitNodeCoreRequest {

	@XmlElement(name = "SolId")
	protected String solId;

	public AddLimitNodeCoreRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddLimitNodeCoreRequest(String solId) {
		super();
		this.solId = solId;
	}

	public String getSolId() {
		return solId;
	}

	public void setSolId(String solId) {
		this.solId = solId;
	}

}
