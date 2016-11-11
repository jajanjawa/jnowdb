package com.github.jajanjawa.jnowdb;

public enum ConditionalSeparator {

	NONE ,AND, OR, LIKE, IN, OUT;
	
	public Operation DELETE() {
		Operation operation = null;
		switch (this) {
		case AND:
			operation = Operation.REMOVE_WHERE;
			break;
		case OR:
			operation = Operation.REMOVE_OR_WHERE;
			break;
		case LIKE:
			operation = Operation.UPDATE_WHERE_LIKE;
			break;
		case IN:
			operation = Operation.UPDATE_WHERE_IN;
			break;
		case OUT:
			operation = Operation.UPDATE_WHERE_NOT_IN;
			break;
		default:
			break;
		}
		return operation;
	}
	
	public Operation PUT() {
		Operation operation = null;
		switch (this) {
		case AND:
			operation = Operation.UPDATE_WHERE;
			break;
		case OR:
			operation = Operation.UPDATE_OR_WHERE;
			break;
		case LIKE:
			operation = Operation.UPDATE_WHERE_LIKE;
			break;
		case IN:
			operation = Operation.UPDATE_WHERE_IN;
			break;
		case OUT:
			operation = Operation.UPDATE_WHERE_NOT_IN;
			break;
		default:
			break;
		}
		return operation;
	}
	
	public Operation GET() {
		Operation operation = null;
		switch (this) {
		case AND:
			operation = Operation.SELECT_WHERE;
			break;
		case OR:
			operation = Operation.SELECT_OR_WHERE;
			break;
		case LIKE:
			operation = Operation.SELECT_WHERE_LIKE;
			break;
		case IN:
			operation = Operation.SELECT_WHERE_IN;
			break;
		case OUT:
			operation = Operation.SELECT_WHERE_NOT_IN;
			break;
		default:
			break;
		}
		return operation;
	}
}
