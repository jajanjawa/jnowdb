package com.github.jajanjawa.jnowdb;

public class ClauseBuilder {

	private StringBuilder nameClause;
	private StringBuilder valueClause;
	private int size;

	public ClauseBuilder() {
		nameClause = new StringBuilder();
		valueClause = new StringBuilder();
		size = 0;
	}
	
	public int getSize() {
		return size;
	}

	public ClauseBuilder append(String name, Object value) {
		nameClause.append(name);
		if (value instanceof String) {
			valueClause.append((String) value);
		} else {
			valueClause.append(value.toString());
		}
		size++;
		return this;
	}

	public String getNameClause() {
		return nameClause.toString();
	}

	public String getValueClause() {
		return valueClause.toString();
	}
}
