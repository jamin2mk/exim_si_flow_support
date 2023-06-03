package com.model.dfltloan_open_detail.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "LOANDTL", namespace = "http://www.finacle.com/fixml", propOrder = {
		"repMthd", "isSalaryLoan", "repRateCode" })
@XmlAccessorType(XmlAccessType.FIELD)
public class LoanDTL {
	@XmlElement(name = "repMthd", namespace = "http://www.finacle.com/fixml")
	protected String repMthd;
	@XmlElement(name = "isSalaryLoan", namespace = "http://www.finacle.com/fixml")
	protected String isSalaryLoan;
	@XmlElement(name = "repRateCode", namespace = "http://www.finacle.com/fixml")
	protected String repRateCode;

	public LoanDTL() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanDTL(String repMthd, String isSalaryLoan, String repRateCode) {
		super();
		this.repMthd = repMthd;
		this.isSalaryLoan = isSalaryLoan;
		this.repRateCode = repRateCode;
	}

	public String getRepMthd() {
		return repMthd;
	}

	public void setRepMthd(String repMthd) {
		this.repMthd = repMthd;
	}

	public String getIsSalaryLoan() {
		return isSalaryLoan;
	}

	public void setIsSalaryLoan(String isSalaryLoan) {
		this.isSalaryLoan = isSalaryLoan;
	}

	public String getRepRateCode() {
		return repRateCode;
	}

	public void setRepRateCode(String repRateCode) {
		this.repRateCode = repRateCode;
	}

}
