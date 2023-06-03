package com.model.dfltloan_open_detail.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "executeFinacleScript_CustomData", namespace = "http://www.finacle.com/fixml", propOrder = { "dfltLoanDtl" })
@XmlAccessorType(XmlAccessType.FIELD)
public class ExecuteFinacleScriptCustomData {

	@XmlElement(name = "DFLTLOANDTL", namespace = "http://www.finacle.com/fixml")
	protected DFLTLoanDtl dfltLoanDtl;

	public ExecuteFinacleScriptCustomData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExecuteFinacleScriptCustomData(DFLTLoanDtl dfltLoanDtl) {
		super();
		this.dfltLoanDtl = dfltLoanDtl;
	}

	public DFLTLoanDtl getDfltLoanDtl() {
		return dfltLoanDtl;
	}

	public void setDfltLoanDtl(DFLTLoanDtl dfltLoanDtl) {
		this.dfltLoanDtl = dfltLoanDtl;
	}

}
