package com.si.util;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.gson.Gson;
import com.si.model.mail.SEmailModel;

public class SEmail {
	
	Properties emailProperties;
	Authenticator authenticator;
	Session mailSession;
	MimeMessage emailMessage;

	SEmailModel mailRequest;

	public SEmail(SEmailModel mailRequest) {
		this.mailRequest = mailRequest;
	}
	
	public static void main(String[] args) throws Exception {
		String req = "{\"host\":\"mail.eximbank.com.vn\",\"port\":\"25\",\"transport\":\"smtp\",\"fromEmail\":\"los-test@eximbank.com.vn\",\"password\":\"Exim@123\",\"toEmails\":[\"los-test@eximbank.com.vn\"],\"subject\":\"LOS MAIL TESTING\",\"body\":\"<!DOCTYPE html><html><head><meta name=\\\"viewport\\\" content=\\\"width=device-width, initial-scale=1\\\"></head><body><p>Anh/Chị vừa nhận được hồ sơ cụ thể:</p><p>- Mã hồ sơ: DBM0000772</p><p>- Tên khách hàng: Nguyễn Minh Trang</p><p>Đề nghị Anh Chị xem xét và xử lý.</p><p>Trân trọng!</p></body></html>\",\"isAuth\":\"true\",\"isSSL\":\"false\",\"isDebug\":\"true\"}";
		SEmailModel mailRequest = new Gson().fromJson(req, SEmailModel.class);
		new SEmail(mailRequest).sentLosMail();
	}

	public boolean sentLosMail() throws Exception {
		
		boolean result = false;
		
		this.setMailServerProperties(this.mailRequest.getHost(), this.mailRequest.getPort(), this.mailRequest.getTransport(), this.mailRequest.getIsSSL(), this.mailRequest.getIsAuth(), this.mailRequest.getIsDebug());

		Session session = this.setAuthenticatorAndSession(this.mailRequest.getFromEmail().split("@")[0], this.mailRequest.getPassword());
		this.setMessageAndSend(session, this.mailRequest.getToEmails(), this.mailRequest.getSubject(), this.mailRequest.getBody(), this.mailRequest.getFromEmail());
		
		result = true;		
		return result;
	}

	public void setMailServerProperties(String emailHost, String emailPort, String transport, String isSSL, String isAuth, String isDebug) {

		this.emailProperties = System.getProperties();

		this.emailProperties.put("mail.smtp.host", emailHost);
		this.emailProperties.put("mail.smtp.port", emailPort);
		this.emailProperties.put("mail.transport.protocol", transport);

		this.emailProperties.put("mail.smtp.socketFactory.port", emailPort);
		this.emailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		// send external mail
		this.emailProperties.put("mail.smtp.ssl.trust", emailHost);
		
		this.emailProperties.put("mail.smtp.ssl.enable", isSSL);
		this.emailProperties.put("mail.smtp.auth", isAuth);
		this.emailProperties.put("mail.debug", isDebug);

	}

	public Session setAuthenticatorAndSession(String user, String password) {
		Session session = null;

		this.authenticator = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		};

		String path = Session.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		System.out.println("MAIL LIB PATH: " + path);

		SecurityManager securityManager = System.getSecurityManager();

		if (securityManager == null) {
			session = Session.getInstance(emailProperties, authenticator);
		} else {
			session = Session.getDefaultInstance(emailProperties, authenticator);
		}

		System.out.println("Session created");

		return session;
	}

	public void setMessageAndSend(Session session, List<String> toEmails, String subject, String body, String fromEmail) throws Exception {

		MimeMessage msg = new MimeMessage(session);

		msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		msg.addHeader("format", "flowed");
		msg.addHeader("Content-Transfer-Encoding", "8bit");

		msg.setFrom(new InternetAddress(fromEmail, "HE THONG RLOS - NO REPLY"));

		msg.setSubject(subject, "UTF-8");
		msg.setContent(body, "text/html; charset=UTF-8");

		msg.setSentDate(new Date());

		for (String email : toEmails) {
			msg.addRecipient(Message.RecipientType.BCC, (Address) new InternetAddress(email));
		}

		System.out.println("Message is ready");
		Transport.send(msg);

		System.out.println("EMail Sent Successfully!!");
	}
}
