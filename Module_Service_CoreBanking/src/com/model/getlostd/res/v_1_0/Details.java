package com.model.getlostd.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Details", namespace = "http://www.finacle.com/fixml",propOrder = { "openDate", "soTheActive", "ngayQHTD",
		"nhomNoCif","soNgayQH", "dsGiaiNgan", "dsGiaiNgan1", "dsGiaiNgan2", "dsThuNo",
		"dsThuNo1", "dsThuNo2" })
public class Details {

	@XmlElement(name = "OpenDate", namespace = "http://www.finacle.com/fixml")
	protected String openDate;
	@XmlElement(name = "soTheActive", namespace = "http://www.finacle.com/fixml")
	protected String soTheActive;
	@XmlElement(name = "NgayQHTD", namespace = "http://www.finacle.com/fixml")
	protected String ngayQHTD;
	@XmlElement(name = "NhomNoCif", namespace = "http://www.finacle.com/fixml")
	protected String nhomNoCif;
	@XmlElement(name = "SoNgayQH", namespace = "http://www.finacle.com/fixml")
	protected String soNgayQH;
	@XmlElement(name = "DSGiaiNgan", namespace = "http://www.finacle.com/fixml")
	protected String dsGiaiNgan;
	@XmlElement(name = "DSGiaiNgan1", namespace = "http://www.finacle.com/fixml")
	protected String dsGiaiNgan1;
	@XmlElement(name = "DSGiaiNgan2", namespace = "http://www.finacle.com/fixml")
	protected String dsGiaiNgan2;
	@XmlElement(name = "DSThuNo", namespace = "http://www.finacle.com/fixml")
	protected String dsThuNo;
	@XmlElement(name = "DSThuNo1", namespace = "http://www.finacle.com/fixml")
	protected String dsThuNo1;
	@XmlElement(name = "DSThuNo2", namespace = "http://www.finacle.com/fixml")
	protected String dsThuNo2;

	public Details() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Details(String openDate, String soTheActive, String ngayQHTD,
			String nhomNoCif, String soNgayQH, String dsGiaiNgan,
			String dsGiaiNgan1, String dsGiaiNgan2, String dsThuNo,
			String dsThuNo1, String dsThuNo2) {
		super();
		this.openDate = openDate;
		this.soTheActive = soTheActive;
		this.ngayQHTD = ngayQHTD;
		this.nhomNoCif = nhomNoCif;
		this.soNgayQH = soNgayQH;
		this.dsGiaiNgan = dsGiaiNgan;
		this.dsGiaiNgan1 = dsGiaiNgan1;
		this.dsGiaiNgan2 = dsGiaiNgan2;
		this.dsThuNo = dsThuNo;
		this.dsThuNo1 = dsThuNo1;
		this.dsThuNo2 = dsThuNo2;
	}

	public String getSoTheActive() {
		return soTheActive;
	}

	public void setSoTheActive(String soTheActive) {
		this.soTheActive = soTheActive;
	}

	public String getSoNgayQH() {
		return soNgayQH;
	}

	public void setSoNgayQH(String soNgayQH) {
		this.soNgayQH = soNgayQH;
	}

	public String getDsGiaiNgan1() {
		return dsGiaiNgan1;
	}

	public void setDsGiaiNgan1(String dsGiaiNgan1) {
		this.dsGiaiNgan1 = dsGiaiNgan1;
	}

	public String getDsGiaiNgan2() {
		return dsGiaiNgan2;
	}

	public void setDsGiaiNgan2(String dsGiaiNgan2) {
		this.dsGiaiNgan2 = dsGiaiNgan2;
	}

	public String getDsThuNo1() {
		return dsThuNo1;
	}

	public void setDsThuNo1(String dsThuNo1) {
		this.dsThuNo1 = dsThuNo1;
	}

	public String getDsThuNo2() {
		return dsThuNo2;
	}

	public void setDsThuNo2(String dsThuNo2) {
		this.dsThuNo2 = dsThuNo2;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getNgayQHTD() {
		return ngayQHTD;
	}

	public void setNgayQHTD(String ngayQHTD) {
		this.ngayQHTD = ngayQHTD;
	}

	public String getNhomNoCif() {
		return nhomNoCif;
	}

	public void setNhomNoCif(String nhomNoCif) {
		this.nhomNoCif = nhomNoCif;
	}

	public String getDsGiaiNgan() {
		return dsGiaiNgan;
	}

	public void setDsGiaiNgan(String dsGiaiNgan) {
		this.dsGiaiNgan = dsGiaiNgan;
	}

	public String getDsThuNo() {
		return dsThuNo;
	}

	public void setDsThuNo(String dsThuNo) {
		this.dsThuNo = dsThuNo;
	}

}
