package com.model.caacctadd.req.v_1_0;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CAAcctAddCustomData", propOrder = { "dSAID", "nEXTINTERESTRUNDATECR", "lOCALCALENDAR", "iNTCRACC", "fREETEXT1", "fREETEXT2", "fREETEXT3", "fREETEXT4", "fREETEXT8", "fREECODE4", "fREECODE8", "fREECODE9",
		"iNTRATECODE", "aCCTPREFINTCR", "iNTCRACCNUM", "aCCTMGRID", "mINBALIND", "mINBAL", "prefLangCode", "relatedDtls", "mINBALCRNCY", "luckyAccNum", "lienFeeAmount" })
public class CAAcctAddCustomData {

	@SerializedName("FREECODE4")
	@XmlElement(name = "FREECODE4")
	private String fREECODE4;

	@SerializedName("MINBALCRNCY")
	@XmlElement(name = "MINBALCRNCY")
	private String mINBALCRNCY;

	@SerializedName("LuckyAccNum")
	@XmlElement(name = "LuckyAccNum")
	private String luckyAccNum;

	@SerializedName("ACCTMGRID")
	@XmlElement(name = "ACCTMGRID")
	private String aCCTMGRID;

	@SerializedName("LOCALCALENDAR")
	@XmlElement(name = "LOCALCALENDAR")
	private String lOCALCALENDAR;

	@SerializedName("FREECODE8")
	@XmlElement(name = "FREECODE8")
	private String fREECODE8;

	@SerializedName("DSAID")
	@XmlElement(name = "DSAID")
	private String dSAID;

	@SerializedName("INTCRACC")
	@XmlElement(name = "INTCRACC")
	private String iNTCRACC;

	@SerializedName("ACCTPREFINTCR")
	@XmlElement(name = "ACCTPREFINTCR")
	private String aCCTPREFINTCR;

	@SerializedName("MINBALIND")
	@XmlElement(name = "MINBALIND")
	private String mINBALIND;

	@SerializedName("NEXTINTERESTRUNDATECR")
	@XmlElement(name = "NEXTINTERESTRUNDATECR")
	private String nEXTINTERESTRUNDATECR;

	@SerializedName("PrefLangCode")
	@XmlElement(name = "PrefLangCode")
	private String prefLangCode;

	@SerializedName("MINBAL")
	@XmlElement(name = "MINBAL")
	private BigDecimal mINBAL;

	@SerializedName("LienFeeAmount")
	@XmlElement(name = "LienFeeAmount")
	private BigDecimal lienFeeAmount;

	@SerializedName("INTCRACCNUM")
	@XmlElement(name = "INTCRACCNUM")
	private String iNTCRACCNUM;

	@SerializedName("FREETEXT4")
	@XmlElement(name = "FREETEXT4")
	private String fREETEXT4;

	@SerializedName("FREECODE9")
	@XmlElement(name = "FREECODE9")
	private String fREECODE9;

	@SerializedName("INTRATECODE")
	@XmlElement(name = "INTRATECODE")
	private String iNTRATECODE;

	@SerializedName("FREETEXT3")
	@XmlElement(name = "FREETEXT3")
	private String fREETEXT3;

	@SerializedName("FREETEXT2")
	@XmlElement(name = "FREETEXT2")
	private String fREETEXT2;

	@SerializedName("FREETEXT1")
	@XmlElement(name = "FREETEXT1")
	private String fREETEXT1;

	@SerializedName("RelatedDtls")
	@XmlElement(name = "RelatedDtls")
	private RelatedDtls relatedDtls;

	@SerializedName("FREETEXT8")
	@XmlElement(name = "FREETEXT8")
	private String fREETEXT8;

	public String getFREECODE4() {
		return fREECODE4;
	}

	public void setFREECODE4(String fREECODE4) {
		this.fREECODE4 = fREECODE4;
	}

	public String getMINBALCRNCY() {
		return mINBALCRNCY;
	}

	public void setMINBALCRNCY(String mINBALCRNCY) {
		this.mINBALCRNCY = mINBALCRNCY;
	}

	public String getLuckyAccNum() {
		return luckyAccNum;
	}

