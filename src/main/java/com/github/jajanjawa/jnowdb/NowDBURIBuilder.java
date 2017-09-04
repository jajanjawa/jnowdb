package com.github.jajanjawa.jnowdb;

import okhttp3.HttpUrl;

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
	private final HttpUrl.Builder urlBuilder;

	public NowDBURIBuilder() {
		this(SERVICE_URL);
	}

	public NowDBURIBuilder(String baseUrl) {
        urlBuilder = HttpUrl.parse(baseUrl).newBuilder();
    }

	public static NowDBURIBuilder useV1() {
	    return new NowDBURIBuilder(SERVICE_URL_V1);
    }

	/**
	 *
	 * @param path
	 * @return
	 */
	public NowDBURIBuilder append(String path) {
		urlBuilder.addEncodedPathSegment(path);
		return this;
	}

	public NowDBURIBuilder append(String name, Object value) {
		urlBuilder.addEncodedPathSegment(name);
		urlBuilder.addEncodedPathSegment(value.toString());
		return this;
	}

	public NowDBURIBuilder appId(String appId) {
		return append(APP_ID, appId);
	}

	/**
	 * @return
	 */
	public String build() {
//        return fullUrl.toString();
        return urlBuilder.build().toString();
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
		urlBuilder.addEncodedPathSegment(operation.toString());
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

    public NowDBURIBuilder token(String token) {
		return append(TOKEN, token);
	}
}
