package com.model.creditcardpublishsub.res.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name = "CreditCardPublish_subResponse", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditCardPublish_subResponse {

	@XmlElement(name = "status_return")
	@SerializedName("status_return")
	protected String statusReturn;
	@XmlElement(name = "description_message")
	@SerializedName("description_message")
	protected String descriptionMessage;
	@XmlElement(name = "brcd")
	@SerializedName("brcd")
	protected String brcd;
	@XmlElement(name = "seqno")
	@SerializedName("seqno")
	protected String seqno;
	@XmlElement(name = "supseqno")
	@SerializedName("supseqno")
	protected String supseqno;
	@XmlElement(name = "receiptdt")
	@SerializedName("receiptdt")
	protected String receiptdt;
	@XmlElement(name = "fullnm")
	@SerializedName("fullnm")
	protected String fullnm;
	@XmlElement(name = "prnnm")
	@SerializedName("prnnm")
	protected String prnnm;
	@XmlElement(name = "birthdt")
	@SerializedName("birthdt")
	protected String birthdt;
	@XmlElement(name = "sex")
	@SerializedName("sex")
	protected String sex;
	@XmlElement(name = "idcard")
	@SerializedName("idcard")
	protected String idcard;
	@XmlElement(name = "pmnaddr")
	@SerializedName("pmnaddr")
	protected String pmnaddr;
	@XmlElement(name = "hmphoneno")
	@SerializedName("hmphoneno")
	protected String hmphoneno;
	@XmlElement(name = "hmnationality")
	@SerializedName("hmnationality")
	protected String hmnationality;
	@XmlElement(name = "ofcnm")
	@SerializedName("ofcnm")
	protected String ofcnm;
	@XmlElement(name = "ofcaddr")
	@SerializedName("ofcaddr")
	protected String ofcaddr;
	@XmlElement(name = "ofcphoneno")
	@SerializedName("ofcphoneno")
	protected String ofcphoneno;
	@XmlElement(name = "mommdnm")
	@SerializedName("mommdnm")
	protected String mommdnm;
	@XmlElement(name = "rationwtapp")
	@SerializedName("rationwtapp")
	protected String rationwtapp;
	@XmlElement(name = "mobifon")
	@SerializedName("mobifon")
	protected String mobifon;
	@XmlElement(name = "photofilename")
	@SerializedName("photofilename")
	protected String photofilename;
	@XmlElement(name = "note")
	@SerializedName("note")
	protected String note;
	@XmlElement(name = "status")
	@SerializedName("status")
	protected String status;
	@XmlElement(name = "checked")
	@SerializedName("checked")
	protected String checked;
	@XmlElement(name = "checkeddt")
	@SerializedName("checkeddt")
	protected String checkeddt;
	@XmlElement(name = "approved")
	@SerializedName("approved")
	protected String approved;
	@XmlElement(name = "approveddt")
	@SerializedName("approveddt")
	protected String approveddt;
	@XmlElement(name = "cardno")
	@SerializedName("cardno")
	protected String cardno;
	@XmlElement(name = "issutp")
	@SerializedName("issutp")
	protected String issutp;
	@XmlElement(name = "flgsms")
	@SerializedName("flgsms")
	protected String flgsms;
	@XmlElement(name = "flglck")
	@SerializedName("flglck")
	protected String flglck;
	@XmlElement(name = "tellck")
	@SerializedName("tellck")
	protected String tellck;
	@XmlElement(name = "flgquery")
	@SerializedName("flgquery")
	protected String flgquery;
	@XmlElement(name = "telquery")
	@SerializedName("telquery")
	protected String telquery;
	@XmlElement(name = "flgnote")
	@SerializedName("flgnote")
	protected String flgnote;
	@XmlElement(name = "telnote")
	@SerializedName("telnote")
	protected String telnote;
	@XmlElement(name = "flgrcv")
	@SerializedName("flgrcv")
	protected String flgrcv;
	@XmlElement(name = "flgtimes")
	@SerializedName("flgtimes")
	protected String flgtimes;
	@XmlElement(name = "times")
	@SerializedName("times")
	protected String times;
	@XmlElement(name = "flgamt")
	@SerializedName("flgamt")
	protected String flgamt;
	@XmlElement(name = "amount")
	@SerializedName("amount")
	protected String amount;
	@XmlElement(name = "flg3dsce")
	@SerializedName("flg3dsce")
	protected String flg3dsce;
	@XmlElement(name = "email3dsce")
	@SerializedName("email3dsce")
	protected String email3dsce;
	@XmlElement(name = "tel13dsce")
	@SerializedName("tel13dsce")
	protected String tel13dsce;
	@XmlElement(name = "tel33dsce")
	@SerializedName("tel33dsce")
	protected String tel33dsce;
	@XmlElement(name = "tel23dsce")
	@SerializedName("tel23dsce")
	protected String tel23dsce;
	@XmlElement(name = "sendmailflg")
	@SerializedName("sendmailflg")
	protected String sendmailflg;
	@XmlElement(name = "brcd_dept_rcv")
	@SerializedName("brcd_dept_rcv")
	protected String brcdDeptRcv;
	@XmlElement(name = "brcd_dept_name_rcv")
	@SerializedName("brcd_dept_name_rcv")
	protected String brcdDeptNameRcv;
	@XmlElement(name = "recevcard_mk")
	@SerializedName("recevcard_mk")
	protected String recevcardMk;
	@XmlElement(name = "recevcard_add")
	@SerializedName("recevcard_add")
	protected String recevcardAdd;
	@XmlElement(name = "email")
	@SerializedName("email")
	protected String email;
	@XmlElement(name = "recevcard_reason")
	@SerializedName("recevcard_reason")
	protected String recevcardReason;

	public CreditCardPublish_subResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreditCardPublish_subResponse(String statusReturn,
			String descriptionMessage, String brcd, String seqno,
			String supseqno, String receiptdt, String fullnm, String prnnm,
			String birthdt, String sex, String idcard, String pmnaddr,
			String hmphoneno, String hmnationality, String ofcnm,
			String ofcaddr, String ofcphoneno, String mommdnm,
			String rationwtapp, String mobifon, String photofilename,
			String note, String status, String checked, String checkeddt,
			String approved, String approveddt, String cardno, String issutp,
			String flgsms, String flglck, String tellck, String flgquery,
			String telquery, String flgnote, String telnote, String flgrcv,
			String flgtimes, String times, String flgamt, String amount,
			String flg3dsce, String email3dsce, String tel13dsce,
			String tel33dsce, String tel23dsce, String sendmailflg,
			String brcdDeptRcv, String brcdDeptNameRcv, String recevcardMk,
			String recevcardAdd, String email, String recevcardReason) {
		super();
		this.statusReturn = statusReturn;
		this.descriptionMessage = descriptionMessage;
		this.brcd = brcd;
		this.seqno = seqno;
		this.supseqno = supseqno;
		this.receiptdt = receiptdt;
		this.fullnm = fullnm;
		this.prnnm = prnnm;
		this.birthdt = birthdt;
		this.sex = sex;
		this.idcard = idcard;
		this.pmnaddr = pmnaddr;
		this.hmphoneno = hmphoneno;
		this.hmnationality = hmnationality;
		this.ofcnm = ofcnm;
		this.ofcaddr = ofcaddr;
		this.ofcphoneno = ofcphoneno;
		this.mommdnm = mommdnm;
		this.rationwtapp = rationwtapp;
		this.mobifon = mobifon;
		this.photofilename = photofilename;
		this.note = note;
		this.status = status;
		this.checked = checked;
		this.checkeddt = checkeddt;
		this.approved = approved;
		this.approveddt = approveddt;
		this.cardno = cardno;
		this.issutp = issutp;
		this.flgsms = flgsms;
		this.flglck = flglck;
		this.tellck = tellck;
		this.flgquery = flgquery;
		this.telquery = telquery;
		this.flgnote = flgnote;
		this.telnote = telnote;
		this.flgrcv = flgrcv;
		this.flgtimes = flgtimes;
		this.times = times;
		this.flgamt = flgamt;
		this.amount = amount;
		this.flg3dsce = flg3dsce;
		this.email3dsce = email3dsce;
		this.tel13dsce = tel13dsce;
		this.tel33dsce = tel33dsce;
		this.tel23dsce = tel23dsce;
		this.sendmailflg = sendmailflg;
		this.brcdDeptRcv = brcdDeptRcv;
		this.brcdDeptNameRcv = brcdDeptNameRcv;
		this.recevcardMk = recevcardMk;
		this.recevcardAdd = recevcardAdd;
		this.email = email;
		this.recevcardReason = recevcardReason;
	}

	public String getStatusReturn() {
		return statusReturn;
	}

	public void setStatusReturn(String statusReturn) {
		this.statusReturn = statusReturn;
	}

	public String getDescriptionMessage() {
		return descriptionMessage;
	}

	public void setDescriptionMessage(String descriptionMessage) {
		this.descriptionMessage = descriptionMessage;
	}

	public String getBrcd() {
		return brcd;
	}

	public void setBrcd(String brcd) {
		this.brcd = brcd;
	}

	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getSupseqno() {
		return supseqno;
	}

	public void setSupseqno(String supseqno) {
		this.supseqno = supseqno;
	}

	public String getReceiptdt() {
		return receiptdt;
	}

	public void setReceiptdt(String receiptdt) {
		this.receiptdt = receiptdt;
	}

	public String getFullnm() {
		return fullnm;
	}

	public void setFullnm(String fullnm) {
		this.fullnm = fullnm;
	}

	public String getPrnnm() {
		return prnnm;
	}

	public void setPrnnm(String prnnm) {
		this.prnnm = prnnm;
	}

	public String getBirthdt() {
		return birthdt;
	}

	public void setBirthdt(String birthdt) {
		this.birthdt = birthdt;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPmnaddr() {
		return pmnaddr;
	}

	public void setPmnaddr(String pmnaddr) {
		this.pmnaddr = pmnaddr;
	}

	public String getHmphoneno() {
		return hmphoneno;
	}

	public void setHmphoneno(String hmphoneno) {
		this.hmphoneno = hmphoneno;
	}

	public String getHmnationality() {
		return hmnationality;
	}

	public void setHmnationality(String hmnationality) {
		this.hmnationality = hmnationality;
	}

	public String getOfcnm() {
		return ofcnm;
	}

	public void setOfcnm(String ofcnm) {
		this.ofcnm = ofcnm;
	}

	public String getOfcaddr() {
		return ofcaddr;
	}

	public void setOfcaddr(String ofcaddr) {
		this.ofcaddr = ofcaddr;
	}

	public String getOfcphoneno() {
		return ofcphoneno;
	}

	public void setOfcphoneno(String ofcphoneno) {
		this.ofcphoneno = ofcphoneno;
	}

	public String getMommdnm() {
		return mommdnm;
	}

	public void setMommdnm(String mommdnm) {
		this.mommdnm = mommdnm;
	}

	public String getRationwtapp() {
		return rationwtapp;
	}

	public void setRationwtapp(String rationwtapp) {
		this.rationwtapp = rationwtapp;
	}

	public String getMobifon() {
		return mobifon;
	}

	public void setMobifon(String mobifon) {
		this.mobifon = mobifon;
	}

	public String getPhotofilename() {
		return photofilename;
	}

	public void setPhotofilename(String photofilename) {
		this.photofilename = photofilename;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getCheckeddt() {
		return checkeddt;
	}

	public void setCheckeddt(String checkeddt) {
		this.checkeddt = checkeddt;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getApproveddt() {
		return approveddt;
	}

	public void setApproveddt(String approveddt) {
		this.approveddt = approveddt;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getIssutp() {
		return issutp;
	}

	public void setIssutp(String issutp) {
		this.issutp = issutp;
	}

	public String getFlgsms() {
		return flgsms;
	}

	public void setFlgsms(String flgsms) {
		this.flgsms = flgsms;
	}

	public String getFlglck() {
		return flglck;
	}

	public void setFlglck(String flglck) {
		this.flglck = flglck;
	}

	public String getTellck() {
		return tellck;
	}

	public void setTellck(String tellck) {
		this.tellck = tellck;
	}

	public String getFlgquery() {
		return flgquery;
	}

	public void setFlgquery(String flgquery) {
		this.flgquery = flgquery;
	}

	public String getTelquery() {
		return telquery;
	}

	public void setTelquery(String telquery) {
		this.telquery = telquery;
	}

	public String getFlgnote() {
		return flgnote;
	}

	public void setFlgnote(String flgnote) {
		this.flgnote = flgnote;
	}

	public String getTelnote() {
		return telnote;
	}

	public void setTelnote(String telnote) {
		this.telnote = telnote;
	}

	public String getFlgrcv() {
		return flgrcv;
	}

	public void setFlgrcv(String flgrcv) {
		this.flgrcv = flgrcv;
	}

	public String getFlgtimes() {
		return flgtimes;
	}

	public void setFlgtimes(String flgtimes) {
		this.flgtimes = flgtimes;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getFlgamt() {
		return flgamt;
	}

	public void setFlgamt(String flgamt) {
		this.flgamt = flgamt;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFlg3dsce() {
		return flg3dsce;
	}

	public void setFlg3dsce(String flg3dsce) {
		this.flg3dsce = flg3dsce;
	}

	public String getEmail3dsce() {
		return email3dsce;
	}

	public void setEmail3dsce(String email3dsce) {
		this.email3dsce = email3dsce;
	}

	public String getTel13dsce() {
		return tel13dsce;
	}

	public void setTel13dsce(String tel13dsce) {
		this.tel13dsce = tel13dsce;
	}

	public String getTel33dsce() {
		return tel33dsce;
	}

	public void setTel33dsce(String tel33dsce) {
		this.tel33dsce = tel33dsce;
	}

	public String getTel23dsce() {
		return tel23dsce;
	}

	public void setTel23dsce(String tel23dsce) {
		this.tel23dsce = tel23dsce;
	}

	public String getSendmailflg() {
		return sendmailflg;
	}

	public void setSendmailflg(String sendmailflg) {
		this.sendmailflg = sendmailflg;
	}

	public String getBrcdDeptRcv() {
		return brcdDeptRcv;
	}

	public void setBrcdDeptRcv(String brcdDeptRcv) {
		this.brcdDeptRcv = brcdDeptRcv;
	}

	public String getBrcdDeptNameRcv() {
		return brcdDeptNameRcv;
	}

	public void setBrcdDeptNameRcv(String brcdDeptNameRcv) {
		this.brcdDeptNameRcv = brcdDeptNameRcv;
	}

	public String getRecevcardMk() {
		return recevcardMk;
	}

	public void setRecevcardMk(String recevcardMk) {
		this.recevcardMk = recevcardMk;
	}

	public String getRecevcardAdd() {
		return recevcardAdd;
	}

	public void setRecevcardAdd(String recevcardAdd) {
		this.recevcardAdd = recevcardAdd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRecevcardReason() {
		return recevcardReason;
	}

	public void setRecevcardReason(String recevcardReason) {
		this.recevcardReason = recevcardReason;
	}

}
