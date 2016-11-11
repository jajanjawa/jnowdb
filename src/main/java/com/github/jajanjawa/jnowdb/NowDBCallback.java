package com.github.jajanjawa.jnowdb;

import java.io.IOException;

public interface NowDBCallback {
	
	public void onFailure(NowDBCall call, IOException e);
	
	public void onResponse(NowDBCall call, NowDBResponse response) throws IOException;

}
