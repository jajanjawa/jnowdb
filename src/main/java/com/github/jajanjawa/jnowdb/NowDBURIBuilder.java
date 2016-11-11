package com.github.jajanjawa.jnowdb;

import static com.github.jajanjawa.jnowdb.NowDBProperty.APP_ID;
import static com.github.jajanjawa.jnowdb.NowDBProperty.COLLECTION;
import static com.github.jajanjawa.jnowdb.NowDBProperty.ID;
import static com.github.jajanjawa.jnowdb.NowDBProperty.LIMIT;
import static com.github.jajanjawa.jnowdb.NowDBProperty.OFFSET;
import static com.github.jajanjawa.jnowdb.NowDBProperty.PROJECT;
import static com.github.jajanjawa.jnowdb.NowDBProperty.TOKEN;

/**
 * Membuat url, harus sesuai urutan.
 * 
 * @author Irwan
 *
 */
public class NowDBURIBuilder {

	public static final String SERVICE_URL = "http://io.nowdb.net/v2/";

	private StringBuilder builder;
	private StringBuilder fullUrl;

	public NowDBURIBuilder() {
		builder = new StringBuilder();
		fullUrl = new StringBuilder();
	}

	/**
	 *
	 * @param path
	 * @return
	 */
	public NowDBURIBuilder append(String path) {
		fullUrl.append(path);
		return this;
	}

	public NowDBURIBuilder appId(String appId) {
		builder.append(APP_ID).append('/');
		builder.append(appId).append('/');

		fullUrl.append(builder);
		builder.setLength(0);
		return this;
	}

	/**
	 * @return
	 */
	public String build() {
		return fullUrl.toString();
	}

	/**
	 * Panggil {@link #project(String)} dahulu.
	 *
	 * @param collection
	 * @return
	 */
	public NowDBURIBuilder collection(String collection) {
		builder.append(COLLECTION).append('/');
		builder.append(collection).append('/');

		fullUrl.append(builder);
		builder.setLength(0);
		return this;
	}

	public NowDBURIBuilder append(String name, String value) {
		builder.append(name).append('/');
		builder.append(value).append('/');

		fullUrl.append(builder);
		builder.setLength(0);
		return this;
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public NowDBURIBuilder id(String id) {
		builder.append(ID).append('/');
		builder.append(id).append('/');

		fullUrl.append(builder);
		builder.setLength(0);
		return this;
	}

	public NowDBURIBuilder limit(Integer limit) {
		builder.append(LIMIT).append('/');
		builder.append(limit).append('/');

		fullUrl.append(builder);
		builder.setLength(0);
		return this;
	}

	public NowDBURIBuilder offset(Integer offset) {
		builder.append(OFFSET).append('/');
		builder.append(offset).append('/');

		fullUrl.append(builder);
		builder.setLength(0);
		return this;
	}

	public NowDBURIBuilder operation(Operation operation) {
		fullUrl.append(operation);
		return this;
	}

	/**
	 * Panggil {@link #token(String)} dahulu.
	 *
	 * @param project
	 * @return
	 */
	public NowDBURIBuilder project(String project) {
		builder.append(PROJECT).append('/');
		builder.append(project).append('/');

		fullUrl.append(builder);
		builder.setLength(0);
		return this;
	}

	/**
	 * Server url
	 * 
	 * @return
	 */
	public NowDBURIBuilder serviceURL() {
		fullUrl.append(SERVICE_URL);
		return this;
	}

	public NowDBURIBuilder token(String token) {
		builder.append(TOKEN).append('/');
		builder.append(token).append('/');

		fullUrl.append(builder);
		builder.setLength(0);
		return this;
	}
}
