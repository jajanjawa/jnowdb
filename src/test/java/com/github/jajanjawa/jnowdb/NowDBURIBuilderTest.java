package com.github.jajanjawa.jnowdb;

import static com.github.jajanjawa.jnowdb.Operation.REMOVE_ID;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NowDBURIBuilderTest {

	private static Logger logger;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger = LoggerFactory.getLogger(NowDBURIBuilderTest.class);
	}

	@Test
	public void testBuild() {
		String url = new NowDBURIBuilder().serviceURL().operation(REMOVE_ID).token("ini.token").project("ini.proyek")
				.collection("online").appId("ini.appid").id("id.test").build();

		logger.info(url);
	}

}
