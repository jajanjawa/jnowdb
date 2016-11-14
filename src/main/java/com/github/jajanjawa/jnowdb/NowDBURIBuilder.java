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
	public static final String SERVICE_URL_V1 = "http://io.nowdb.net/operation/";

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

	public NowDBURIBuilder append(String name, Object value) {
		builder.append(name).append('/');
		builder.append(value).append('/');

		fullUrl.append(builder);
		builder.setLength(0);
		return this;
	}

	public NowDBURIBuilder appId(String appId) {
		return append(APP_ID, appId);
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
		return append(COLLECTION, collection);
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public NowDBURIBuilder id(String id) {
		return append(ID, id);
	}

	public NowDBURIBuilder limit(Integer limit) {
		return append(LIMIT, limit);
	}

	public NowDBURIBuilder offset(Integer offset) {
		return append(OFFSET, offset);
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
		return append(PROJECT, project);
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
		return append(TOKEN, token);
	}
}
