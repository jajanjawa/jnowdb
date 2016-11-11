package com.github.jajanjawa.jnowdb;

import static com.github.jajanjawa.jnowdb.NowDBProperty.APP_ID;
import static com.github.jajanjawa.jnowdb.NowDBProperty.COLLECTION;
import static com.github.jajanjawa.jnowdb.NowDBProperty.ID;
import static com.github.jajanjawa.jnowdb.NowDBProperty.PROJECT;
import static com.github.jajanjawa.jnowdb.NowDBProperty.TOKEN;
import static com.github.jajanjawa.jnowdb.NowDBProperty.UPDATE_FIELD;
import static com.github.jajanjawa.jnowdb.NowDBProperty.UPDATE_VALUE;
import static com.github.jajanjawa.jnowdb.NowDBURIBuilder.SERVICE_URL;
import static com.github.jajanjawa.jnowdb.Operation.INSERT;
import static com.github.jajanjawa.jnowdb.Operation.UPDATE_ALL;
import static com.github.jajanjawa.jnowdb.Operation.UPDATE_ID;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class NowDB {

	public static final class Builder {

		private NowDBConfig config;
		private Gson gson;
		private OkHttpClient httpClient;

		public NowDB build() {
			if (config == null) {
				config = new NowDBConfig();
			}
			if (gson == null) {
				gson = new GsonBuilder().setPrettyPrinting().create();
			}
			if (httpClient == null) {
				httpClient = new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS)
						.readTimeout(30L, TimeUnit.SECONDS).writeTimeout(15L, TimeUnit.SECONDS).build();
			}

			NowDB nowDB = new NowDB(config, httpClient, gson);
			return nowDB;
		}

		public Builder client(OkHttpClient client) {
			httpClient = client;
			return this;
		}

		public Builder config(NowDBConfig config) {
			this.config = config;
			return this;
		}

		public Builder gson(Gson gson) {
			this.gson = gson;
			return this;
		}
	}
	
	private static Gson gson;

	private static OkHttpClient httpClient;

	public static Gson getGson() {
		return gson;
	}
	public static OkHttpClient getHttpClient() {
		return httpClient;
	}
	/**
	 * Membuat request builder dengan setelan cache control.
	 *
	 * @return request builder baru.
	 */
	public static Request.Builder requestBuilder() {
		CacheControl cacheControl = new CacheControl.Builder().noCache().build();
		return new Request.Builder().cacheControl(cacheControl);
	}
	private NowDBConfig config;

	private ObjectMapper mapper;

	private NowDB(NowDBConfig config, OkHttpClient httpClient, Gson gson) {
		this.config = config;
		NowDB.httpClient = httpClient;
		NowDB.gson = gson;

		mapper = new ObjectMapper(gson);
	}

	/**
	 * Membuat {@link Map} dari koleksi kemudian ditambahkan ke {@link FormBody}
	 * .
	 *
	 * @param data
	 * @return
	 */
	private FormBody buildFormBody(NowDBCollection data) {
		FormBody.Builder builder = new FormBody.Builder();

		Map<String, Object> map = convert(data);
		return ObjectMapper.addAll(builder, map).build();
	}
	
	/**
	 * Konversi {@link NowDBCollection} menjadi Map. setelan token, project dan
	 * app id ditambahkan ke dalam map.
	 *
	 * @param src
	 * @return
	 * @see #getConfig()
	 */
	public Map<String, Object> convert(NowDBCollection src) {
		Map<String, Object> map = mapper.convert(src);

		map.put(TOKEN, config.token);
		map.put(PROJECT, config.project);
		map.put(APP_ID, config.appId);
		return map;
	}
	
	public NowDBCall delete(NowDBQuery query) {
		String url = NowDBURI.delete(query, config);
		Request request = requestBuilder().url(url).delete().build();
		
		return new NowDBCall(httpClient.newCall(request));
	}
	
	/**
	 * Hapus semua data pada koleksi ini.
	 * @param collection
	 * @return
	 */
	public NowDBCall delete(String collection) {
		String url = NowDBURI.removeAll(collection, config);
		Request request = requestBuilder().url(url).delete().build();
		
		return new NowDBCall(httpClient.newCall(request));
	}

	/**
	 * @param collection nama koleksi
	 * @param id 
	 * @return
	 */
	public NowDBCall delete(String collection, String id) {
		String url = NowDBURI.removeId(id, collection, config);
		Request request = requestBuilder().url(url).delete().build();
		
		return new NowDBCall(httpClient.newCall(request));
	}

	/**
	 * Mendapatkan data.
	 * @param query
	 * @return
	 */
	public NowDBCall get(NowDBQuery query) {
		String url = NowDBURI.get(query, config);
		Request request = requestBuilder().url(url).get().build();
		return new NowDBCall(httpClient.newCall(request));
	}
	
	/**
	 * Mendapatkan beberapa entry dalam koleksi.
	 * @param collection
	 * @param offset
	 * @param limit
	 * @return
	 */
	public NowDBCall get(String collection, Integer offset, Integer limit) {
		String url = NowDBURI.selectAll(collection, offset, limit, config);
		Request request = requestBuilder().url(url).get().build();
		return new NowDBCall(httpClient.newCall(request));
	}
	
	/**
	 * Mendapatkan koleksi. 
	 * @param collection
	 * @param id
	 * @return
	 */
	public NowDBCall get(String collection, String id) {
		String url = NowDBURI.selectId(collection, id, config);

		Request request = requestBuilder().url(url).get().build();
		return new NowDBCall(httpClient.newCall(request));
	}
	
	public NowDBConfig getConfig() {
		return config;
	}

	/**
	 * Simpan koleksi baru pada server.
	 * @param collection
	 * @return
	 * @throws IOException
	 */
	public NowDBCall post(NowDBCollection collection) throws IOException {
		FormBody body = buildFormBody(collection);
		Request request = requestBuilder().url(SERVICE_URL + INSERT).post(body).build();
		return new NowDBCall(httpClient.newCall(request));
	}

	/**
	 * @param collection nama koleksi
	 * @param data
	 * @return
	 */
	public NowDBCall post(String collection, Map<String, Object> data) {
		data.put(COLLECTION, collection);
		data.put(TOKEN, config.token);
		data.put(PROJECT, config.project);
		data.put(APP_ID, config.appId);
		
		FormBody body = ObjectMapper.addAll(new FormBody.Builder(), data).build();
		Request request = requestBuilder().url(SERVICE_URL + INSERT).post(body).build();
		
		return new NowDBCall(httpClient.newCall(request));
	}

	/**
	 * Memperbaruhi koleksi.
	 * @param collection id harus sudah disetel.
	 * @return
	 * @see NowDBCollection#setId(String)
	 */
	public NowDBCall put(NowDBCollection collection) {
		if (collection.getId() == null || collection.getId().isEmpty()) {
			throw new IllegalStateException("id belum disetel");
		}
		FormBody body = buildFormBody(collection);
		Request request = requestBuilder().url(SERVICE_URL + UPDATE_ID).put(body).build();
		return new NowDBCall(httpClient.newCall(request));
	}

	/**
	 * @param query field yang dicari
	 * @param data data baru
	 * @return
	 */
	public NowDBCall put(NowDBQuery query, Map<String, Object> data) {
		FormBody.Builder builder = config.writeTo(new FormBody.Builder());
		Operation operation = query.getConditionalSeparator().PUT();
		
		query.writeTo(builder);
		
		ClauseBuilder update = NowDBQuery.join(data, NowDBQuery.AND);
		builder.add(UPDATE_FIELD, update.getNameClause());
		builder.add(UPDATE_VALUE, update.getValueClause());
		
		Request request = requestBuilder().url(SERVICE_URL + operation).put(builder.build()).build();
		return new NowDBCall(httpClient.newCall(request));
	}

	/**
	 * Perbaruhi semua entry dalam koleksi dengan data yang diberikan.
	 * @param collection nama koleksi
	 * @param data 
	 * @return
	 */
	public NowDBCall put(String collection, Map<String, Object> data) {
		FormBody.Builder builder = config.writeTo(new FormBody.Builder());
		builder.add(COLLECTION, collection);
		
		ClauseBuilder join = NowDBQuery.join(data, NowDBQuery.AND);
		builder.add(UPDATE_FIELD, join.getNameClause());
		builder.add(UPDATE_VALUE, join.getValueClause());
		
		Request request = requestBuilder().url(SERVICE_URL + UPDATE_ALL).put(builder.build()).build();
		return new NowDBCall(httpClient.newCall(request));
	}
	
	/**
	 * Memperbaruhi koleksi pada id yang diberikan.
	 * @param collection
	 * @param id didapat ketika melakukan POST.
	 * @param data data baru
	 * @return
	 */
	public NowDBCall put(String collection, String id, Map<String, Object> data) {
		FormBody.Builder builder = config.writeTo(new FormBody.Builder());
		builder.add(COLLECTION, collection);
		builder.add(ID, id);
		
		FormBody body = ObjectMapper.addAll(builder, data).build();
		
		Request request = requestBuilder().url(SERVICE_URL + UPDATE_ID).put(body).build();
		return new NowDBCall(httpClient.newCall(request));
	}
	
	public NowDB setAppId(String appId) {
		config.appId = appId;
		return this;
	}
	
	public NowDB setConfig(NowDBConfig config) {
		this.config = config;
		return this;
	}
	
	public NowDB setProject(String project) {
		config.project = project;
		return this;
	}
	
	public NowDB setToken(String token) {
		config.token = token;
		return this;
	}

}
