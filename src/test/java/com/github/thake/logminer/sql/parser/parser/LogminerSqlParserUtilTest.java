/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package com.github.thake.logminer.sql.parser.parser;

import com.github.thake.logminer.sql.parser.LogminerSqlParserException;
import com.github.thake.logminer.sql.parser.LogminerSqlParserUtil;
import com.github.thake.logminer.sql.parser.expression.Expression;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class LogminerSqlParserUtilTest {

    public LogminerSqlParserUtilTest() {
    }

    @BeforeClass public static void setUpClass() {
    }

    @AfterClass public static void tearDownClass() {
    }

    @Before public void setUp() {
    }

    @After public void tearDown() {
    }

    @Test public void testParseCondExpressionNonPartial() throws Exception {
        Expression result = LogminerSqlParserUtil.parseCondExpression("x=92 and y=29", false);
        assertEquals("x = 92 AND y = 29", result.toString());
    }

    @Test(expected = LogminerSqlParserException.class) public void testParseCondExpressionNonPartial2() throws Exception {
        Expression result = LogminerSqlParserUtil.parseCondExpression("x=92 lasd y=29", false);
        System.out.println(result.toString());
    }

    @Test public void testParseCondExpressionPartial2() throws Exception {
        Expression result = LogminerSqlParserUtil.parseCondExpression("x=92 lasd y=29", true);
        assertEquals("x = 92", result.toString());
    }

}
