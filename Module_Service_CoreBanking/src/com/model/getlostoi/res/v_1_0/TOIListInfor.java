package com.model.getlostoi.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TOIListInfor", propOrder = {})
public class TOIListInfor {

	@XmlElement(name = "TRDT")
	protected String trdt;
	@XmlElement(name = "TOI")
	protected String toi;
	@XmlElement(name = "SDBQ_QD")
	protected String sdbqQd;
	@XmlElement(name = "DNBQ_CHO_VAY_QD")
	protected String dnbqChoVayQd;
	@XmlElement(name = "DS_TKTG_TT")
	protected String dsTktgTt;
	@XmlElement(name = "SDTG_CKH_CK")
	protected String sdtgCkhCk;
	@XmlElement(name = "SDTG_CKH_BQ")
	protected String sdtgCkhBq;

	public TOIListInfor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TOIListInfor(String trdt, String toi, String sdbqQd,
			String dnbqChoVayQd, String dsTktgTt, String sdtgCkhCk,
			String sdtgCkhBq) {
		super();
		this.trdt = trdt;
		this.toi = toi;
		this.sdbqQd = sdbqQd;
		this.dnbqChoVayQd = dnbqChoVayQd;
		this.dsTktgTt = dsTktgTt;
		this.sdtgCkhCk = sdtgCkhCk;
		this.sdtgCkhBq = sdtgCkhBq;
	}

	public String getTrdt() {
		return trdt;
	}

	public void setTrdt(String trdt) {
		this.trdt = trdt;
	}

	public String getToi() {
		return toi;
	}

	public void setToi(String toi) {
		this.toi = toi;
	}

	public String getSdbqQd() {
		return sdbqQd;
	}

	public void setSdbqQd(String sdbqQd) {
		this.sdbqQd = sdbqQd;
	}

	public String getDnbqChoVayQd() {
		return dnbqChoVayQd;
	}

	public void setDnbqChoVayQd(String dnbqChoVayQd) {
		this.dnbqChoVayQd = dnbqChoVayQd;
	}

	public String getDsTktgTt() {
		return dsTktgTt;
	}

	public void setDsTktgTt(String dsTktgTt) {
		this.dsTktgTt = dsTktgTt;
	}

	public String getSdtgCkhCk() {
		return sdtgCkhCk;
	}

	public void setSdtgCkhCk(String sdtgCkhCk) {
		this.sdtgCkhCk = sdtgCkhCk;
	}

	public String getSdtgCkhBq() {
		return sdtgCkhBq;
	}

	public void setSdtgCkhBq(String sdtgCkhBq) {
		this.sdtgCkhBq = sdtgCkhBq;
	}

}
