package com.si.model.env;

import com.google.gson.annotations.SerializedName;

public class OdmEnv {

	@SerializedName("ODM_URI")
	protected String uri;
	@SerializedName("ODM_AppId")
	protected String appId;
	@SerializedName("pkg_ODM_EXECUTE")
	protected String pck;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getPck() {
		return pck;
	}

	public void setPck(String pck) {
		this.pck = pck;
	}

}
