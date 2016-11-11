package com.github.jajanjawa.jnowdb;

import static com.github.jajanjawa.jnowdb.Operation.REMOVE_ALL;
import static com.github.jajanjawa.jnowdb.Operation.REMOVE_ID;
import static com.github.jajanjawa.jnowdb.Operation.SELECT_ALL;
import static com.github.jajanjawa.jnowdb.Operation.SELECT_ID;

/**
 * Membuat url untuk tiap operasi.
 * 
 * @author Irwan
 *
 */
public class NowDBURI {

	public static String delete(NowDBQuery query, NowDBConfig config) {
		Operation operation = query.getConditionalSeparator().DELETE();
		String[] keys = operation.keys();
		ClauseBuilder whereClause = query.getWhereClause();
		
		String url = new NowDBURIBuilder().serviceURL().operation(operation).token(config.token)
				.project(config.project).collection(query.getCollection()).appId(config.appId)
				.append(keys[0], whereClause.getNameClause()).append(keys[1], whereClause.getValueClause()).build();

		return url;
	}

	public static String removeAll(String collection, NowDBConfig config) {
		String url = new NowDBURIBuilder().serviceURL().operation(REMOVE_ALL).token(config.token)
				.project(config.project).collection(collection).appId(config.appId).build();
		return url;
	}

	public static String removeId(String id, String collection, NowDBConfig config) {
		String url = new NowDBURIBuilder().serviceURL().operation(REMOVE_ID).token(config.token).project(config.project)
				.collection(collection).appId(config.appId).id(id).build();
		return url;
	}

	/**
	 * 
	 * @param collection
	 * @param offset
	 * @param limit
	 * @param config
	 * @return
	 */
	public static String selectAll(String collection, Integer offset, Integer limit, NowDBConfig config) {
		String url = new NowDBURIBuilder().serviceURL().operation(SELECT_ALL).token(config.token)
				.project(config.project).collection(collection).appId(config.appId).limit(limit).offset(offset).build();
		return url;
	}

	/**
	 * Membuat url untuk operasi GET.
	 * 
	 * @param query
	 * @param config
	 * @return
	 */
	public static String get(NowDBQuery query, NowDBConfig config) {
		Operation operation = query.getConditionalSeparator().GET();
		String[] keys = operation.keys();
		ClauseBuilder where = query.getWhereClause();

		String url = new NowDBURIBuilder().serviceURL().operation(operation).token(config.token).project(config.project)
				.collection(query.getCollection()).appId(config.appId).append(keys[0], where.getNameClause())
				.append(keys[1], where.getValueClause()).limit(query.getLimit()).offset(query.getOffset())
				.build();
		return url;
	}

	public static String selectId(String collection, String id, NowDBConfig config) {
		String url = new NowDBURIBuilder().serviceURL().operation(SELECT_ID).token(config.token).project(config.project)
				.collection(collection).appId(config.appId).id(id).build();
		return url;
	}

	private NowDBURI() {
	}

}
