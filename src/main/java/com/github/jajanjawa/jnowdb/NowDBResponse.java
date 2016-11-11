package com.github.jajanjawa.jnowdb;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.slf4j.LoggerFactory;

import okhttp3.Response;

public class NowDBResponse {

	private Response response;
	private String json;

	public NowDBResponse(Response response) throws IOException {
		this.response = response;
		if (!response.isSuccessful()) {
			throw new IOException(response.toString());
		}
		try {
			json = response.body().string();
			System.out.println(json);
		} finally {
			response.close();
		}
	}

	/**
	 * Konversi menjadi class ini.
	 * 
	 * @param clazz
	 *            target konversi yang di inginkan.
	 * @return instance dari class yang di inginkan.
	 * @throws IOException
	 */
	public <T> T as(Class<T> clazz) throws IOException {
		return list(json, clazz).get(0);
	}

	public <T> List<T> list(Class<T> clazz) throws IOException {
		return list(json, clazz);
	}

	protected <T> List<T> list(String json, Class<T> listOfT) {
		ListNowDBCollection<T> collection = new ListNowDBCollection<T>(listOfT);
		return NowDB.getGson().fromJson(json, collection);
	}

	/**
	 * @param response
	 * @param listOfT
	 * @return
	 * @throws IOException
	 */
	@Deprecated
	protected <T> List<T> list(Response response, Class<T> listOfT) throws IOException {
		if (!response.isSuccessful()) {
			throw new IOException(response.toString());
		}
		ListNowDBCollection<T> collection = new ListNowDBCollection<T>(listOfT);
		try (Reader reader = response.body().charStream()) {
			return NowDB.getGson().fromJson(reader, collection);
		}
	}

	public Status status() throws IOException {
		return NowDB.getGson().fromJson(json, Status.class);
	}

}
