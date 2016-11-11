package com.github.jajanjawa.jnowdb;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

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
	 * Untuk konversi secara manual.
	 * @return data asli dari server.
	 * @see NowDB#getGson()
	 */
	public String raw() {
		return json;
	}
	
	/**
	 * Untuk operasi {@link NowDB#countAll(String)}
	 * @return jumlah data dalam koleksi
	 */
	public int count() {
		JsonObject object = NowDB.getGson().fromJson(json, JsonObject.class);
		return object.get("total").getAsInt();
	}

	public Status status() throws IOException {
		return NowDB.getGson().fromJson(json, Status.class);
	}

}
