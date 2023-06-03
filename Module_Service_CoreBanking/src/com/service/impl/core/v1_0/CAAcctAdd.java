package com.service.impl.core.v1_0;

import java.text.MessageFormat;
import java.util.Date;

import com.consts.Service;
import com.model.caacctadd.req.v_1_0.CAAcctAddRequest;
import com.model.caacctadd.req.v_1_0.TellerAppRequest;
import com.model.caacctadd.res.v_1_0.Body;
import com.model.caacctadd.res.v_1_0.TellerAppResponse;
import com.si.consts.DATE_PATTERN;
import com.si.consts.Error;
import com.si.consts.Message;
import com.si.exception.SIException;
import com.si.helper.DateHelper;
import com.si.helper.XmlHelper;
import com.si.model.SI_Log;
import com.si.model.serviceconfig.ServiceConfig;
import com.si.service.SI_Service;
import com.si.service.invoke.impl.CoreBankingInvoker;

public class CAAcctAdd extends SI_Service{
	
	protected Body body = null;

	public CAAcctAdd(String data, boolean isMergeData, String siVersion,
			String processDataSource, String logDataSource,
			ServiceConfig serviceConfig, SI_Log log) {
		super(data, isMergeData, siVersion, processDataSource, logDataSource,
				serviceConfig, log);
		super.isManualMapping_Input= true;
		super.isManualMapping_Output = true;
		super.invoker = new CoreBankingInvoker(serviceConfig, processDataSource, TellerAppRequest.class, TellerAppResponse.class, log);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void businessMapping_Input() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manualMapping_input() throws SIException {
		// TODO Auto-generated method stub
		String preMessage = "<FIXML xsi:schemaLocation=\"http://www.finacle.com/fixml CAAcctAdd.xsd\" xmlns=\"http://www.finacle.com/fixml\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><Header><RequestHeader><MessageKey><RequestUUID>{0}</RequestUUID><ServiceRequestId>{1}</ServiceRequestId><ServiceRequestVersion>{2}</ServiceRequestVersion><ChannelId>{3}</ChannelId><LanguageId></LanguageId></MessageKey><RequestMessageInfo><BankId></BankId><TimeZone></TimeZone><EntityId></EntityId><EntityType></EntityType><ArmCorrelationId></ArmCorrelationId><MessageDateTime>{4}</MessageDateTime></RequestMessageInfo><Security><Token><PasswordToken><UserId></UserId><Password></Password></PasswordToken></Token><FICertToken></FICertToken><RealUserLoginSessionId></RealUserLoginSessionId><RealUser></RealUser><RealUserPwd></RealUserPwd><SSOTransferToken></SSOTransferToken></Security></RequestHeader></Header><Body>{5}</Body></FIXML>";
//		String data = "{\"CAAcctAdd_CustomData\":{\"FREECODE4\":\"\",\"MINBALCRNCY\":\"VND\",\"LuckyAccNum\":\"152488368\",\"ACCTMGRID\":\"RENXI.VQ\",\"LOCALCALENDAR\":\"N\",\"FREECODE8\":\"N\",\"DSAID\":\"TEL200009045\",\"v\":\"S\",\"ACCTPREFINTCR\":0.2,\"MINBALIND\":\"A\",\"NEXTINTERESTRUNDATECR\":\"15-10-2022\",\"PrefLangCode\":\"VN\",\"MINBAL\":200000,\"LienFeeAmount\":50000,\"INTCRACCNUM\":\"\",\"FREETEXT4\":\"\",\"FREECODE9\":\"\",\"INTRATECODE\":\"DRKKH\",\"FREETEXT3\":\"\",\"FREETEXT2\":\"31-12-2099\",\"FREETEXT1\":\"08-10-2019\",\"RelatedDtls\":{\"notes\":\"Simple notes\",\"phone\":\"0773159371\",\"addrLine1\":\"\",\"title\":\"MR.\",\"userName\":\"Crazy guy\",\"isMultiRec\":null,\"email\":\"ritish2790@gmail.com\",\"relation\":\"O\"},\"FREETEXT8\":\"\"},\"CAAcctAddRq\":{\"CustId\":{\"CustId\":\"104491256\"},\"CAAcctId\":{\"AcctType\":{\"SchmCode\":\"10101\"},\"AcctCurr\":\"VND\",\"BankInfo\":{\"BranchId\":\"2000\"}},\"CAAcctGenInfo\":{\"AcctName\":\"HUYNH THI NGUYET THANH\",\"DespatchMode\":\"N\",\"AcctShortName\":\"THANH\",\"GenLedgerSubHead\":{\"GenLedgerSubHeadCode\":\"42110\"},\"AcctStmtFreq\":{\"StartDt\":31,\"Type\":\"Y\",\"HolStat\":\"S\"},\"AcctStmtMode\":\"S\"}}}";
		
//		String data = "{\"CAAcctAddRq\":{\"CustId\":{\"CustId\":121250146},\"CAAcctId\":{\"AcctType\":{\"SchmCode\":10501},\"AcctCurr\":\"VND\",\"BankInfo\":{\"BranchId\":1002}},\"CAAcctGenInfo\":{\"GenLedgerSubHead\":{\"GenLedgerSubHeadCode\":42732},\"AcctName\":\"PHAN LE KIM PHUONG\",\"AcctStmtMode\":\"S\",\"AcctStmtFreq\":{\"Type\":\"Y\",\"StartDt\":31,\"HolStat\":\"S\"},\"DespatchMode\":\"N\"}},\"CAAcctAdd_CustomData\":{\"DSAID\":\"TEL120101124\",\"LOCALCALENDAR\":\"N\",\"FREETEXT8\":\"N\",\"FREECODE8\":\"N\",\"ACCTMGRID\":\"LANH.NLD\",\"MINBALIND\":\"S\",\"MINBAL\":0,\"PrefLangCode\":\"VN\",\"MINBALCRNCY\":\"VND\",\"LienFeeAmount\":0}}";
		CAAcctAddRequest caAcctAddRequest = super.gson.fromJson(super.data, CAAcctAddRequest.class);
		String requestData = XmlHelper.convertObjectToXml(CAAcctAddRequest.class, caAcctAddRequest);
		String requestUUID = MessageFormat.format("CAA{0}", DateHelper.convertDateToString(new Date(), DATE_PATTERN.YYYYMMDD_HH_MM_SS));
		String messageDateTime = DateHelper.getCurrentDate(DATE_PATTERN.HYPHEN_LONG_T_S3);
		String result = MessageFormat.format(preMessage, requestUUID, Service.CAAcctAdd, "10.2", "COR", messageDateTime, requestData);
//		String inputRequest = String.format("<![CDATA[%s]]>", result);
		
		TellerAppRequest tellerAppRequest = new TellerAppRequest(result);
		super.invoked_input = super.gson.toJsonTree(tellerAppRequest);
		
		
		
	}

	@Override
	public void customize_InvokedInput() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void invoke_manual() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void check_invokeResult() throws SIException {
		// TODO Auto-generated method stub
		
		String s = XmlHelper.fetchSubElementAsString(super.invoked_output
				.getAsJsonObject().get("outputresponse").getAsString(), "Body");
		try {
			this.body = XmlHelper.convertXmlToObject(Body.class, s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new SIException("MAP-99", e.getMessage());
		}
		if (this.body.getAcctAddResponse() == null) {
			if (this.body.getError() != null) {
				if(this.body.getError().getFiBusinessException()!=null){
					throw new SIException(String.format("SV-%s", this.body.getError().getFiBusinessException().getErrorDetail().getErrorCode()), this.body.getError().getFiBusinessException().getErrorDetail().getErrorDesc());
				} else {
					throw new SIException(String.format("SV-%s", this.body.getError().getFiSystemException().getErrorDetail().getErrorCode()), this.body.getError().getFiSystemException().getErrorDetail().getErrorDesc());
				}
				
			} else {
				throw new SIException(Error.SV_99, MessageFormat.format(Message.NO_DATA_RESPONSE,super.log.getSystem().getTo(), super.log.getService()));
			}
		}
		super.invoked_output = super.gson.toJsonTree(this.body);
		
	}

	@Override
	public void customize_InvokedOutput() throws SIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void manualMapping_output() throws SIException {
		// TODO Auto-generated method stub
		super.data = super.invoked_output;
	}

	@Override
	public void businessMapping_Output() throws SIException {
		// TODO Auto-generated method stub
		
	}

}
