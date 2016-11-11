package com.github.jajanjawa.jnowdb;

import static com.github.jajanjawa.jnowdb.NowDBProperty.APP_ID;
import static com.github.jajanjawa.jnowdb.NowDBProperty.PROJECT;
import static com.github.jajanjawa.jnowdb.NowDBProperty.TOKEN;

import okhttp3.FormBody;

public class NowDBConfig {
	public String appId;
	public String project;
	public String token;

	public NowDBConfig() {
	}

	public NowDBConfig(NowDBConfig config) {
		this(config.token, config.project, config.appId);
	}

	public NowDBConfig(String token, String project, String appId) {
		this.token = token;
		this.project = project;
		this.appId = appId;
	}

	public NowDBConfig appId(String appId) {
		this.appId = appId;
		return this;
	}

	/**
	 * Membuat salinan baru untuk konfigurasi ini.
	 * 
	 * @return Salinan baru.
	 */
	public NowDBConfig copy() {
		return new NowDBConfig(token, project, appId);
	}
	
	public FormBody.Builder writeTo(FormBody.Builder builder) {
		builder.add(TOKEN, token);
		builder.add(PROJECT, project);
		builder.add(APP_ID, appId);
		return builder;
	}
	
	public NowDBConfig project(String project) {
		this.project = project;
		return this;
	}

	public NowDBConfig token(String token) {
		this.token = token;
		return this;
	}

}