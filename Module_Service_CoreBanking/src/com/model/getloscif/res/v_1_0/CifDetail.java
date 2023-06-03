package com.model.getloscif.res.v_1_0;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.Expose;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cifDetail", namespace = "http://www.finacle.com/fixml", propOrder = { "cif", "tenKh", "gioiTinh", "ngaySinh", "noiSinh", "quocTich", "danToc", "ttHonNhan", "khDen", "cuTru", "kCapTinDung", "hanChe",
		"vip", "dsaid", "acctmngrid", "maNVEIB","loaiHinhDN","taxId","vonDL","nnTT41","nguoiDaiDien","chucVu", "salutation", "document", "phone", "loaiKH", "address", "khlq", "email","tttc", "errcode", "errDesc" })
public class CifDetail {

	@Expose
	@XmlElement(name = "cif", namespace = "http://www.finacle.com/fixml")
	protected String cif;
	@Expose
	@XmlElement(name = "tenKh", namespace = "http://www.finacle.com/fixml")
	protected String tenKh;
	@Expose
	@XmlElement(name = "gioiTinh", namespace = "http://www.finacle.com/fixml")
	protected String gioiTinh;
	@Expose
	@XmlElement(name = "ngaySinh", namespace = "http://www.finacle.com/fixml")
	protected String ngaySinh;
	@Expose
	@XmlElement(name = "noiSinh", namespace = "http://www.finacle.com/fixml")
	protected String noiSinh;
	@Expose
	@XmlElement(name = "quocTich", namespace = "http://www.finacle.com/fixml")
	protected String quocTich;
	@Expose
	@XmlElement(name = "danToc", namespace = "http://www.finacle.com/fixml")
	protected String danToc;
	@Expose
	@XmlElement(name = "ttHonNhan", namespace = "http://www.finacle.com/fixml")
	protected String ttHonNhan;
	@Expose
	@XmlElement(name = "khDen", namespace = "http://www.finacle.com/fixml")
	protected String khDen;
	@Expose
	@XmlElement(name = "cuTru", namespace = "http://www.finacle.com/fixml")
	protected String cuTru;
	@Expose
	@XmlElement(name = "kCapTinDung", namespace = "http://www.finacle.com/fixml")
	protected String kCapTinDung;
	@Expose
	@XmlElement(name = "hanChe", namespace = "http://www.finacle.com/fixml")
	protected String hanChe;
	@Expose
	@XmlElement(name = "vip", namespace = "http://www.finacle.com/fixml")
	protected String vip;
	@Expose
	@XmlElement(name = "loaiKH", namespace = "http://www.finacle.com/fixml")
	protected String loaiKH;
	@Expose
	@XmlElement(name = "dsaid", namespace = "http://www.finacle.com/fixml")
	protected String dsaid;
	@Expose
	@XmlElement(name = "acctmngrid", namespace = "http://www.finacle.com/fixml")
	protected String acctmngrid;
	@Expose
	@XmlElement(name = "maNVEIB", namespace = "http://www.finacle.com/fixml")
	protected String maNVEIB;
	@Expose
	@XmlElement(name = "loaiHinhDN", namespace = "http://www.finacle.com/fixml")
	protected String loaiHinhDN;
	@Expose
	@XmlElement(name = "taxId", namespace = "http://www.finacle.com/fixml")
	protected String taxId;
	@Expose
	@XmlElement(name = "vonDL", namespace = "http://www.finacle.com/fixml")
	protected String vonDL;
	@Expose
	@XmlElement(name = "nnTT41", namespace = "http://www.finacle.com/fixml")
	protected String nnTT41;
	@Expose
	@XmlElement(name = "nguoiDaiDien", namespace = "http://www.finacle.com/fixml")
	protected String nguoiDaiDien;
	@Expose
	@XmlElement(name = "chucVu", namespace = "http://www.finacle.com/fixml")
	protected String chucVu;
	@Expose
	@XmlElement(name = "salutation", namespace = "http://www.finacle.com/fixml")
	protected String salutation;

