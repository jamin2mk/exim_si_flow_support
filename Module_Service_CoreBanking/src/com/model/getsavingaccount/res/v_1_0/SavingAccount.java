package com.model.getsavingaccount.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.SerializedName;

@XmlType(name = "SAVINGACCOUNT")
@XmlAccessorType(XmlAccessType.FIELD)
public class SavingAccount {

	@XmlElement(name = "SOTIETKIEM")
	@SerializedName("SOTIETKIEM")
	protected String soTietKiem;
	@XmlElement(name = "LOAITIEN")
	@SerializedName("LOAITIEN")
	protected String loaiTien;
	@XmlElement(name = "LS")
	@SerializedName("LS")
	protected String ls;
	@XmlElement(name = "NGAYPHATHANH")
	@SerializedName("NGAYPHATHANH")
	protected String ngayPhatHanh;
	@XmlElement(name = "NGAYDAOHAN")
	@SerializedName("NGAYDAOHAN")
	protected String ngayDaoHan;
	@XmlElement(name = "GIATRI")
	@SerializedName("GIATRI")
	protected String giaTri;
	@XmlElement(name = "DANGDAMBAO")
	@SerializedName("DANGDAMBAO")
	protected String dangDamBao;
	@XmlElement(name = "SODUKHADUNG")
	@SerializedName("SODUKHADUNG")
	protected String soDuKhaDung;

	public SavingAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SavingAccount(String soTietKiem, String loaiTien, String ls,
			String ngayPhatHanh, String ngayDaoHan, String giaTri,
			String dangDamBao, String soDuKhaDung) {
		super();
		this.soTietKiem = soTietKiem;
		this.loaiTien = loaiTien;
		this.ls = ls;
		this.ngayPhatHanh = ngayPhatHanh;
		this.ngayDaoHan = ngayDaoHan;
		this.giaTri = giaTri;
		this.dangDamBao = dangDamBao;
		this.soDuKhaDung = soDuKhaDung;
	}

	public String getSoTietKiem() {
		return soTietKiem;
	}

	public void setSoTietKiem(String soTietKiem) {
		this.soTietKiem = soTietKiem;
	}

	public String getLoaiTien() {
		return loaiTien;
	}

	public void setLoaiTien(String loaiTien) {
		this.loaiTien = loaiTien;
	}

	public String getLs() {
		return ls;
	}

	public void setLs(String ls) {
		this.ls = ls;
	}

	public String getNgayPhatHanh() {
		return ngayPhatHanh;
	}

	public void setNgayPhatHanh(String ngayPhatHanh) {
		this.ngayPhatHanh = ngayPhatHanh;
	}

	public String getNgayDaoHan() {
		return ngayDaoHan;
	}

	public void setNgayDaoHan(String ngayDaoHan) {
		this.ngayDaoHan = ngayDaoHan;
	}

	public String getGiaTri() {
		return giaTri;
	}

	public void setGiaTri(String giaTri) {
		this.giaTri = giaTri;
	}

	public String getDangDamBao() {
		return dangDamBao;
	}

	public void setDangDamBao(String dangDamBao) {
		this.dangDamBao = dangDamBao;
	}

	public String getSoDuKhaDung() {
		return soDuKhaDung;
	}

	public void setSoDuKhaDung(String soDuKhaDung) {
		this.soDuKhaDung = soDuKhaDung;
	}

}
