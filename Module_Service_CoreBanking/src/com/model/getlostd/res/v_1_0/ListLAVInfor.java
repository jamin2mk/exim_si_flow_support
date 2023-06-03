package com.model.getlostd.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListLAVInfor", namespace = "http://www.finacle.com/fixml", propOrder = {"soHopDong","limitSOLID","ngayMoHD","limExpDate","ccy","ptCapTd","loaiCapTd","hanMuc","hmKhadung","duNo","lsbq"
		,"htCapTd","tsdb","capPheDuyet"})
public class ListLAVInfor {

	@XmlElement(name = "SOHOPDONG", namespace = "http://www.finacle.com/fixml")
	protected String soHopDong;
	@XmlElement(name = "LIMITSOLID", namespace = "http://www.finacle.com/fixml")
	protected String limitSOLID;
	@XmlElement(name = "NGAYMOHD", namespace = "http://www.finacle.com/fixml")
	protected String ngayMoHD;
	@XmlElement(name = "LIMEXPDATE", namespace = "http://www.finacle.com/fixml")
	protected String limExpDate;
	@XmlElement(name = "CCY", namespace = "http://www.finacle.com/fixml")
	protected String ccy;
	@XmlElement(name = "PTCAPTD", namespace = "http://www.finacle.com/fixml")
	protected String ptCapTd;
	@XmlElement(name = "LOAICAPTD", namespace = "http://www.finacle.com/fixml")
	protected String loaiCapTd;
	@XmlElement(name = "HANMUC", namespace = "http://www.finacle.com/fixml")
	protected String hanMuc;
	@XmlElement(name = "HMKHADUNG", namespace = "http://www.finacle.com/fixml")
	protected String hmKhadung;
	@XmlElement(name = "DUNO", namespace = "http://www.finacle.com/fixml")
	protected String duNo;
	@XmlElement(name = "LSBQ", namespace = "http://www.finacle.com/fixml")
	protected String lsbq;
	@XmlElement(name = "HTCAPTD", namespace = "http://www.finacle.com/fixml")
	protected String htCapTd;
	@XmlElement(name = "TSDB", namespace = "http://www.finacle.com/fixml")
	protected String tsdb;
	@XmlElement(name = "CAPPHEDUYET", namespace = "http://www.finacle.com/fixml")
	protected String capPheDuyet;

	public ListLAVInfor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListLAVInfor(String ptCapTd, String loaiCapTd, String hanMuc,
			String hmKhadung, String duNo, String lsbq, String htCapTd,
			String tsdb) {
		super();
		this.ptCapTd = ptCapTd;
		this.loaiCapTd = loaiCapTd;
		this.hanMuc = hanMuc;
		this.hmKhadung = hmKhadung;
		this.duNo = duNo;
		this.lsbq = lsbq;
		this.htCapTd = htCapTd;
		this.tsdb = tsdb;
	}

	public String getPtCapTd() {
		return ptCapTd;
	}

	public void setPtCapTd(String ptCapTd) {
		this.ptCapTd = ptCapTd;
	}

	public String getLoaiCapTd() {
		return loaiCapTd;
	}

	public void setLoaiCapTd(String loaiCapTd) {
		this.loaiCapTd = loaiCapTd;
	}

	public String getHanMuc() {
		return hanMuc;
	}

	public void setHanMuc(String hanMuc) {
		this.hanMuc = hanMuc;
	}

	public String getHmKhadung() {
		return hmKhadung;
	}

	public void setHmKhadung(String hmKhadung) {
		this.hmKhadung = hmKhadung;
	}

	public String getDuNo() {
		return duNo;
	}

	public void setDuNo(String duNo) {
		this.duNo = duNo;
	}

	public String getLsbq() {
		return lsbq;
	}

	public void setLsbq(String lsbq) {
		this.lsbq = lsbq;
	}

	public String getHtCapTd() {
		return htCapTd;
	}

	public void setHtCapTd(String htCapTd) {
		this.htCapTd = htCapTd;
	}

	public String getTsdb() {
		return tsdb;
	}

	public void setTsdb(String tsdb) {
		this.tsdb = tsdb;
	}

	public String getSoHopDong() {
		return soHopDong;
	}

	public void setSoHopDong(String soHopDong) {
		this.soHopDong = soHopDong;
	}

	public String getLimitSOLID() {
		return limitSOLID;
	}

	public void setLimitSOLID(String limitSOLID) {
		this.limitSOLID = limitSOLID;
	}

	public String getNgayMoHD() {
		return ngayMoHD;
	}

	public void setNgayMoHD(String ngayMoHD) {
		this.ngayMoHD = ngayMoHD;
	}

	public String getLimExpDate() {
		return limExpDate;
	}

	public void setLimExpDate(String limExpDate) {
		this.limExpDate = limExpDate;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
	}

	public String getCapPheDuyet() {
		return capPheDuyet;
	}

	public void setCapPheDuyet(String capPheDuyet) {
		this.capPheDuyet = capPheDuyet;
	}

	
}
