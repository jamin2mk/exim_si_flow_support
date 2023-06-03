package com.model.caacctadd.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CAAcctGenInfo", propOrder = { "genLedgerSubHead", "acctName", "acctShortName", "acctStmtMode", "acctStmtFreq", "despatchMode" })
public class CAAcctGenInfo {

	@SerializedName("AcctName")
	@XmlElement(name = "AcctName")
	private String acctName;

	@SerializedName("DespatchMode")
	@XmlElement(name = "DespatchMode")
	private String despatchMode;

	@SerializedName("AcctShortName")
	@XmlElement(name = "AcctShortName")
	private String acctShortName;

	@SerializedName("GenLedgerSubHead")
	@XmlElement(name = "GenLedgerSubHead")
	private GenLedgerSubHead genLedgerSubHead;

	@SerializedName("AcctStmtFreq")
	@XmlElement(name = "AcctStmtFreq")
	private AcctStmtFreq acctStmtFreq;

	@SerializedName("AcctStmtMode")
	@XmlElement(name = "AcctStmtMode")
	private String acctStmtMode;

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getDespatchMode() {
		return despatchMode;
	}

	public void setDespatchMode(String despatchMode) {
		this.despatchMode = despatchMode;
	}

	public String getAcctShortName() {
		return acctShortName;
	}

	public void setAcctShortName(String acctShortName) {
		this.acctShortName = acctShortName;
	}

	public GenLedgerSubHead getGenLedgerSubHead() {
		return genLedgerSubHead;
	}

	public void setGenLedgerSubHead(GenLedgerSubHead genLedgerSubHead) {
		this.genLedgerSubHead = genLedgerSubHead;
	}

	public AcctStmtFreq getAcctStmtFreq() {
		return acctStmtFreq;
	}

	public void setAcctStmtFreq(AcctStmtFreq acctStmtFreq) {
		this.acctStmtFreq = acctStmtFreq;
	}

	public String getAcctStmtMode() {
		return acctStmtMode;
	}

	public void setAcctStmtMode(String acctStmtMode) {
		this.acctStmtMode = acctStmtMode;
	}
}
