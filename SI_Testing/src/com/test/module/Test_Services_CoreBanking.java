package com.test.module;

import com.consts.core.Service;
import com.pre.impl.core.Pre_AddLimitNodeCore;
import com.pre.impl.core.Pre_AddLimitNodeTeller;
import com.pre.impl.core.Pre_CAAcctAdd;
import com.pre.impl.core.Pre_CheckAML;
import com.pre.impl.core.Pre_CheckDueDt10;
import com.pre.impl.core.Pre_CollateralCreateCustom;
import com.pre.impl.core.Pre_CreateDepositColtrl;
import com.pre.impl.core.Pre_CreditCardPublish;
import com.pre.impl.core.Pre_CreditCardPublishSub;
import com.pre.impl.core.Pre_DfltLoanOpenDetail;
import com.pre.impl.core.Pre_GR_GetLosCif;
import com.pre.impl.core.Pre_GR_Test;
import com.pre.impl.core.Pre_GetCurrentAccount;
import com.pre.impl.core.Pre_GetExchangeRate;
import com.pre.impl.core.Pre_GetLOSAddBusRul;
import com.pre.impl.core.Pre_GetLOSCIF;
import com.pre.impl.core.Pre_GetLOSDS;
import com.pre.impl.core.Pre_GetLOSTD;
import com.pre.impl.core.Pre_GetLOSTOI;
import com.pre.impl.core.Pre_GetSavingAccount;
import com.pre.impl.core.Pre_LinkCollateralWithLimit;
import com.pre.impl.core.Pre_ODAcctAdd;
import com.pre.impl.core.Pre_OpenLoanAccount;
import com.pre.impl.core.Pre_SearchCurrentAccount;
import com.pre.impl.core.Pre_SendMail;
import com.pre.impl.core.Pre_SendSMS;
import com.pre.impl.core.Pre_UnlinkCollateralDetails;
//import com.si.exception.SIException;
import com.si.pre.SIPre;

public class Test_Services_CoreBanking {

