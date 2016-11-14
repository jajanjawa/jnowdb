package com.github.jajanjawa.jnowdb;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ConditionalSeparatorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testOperationGET() {
		ConditionalSeparator and = ConditionalSeparator.AND;
		
		Operation operation = and.GET();
		
		assertEquals(Operation.SELECT_WHERE, operation);
	}

}
