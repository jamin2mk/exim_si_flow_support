package com.model.getloscif.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "khlq", namespace = "http://www.finacle.com/fixml", propOrder = { "khlqCif", "khlqTen", "khlQngaySinh", "khlQloaiID", "khlqid", "khlqNgayCap", "khlqQuocGiaCap", "khlqNoiCap", "khlqDtdd", "khlqDtcd",
		"khlqEmail", "khlqQuocGiaDiaChi", "khlqThanhPhoDiaChi", "khlqdDiaChi", "khlqThoiDiem", "khlqLoaiKH", "khlqLoaiDiaChi" })
public class KHLQ {

	@Expose
	@SerializedName("KHLQCif")
	@XmlElement(name = "KHLQCif", namespace = "http://www.finacle.com/fixml")
	protected String khlqCif;

	@Expose
	@SerializedName("KHLQTen")
	@XmlElement(name = "KHLQTen", namespace = "http://www.finacle.com/fixml")
	protected String khlqTen;

	@Expose
	@SerializedName("KHLQngaySinh")
	@XmlElement(name = "KHLQngaySinh", namespace = "http://www.finacle.com/fixml")
	protected String khlQngaySinh;

	@Expose
	@SerializedName("KHLQloaiID")
	@XmlElement(name = "KHLQloaiID", namespace = "http://www.finacle.com/fixml")
	protected String khlQloaiID;

	@Expose
	@SerializedName("KHLQID")
	@XmlElement(name = "KHLQID", namespace = "http://www.finacle.com/fixml")
	protected String khlqid;

	@Expose
	@SerializedName("KHLQNgayCap")
	@XmlElement(name = "KHLQNgayCap", namespace = "http://www.finacle.com/fixml")
	protected String khlqNgayCap;

	@Expose
	@SerializedName("KHLQQuocGiaCap")
	@XmlElement(name = "KHLQQuocGiaCap", namespace = "http://www.finacle.com/fixml")
	protected String khlqQuocGiaCap;

	@Expose
	@SerializedName("KHLQNoiCap")
	@XmlElement(name = "KHLQNoiCap", namespace = "http://www.finacle.com/fixml")
	protected String khlqNoiCap;

	@Expose
	@SerializedName("KHLQDtdd")
	@XmlElement(name = "KHLQDtdd", namespace = "http://www.finacle.com/fixml")
	protected String khlqDtdd;

	@Expose
	@SerializedName("KHLQDtcd")
	@XmlElement(name = "KHLQDtcd", namespace = "http://www.finacle.com/fixml")
	protected String khlqDtcd;

	@Expose
	@SerializedName("KHLQEmail")
	@XmlElement(name = "KHLQEmail", namespace = "http://www.finacle.com/fixml")
	protected String khlqEmail;

	@Expose
	@SerializedName("KHLQQuocGiaDiaChi")
	@XmlElement(name = "KHLQQuocGiaDiaChi", namespace = "http://www.finacle.com/fixml")
	protected String khlqQuocGiaDiaChi;

	@Expose
	@SerializedName("KHLQThanhPhoDiaChi")
	@XmlElement(name = "KHLQThanhPhoDiaChi", namespace = "http://www.finacle.com/fixml")
	protected String khlqThanhPhoDiaChi;

	@Expose
	@SerializedName("KHLQDDiaChi")
	@XmlElement(name = "KHLQDDiaChi", namespace = "http://www.finacle.com/fixml")
	protected String khlqdDiaChi;

	@Expose
	@SerializedName("KHLQThoiDiem")
	@XmlElement(name = "KHLQThoiDiem", namespace = "http://www.finacle.com/fixml")
	protected String khlqThoiDiem;

	@Expose
	@SerializedName("KHLQLoaiKH")
	@XmlElement(name = "KHLQLoaiKH", namespace = "http://www.finacle.com/fixml")
	protected String khlqLoaiKH;

	@Expose
	@SerializedName("KHLQLoaiDiaChi")
	@XmlElement(name = "KHLQLoaiDiaChi", namespace = "http://www.finacle.com/fixml")
	protected String khlqLoaiDiaChi;

	public String getKhlqCif() {
		return khlqCif;
	}

	public void setKhlqCif(String khlqCif) {
		this.khlqCif = khlqCif;
	}

	public String getKhlqTen() {
		return khlqTen;
	}

	public void setKhlqTen(String khlqTen) {
		this.khlqTen = khlqTen;
	}

	public String getKhlQngaySinh() {
		return khlQngaySinh;
	}

	public void setKhlQngaySinh(String khlQngaySinh) {
		this.khlQngaySinh = khlQngaySinh;
	}

	public String getKhlQloaiID() {
		return khlQloaiID;
	}

	public void setKhlQloaiID(String khlQloaiID) {
		this.khlQloaiID = khlQloaiID;
	}

	public String getKhlqid() {
		return khlqid;
	}

	public void setKhlqid(String khlqid) {
		this.khlqid = khlqid;
	}

	public String getKhlqNgayCap() {
		return khlqNgayCap;
	}

	public void setKhlqNgayCap(String khlqNgayCap) {
		this.khlqNgayCap = khlqNgayCap;
	}

	public String getKhlqQuocGiaCap() {
		return khlqQuocGiaCap;
	}

	public void setKhlqQuocGiaCap(String khlqQuocGiaCap) {
		this.khlqQuocGiaCap = khlqQuocGiaCap;
	}

	public String getKhlqNoiCap() {
		return khlqNoiCap;
	}

	public void setKhlqNoiCap(String khlqNoiCap) {
		this.khlqNoiCap = khlqNoiCap;
	}

	public String getKhlqDtdd() {
		return khlqDtdd;
	}

	public void setKhlqDtdd(String khlqDtdd) {
		this.khlqDtdd = khlqDtdd;
	}

	public String getKhlqDtcd() {
		return khlqDtcd;
	}

	public void setKhlqDtcd(String khlqDtcd) {
		this.khlqDtcd = khlqDtcd;
	}

	public String getKhlqEmail() {
		return khlqEmail;
	}

	public void setKhlqEmail(String khlqEmail) {
		this.khlqEmail = khlqEmail;
	}

	public String getKhlqQuocGiaDiaChi() {
		return khlqQuocGiaDiaChi;
	}

	public void setKhlqQuocGiaDiaChi(String khlqQuocGiaDiaChi) {
		this.khlqQuocGiaDiaChi = khlqQuocGiaDiaChi;
	}

	public String getKhlqThanhPhoDiaChi() {
		return khlqThanhPhoDiaChi;
	}

	public void setKhlqThanhPhoDiaChi(String khlqThanhPhoDiaChi) {
		this.khlqThanhPhoDiaChi = khlqThanhPhoDiaChi;
	}

	public String getKhlqdDiaChi() {
		return khlqdDiaChi;
	}

	public void setKhlqdDiaChi(String khlqdDiaChi) {
		this.khlqdDiaChi = khlqdDiaChi;
	}

	public String getKhlqThoiDiem() {
		return khlqThoiDiem;
	}

	public void setKhlqThoiDiem(String khlqThoiDiem) {
		this.khlqThoiDiem = khlqThoiDiem;
	}

	public String getKhlqLoaiKH() {
		return khlqLoaiKH;
	}

	public void setKhlqLoaiKH(String khlqLoaiKH) {
		this.khlqLoaiKH = khlqLoaiKH;
	}

	public String getKhlqLoaiDiaChi() {
		return khlqLoaiDiaChi;
	}

	public void setKhlqLoaiDiaChi(String khlqLoaiDiaChi) {
		this.khlqLoaiDiaChi = khlqLoaiDiaChi;
	}

}