	public void setLuckyAccNum(String luckyAccNum) {
		this.luckyAccNum = luckyAccNum;
	}

	public String getACCTMGRID() {
		return aCCTMGRID;
	}

	public void setACCTMGRID(String aCCTMGRID) {
		this.aCCTMGRID = aCCTMGRID;
	}

	public String getLOCALCALENDAR() {
		return lOCALCALENDAR;
	}

	public void setLOCALCALENDAR(String lOCALCALENDAR) {
		this.lOCALCALENDAR = lOCALCALENDAR;
	}

	public String getFREECODE8() {
		return fREECODE8;
	}

	public void setFREECODE8(String fREECODE8) {
		this.fREECODE8 = fREECODE8;
	}

	public String getDSAID() {
		return dSAID;
	}

	public void setDSAID(String dSAID) {
		this.dSAID = dSAID;
	}

	public String getINTCRACC() {
		return iNTCRACC;
	}

	public void setINTCRACC(String iNTCRACC) {
		this.iNTCRACC = iNTCRACC;
	}

	public String getACCTPREFINTCR() {
		return aCCTPREFINTCR;
	}

	public void setACCTPREFINTCR(String aCCTPREFINTCR) {
		this.aCCTPREFINTCR = aCCTPREFINTCR;
	}

	public String getMINBALIND() {
		return mINBALIND;
	}

	public void setMINBALIND(String mINBALIND) {
		this.mINBALIND = mINBALIND;
	}

	public String getNEXTINTERESTRUNDATECR() {
		return nEXTINTERESTRUNDATECR;
	}

	public void setNEXTINTERESTRUNDATECR(String nEXTINTERESTRUNDATECR) {
		this.nEXTINTERESTRUNDATECR = nEXTINTERESTRUNDATECR;
	}

	public String getPrefLangCode() {
		return prefLangCode;
	}

	public void setPrefLangCode(String prefLangCode) {
		this.prefLangCode = prefLangCode;
	}

	public BigDecimal getMINBAL() {
		return mINBAL;
	}

	public void setMINBAL(BigDecimal mINBAL) {
		this.mINBAL = mINBAL;
	}

	public BigDecimal getLienFeeAmount() {
		return lienFeeAmount;
	}

	public void setLienFeeAmount(BigDecimal lienFeeAmount) {
		this.lienFeeAmount = lienFeeAmount;
	}

	public String getINTCRACCNUM() {
		return iNTCRACCNUM;
	}

	public void setINTCRACCNUM(String iNTCRACCNUM) {
		this.iNTCRACCNUM = iNTCRACCNUM;
	}

	public String getFREETEXT4() {
		return fREETEXT4;
	}

	public void setFREETEXT4(String fREETEXT4) {
		this.fREETEXT4 = fREETEXT4;
	}

	public String getFREECODE9() {
		return fREECODE9;
	}

	public void setFREECODE9(String fREECODE9) {
		this.fREECODE9 = fREECODE9;
	}

	public String getINTRATECODE() {
		return iNTRATECODE;
	}

	public void setINTRATECODE(String iNTRATECODE) {
		this.iNTRATECODE = iNTRATECODE;
	}

	public String getFREETEXT3() {
		return fREETEXT3;
	}

	public void setFREETEXT3(String fREETEXT3) {
		this.fREETEXT3 = fREETEXT3;
	}

	public String getFREETEXT2() {
		return fREETEXT2;
	}

	public void setFREETEXT2(String fREETEXT2) {
		this.fREETEXT2 = fREETEXT2;
	}

	public String getFREETEXT1() {
		return fREETEXT1;
	}

	public void setFREETEXT1(String fREETEXT1) {
		this.fREETEXT1 = fREETEXT1;
	}

	public RelatedDtls getRelatedDtls() {
		return relatedDtls;
	}

	public void setRelatedDtls(RelatedDtls relatedDtls) {
		this.relatedDtls = relatedDtls;
	}

	public String getFREETEXT8() {
		return fREETEXT8;
	}

	public void setFREETEXT8(String fREETEXT8) {
		this.fREETEXT8 = fREETEXT8;
	}
}
