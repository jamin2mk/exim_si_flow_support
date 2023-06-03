package com.model.createdepositcoltrl.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DepColtrlAddResponse", propOrder = { "depColtrlAddRs",
		"depColtrlAddCustomData" })
@XmlAccessorType(XmlAccessType.FIELD)
public class DepColtrlAddResponse {

	@XmlElement(name = "DepColtrlAddRs")
	protected DepColtrlAddRs depColtrlAddRs;
	@XmlElement(name = "DepColtrlAdd_CustomData")
	protected String depColtrlAddCustomData;

	public DepColtrlAddResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepColtrlAddResponse(DepColtrlAddRs depColtrlAddRs,
			String depColtrlAddCustomData) {
		super();
		this.depColtrlAddRs = depColtrlAddRs;
		this.depColtrlAddCustomData = depColtrlAddCustomData;
	}

	public DepColtrlAddRs getDepColtrlAddRs() {
		return depColtrlAddRs;
	}

	public void setDepColtrlAddRs(DepColtrlAddRs depColtrlAddRs) {
		this.depColtrlAddRs = depColtrlAddRs;
	}

	public String getDepColtrlAddCustomData() {
		return depColtrlAddCustomData;
	}

	public void setDepColtrlAddCustomData(String depColtrlAddCustomData) {
		this.depColtrlAddCustomData = depColtrlAddCustomData;
	}

}
