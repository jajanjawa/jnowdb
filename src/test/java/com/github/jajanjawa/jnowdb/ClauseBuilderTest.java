package com.github.jajanjawa.jnowdb;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class ClauseBuilderTest {

	private static ClauseBuilder builder;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		builder = new ClauseBuilder();
	}

	@Test
	public void testBuildClause() {
		builder.append("pertama", "bagus")
		.append("AND", "AND")
		.append("kedua", "luar biasa");

		String nameClause = builder.getNameClause();
		String valueClause = builder.getValueClause();

		assertEquals("pertamaANDkedua", nameClause);
		assertEquals("bagusANDluar biasa", valueClause);
	}

}
