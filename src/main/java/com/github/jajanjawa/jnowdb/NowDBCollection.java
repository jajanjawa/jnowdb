package com.github.jajanjawa.jnowdb;

import com.google.gson.annotations.SerializedName;

public abstract class NowDBCollection {

	@SerializedName("id")
	protected String id;

	public abstract String getCollectionName();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
