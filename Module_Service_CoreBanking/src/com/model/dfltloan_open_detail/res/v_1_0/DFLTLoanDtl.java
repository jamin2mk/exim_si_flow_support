package com.model.dfltloan_open_detail.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DFLTLOANDTL", namespace = "http://www.finacle.com/fixml", propOrder = {
		"genDTL", "loanDTL", "acctint", "relatedPartyDTL" })
@XmlAccessorType(XmlAccessType.FIELD)
public class DFLTLoanDtl {

	@XmlElement(name = "GENDTL", namespace = "http://www.finacle.com/fixml")
	protected GenDTL genDTL;
	@XmlElement(name = "LOANDTL", namespace = "http://www.finacle.com/fixml")
	protected LoanDTL loanDTL;
	@XmlElement(name = "ACCTINT", namespace = "http://www.finacle.com/fixml")
	protected ACCTINT acctint;
	@XmlElement(name = "RELATEDPARTYDTL", namespace = "http://www.finacle.com/fixml")
	protected RelatedPartyDTL relatedPartyDTL;

	public DFLTLoanDtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DFLTLoanDtl(GenDTL genDTL, LoanDTL loanDTL, ACCTINT acctint,
			RelatedPartyDTL relatedPartyDTL) {
		super();
		this.genDTL = genDTL;
		this.loanDTL = loanDTL;
		this.acctint = acctint;
		this.relatedPartyDTL = relatedPartyDTL;
	}

	public GenDTL getGenDTL() {
		return genDTL;
	}

	public void setGenDTL(GenDTL genDTL) {
		this.genDTL = genDTL;
	}

	public LoanDTL getLoanDTL() {
		return loanDTL;
	}

	public void setLoanDTL(LoanDTL loanDTL) {
		this.loanDTL = loanDTL;
	}

	public ACCTINT getAcctint() {
		return acctint;
	}

	public void setAcctint(ACCTINT acctint) {
		this.acctint = acctint;
	}

	public RelatedPartyDTL getRelatedPartyDTL() {
		return relatedPartyDTL;
	}

	public void setRelatedPartyDTL(RelatedPartyDTL relatedPartyDTL) {
		this.relatedPartyDTL = relatedPartyDTL;
	}

}
