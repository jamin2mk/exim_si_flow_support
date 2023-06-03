package com.test.module;

import com.consts.communication.Service;
import com.pre.impl.communication.Pre_SendMail;
import com.pre.impl.communication.Pre_SendSMS;
import com.si.pre.SIPre;

public class Test_Services_Communication {

	public static void main(String[] args) throws Exception {

		String data = null;
		String siVersion = "RLOSv1.0";
		String processInfo = null;
		String processDataSource = "SAALEM_51";
		String logDataSource = "SAALEM_51";

		SIPre pre;
		String service = Service.SendMail;

		switch (service) {

		case Service.SendSMS:
			data = "{\"clientId\":\"RLOSService\",\"referenceNo\":\"AR-1565741721623636479496\",\"sendType\":2,\"PhoneNumber\":849093083166,\"ottPhoneNumber\":849093083166,\"message\":\"Noi dung tin nhan\",\"custId\":118816270,\"branchCode\":1000,\"requestTime\":\"2021-06-14 09:08:03\"}";
			pre = new Pre_SendSMS(data, siVersion, processInfo, processDataSource, logDataSource);
			break;

		case Service.SendMail:
			data = "{\"host\":\"mail.eximbank.com.vn\",\"port\":\"25\",\"transport\":\"smtp\",\"fromEmail\":\"los-test@eximbank.com.vn\",\"password\":\"Exim@123\",\"toEmails\":[\"los-test@eximbank.com.vn\"],\"subject\":\"LOS MAIL TESTING\",\"body\":\"<!DOCTYPE html><html><head><meta name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1\\\"></head><body><p>Anh/Chị vừa nhận được hồ sơ cụ thể:</p><p>- Mã hồ sơ: DBM0000772</p><p>- Tên khách hàng: Nguyễn Minh Trang</p><p>Đề nghị Anh Chị xem xét và xử lý.</p><p>Trân trọng!</p></body></html>\",\"isAuth\":\"true\",\"isSSL\":\"false\",\"isDebug\":\"true\"}";
			pre = new Pre_SendMail(data, siVersion, processInfo, processDataSource, logDataSource);
			break;

		default:
			throw new Exception("Invalid service");
		}

		System.err.println("pre result - " + pre.invoke());
	}
}
