package com.model.dfltloan_open_detail.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "GENDTL", namespace = "http://www.finacle.com/fixml", propOrder = {
		"acctMgrAtAcct", "acctMgrAtAcctName", "relativeToStaff",
		"relativeStaffId" })
@XmlAccessorType(XmlAccessType.FIELD)
public class GenDTL {
	@XmlElement(name = "acctMgrAtAcct", namespace = "http://www.finacle.com/fixml")
	protected String acctMgrAtAcct;
	@XmlElement(name = "acctMgrAtAcctName", namespace = "http://www.finacle.com/fixml")
	protected String acctMgrAtAcctName;
	@XmlElement(name = "relativeToStaff", namespace = "http://www.finacle.com/fixml")
	protected String relativeToStaff;
	@XmlElement(name = "relativeStaffId", namespace = "http://www.finacle.com/fixml")
	protected String relativeStaffId;

	public GenDTL() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GenDTL(String acctMgrAtAcct, String acctMgrAtAcctName,
			String relativeToStaff, String relativeStaffId) {
		super();
		this.acctMgrAtAcct = acctMgrAtAcct;
		this.acctMgrAtAcctName = acctMgrAtAcctName;
		this.relativeToStaff = relativeToStaff;
		this.relativeStaffId = relativeStaffId;
	}

	public String getAcctMgrAtAcct() {
		return acctMgrAtAcct;
	}

	public void setAcctMgrAtAcct(String acctMgrAtAcct) {
		this.acctMgrAtAcct = acctMgrAtAcct;
	}

	public String getAcctMgrAtAcctName() {
		return acctMgrAtAcctName;
	}

	public void setAcctMgrAtAcctName(String acctMgrAtAcctName) {
		this.acctMgrAtAcctName = acctMgrAtAcctName;
	}

	public String getRelativeToStaff() {
		return relativeToStaff;
	}

	public void setRelativeToStaff(String relativeToStaff) {
		this.relativeToStaff = relativeToStaff;
	}

	public String getRelativeStaffId() {
		return relativeStaffId;
	}

	public void setRelativeStaffId(String relativeStaffId) {
		this.relativeStaffId = relativeStaffId;
	}

}
