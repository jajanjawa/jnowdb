package com.github.jajanjawa.jnowdb;

import static com.github.jajanjawa.jnowdb.NowDBProperty.COLLECTION;

import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.FormBody;
import okhttp3.FormBody.Builder;

/**
 * Konversi {@link Object} yang extend {@link NowDBCollection} menjadi
 * {@link Map}.
 * 
 * @author Irwan
 *
 */
public class ObjectMapper {

	/**
	 * entry dalam {@link Map} ditambahkan semua ke {@link FormBody.Builder}.
	 * 
	 * @param builder
	 * @param src
	 * @return builder yang mengandung entry dari src.
	 */
	public static Builder addAll(FormBody.Builder builder, Map<String, Object> src) {
		for (Entry<String, Object> entry : src.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();

			if (value == null) {
				throw new IllegalStateException(key + " belum disetel");
			}
			if (value instanceof String) {
				builder.add(key, (String) value);
			} else {
				builder.add(key, value.toString());
			}
		}
		return builder;
	}

	private Gson gson;

	public ObjectMapper() {
		gson = new GsonBuilder().setPrettyPrinting().create();
	}

	public ObjectMapper(Gson gson) {
		this.gson = gson;
	}

	/**
	 * Konversi {@link NowDBCollection} menjadi {@link Map}.
	 * {@link NowDBProperty#COLLECTION} akan diisi kedalam map hasil konversi.
	 * 
	 * @param src
	 *            object yang akan di konversi.
	 * @return {@link Map} yang sudah punya nama koleksi.
	 */
	public Map<String, Object> convert(NowDBCollection src) {
		String json = gson.toJson(src);

		@SuppressWarnings("unchecked")
		Map<String, Object> map = gson.fromJson(json, Map.class);
		map.put(COLLECTION, src.getCollectionName());
		return map;
	}
}
