package com.github.jajanjawa.jnowdb;

import static com.github.jajanjawa.jnowdb.NowDBProperty.COLLECTION;

import java.util.Map;
import java.util.Map.Entry;

import okhttp3.FormBody;

public class NowDBQuery {

	public static final String AND = "AND";
	public static final String OR = "OR";

	public static ClauseBuilder join(Map<String, Object> data, String separator) {
		ClauseBuilder builder = new ClauseBuilder();
		for (Entry<String, Object> entry : data.entrySet()) {
			if (builder.getSize() > 0) {
				builder.append(separator, separator);
			}
			builder.append(entry.getKey(), entry.getValue());
		}
		return builder;
	}

	protected ClauseBuilder whereClause;
	protected String collection;
	protected Integer limit = Integer.valueOf(10);
	protected Integer offset = Integer.valueOf(0);
	private ConditionalSeparator conditionalSeparator;

	public NowDBQuery() {
		conditionalSeparator = ConditionalSeparator.AND;
		whereClause = new ClauseBuilder();
	}

	public NowDBQuery(String collection) {
		this();
		this.collection = collection;
	}

	/**
	 * Separator untuk field yang keduanya digunakan untuk pencarian.
	 * @return
	 */
	public NowDBQuery and() {
		whereClause.append(AND, AND);
		conditionalSeparator = ConditionalSeparator.AND;
		return this;
	}

	public NowDBQuery or() {
		whereClause.append(OR, OR);

		conditionalSeparator = ConditionalSeparator.OR;
		return this;
	}

	public ConditionalSeparator getConditionalSeparator() {
		return conditionalSeparator;
	}

	/**
	 * Akan mencari pada koleksi ini.
	 * @param collection
	 * @return
	 */
	public NowDBQuery collection(String collection) {
		this.collection = collection;
		return this;
	}

	public String getCollection() {
		return collection;
	}

	/**
	 * where name='Budi'
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @see #and()
	 * @see #or()
	 */
	public NowDBQuery equal(String name, Object value) {
		whereClause.append(name, value);
		return this;
	}

	public Integer getLimit() {
		return limit;
	}

	public Integer getOffset() {
		return offset;
	}

	/**
	 * isi dalam field mengandung kata dari value.
	 * 
	 * @param name
	 *            nama field
	 * @param value
	 *            kata yang dicari
	 * @return
	 * @see Operation#SELECT_WHERE_LIKE
	 * @see Operation#UPDATE_WHERE_LIKE
	 */
	public NowDBQuery like(String name, Object value) {
		whereClause.append(name, value);

		conditionalSeparator = ConditionalSeparator.LIKE;
		return this;
	}
	
	public NowDBQuery out(String name, Object[] values) {
		StringBuilder builder = new StringBuilder();
		int index = 0;
		for (Object object : values) {
			if (index > 0) {
				builder.append('_');
			}
			builder.append(object);
			index++;
		}
		whereClause.append(name, builder);
		conditionalSeparator = ConditionalSeparator.OUT;
		return this;
	}

	/**
	 * @param name
	 * @param values
	 * @return
	 * @see Operation#SELECT_WHERE_IN
	 * @see Operation#UPDATE_WHERE_IN
	 */
	public NowDBQuery in(String name, Object[] values) {
		StringBuilder builder = new StringBuilder();
		int index = 0;
		for (Object object : values) {
			if (index > 0) {
				builder.append('_');
			}
			builder.append(object);
			index++;
		}
		whereClause.append(name, builder);
		conditionalSeparator = ConditionalSeparator.IN;
		return this;
	}

	/**
	 * @param limit
	 * @return
	 */
	public NowDBQuery limit(Integer limit) {
		this.limit = limit;
		return this;
	}

	public ClauseBuilder getWhereClause() {
		return whereClause;
	}

	public NowDBQuery offset(Integer offset) {
		this.offset = offset;
		return this;
	}
	
	/**
	 * PUT
	 * 
	 * @param builder
	 * @return
	 */
	public FormBody.Builder writeTo(FormBody.Builder builder) {
		builder.add(COLLECTION, collection);
		
		String[] keys = conditionalSeparator.PUT().keys();
		builder.add(keys[0], whereClause.getNameClause());
		builder.add(keys[1], whereClause.getValueClause());

		return builder;
	}
}
