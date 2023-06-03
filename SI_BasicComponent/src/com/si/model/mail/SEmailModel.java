package com.si.model.mail;

import java.util.List;

public class SEmailModel {
	protected String host;
	protected String port;
	protected String transport;
	protected String fromEmail;
	protected String password;
	protected List<String> toEmails;
	protected String subject;
	protected String body;
	protected String isAuth;
	protected String isSSL;
	protected String isDebug;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getToEmails() {
		return toEmails;
	}

	public void setToEmails(List<String> toEmails) {
		this.toEmails = toEmails;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getIsAuth() {
		return isAuth;
	}

	public void setIsAuth(String isAuth) {
		this.isAuth = isAuth;
	}

	public String getIsSSL() {
		return isSSL;
	}

	public void setIsSSL(String isSSL) {
		this.isSSL = isSSL;
	}

	public String getIsDebug() {
		return isDebug;
	}

	public void setIsDebug(String isDebug) {
		this.isDebug = isDebug;
	}
}
