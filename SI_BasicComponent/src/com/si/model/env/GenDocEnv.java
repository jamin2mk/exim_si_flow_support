package com.si.model.env;

import com.google.gson.annotations.SerializedName;

public class GenDocEnv {

	@SerializedName("GENDOC_URL")
	protected String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
