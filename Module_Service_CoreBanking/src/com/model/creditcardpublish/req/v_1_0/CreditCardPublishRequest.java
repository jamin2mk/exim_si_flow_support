package com.model.creditcardpublish.req.v_1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CreditCardPublish", namespace = "http://www.alsb.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditCardPublishRequest {

	@XmlElement(name = "flag_status")
	protected String flagStatus;
	@XmlElement(name = "brcd")
	protected String brcd;
	@XmlElement(name = "seqno")
	protected String seqno;
	@XmlElement(name = "psncrd")
	protected String psncrd;
	@XmlElement(name = "cprcrd")
	protected String cprcrd;
	@XmlElement(name = "ppscrlmt")
	protected String ppscrlmt;
	@XmlElement(name = "fullnm")
	protected String fullnm;
	@XmlElement(name = "prnnm")
	protected String prnnm;
	@XmlElement(name = "birthdt")
	protected String birthdt;
	@XmlElement(name = "sex")
	protected String sex;
	@XmlElement(name = "idcard")
	protected String idcard;
	@XmlElement(name = "pmnaddr")
	protected String pmnaddr;
	@XmlElement(name = "yrsofrsd")
	protected String yrsofrsd;
	@XmlElement(name = "phoneno")
	protected String phoneno;
	@XmlElement(name = "lvlofedu")
	protected String lvlofedu;
	@XmlElement(name = "nationality")
	protected String nationality;
	@XmlElement(name = "mrtsts")
	protected String mrtsts;
	@XmlElement(name = "mommdnm")
	protected String mommdnm;
	@XmlElement(name = "housetp")
	protected String housetp;
	@XmlElement(name = "officenm")
	protected String officenm;
	@XmlElement(name = "officeaddr")
	protected String officeaddr;
	@XmlElement(name = "officetelno")
	protected String officetelno;
	@XmlElement(name = "officepos")
	protected String officepos;
	@XmlElement(name = "wrksnr")
	protected String wrksnr;
	@XmlElement(name = "labourtp")
	protected String labourtp;
	@XmlElement(name = "annual")
	protected String annual;
	@XmlElement(name = "rcvloc")
	protected String rcvloc;
	@XmlElement(name = "settltp")
	protected String settltp;
	@XmlElement(name = "dracctno")
	protected String dracctno;
	@XmlElement(name = "sttlwith")
	protected String sttlwith;
	@XmlElement(name = "accttp")
	protected String accttp;
	@XmlElement(name = "drratio")
	protected String drratio;
	@XmlElement(name = "receiptdt")
	protected String receiptdt;
	@XmlElement(name = "crdtp")
	protected String crdtp;
	@XmlElement(name = "colltp")
	protected String colltp;
	@XmlElement(name = "crlmt")
	protected String crlmt;
	@XmlElement(name = "expdt")
	protected String expdt;
	@XmlElement(name = "checked")
	protected String checked;
	@XmlElement(name = "checkeddt")
	protected String checkeddt;
	@XmlElement(name = "approved")
	protected String approved;
	@XmlElement(name = "approveddt")
	protected String approveddt;
	@XmlElement(name = "status")
	protected String status;
	@XmlElement(name = "photofilename")
	protected String photofilename;
	@XmlElement(name = "fullnm_1")
	protected String fullnm1;
	@XmlElement(name = "idcard_1")
	protected String idcard1;
	@XmlElement(name = "ofcnm")
	protected String ofcnm;
	@XmlElement(name = "ofcaddr")
	protected String ofcaddr;
	@XmlElement(name = "ofcphoneno")
	protected String ofcphoneno;
	@XmlElement(name = "hmaddr")
	protected String hmaddr;
	@XmlElement(name = "hmphoneno")
	protected String hmphoneno;
	@XmlElement(name = "appdate")
	protected String appdate;
	@XmlElement(name = "crdtype")
	protected String crdtype;
	@XmlElement(name = "crdvalidaty")
	protected String crdvalidaty;
	@XmlElement(name = "mobifon")
	protected String mobifon;
	@XmlElement(name = "email")
	protected String email;
	@XmlElement(name = "other")
	protected String other;
	@XmlElement(name = "crdtypebank")
	protected String crdtypebank;
	@XmlElement(name = "psncrdbank")
	protected String psncrdbank;
	@XmlElement(name = "cprcrdbank")
	protected String cprcrdbank;
	@XmlElement(name = "note")
	protected String note;
	@XmlElement(name = "fileno")
	protected String fileno;
	@XmlElement(name = "contractno")
	protected String contractno;
	@XmlElement(name = "accountcd")
	protected String accountcd;
	@XmlElement(name = "cardno")
	protected String cardno;
	@XmlElement(name = "brname")
	protected String brname;
	@XmlElement(name = "mntcrdval")
	protected String mntcrdval;
	@XmlElement(name = "issutp")
	protected String issutp;
	@XmlElement(name = "noteofho")
	protected String noteofho;
	@XmlElement(name = "anlfeereason")
	protected String anlfeereason;
	@XmlElement(name = "compid")
	protected String compid;
	@XmlElement(name = "custid")
	protected String custid;
	@XmlElement(name = "frdt")
	protected String frdt;
	@XmlElement(name = "todt")
	protected String todt;
	@XmlElement(name = "vsvldt")
	protected String vsvldt;
	@XmlElement(name = "flgsms")
	protected String flgsms;
	@XmlElement(name = "flglck")
	protected String flglck;
	@XmlElement(name = "tellck")
	protected String tellck;
	@XmlElement(name = "flgquery")
	protected String flgquery;
	@XmlElement(name = "telquery")
	protected String telquery;
	@XmlElement(name = "flgnote")
	protected String flgnote;
	@XmlElement(name = "telnote")
	protected String telnote;
	@XmlElement(name = "flgrcv")
	protected String flgrcv;
	@XmlElement(name = "flgtimes")
	protected String flgtimes;
	@XmlElement(name = "times")
	protected String times;
	@XmlElement(name = "flgamt")
	protected String flgamt;
	@XmlElement(name = "amount")
	protected String amount;
	@XmlElement(name = "cshlmtofcust")
	protected String cshlmtofcust;
	@XmlElement(name = "cshlmtofbnk")
	protected String cshlmtofbnk;
	@XmlElement(name = "custtpcd")
	protected String custtpcd;
	@XmlElement(name = "custdtltpcd")
	protected String custdtltpcd;
	@XmlElement(name = "colldtltp")
	protected String colldtltp;
	@XmlElement(name = "cardnbr")
	protected String cardnbr;
	@XmlElement(name = "compcustseq")
	protected String compcustseq;
	@XmlElement(name = "statedt")
	protected String statedt;
	@XmlElement(name = "comptp")
	protected String comptp;
	@XmlElement(name = "compothr")
	protected String compothr;
	@XmlElement(name = "jobtp")
	protected String jobtp;
	@XmlElement(name = "jobothr")
	protected String jobothr;
	@XmlElement(name = "edcntp")
	protected String edcntp;
	@XmlElement(name = "edcnothr")
	protected String edcnothr;
	@XmlElement(name = "addrtp")
	protected String addrtp;
	@XmlElement(name = "addrothr")
	protected String addrothr;
	@XmlElement(name = "depdno")
	protected String depdno;
	@XmlElement(name = "chkapp")
	protected String chkapp;
	@XmlElement(name = "flg3dsce")
	protected String flg3dsce;
	@XmlElement(name = "email3dsce")
	protected String email3dsce;
	@XmlElement(name = "tel13dsce")
	protected String tel13dsce;
	@XmlElement(name = "tel23dsce")
	protected String tel23dsce;
	@XmlElement(name = "tel33dsce")
	protected String tel33dsce;
	@XmlElement(name = "chkecommerce")
	protected String chkecommerce;
	@XmlElement(name = "purpose")
	protected String purpose;
	@XmlElement(name = "deptcd")
	protected String deptcd;
	@XmlElement(name = "chkemail")
	protected String chkemail;
	@XmlElement(name = "address1")
	protected String address1;
	@XmlElement(name = "address2")
	protected String address2;
	@XmlElement(name = "address3")
	protected String address3;
	@XmlElement(name = "address4")
	protected String address4;
	@XmlElement(name = "officeaddr1")
	protected String officeaddr1;
	@XmlElement(name = "rcvcardaddr")
	protected String rcvcardaddr;
	@XmlElement(name = "prdtcd")
	protected String prdtcd;
	@XmlElement(name = "vipflg")
	protected String vipflg;
	@XmlElement(name = "cmpnynm")
	protected String cmpnynm;
	@XmlElement(name = "usrname_xetduyet")
	protected String usrnameXetduyet;
	@XmlElement(name = "usrname_xetduyet_01")
	protected String usrnameXetduyet01;
	@XmlElement(name = "usrname_xetduyet_02")
	protected String usrnameXetduyet02;
	@XmlElement(name = "usrname_xetduyet_03")
	protected String usrnameXetduyet03;
	@XmlElement(name = "usrname_xetduyet_04")
	protected String usrnameXetduyet04;
	@XmlElement(name = "usrname_xetduyet_05")
	protected String usrnameXetduyet05;
	@XmlElement(name = "usrname_xetduyet_06")
	protected String usrnameXetduyet06;
	@XmlElement(name = "brcd_dept_rcv")
	protected String brcdDeptRcv;
	@XmlElement(name = "brcd_dept_name_rcv")
	protected String brcdDeptNameRcv;
	@XmlElement(name = "flgloan")
	protected String flgloan;
	@XmlElement(name = "disbursementno")
	protected String disbursementno;
	@XmlElement(name = "disbursementamt")
	protected String disbursementamt;
	@XmlElement(name = "eib_chucvu")
	protected String eibChucvu;
	@XmlElement(name = "recevcard_mk")
	protected String recevcardMk;
	@XmlElement(name = "recevcard_add")
	protected String recevcardAdd;
	@XmlElement(name = "officeaddr2")
	protected String officeaddr2;
	@XmlElement(name = "officeaddr3")
	protected String officeaddr3;
	@XmlElement(name = "other1")
	protected String other1;
	@XmlElement(name = "other2")
	protected String other2;
	@XmlElement(name = "other3")
	protected String other3;
	@XmlElement(name = "employid")
	protected String employid;
	@XmlElement(name = "addusrid")
	protected String addusrid;
	@XmlElement(name = "apprusrid")
	protected String apprusrid;
	@XmlElement(name = "brapprusrid")
	protected String brapprusrid;
	@XmlElement(name = "checkusrid")
	protected String checkusrid;
	@XmlElement(name = "odaccount")
	protected String odaccount;
	@XmlElement(name = "payacct")
	protected String payacct;
	@XmlElement(name = "ge_acctcd")
	protected String geAcctcd;
	@XmlElement(name = "ge_fileno")
	protected String geFileno;
	@XmlElement(name = "ge_contractno")
	protected String geContractno;
	@XmlElement(name = "ge_autopaypecent")
	protected String geAutopaypecent;
	@XmlElement(name = "ge_autopayamt")
	protected String geAutopayamt;
	@XmlElement(name = "ge_autocash")
	protected String geAutocash;
	@XmlElement(name = "ge_autodebit")
	protected String geAutodebit;
	@XmlElement(name = "ge_autopayacct")
	protected String geAutopayacct;
	@XmlElement(name = "shrtname")
	protected String shrtname;
	@XmlElement(name = "con_name")
	protected String conName;
	@XmlElement(name = "ge_limit")
	protected String geLimit;
	@XmlElement(name = "ge_sendaddr")
	protected String geSendaddr;
	@XmlElement(name = "ge_ctrynmloc")
	protected String geCtrynmloc;
	@XmlElement(name = "ge_cardtp1")
	protected String geCardtp1;
	@XmlElement(name = "ge_cardtp2")
	protected String geCardtp2;
	@XmlElement(name = "ge_cardtp3")
	protected String geCardtp3;
	@XmlElement(name = "ge_cardtp4")
	protected String geCardtp4;
	@XmlElement(name = "freetp")
	protected String freetp;
	@XmlElement(name = "crdlmtcd")
	protected String crdlmtcd;
	@XmlElement(name = "unststm")
	protected String unststm;
	@XmlElement(name = "recevcard_reason")
	protected String recevcardReason;

	public CreditCardPublishRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreditCardPublishRequest(String flagStatus, String brcd,
			String seqno, String psncrd, String cprcrd, String ppscrlmt,
			String fullnm, String prnnm, String birthdt, String sex,
			String idcard, String pmnaddr, String yrsofrsd, String phoneno,
			String lvlofedu, String nationality, String mrtsts, String mommdnm,
			String housetp, String officenm, String officeaddr,
			String officetelno, String officepos, String wrksnr,
			String labourtp, String annual, String rcvloc, String settltp,
			String dracctno, String sttlwith, String accttp, String drratio,
			String receiptdt, String crdtp, String colltp, String crlmt,
			String expdt, String checked, String checkeddt, String approved,
			String approveddt, String status, String photofilename,
			String fullnm1, String idcard1, String ofcnm, String ofcaddr,
			String ofcphoneno, String hmaddr, String hmphoneno, String appdate,
			String crdtype, String crdvalidaty, String mobifon, String email,
			String other, String crdtypebank, String psncrdbank,
			String cprcrdbank, String note, String fileno, String contractno,
			String accountcd, String cardno, String brname, String mntcrdval,
			String issutp, String noteofho, String anlfeereason, String compid,
			String custid, String frdt, String todt, String vsvldt,
			String flgsms, String flglck, String tellck, String flgquery,
			String telquery, String flgnote, String telnote, String flgrcv,
			String flgtimes, String times, String flgamt, String amount,
			String cshlmtofcust, String cshlmtofbnk, String custtpcd,
			String custdtltpcd, String colldtltp, String cardnbr,
			String compcustseq, String statedt, String comptp, String compothr,
			String jobtp, String jobothr, String edcntp, String edcnothr,
			String addrtp, String addrothr, String depdno, String chkapp,
			String flg3dsce, String email3dsce, String tel13dsce,
			String tel23dsce, String tel33dsce, String chkecommerce,
			String purpose, String deptcd, String chkemail, String address1,
			String address2, String address3, String address4,
			String officeaddr1, String rcvcardaddr, String prdtcd,
			String vipflg, String cmpnynm, String usrnameXetduyet,
			String usrnameXetduyet01, String usrnameXetduyet02,
			String usrnameXetduyet03, String usrnameXetduyet04,
			String usrnameXetduyet05, String usrnameXetduyet06,
			String brcdDeptRcv, String brcdDeptNameRcv, String flgloan,
			String disbursementno, String disbursementamt, String eibChucvu,
			String recevcardMk, String recevcardAdd, String officeaddr2,
			String officeaddr3, String other1, String other2, String other3,
			String employid, String addusrid, String apprusrid,
			String brapprusrid, String checkusrid, String odaccount,
			String payacct, String geAcctcd, String geFileno,
			String geContractno, String geAutopaypecent, String geAutopayamt,
			String geAutocash, String geAutodebit, String geAutopayacct,
			String shrtname, String conName, String geLimit, String geSendaddr,
			String geCtrynmloc, String geCardtp1, String geCardtp2,
			String geCardtp3, String geCardtp4, String freetp, String crdlmtcd,
			String unststm, String recevcardReason) {
		super();
		this.flagStatus = flagStatus;
		this.brcd = brcd;
		this.seqno = seqno;
		this.psncrd = psncrd;
		this.cprcrd = cprcrd;
		this.ppscrlmt = ppscrlmt;
		this.fullnm = fullnm;
		this.prnnm = prnnm;
		this.birthdt = birthdt;
		this.sex = sex;
		this.idcard = idcard;
		this.pmnaddr = pmnaddr;
		this.yrsofrsd = yrsofrsd;
		this.phoneno = phoneno;
		this.lvlofedu = lvlofedu;
		this.nationality = nationality;
		this.mrtsts = mrtsts;
		this.mommdnm = mommdnm;
		this.housetp = housetp;
		this.officenm = officenm;
		this.officeaddr = officeaddr;
		this.officetelno = officetelno;
		this.officepos = officepos;
		this.wrksnr = wrksnr;
		this.labourtp = labourtp;
		this.annual = annual;
		this.rcvloc = rcvloc;
		this.settltp = settltp;
		this.dracctno = dracctno;
		this.sttlwith = sttlwith;
		this.accttp = accttp;
		this.drratio = drratio;
		this.receiptdt = receiptdt;
		this.crdtp = crdtp;
		this.colltp = colltp;
		this.crlmt = crlmt;
		this.expdt = expdt;
		this.checked = checked;
		this.checkeddt = checkeddt;
		this.approved = approved;
		this.approveddt = approveddt;
		this.status = status;
		this.photofilename = photofilename;
		this.fullnm1 = fullnm1;
		this.idcard1 = idcard1;
		this.ofcnm = ofcnm;
		this.ofcaddr = ofcaddr;
		this.ofcphoneno = ofcphoneno;
		this.hmaddr = hmaddr;
		this.hmphoneno = hmphoneno;
		this.appdate = appdate;
		this.crdtype = crdtype;
		this.crdvalidaty = crdvalidaty;
		this.mobifon = mobifon;
		this.email = email;
		this.other = other;
		this.crdtypebank = crdtypebank;
		this.psncrdbank = psncrdbank;
		this.cprcrdbank = cprcrdbank;
		this.note = note;
		this.fileno = fileno;
		this.contractno = contractno;
		this.accountcd = accountcd;
		this.cardno = cardno;
		this.brname = brname;
		this.mntcrdval = mntcrdval;
		this.issutp = issutp;
		this.noteofho = noteofho;
		this.anlfeereason = anlfeereason;
		this.compid = compid;
		this.custid = custid;
		this.frdt = frdt;
		this.todt = todt;
		this.vsvldt = vsvldt;
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
		this.cshlmtofcust = cshlmtofcust;
		this.cshlmtofbnk = cshlmtofbnk;
		this.custtpcd = custtpcd;
		this.custdtltpcd = custdtltpcd;
		this.colldtltp = colldtltp;
		this.cardnbr = cardnbr;
		this.compcustseq = compcustseq;
		this.statedt = statedt;
		this.comptp = comptp;
		this.compothr = compothr;
		this.jobtp = jobtp;
		this.jobothr = jobothr;
		this.edcntp = edcntp;
		this.edcnothr = edcnothr;
		this.addrtp = addrtp;
		this.addrothr = addrothr;
		this.depdno = depdno;
		this.chkapp = chkapp;
		this.flg3dsce = flg3dsce;
		this.email3dsce = email3dsce;
		this.tel13dsce = tel13dsce;
		this.tel23dsce = tel23dsce;
		this.tel33dsce = tel33dsce;
		this.chkecommerce = chkecommerce;
		this.purpose = purpose;
		this.deptcd = deptcd;
		this.chkemail = chkemail;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.address4 = address4;
		this.officeaddr1 = officeaddr1;
		this.rcvcardaddr = rcvcardaddr;
		this.prdtcd = prdtcd;
		this.vipflg = vipflg;
		this.cmpnynm = cmpnynm;
		this.usrnameXetduyet = usrnameXetduyet;
		this.usrnameXetduyet01 = usrnameXetduyet01;
		this.usrnameXetduyet02 = usrnameXetduyet02;
		this.usrnameXetduyet03 = usrnameXetduyet03;
		this.usrnameXetduyet04 = usrnameXetduyet04;
		this.usrnameXetduyet05 = usrnameXetduyet05;
		this.usrnameXetduyet06 = usrnameXetduyet06;
		this.brcdDeptRcv = brcdDeptRcv;
		this.brcdDeptNameRcv = brcdDeptNameRcv;
		this.flgloan = flgloan;
		this.disbursementno = disbursementno;
		this.disbursementamt = disbursementamt;
		this.eibChucvu = eibChucvu;
		this.recevcardMk = recevcardMk;
		this.recevcardAdd = recevcardAdd;
		this.officeaddr2 = officeaddr2;
		this.officeaddr3 = officeaddr3;
		this.other1 = other1;
		this.other2 = other2;
		this.other3 = other3;
		this.employid = employid;
		this.addusrid = addusrid;
		this.apprusrid = apprusrid;
		this.brapprusrid = brapprusrid;
		this.checkusrid = checkusrid;
		this.odaccount = odaccount;
		this.payacct = payacct;
		this.geAcctcd = geAcctcd;
		this.geFileno = geFileno;
		this.geContractno = geContractno;
		this.geAutopaypecent = geAutopaypecent;
		this.geAutopayamt = geAutopayamt;
		this.geAutocash = geAutocash;
		this.geAutodebit = geAutodebit;
		this.geAutopayacct = geAutopayacct;
		this.shrtname = shrtname;
		this.conName = conName;
		this.geLimit = geLimit;
		this.geSendaddr = geSendaddr;
		this.geCtrynmloc = geCtrynmloc;
		this.geCardtp1 = geCardtp1;
		this.geCardtp2 = geCardtp2;
		this.geCardtp3 = geCardtp3;
		this.geCardtp4 = geCardtp4;
		this.freetp = freetp;
		this.crdlmtcd = crdlmtcd;
		this.unststm = unststm;
		this.recevcardReason = recevcardReason;
	}

	public String getFlagStatus() {
		return flagStatus;
	}

	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
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

	public String getPsncrd() {
		return psncrd;
	}

	public void setPsncrd(String psncrd) {
		this.psncrd = psncrd;
	}

	public String getCprcrd() {
		return cprcrd;
	}

	public void setCprcrd(String cprcrd) {
		this.cprcrd = cprcrd;
	}

	public String getPpscrlmt() {
		return ppscrlmt;
	}

	public void setPpscrlmt(String ppscrlmt) {
		this.ppscrlmt = ppscrlmt;
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

	public String getYrsofrsd() {
		return yrsofrsd;
	}

	public void setYrsofrsd(String yrsofrsd) {
		this.yrsofrsd = yrsofrsd;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getLvlofedu() {
		return lvlofedu;
	}

	public void setLvlofedu(String lvlofedu) {
		this.lvlofedu = lvlofedu;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getMrtsts() {
		return mrtsts;
	}

	public void setMrtsts(String mrtsts) {
		this.mrtsts = mrtsts;
	}

	public String getMommdnm() {
		return mommdnm;
	}

	public void setMommdnm(String mommdnm) {
		this.mommdnm = mommdnm;
	}

	public String getHousetp() {
		return housetp;
	}

	public void setHousetp(String housetp) {
		this.housetp = housetp;
	}

	public String getOfficenm() {
		return officenm;
	}

	public void setOfficenm(String officenm) {
		this.officenm = officenm;
	}

	public String getOfficeaddr() {
		return officeaddr;
	}

	public void setOfficeaddr(String officeaddr) {
		this.officeaddr = officeaddr;
	}

	public String getOfficetelno() {
		return officetelno;
	}

	public void setOfficetelno(String officetelno) {
		this.officetelno = officetelno;
	}

	public String getOfficepos() {
		return officepos;
	}

	public void setOfficepos(String officepos) {
		this.officepos = officepos;
	}

	public String getWrksnr() {
		return wrksnr;
	}

	public void setWrksnr(String wrksnr) {
		this.wrksnr = wrksnr;
	}

	public String getLabourtp() {
		return labourtp;
	}

	public void setLabourtp(String labourtp) {
		this.labourtp = labourtp;
	}

	public String getAnnual() {
		return annual;
	}

	public void setAnnual(String annual) {
		this.annual = annual;
	}

	public String getRcvloc() {
		return rcvloc;
	}

	public void setRcvloc(String rcvloc) {
		this.rcvloc = rcvloc;
	}

	public String getSettltp() {
		return settltp;
	}

	public void setSettltp(String settltp) {
		this.settltp = settltp;
	}

	public String getDracctno() {
		return dracctno;
	}

	public void setDracctno(String dracctno) {
		this.dracctno = dracctno;
	}

	public String getSttlwith() {
		return sttlwith;
	}

	public void setSttlwith(String sttlwith) {
		this.sttlwith = sttlwith;
	}

	public String getAccttp() {
		return accttp;
	}

	public void setAccttp(String accttp) {
		this.accttp = accttp;
	}

	public String getDrratio() {
		return drratio;
	}

	public void setDrratio(String drratio) {
		this.drratio = drratio;
	}

	public String getReceiptdt() {
		return receiptdt;
	}

	public void setReceiptdt(String receiptdt) {
		this.receiptdt = receiptdt;
	}

	public String getCrdtp() {
		return crdtp;
	}

	public void setCrdtp(String crdtp) {
		this.crdtp = crdtp;
	}

	public String getColltp() {
		return colltp;
	}

	public void setColltp(String colltp) {
		this.colltp = colltp;
	}

	public String getCrlmt() {
		return crlmt;
	}

	public void setCrlmt(String crlmt) {
		this.crlmt = crlmt;
	}

	public String getExpdt() {
		return expdt;
	}

	public void setExpdt(String expdt) {
		this.expdt = expdt;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPhotofilename() {
		return photofilename;
	}

	public void setPhotofilename(String photofilename) {
		this.photofilename = photofilename;
	}

	public String getFullnm1() {
		return fullnm1;
	}

	public void setFullnm1(String fullnm1) {
		this.fullnm1 = fullnm1;
	}

	public String getIdcard1() {
		return idcard1;
	}

	public void setIdcard1(String idcard1) {
		this.idcard1 = idcard1;
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

	public String getHmaddr() {
		return hmaddr;
	}

	public void setHmaddr(String hmaddr) {
		this.hmaddr = hmaddr;
	}

	public String getHmphoneno() {
		return hmphoneno;
	}

	public void setHmphoneno(String hmphoneno) {
		this.hmphoneno = hmphoneno;
	}

	public String getAppdate() {
		return appdate;
	}

	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}

	public String getCrdtype() {
		return crdtype;
	}

	public void setCrdtype(String crdtype) {
		this.crdtype = crdtype;
	}

	public String getCrdvalidaty() {
		return crdvalidaty;
	}

	public void setCrdvalidaty(String crdvalidaty) {
		this.crdvalidaty = crdvalidaty;
	}

	public String getMobifon() {
		return mobifon;
	}

	public void setMobifon(String mobifon) {
		this.mobifon = mobifon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getCrdtypebank() {
		return crdtypebank;
	}

	public void setCrdtypebank(String crdtypebank) {
		this.crdtypebank = crdtypebank;
	}

	public String getPsncrdbank() {
		return psncrdbank;
	}

	public void setPsncrdbank(String psncrdbank) {
		this.psncrdbank = psncrdbank;
	}

	public String getCprcrdbank() {
		return cprcrdbank;
	}

	public void setCprcrdbank(String cprcrdbank) {
		this.cprcrdbank = cprcrdbank;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFileno() {
		return fileno;
	}

	public void setFileno(String fileno) {
		this.fileno = fileno;
	}

	public String getContractno() {
		return contractno;
	}

	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	public String getAccountcd() {
		return accountcd;
	}

	public void setAccountcd(String accountcd) {
		this.accountcd = accountcd;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getBrname() {
		return brname;
	}

	public void setBrname(String brname) {
		this.brname = brname;
	}

	public String getMntcrdval() {
		return mntcrdval;
	}

	public void setMntcrdval(String mntcrdval) {
		this.mntcrdval = mntcrdval;
	}

	public String getIssutp() {
		return issutp;
	}

	public void setIssutp(String issutp) {
		this.issutp = issutp;
	}

	public String getNoteofho() {
		return noteofho;
	}

	public void setNoteofho(String noteofho) {
		this.noteofho = noteofho;
	}

	public String getAnlfeereason() {
		return anlfeereason;
	}

	public void setAnlfeereason(String anlfeereason) {
		this.anlfeereason = anlfeereason;
	}

	public String getCompid() {
		return compid;
	}

	public void setCompid(String compid) {
		this.compid = compid;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getFrdt() {
		return frdt;
	}

	public void setFrdt(String frdt) {
		this.frdt = frdt;
	}

	public String getTodt() {
		return todt;
	}

	public void setTodt(String todt) {
		this.todt = todt;
	}

	public String getVsvldt() {
		return vsvldt;
	}

	public void setVsvldt(String vsvldt) {
		this.vsvldt = vsvldt;
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

	public String getCshlmtofcust() {
		return cshlmtofcust;
	}

	public void setCshlmtofcust(String cshlmtofcust) {
		this.cshlmtofcust = cshlmtofcust;
	}

	public String getCshlmtofbnk() {
		return cshlmtofbnk;
	}

	public void setCshlmtofbnk(String cshlmtofbnk) {
		this.cshlmtofbnk = cshlmtofbnk;
	}

	public String getCusttpcd() {
		return custtpcd;
	}

	public void setCusttpcd(String custtpcd) {
		this.custtpcd = custtpcd;
	}

	public String getCustdtltpcd() {
		return custdtltpcd;
	}

	public void setCustdtltpcd(String custdtltpcd) {
		this.custdtltpcd = custdtltpcd;
	}

	public String getColldtltp() {
		return colldtltp;
	}

	public void setColldtltp(String colldtltp) {
		this.colldtltp = colldtltp;
	}

	public String getCardnbr() {
		return cardnbr;
	}

	public void setCardnbr(String cardnbr) {
		this.cardnbr = cardnbr;
	}

	public String getCompcustseq() {
		return compcustseq;
	}

	public void setCompcustseq(String compcustseq) {
		this.compcustseq = compcustseq;
	}

	public String getStatedt() {
		return statedt;
	}

	public void setStatedt(String statedt) {
		this.statedt = statedt;
	}

	public String getComptp() {
		return comptp;
	}

	public void setComptp(String comptp) {
		this.comptp = comptp;
	}

	public String getCompothr() {
		return compothr;
	}

	public void setCompothr(String compothr) {
		this.compothr = compothr;
	}

	public String getJobtp() {
		return jobtp;
	}

	public void setJobtp(String jobtp) {
		this.jobtp = jobtp;
	}

	public String getJobothr() {
		return jobothr;
	}

	public void setJobothr(String jobothr) {
		this.jobothr = jobothr;
	}

	public String getEdcntp() {
		return edcntp;
	}

	public void setEdcntp(String edcntp) {
		this.edcntp = edcntp;
	}

	public String getEdcnothr() {
		return edcnothr;
	}

	public void setEdcnothr(String edcnothr) {
		this.edcnothr = edcnothr;
	}

	public String getAddrtp() {
		return addrtp;
	}

	public void setAddrtp(String addrtp) {
		this.addrtp = addrtp;
	}

	public String getAddrothr() {
		return addrothr;
	}

	public void setAddrothr(String addrothr) {
		this.addrothr = addrothr;
	}

	public String getDepdno() {
		return depdno;
	}

	public void setDepdno(String depdno) {
		this.depdno = depdno;
	}

	public String getChkapp() {
		return chkapp;
	}

	public void setChkapp(String chkapp) {
		this.chkapp = chkapp;
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

	public String getTel23dsce() {
		return tel23dsce;
	}

	public void setTel23dsce(String tel23dsce) {
		this.tel23dsce = tel23dsce;
	}

	public String getTel33dsce() {
		return tel33dsce;
	}

	public void setTel33dsce(String tel33dsce) {
		this.tel33dsce = tel33dsce;
	}

	public String getChkecommerce() {
		return chkecommerce;
	}

	public void setChkecommerce(String chkecommerce) {
		this.chkecommerce = chkecommerce;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getDeptcd() {
		return deptcd;
	}

	public void setDeptcd(String deptcd) {
		this.deptcd = deptcd;
	}

	public String getChkemail() {
		return chkemail;
	}

	public void setChkemail(String chkemail) {
		this.chkemail = chkemail;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getOfficeaddr1() {
		return officeaddr1;
	}

	public void setOfficeaddr1(String officeaddr1) {
		this.officeaddr1 = officeaddr1;
	}

	public String getRcvcardaddr() {
		return rcvcardaddr;
	}

	public void setRcvcardaddr(String rcvcardaddr) {
		this.rcvcardaddr = rcvcardaddr;
	}

	public String getPrdtcd() {
		return prdtcd;
	}

	public void setPrdtcd(String prdtcd) {
		this.prdtcd = prdtcd;
	}

	public String getVipflg() {
		return vipflg;
	}

	public void setVipflg(String vipflg) {
		this.vipflg = vipflg;
	}

	public String getCmpnynm() {
		return cmpnynm;
	}

	public void setCmpnynm(String cmpnynm) {
		this.cmpnynm = cmpnynm;
	}

	public String getUsrnameXetduyet() {
		return usrnameXetduyet;
	}

	public void setUsrnameXetduyet(String usrnameXetduyet) {
		this.usrnameXetduyet = usrnameXetduyet;
	}

	public String getUsrnameXetduyet01() {
		return usrnameXetduyet01;
	}

	public void setUsrnameXetduyet01(String usrnameXetduyet01) {
		this.usrnameXetduyet01 = usrnameXetduyet01;
	}

	public String getUsrnameXetduyet02() {
		return usrnameXetduyet02;
	}

	public void setUsrnameXetduyet02(String usrnameXetduyet02) {
		this.usrnameXetduyet02 = usrnameXetduyet02;
	}

	public String getUsrnameXetduyet03() {
		return usrnameXetduyet03;
	}

	public void setUsrnameXetduyet03(String usrnameXetduyet03) {
		this.usrnameXetduyet03 = usrnameXetduyet03;
	}

	public String getUsrnameXetduyet04() {
		return usrnameXetduyet04;
	}

	public void setUsrnameXetduyet04(String usrnameXetduyet04) {
		this.usrnameXetduyet04 = usrnameXetduyet04;
	}

	public String getUsrnameXetduyet05() {
		return usrnameXetduyet05;
	}

	public void setUsrnameXetduyet05(String usrnameXetduyet05) {
		this.usrnameXetduyet05 = usrnameXetduyet05;
	}

	public String getUsrnameXetduyet06() {
		return usrnameXetduyet06;
	}

	public void setUsrnameXetduyet06(String usrnameXetduyet06) {
		this.usrnameXetduyet06 = usrnameXetduyet06;
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

	public String getFlgloan() {
		return flgloan;
	}

	public void setFlgloan(String flgloan) {
		this.flgloan = flgloan;
	}

	public String getDisbursementno() {
		return disbursementno;
	}

	public void setDisbursementno(String disbursementno) {
		this.disbursementno = disbursementno;
	}

	public String getDisbursementamt() {
		return disbursementamt;
	}

	public void setDisbursementamt(String disbursementamt) {
		this.disbursementamt = disbursementamt;
	}

	public String getEibChucvu() {
		return eibChucvu;
	}

	public void setEibChucvu(String eibChucvu) {
		this.eibChucvu = eibChucvu;
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

	public String getOfficeaddr2() {
		return officeaddr2;
	}

	public void setOfficeaddr2(String officeaddr2) {
		this.officeaddr2 = officeaddr2;
	}

	public String getOfficeaddr3() {
		return officeaddr3;
	}

	public void setOfficeaddr3(String officeaddr3) {
		this.officeaddr3 = officeaddr3;
	}

	public String getOther1() {
		return other1;
	}

	public void setOther1(String other1) {
		this.other1 = other1;
	}

	public String getOther2() {
		return other2;
	}

	public void setOther2(String other2) {
		this.other2 = other2;
	}

	public String getOther3() {
		return other3;
	}

	public void setOther3(String other3) {
		this.other3 = other3;
	}

	public String getEmployid() {
		return employid;
	}

	public void setEmployid(String employid) {
		this.employid = employid;
	}

	public String getAddusrid() {
		return addusrid;
	}

	public void setAddusrid(String addusrid) {
		this.addusrid = addusrid;
	}

	public String getApprusrid() {
		return apprusrid;
	}

	public void setApprusrid(String apprusrid) {
		this.apprusrid = apprusrid;
	}

	public String getBrapprusrid() {
		return brapprusrid;
	}

	public void setBrapprusrid(String brapprusrid) {
		this.brapprusrid = brapprusrid;
	}

	public String getCheckusrid() {
		return checkusrid;
	}

	public void setCheckusrid(String checkusrid) {
		this.checkusrid = checkusrid;
	}

	public String getOdaccount() {
		return odaccount;
	}

	public void setOdaccount(String odaccount) {
		this.odaccount = odaccount;
	}

	public String getPayacct() {
		return payacct;
	}

	public void setPayacct(String payacct) {
		this.payacct = payacct;
	}

	public String getGeAcctcd() {
		return geAcctcd;
	}

	public void setGeAcctcd(String geAcctcd) {
		this.geAcctcd = geAcctcd;
	}

	public String getGeFileno() {
		return geFileno;
	}

	public void setGeFileno(String geFileno) {
		this.geFileno = geFileno;
	}

	public String getGeContractno() {
		return geContractno;
	}

	public void setGeContractno(String geContractno) {
		this.geContractno = geContractno;
	}

	public String getGeAutopaypecent() {
		return geAutopaypecent;
	}

	public void setGeAutopaypecent(String geAutopaypecent) {
		this.geAutopaypecent = geAutopaypecent;
	}

	public String getGeAutopayamt() {
		return geAutopayamt;
	}

	public void setGeAutopayamt(String geAutopayamt) {
		this.geAutopayamt = geAutopayamt;
	}

	public String getGeAutocash() {
		return geAutocash;
	}

	public void setGeAutocash(String geAutocash) {
		this.geAutocash = geAutocash;
	}

	public String getGeAutodebit() {
		return geAutodebit;
	}

	public void setGeAutodebit(String geAutodebit) {
		this.geAutodebit = geAutodebit;
	}

	public String getGeAutopayacct() {
		return geAutopayacct;
	}

	public void setGeAutopayacct(String geAutopayacct) {
		this.geAutopayacct = geAutopayacct;
	}

	public String getShrtname() {
		return shrtname;
	}

	public void setShrtname(String shrtname) {
		this.shrtname = shrtname;
	}

	public String getConName() {
		return conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}

	public String getGeLimit() {
		return geLimit;
	}

	public void setGeLimit(String geLimit) {
		this.geLimit = geLimit;
	}

	public String getGeSendaddr() {
		return geSendaddr;
	}

	public void setGeSendaddr(String geSendaddr) {
		this.geSendaddr = geSendaddr;
	}

	public String getGeCtrynmloc() {
		return geCtrynmloc;
	}

	public void setGeCtrynmloc(String geCtrynmloc) {
		this.geCtrynmloc = geCtrynmloc;
	}

	public String getGeCardtp1() {
		return geCardtp1;
	}

	public void setGeCardtp1(String geCardtp1) {
		this.geCardtp1 = geCardtp1;
	}

	public String getGeCardtp2() {
		return geCardtp2;
	}

	public void setGeCardtp2(String geCardtp2) {
		this.geCardtp2 = geCardtp2;
	}

	public String getGeCardtp3() {
		return geCardtp3;
	}

	public void setGeCardtp3(String geCardtp3) {
		this.geCardtp3 = geCardtp3;
	}

	public String getGeCardtp4() {
		return geCardtp4;
	}

	public void setGeCardtp4(String geCardtp4) {
		this.geCardtp4 = geCardtp4;
	}

	public String getFreetp() {
		return freetp;
	}

	public void setFreetp(String freetp) {
		this.freetp = freetp;
	}

	public String getCrdlmtcd() {
		return crdlmtcd;
	}

	public void setCrdlmtcd(String crdlmtcd) {
		this.crdlmtcd = crdlmtcd;
	}

	public String getUnststm() {
		return unststm;
	}

	public void setUnststm(String unststm) {
		this.unststm = unststm;
	}

	public String getRecevcardReason() {
		return recevcardReason;
	}

	public void setRecevcardReason(String recevcardReason) {
		this.recevcardReason = recevcardReason;
	}

}
