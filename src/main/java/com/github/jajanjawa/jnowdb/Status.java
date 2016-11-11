package com.github.jajanjawa.jnowdb;

import com.google.gson.annotations.SerializedName;

/**
 * Status untuk operasi remove, update, remove all
 * 
 * @author Irwan
 *
 */
public class Status {

	@SerializedName("message")
	private String message;
	@SerializedName("status")
	private Integer status;

	public String getMessage() {
		return message;
	}

	/**
	 * @return 1 = berhasil
	 */
	public Integer getStatus() {
		return status;
	}
	
	public boolean ok() {
		return getStatus() == 1;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("status: ").append(status).append(", ");
		builder.append("pesan: ").append(message).append('.');
		return builder.toString();
	}
}