	public static void main(String[] args) throws Exception {

		String data = null;
		String siVersion = "RLOSv1.0";
		String processInfo = null;
		String processDataSource = "SAALEM_51";
		String logDataSource = "SAALEM_51";
//		
//		JsonObject test = new JsonObject();
//		test = new Gson().fromJson("{\"data\":{\"cif\":\"106058979\",\"tenKh\":\"LE PHAN VI AI\",\"gioiTinh\":\"GT_NU\",\"ngaySinh\":\"13/12/1988\",\"noiSinh\":\"\",\"quocTich\":\"VNM\",\"danToc\":\"DT01\",\"ttHonNhan\":\"LYTHAN\",\"khDen\":null,\"cuTru\":\"1\",\"kCapTinDung\":\"DTCTD01\",\"hanChe\":\"DTHC01\",\"vip\":\"2\",\"loaiKH\":\"CN\",\"dsaid\":\"TEL200009812\",\"acctmngrid\":\"MAI.NT03\",\"maNVEIB\":\"100401934\",\"salutation\":\"MS.\",\"document\":[{\"docCode\":\"THECCCD\",\"docId\":\"052188017684\",\"docNgayCap\":\"20/12/2021\",\"docQuocGia\":\"VNM\",\"docNoiCap\":\"NCID343\",\"docPrefer\":\"Y\"}],\"phone\":[{\"phoneType\":\"CELLPH\",\"phonePrefer\":\"Y\",\"phoneNo\":\"0933094247\"},{\"phoneType\":\"HOMEPH1\",\"phonePrefer\":\"N\",\"phoneNo\":\"038397756\"}],\"address\":[{\"quocGiaDiaChi\":\"VNM\",\"thanhPhoDiaChi\":\"79\",\"diaChi\":\"93/16 HO THI KI P1 Q10 HCM..\",\"hieuLucDiaChi\":\"05/07/2022\",\"loaiDiaChi\":\"DCLIENHE\",\"addrPreferType\":\"N\"},{\"quocGiaDiaChi\":\"VNM\",\"thanhPhoDiaChi\":\"79\",\"diaChi\":\"93/16 HO THI KI P1 Q10 HCM..\",\"hieuLucDiaChi\":\"05/07/2022\",\"loaiDiaChi\":\"HKTHUONGTRU\",\"addrPreferType\":\"Y\"}],\"khlq\":null,\"email\":[{\"email\":\"ai.lpv@eximbank.com.vn\"}],\"tttc\":{\"tnLuong\":\"0\",\"tnChoThue\":\"0\",\"tnKinhDoanh\":\"0\",\"tnKhac\":\"0\",\"chiPhiSinhHoat\":\"0\",\"chiTraTTThe\":\"\",\"chiPhiKhac\":\"\"},\"errcode\":\"0\",\"errDesc\":\"Th�nh c�ng\"},\"errorInfo\":{\"errorCode\":\"00\",\"message\":\"Success [LOG_ID:32768]\"}}", JsonObject.class);
//		String result = test.toString();
//		System.err.println(new Gson().fromJson("{\"data\":{\"cif\":\"106058979\",\"tenKh\":\"LE PHAN VI AI\",\"gioiTinh\":\"GT_NU\",\"ngaySinh\":\"13/12/1988\",\"noiSinh\":\"\",\"quocTich\":\"VNM\",\"danToc\":\"DT01\",\"ttHonNhan\":\"LYTHAN\",\"khDen\":null,\"cuTru\":\"1\",\"kCapTinDung\":\"DTCTD01\",\"hanChe\":\"DTHC01\",\"vip\":\"2\",\"loaiKH\":\"CN\",\"dsaid\":\"TEL200009812\",\"acctmngrid\":\"MAI.NT03\",\"maNVEIB\":\"100401934\",\"salutation\":\"MS.\",\"document\":[{\"docCode\":\"THECCCD\",\"docId\":\"052188017684\",\"docNgayCap\":\"20/12/2021\",\"docQuocGia\":\"VNM\",\"docNoiCap\":\"NCID343\",\"docPrefer\":\"Y\"}],\"phone\":[{\"phoneType\":\"CELLPH\",\"phonePrefer\":\"Y\",\"phoneNo\":\"0933094247\"},{\"phoneType\":\"HOMEPH1\",\"phonePrefer\":\"N\",\"phoneNo\":\"038397756\"}],\"address\":[{\"quocGiaDiaChi\":\"VNM\",\"thanhPhoDiaChi\":\"79\",\"diaChi\":\"93/16 HO THI KI P1 Q10 HCM..\",\"hieuLucDiaChi\":\"05/07/2022\",\"loaiDiaChi\":\"DCLIENHE\",\"addrPreferType\":\"N\"},{\"quocGiaDiaChi\":\"VNM\",\"thanhPhoDiaChi\":\"79\",\"diaChi\":\"93/16 HO THI KI P1 Q10 HCM..\",\"hieuLucDiaChi\":\"05/07/2022\",\"loaiDiaChi\":\"HKTHUONGTRU\",\"addrPreferType\":\"Y\"}],\"khlq\":null,\"email\":[{\"email\":\"ai.lpv@eximbank.com.vn\"}],\"tttc\":{\"tnLuong\":\"0\",\"tnChoThue\":\"0\",\"tnKinhDoanh\":\"0\",\"tnKhac\":\"0\",\"chiPhiSinhHoat\":\"0\",\"chiTraTTThe\":\"\",\"chiPhiKhac\":\"\"},\"errcode\":\"0\",\"errDesc\":\"Th�nh c�ng\"},\"errorInfo\":{\"errorCode\":\"00\",\"message\":\"Success [LOG_ID:32768]\"}}", JsonObject.class).toString());
		SIPre pre;
		String service = Service.GetLOSTD;
//		String service="";

		switch (service) {

		case Service.GR_GetLosCif:
			data = "{\"userInfo\":{\"userCode\":\"U0000142\"},\"cif\":\"106058979\",\"id\":\"\"}";
			pre = new Pre_GR_GetLosCif(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.GR_Test:
			data = "{\"SolId\":\"1504\"}";
			pre = new Pre_GR_Test(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.ODAcctAdd:
			data = "{\"CustId\":120934108,\"SchmCode\":\"ODPCC\",\"SchmType\":\"ODA\",\"AcctCurr\":\"VND\",\"BankId\":1,\"BranchId\":2227,\"GenLedgerSubHeadCode\":27510,\"CurCode\":\"VND\",\"AcctName\":\"VO GIAO UYEN\",\"AcctStmtMode\":\"N\",\"RelPartyType\":\"M\",\"Name\":\"VO GIAO UYEN\",\"TitlePrefix\":\"MS.\",\"amountValue\":5000000,\"currencyCode\":\"VND\",\"LimitLevelIntFlg\":\"N\",\"SanctionDt\":\"2022-10-19T00:00:00.000\",\"ExpDt\":\"2024-10-31T00:00:00.000\",\"DocumentDt\":\"2022-10-19T00:00:00.000\",\"ReviewDt\":\"2023-10-19T00:00:00.000\",\"SanctionLevel\":6,\"DrawingPowerInd\":\"P\",\"LimitIdPrefix\":\"LAV220090875\",\"LimitIdSuffix\":2227,\"AcctRecallFlg\":\"N\",\"value\":100,\"IntTblCode\":\"DUMMY\",\"IntPeriodInMonths\":25,\"IntPeriodInDays\":12,\"PeggedFlg\":\"N\",\"LIM_CUST_ID\":120934108,\"LIM_SCHM_CODE\":\"ODPCC\",\"LIM_CRNCY_CODE\":\"VND\",\"DSA_ID\":\"MIGRDSA\",\"LANG_CODE\":\"VN\",\"REGION\":\"HCMC\",\"PRODUCT\":\"CARD\",\"MARKET\":0,\"fundPurpCode\":\"9W\",\"subPurpCodePurp2\":\"9W100\",\"subPurpCodePurp3\":\"9W10099\",\"otherType1Purp4\":9906,\"otherType1Purp5\":9906000,\"otherType2\":\"9KHAC\",\"industryGroup\":0,\"insStDt\":\"2022-10-19T00:00:00.000\",\"insEndDt\":\"2024-11-30T00:00:00.000\",\"crdtrskrtdate\":\"2022-11-29T00:00:00.000\",\"crdtrskrt\":\"XHAA\",\"c39EnableHid\":\"Y\"}";
			pre = new Pre_ODAcctAdd(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.CreditCardPublish_Sub:
			data = "{\"flag_status\":\"N\",\"brcd\":1002,\"seqno\":177715,\"receiptdt\":20221129,\"fullnm\":\"NGUYEN THI HUONG\",\"prnnm\":\"NGUYEN THI HUONG\",\"birthdt\":19731012,\"sex\":2,\"idcard\":40173000089,\"pmnaddr\":\"45 NGUYEN DUONG\",\"hmphoneno\":903555555,\"hmnationality\":\"VN\",\"rationwtapp\":\"MQH05\",\"mobifon\":903555555,\"photofilename\":\"LOS\",\"note\":\"MIEN PHI TN VA SMS\",\"status\":7,\"checked\":\"HUONG.TL\",\"checkeddt\":20221129,\"approved\":\"CU.NH\",\"approveddt\":20221129,\"issutp\":2,\"flgsms\":1,\"flglck\":1,\"tellck\":903555555,\"flgquery\":1,\"telquery\":903555555,\"flgnote\":1,\"telnote\":903555555,\"flgrcv\":1,\"flg3dsce\":1,\"email3dsce\":\"HUONG.NT05@EXIMBANK.COM.VN\",\"tel13dsce\":903555555,\"sendmailflg\":\"Y\",\"brcd_dept_rcv\":1002,\"recevcard_mk\":3,\"recevcard_add\":1002,\"email\":\"HUONG.NT05@EXIMBANK.COM.VN\"}";
			pre = new Pre_CreditCardPublishSub(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.CheckDueDt10:
			data = "{\"FLGSTS\":\"C\",\"CUSTSEQ\":107169517}";
			pre = new Pre_CheckDueDt10(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.GetSavingAccount:
			data = "{\"Cif\":104513224}";
			pre = new Pre_GetSavingAccount(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.GetCurrentAccount:
			data = "{\"Cif\":104513224}";
			pre = new Pre_GetCurrentAccount(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.CAAcctAdd:
			data = "{\"CAAcctAddRq\":{\"CustId\":{\"CustId\":121250146},\"CAAcctId\":{\"AcctType\":{\"SchmCode\":10501},\"AcctCurr\":\"VND\",\"BankInfo\":{\"BranchId\":1002}},\"CAAcctGenInfo\":{\"GenLedgerSubHead\":{\"GenLedgerSubHeadCode\":42732},\"AcctName\":\"PHAN LE KIM PHUONG\",\"AcctStmtMode\":\"S\",\"AcctStmtFreq\":{\"Type\":\"Y\",\"StartDt\":31,\"HolStat\":\"S\"},\"DespatchMode\":\"N\"}},\"CAAcctAdd_CustomData\":{\"DSAID\":\"TEL120101124\",\"LOCALCALENDAR\":\"N\",\"FREETEXT8\":\"N\",\"FREECODE8\":\"N\",\"ACCTMGRID\":\"LANH.NLD\",\"MINBALIND\":\"S\",\"MINBAL\":0,\"PrefLangCode\":\"VN\",\"MINBALCRNCY\":\"VND\",\"LienFeeAmount\":0}}";
			pre = new Pre_CAAcctAdd(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.SearchCurrentAccount:
			data = "{\"accountNo\":200014949155832}";
			pre = new Pre_SearchCurrentAccount(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.GetLOSAddBusRul:
			data = "{\"Cif\":107169517,\"CurSol\":1003}";
			pre = new Pre_GetLOSAddBusRul(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.GetLOSCIF:
			data = "{\"cif\":102589918}";
			pre = new Pre_GetLOSCIF(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.CheckAML:
			data = "{\"iData\":{\"LOS_CUSTOMER\":{\"ARG0\":\"FT|null|EKYC20220901113210|FTO|VND|null|NGUYEN DUC TRUONG|052078016110|null|51/17A14 D.22, Kp7, Linh Dong, Tp Thu Duc, Ho Chi Minh|HCM|Viet Nam|EXIMBANK|null|2108|null|null|null|null|null\"}}}";
			pre = new Pre_CheckAML(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.GetLOSTD:
			data = "{\"Cif\":\"121079908\",\"DocId\":\"\",\"sol\":\"2235\"}";
			pre = new Pre_GetLOSTD(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.GetExchangeRate:
			data = "{\"ExchangeDate\":20230202,\"CCYCD\":\"\",\"BRID\":1000}";
			pre = new Pre_GetExchangeRate(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.GetLOSTOI:
			data = "{\"cif\":\"102589918\"}";
			pre = new Pre_GetLOSTOI(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;
		case Service.DfltLoanOpenDetail:
			data = "{\"cifId\":104513224,\"solId\":2000,\"schmCode\":\"A5941\",\"glSubHeadCode\":21110,\"crncyCode\":\"VND\"}";
			pre = new Pre_DfltLoanOpenDetail(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;
		case Service.LinkCollateralWithLimit:
			data = "{\"ColtrlLinkageType\":\"N\",\"LimitPrefix\":\"LAV220066704\",\"LimitSuffix\":2000,\"ColtrlId\":000248168,\"amountValue\":15000000,\"currencyCode\":\"VND\",\"ColtrlNatureInd\":\"P\",\"MarginPcnt\":10,\"LoanToValuePcnt\":90,\"colFromLoanFlg\":\"N\",\"approveTSBD\":\"\",\"topupTSBD\":\"\"}";
			pre = new Pre_LinkCollateralWithLimit(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;
		case Service.UnlinkCollateralDetails:
			data = "{\"coltrlLinkage\":\"N\",\"coltrlSrlNum\":000248168,\"coltrlType\":\"D\",\"limitPrefix\":\"LAV220066704\",\"limitSuffix\":2000,\"primSecndry\":\"P\",\"withdrawReasonCode\":00001,\"APPR_CRNCY\":\"VND\",\"APPR_VALUE\":1050000000,\"colFromLoanFlg\":\"N\",\"approveTSBD\":\"\",\"topupTSBD\":\"\"}";
			pre = new Pre_UnlinkCollateralDetails(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.AddLimitNodeCore:
			data = "{\"SolId\":\"1503\"}";
			pre = new Pre_AddLimitNodeCore(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.AddLimitNodeTeller:
			data = "{\"cifId\":106058979,\"drwngPowerInd\":\"D\",\"limitDesc\":\"TEST VAY CCOL\",\"limitEffDate\":\"2023-01-03\",\"limitExpiryDate\":\"2023-12-03\",\"limitPrefix\":\"LAV230013392\",\"limitReviewDate\":\"2023-12-03\",\"limitSanctDate\":\"2023-01-03\",\"limitSigningDate\":\"2023-01-03\",\"limitSuffix\":1504,\"limitType\":\"C\",\"serial_num1\":\"0001\",\"primaryCustomer1\":\"Y\",\"categoryType1\":\"I\",\"categoryCode1\":106058979,\"activeFlg1\":\"Y\",\"serial_num2\":\"0002\",\"primaryCustomer2\":\"Y\",\"categoryType2\":\"C\",\"categoryCode2\":\"VND\",\"activeFlg2\":\"Y\",\"serial_num3\":\"0003\",\"primaryCustomer3\":\"Y\",\"categoryType3\":\"P\",\"categoryCode3\":\"02SL\",\"activeFlg3\":\"Y\",\"sanctAuthCode\":\"001\",\"sanctLevelCode\":\"01\",\"amountValue\":150000000,\"currencyCode\":\"VND\",\"custSanctLim\":150000000,\"termType\":\"00002\",\"month\":10,\"day\":0,\"matDate_ui\":\"03-12-2023\",\"grcMonth\":1,\"grcDay\":0,\"limitAmt\":150000000,\"limUnsecAmt\":0,\"limitSolId\":1000,\"maxMonth\":20,\"maxDay\":0,\"isCollateral\":\"\",\"isTermAccount\":\"N\",\"isValuecollateral\":0,\"titName\":\"\",\"proName\":\"\",\"appName\":\"\",\"PRODUCT_TYPE\":\"02SL\",\"MASTER_CODE\":\"OPNEW\",\"totSAmt\":0}";
			pre = new Pre_AddLimitNodeTeller(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.CreditCardPublish:
			data = "{\"flagStatus\":\"N\",\"brcd\":2106,\"seqno\":\"\",\"psncrd\":\"\",\"cprcrd\":1,\"ppscrlmt\":1000000000,\"fullnm\":\"NGUYEN MINH DUY\",\"prnnm\":\"NGUYEN MINH DUY\",\"birthdt\":19881130,\"sex\":1,\"idcard\":79088034464,\"pmnaddr\":\"419 VO VAN TAN, PHUONG 5,\",\"yrsofrsd\":\"\",\"phoneno\":\"\",\"lvlofedu\":\"\",\"nationality\":\"VN\",\"mrtsts\":2,\"mommdnm\":\"\",\"housetp\":\"\",\"officenm\":\"CT TNHH VSTARSCHOOL\",\"officeaddr\":\"KDC HIMLAM QUAN 7,\",\"officetelno\":\"\",\"officepos\":\"GIAM DOC\",\"wrksnr\":\"\",\"labourtp\":0,\"annual\":\"\",\"rcvloc\":\"\",\"settltp\":2,\"dracctno\":\"\",\"sttlwith\":\"\",\"accttp\":\"\",\"drratio\":\"\",\"receiptdt\":20230106,\"crdtp\":\"V601\",\"colltp\":6,\"crlmt\":1000000000,\"expdt\":20260101,\"checked\":\"LINH.TTH\",\"checkeddt\":20230106,\"approved\":\"HUY.TVD\",\"approveddt\":20230106,\"status\":7,\"photofilename\":\"\",\"fullnm1\":\"BUI NGOC MY\",\"idcard1\":84168008399,\"ofcnm\":\"\",\"ofcaddr\":\"\",\"ofcphoneno\":\"\",\"hmaddr\":\"\",\"hmphoneno\":913931921,\"appdate\":20230106,\"crdtype\":\"\",\"crdvalidaty\":3,\"mobifon\":968689989,\"email\":\"MINHDUY@ME.COM\",\"other\":\"\",\"crdtypebank\":\"\",\"psncrdbank\":\"\",\"cprcrdbank\":1,\"note\":\"MIEN PHI PH VA TN THEO DE NGHI DVKD\",\"fileno\":\"MAI.NTT\",\"contractno\":\"LAV230001404\",\"accountcd\":210610505000020,\"cardno\":\"\",\"brname\":2106,\"mntcrdval\":\"\",\"issutp\":2,\"noteofho\":\"\",\"anlfeereason\":\"THEO DE NGHI CUA DVKD\",\"compid\":\"\",\"custid\":\"2106-114834133\",\"frdt\":\"\",\"todt\":\"\",\"vsvldt\":\"\",\"flgsms\":1,\"flglck\":1,\"tellck\":968689989,\"flgquery\":1,\"telquery\":968689989,\"flgnote\":1,\"telnote\":968689989,\"flgrcv\":1,\"flgtimes\":1,\"times\":0,\"flgamt\":0,\"amount\":\"\",\"cshlmtofcust\":500000000,\"cshlmtofbnk\":500000000,\"custtpcd\":\"TN1\",\"custdtltpcd\":\"\",\"colldtltp\":\"\",\"cardnbr\":\"\",\"compcustseq\":102087764,\"statedt\":\"\",\"comptp\":\"\",\"compothr\":\"\",\"jobtp\":\"\",\"jobothr\":\"\",\"edcntp\":\"\",\"edcnothr\":\"\",\"addrtp\":\"\",\"addrothr\":\"\",\"depdno\":\"\",\"chkapp\":0,\"flg3dsce\":1,\"email3dsce\":\"MINHDUY@ME.COM\",\"tel13dsce\":968689989,\"tel23dsce\":\"\",\"tel33dsce\":\"\",\"chkecommerce\":2,\"purpose\":\"\",\"deptcd\":2106,\"chkemail\":3,\"address1\":\"QUAN 3,\",\"address2\":\"\",\"address3\":\"TP HCM\",\"address4\":\"VN\",\"officeaddr1\":\"\",\"rcvcardaddr\":\"\",\"prdtcd\":\"V601\",\"vipflg\":\"N\",\"cmpnynm\":\"CT TNHH VSTARSCHOOL\",\"usrnameXetduyet\":\"\",\"usrnameXetduyet01\":\"\",\"usrnameXetduyet02\":\"\",\"usrnameXetduyet03\":\"\",\"usrnameXetduyet04\":\"\",\"usrnameXetduyet05\":\"\",\"usrnameXetduyet06\":\"\",\"brcdDeptRcv\":2106,\"brcdDeptNameRcv\":\"PGD TRUNG SON\",\"flgloan\":\"\",\"disbursementno\":\"\",\"disbursementamt\":\"\",\"eibChucvu\":\"\",\"recevcardMk\":3,\"recevcardAdd\":2106,\"officeaddr2\":\"\",\"officeaddr3\":\"TP HCM\",\"other1\":\"\",\"other2\":\"\",\"other3\":\"\",\"employid\":\"\",\"addusrid\":\"LINH.TTH\",\"apprusrid\":\"HUY.TVD\",\"brapprusrid\":2106,\"checkusrid\":2106,\"odaccount\":\"2106ODBCC2300002\",\"payacct\":\"\",\"geAcctcd\":210624849219273,\"geFileno\":\"\",\"geContractno\":\"\",\"geAutopaypecent\":100,\"geAutopayamt\":\"\",\"geAutocash\":\"N\",\"geAutodebit\":\"Y\",\"geAutopayacct\":210614851686868,\"shrtname\":\"\",\"conName\":\"\",\"geLimit\":1000000000,\"geSendaddr\":\"\",\"geCtrynmloc\":\"\",\"geCardtp1\":\"\",\"geCardtp2\":\"\",\"geCardtp3\":\"tinchp\",\"geCardtp4\":\"\",\"freetp\":\"N\",\"crdlmtcd\":11,\"unststm\":\"N\",\"recevcardReason\":5}";
			pre = new Pre_CreditCardPublish(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.CreateDepositColtrl:
			data = "{\"MarginPcnt\":10,\"LoanToValuePcnt\":90,\"LodgedDt\":\"2022-08-09\",\"ReviewDt\":\"2024-03-03\",\"ReceivedDt\":\"2022-08-09\",\"DueDt\":\"2024-03-03\",\"DepAcctId\":210450116000175,\"ApportionedAmt\":350000000,\"LienAmt\":350000000,\"CustId\":100527045,\"CollateralValue\":350000000}";
			pre = new Pre_CreateDepositColtrl(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.CollateralCreateCustom:
			// data
			// ="{\"coltrlId\":000248168,\"issDat\":\"19-12-2022\",\"issTerm\":\"19-12-2023\",\"brnch\":1817,\"secuObliga\":\"H\",\"valDate\":\"19-12-2022\"}";
			data = "{\"coltrlId\":000248168,\"issDat\":\"19-12-2022\",\"issTerm\":\"19-12-2023\",\"brnch\":1817,\"secuObliga\":\"Hadsada\",\"valDate\":\"19-12-2022\"}";
			pre = new Pre_CollateralCreateCustom(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.OpenLoanAccount:
			data = "{\"CustId\":118385271,\"LASchmCode\":\"OA001\",\"LASchmType\":\"LAA\",\"LAAcctCurr\":\"VND\",\"LABranchId\":1503,\"GenLedgerSubHeadCode\":21120,\"CurCode\":\"VND\",\"AcctStmtMode\":\"N\",\"LoanPeriodMonths\":0,\"LoanPeriodDays\":23,\"RePmtMethod\":\"E\",\"AcctId\":150310102000283,\"SchmCode\":10102,\"SchmType\":\"CAA\",\"AcctCurr\":\"VND\",\"BankId\":\"01\",\"BranchId\":1503,\"EqInstallFlg\":\"N\",\"InstallmentId1\":\"PRDEM\",\"InstallStartDt1\":\"2023-03-28\",\"InstallFreq_Cal1\":\"00\",\"InstallFreq_Type1\":\"M\",\"InstallFreq_StartDt1\":28,\"InstallFreq_WeekDay1\":0,\"InstallFreq_HolStat1\":\"N\",\"IntFreq_Cal1\":\"00\",\"IntFreq_Type1\":\"M\",\"IntFreq_StartDt1\":28,\"IntFreq_WeekDay1\":0,\"IntFreq_HolStat1\":\"N\",\"NoOfInstall\":1,\"IntStartDt1\":\"2023-03-28\",\"FlowAmt_amountValue1\":500000000,\"FlowAmt_currencyCode1\":\"VND\",\"InstallmentId2\":\"INDEM\",\"InstallStartDt2\":\"2023-03-28\",\"InstallFreq_Cal2\":\"00\",\"InstallFreq_Type2\":\"M\",\"InstallFreq_StartDt2\":28,\"InstallFreq_WeekDay2\":0,\"InstallFreq_HolStat2\":\"N\",\"IntFreq_Cal2\":\"00\",\"IntFreq_Type2\":\"M\",\"IntFreq_StartDt2\":28,\"IntFreq_WeekDay2\":0,\"IntFreq_HolStat2\":\"N\",\"IntStartDt2\":\"2023-03-28\",\"FlowAmt_amountValue2\":0,\"FlowAmt_currencyCode2\":\"VND\",\"LoanAmt_amountValue\":500000000,\"LoanAmt_currencyCode\":\"VND\",\"HoldInOperAcctFlg\":\"N\",\"AcctDrPrefPcnt\":8,\"MaxIntPcntDr\":99,\"MinIntPcntDr\":0,\"OCCUPATION\":99,\"REGION\":\"HCMC\",\"PRODUCT\":\"00000\",\"MARKET\":\"00000\",\"otherType2\":\"9KHAC\",\"ACCT_LIM_ENT\":1,\"DRWNG_PCNT\":100,\"LIM_DOC_DATE\":\"28-02-2023\",\"LIM_EXP_DATE\":\"27-03-2023\",\"LIM_LEVL_INT\":\"N\",\"LIM_PREFIX\":\"LAV230000028\",\"LIM_SUFFIX\":1503,\"SANCTL_LIM\":500000000,\"SANCTL_LIM_CRNCY\":\"VND\",\"SANCT_DATE\":\"28-02-2023\",\"DRWNG_IND\":\"P\",\"LIM_CIF_ID\":118385271,\"LIM_CRNCY_CODE\":\"VND\",\"LIM_SCHM_CODE\":\"OA001\"}";
			pre = new Pre_OpenLoanAccount(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		case Service.SendSMS:
			data = "{\"clientId\":\"RLOSService\",\"referenceNo\":\"AR-1565741721623636479496\",\"sendType\":2,\"PhoneNumber\":849093083166,\"ottPhoneNumber\":849093083166,\"message\":\"Noi dung tin nhan\",\"custId\":118816270,\"branchCode\":1000,\"requestTime\":\"2021-06-14 09:08:03\"}";
			pre = new Pre_SendSMS(data, siVersion, processInfo, processDataSource, logDataSource);
			break;
			
		case Service.SendMail:
			data = "{\"host\":\"mail.eximbank.com.vn\",\"port\":\"25\",\"transport\":\"smtp\",\"fromEmail\":\"los-test@eximbank.com.vn\",\"password\":\"Exim@123\",\"toEmails\":[\"los-test@eximbank.com.vn\"],\"subject\":\"LOS MAIL TESTING\",\"body\":\"<!DOCTYPE html><html><head><meta name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1\\\"></head><body><p>Anh/Ch? v?a nh?n du?c h? so c? th?:</p><p>- M� h? so: DBM0000772</p><p>- T�n kh�ch h�ng: Nguy?n Minh Trang</p><p>�? ngh? Anh Ch? xem x�t v� x? l�.</p><p>Tr�n tr?ng!</p></body></html>\",\"isAuth\":\"true\",\"isSSL\":\"false\",\"isDebug\":\"true\"}";
			pre = new Pre_SendMail(data, siVersion, processInfo, processDataSource, logDataSource);
			break;

		case Service.GetLOSDS:
			data = "{\"Cif\":116997630}";
			pre = new Pre_GetLOSDS(data, siVersion, processInfo, processDataSource, logDataSource);
			pre.isMergeData = true;
			break;

		default:
			throw new Exception("Invalid service");
		}

		System.err.println("pre result - " + pre.invoke());
	}
}
