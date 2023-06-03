package com.si.model.serviceconfig;


/**
 * @author trangnmt
 *
 */
public class ServiceConfig {

	protected String url;
	protected String username;
	protected String password;

	protected DBConfig db;
	protected OdmConfig odm;
	protected GroupConfig group;
	protected SibsConfig sibs;

	public ServiceConfig() {
		// TODO Auto-generated constructor stub
	}

	public ServiceConfig(String url, String username, String password, String serviceVersion) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public DBConfig getDb() {
		return db;
	}

	public void setDb(DBConfig db) {
		this.db = db;
	}

	public OdmConfig getOdm() {
		return odm;
	}

	public void setOdm(OdmConfig odm) {
		this.odm = odm;
	}

	public GroupConfig getGroup() {
		return group;
	}

	public void setGroup(GroupConfig group) {
		this.group = group;
	}

	public SibsConfig getSibs() {
		return sibs;
	}

	public void setSibs(SibsConfig sibs) {
		this.sibs = sibs;
	}

}
