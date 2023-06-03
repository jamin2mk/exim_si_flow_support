package com.model.getloscif.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.Expose;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "address", namespace = "http://www.finacle.com/fixml", propOrder = {
		"quocGiaDiaChi", "thanhPhoDiaChi", "diaChi", "hieuLucDiaChi",
		"loaiDiaChi","addrPreferType" })
public class Address {

	@Expose
	@XmlElement(name = "quocGiaDiaChi", namespace = "http://www.finacle.com/fixml")
	protected String quocGiaDiaChi;
	@Expose
	@XmlElement(name = "thanhPhoDiaChi", namespace = "http://www.finacle.com/fixml")
	protected String thanhPhoDiaChi;
	@Expose
	@XmlElement(name = "diaChi", namespace = "http://www.finacle.com/fixml")
	protected String diaChi;
	@Expose
	@XmlElement(name = "hieuLucDiaChi", namespace = "http://www.finacle.com/fixml")
	protected String hieuLucDiaChi;
	@Expose
	@XmlElement(name = "loaiDiaChi", namespace = "http://www.finacle.com/fixml")
	protected String loaiDiaChi;
	@Expose
	@XmlElement(name = "addrPreferType", namespace = "http://www.finacle.com/fixml")
	protected String addrPreferType;

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String quocGiaDiaChi, String thanhPhoDiaChi, String diaChi,
			String hieuLucDiaChi, String loaiDiaChi, String addrPreferType) {
		super();
		this.quocGiaDiaChi = quocGiaDiaChi;
		this.thanhPhoDiaChi = thanhPhoDiaChi;
		this.diaChi = diaChi;
		this.hieuLucDiaChi = hieuLucDiaChi;
		this.loaiDiaChi = loaiDiaChi;
		this.addrPreferType = addrPreferType;
	}

	public String getQuocGiaDiaChi() {
		return quocGiaDiaChi;
	}

	public void setQuocGiaDiaChi(String quocGiaDiaChi) {
		this.quocGiaDiaChi = quocGiaDiaChi;
	}

	public String getThanhPhoDiaChi() {
		return thanhPhoDiaChi;
	}

	public void setThanhPhoDiaChi(String thanhPhoDiaChi) {
		this.thanhPhoDiaChi = thanhPhoDiaChi;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getHieuLucDiaChi() {
		return hieuLucDiaChi;
	}

	public void setHieuLucDiaChi(String hieuLucDiaChi) {
		this.hieuLucDiaChi = hieuLucDiaChi;
	}

	public String getLoaiDiaChi() {
		return loaiDiaChi;
	}

	public void setLoaiDiaChi(String loaiDiaChi) {
		this.loaiDiaChi = loaiDiaChi;
	}

	public String getAddrPreferType() {
		return addrPreferType;
	}

	public void setAddrPreferType(String addrPreferType) {
		this.addrPreferType = addrPreferType;
	}

}