	@Expose
	@XmlElement(name = "document", namespace = "http://www.finacle.com/fixml")
	protected List<Document> document;

	@Expose
	@XmlElement(name = "phone", namespace = "http://www.finacle.com/fixml")
	protected List<Phone> phone;

	@Expose
	@XmlElement(name = "address", namespace = "http://www.finacle.com/fixml")
	protected List<Address> address;

	@Expose
	@XmlElement(name = "khlq", namespace = "http://www.finacle.com/fixml")
	protected List<KHLQ> khlq;

	@Expose
	@XmlElement(name = "email", namespace = "http://www.finacle.com/fixml")
	protected List<Email> email;

	@XmlElement(name = "tttc", namespace = "http://www.finacle.com/fixml")
	protected TTTC tttc;


	protected String errcode;
	protected String errDesc;

	public CifDetail() {

	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getTenKh() {
		return tenKh;
	}

	public void setTenKh(String tenKh) {
		this.tenKh = tenKh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public String getLoaiHinhDN() {
		return loaiHinhDN;
	}

	public void setLoaiHinhDN(String loaiHinhDN) {
		this.loaiHinhDN = loaiHinhDN;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getVonDL() {
		return vonDL;
	}

	public void setVonDL(String vonDL) {
		this.vonDL = vonDL;
	}

	public String getNguoiDaiDien() {
		return nguoiDaiDien;
	}

	public void setNguoiDaiDien(String nguoiDaiDien) {
		this.nguoiDaiDien = nguoiDaiDien;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public TTTC getTttc() {
		return tttc;
	}

	public void setTttc(TTTC tttc) {
		this.tttc = tttc;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getNoiSinh() {
		return noiSinh;
	}

	public void setNoiSinh(String noiSinh) {
		this.noiSinh = noiSinh;
	}

	public String getQuocTich() {
		return quocTich;
	}

	public void setQuocTich(String quocTich) {
		this.quocTich = quocTich;
	}

	public String getDanToc() {
		return danToc;
	}

	public void setDanToc(String danToc) {
		this.danToc = danToc;
	}

	public String getTtHonNhan() {
		return ttHonNhan;
	}

	public void setTtHonNhan(String ttHonNhan) {
		this.ttHonNhan = ttHonNhan;
	}

	public String getKhDen() {
		return khDen;
	}

	public void setKhDen(String khDen) {
		this.khDen = khDen;
	}

	public String getCuTru() {
		return cuTru;
	}

	public void setCuTru(String cuTru) {
		this.cuTru = cuTru;
	}

	public String getkCapTinDung() {
		return kCapTinDung;
	}

	public void setkCapTinDung(String kCapTinDung) {
		this.kCapTinDung = kCapTinDung;
	}

	public String getHanChe() {
		return hanChe;
	}

	public void setHanChe(String hanChe) {
		this.hanChe = hanChe;
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip;
	}

	public String getDsaid() {
		return dsaid;
	}

	public void setDsaid(String dsaid) {
		this.dsaid = dsaid;
	}

	public String getAcctmngrid() {
		return acctmngrid;
	}

	public void setAcctmngrid(String acctmngrid) {
		this.acctmngrid = acctmngrid;
	}

	public String getMaNVEIB() {
		return maNVEIB;
	}

	public void setMaNVEIB(String maNVEIB) {
		this.maNVEIB = maNVEIB;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public List<Document> getDocument() {
		return document;
	}

	public void setDocument(List<Document> document) {
		this.document = document;
	}

	public List<Phone> getPhone() {
		return phone;
	}

	public void setPhone(List<Phone> phone) {
		this.phone = phone;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<KHLQ> getKhlq() {
		return khlq;
	}

	public void setKhlq(List<KHLQ> khlq) {
		this.khlq = khlq;
	}

	public List<Email> getEmail() {
		return email;
	}

	public void setEmail(List<Email> email) {
		this.email = email;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrDesc() {
		return errDesc;
	}

	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}

	public String getLoaiKH() {
		return loaiKH;
	}

	public void setLoaiKH(String loaiKH) {
		this.loaiKH = loaiKH;
	}

}
