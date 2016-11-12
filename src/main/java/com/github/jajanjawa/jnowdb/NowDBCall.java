package com.github.jajanjawa.jnowdb;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 
 * @author irwantoro
 *
 */
public class NowDBCall {
	
	private Call call;

	public NowDBCall(Call call) {
		this.call = call;
	}
	
	/**
	 * Eksekusi dari {@link Thread} yang sama.
	 * @return
	 * @throws IOException
	 */
	public NowDBResponse execute() throws IOException {
		NowDBResponse response = new NowDBResponse(call.execute());
		
		return response;
	}

	public boolean isExecuted() {
		return call.isExecuted();
	}

	public boolean isCanceled() {
		return call.isCanceled();
	}

	public void cancel() {
		call.cancel();
	}

	/**
	 * Eksekusi pada {@link Thread} yang lain.
	 * @param responseCallback
	 */
	public void enqueue(NowDBCallback responseCallback) {
		Callback callback = new Callback() {

			@Override
			public void onFailure(Call call, IOException e) {
				responseCallback.onFailure(NowDBCall.this, e);
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				responseCallback.onResponse(NowDBCall.this, new NowDBResponse(response));
			}
			
		};
		call.enqueue(callback);
	}
	
	

}
