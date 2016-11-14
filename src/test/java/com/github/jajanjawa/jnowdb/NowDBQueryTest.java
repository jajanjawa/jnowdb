package com.github.jajanjawa.jnowdb;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NowDBQueryTest {

	@Test
	public void testJoin() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "ini budi");
		map.put("attack", 412);
		
		ClauseBuilder join = NowDBQuery.join(map, NowDBQuery.AND);
		
		assertEquals("attackANDname", join.getNameClause());
		assertEquals("412ANDini budi", join.getValueClause());
	}

}
