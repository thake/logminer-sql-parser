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
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

/**
 * @author schwitters
 */
public class LogminerSqlParserExceptionTest {

    public LogminerSqlParserExceptionTest() {
    }

    @BeforeClass public static void setUpClass() {
    }

    @AfterClass public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of parseExpression method, of class CCJSqlParserUtil.
     */
    @Test public void testExceptionWithCause() {
        IllegalArgumentException arg1 = new IllegalArgumentException();
        LogminerSqlParserException ex1 = new LogminerSqlParserException("", arg1);
        assertSame(arg1, ex1.getCause());
    }

    @Test public void testExceptionPrintStacktrace() {
        IllegalArgumentException arg1 = new IllegalArgumentException("BRATKARTOFFEL");
        LogminerSqlParserException ex1 = new LogminerSqlParserException("", arg1);
        StringWriter sw = new StringWriter();
        ex1.printStackTrace(new PrintWriter(sw, true));
        assertTrue(sw.toString().contains("BRATKARTOFFEL"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ex1.printStackTrace(new PrintStream(bos, true));
        assertTrue(new String(bos.toByteArray(), StandardCharsets.UTF_8).contains("BRATKARTOFFEL"));

    }

    @Test public void testExceptionPrintStacktraceNoCause() {
        LogminerSqlParserException ex1 = new LogminerSqlParserException("", null);
        StringWriter sw = new StringWriter();
        ex1.printStackTrace(new PrintWriter(sw, true));
        assertFalse(sw.toString().contains("BRATKARTOFFEL"));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ex1.printStackTrace(new PrintStream(bos, true));
        assertFalse(new String(bos.toByteArray(), StandardCharsets.UTF_8).contains("BRATKARTOFFEL"));
    }

    @Test public void testExceptionDefaultContructorCauseInit() {
        LogminerSqlParserException ex1 = new LogminerSqlParserException();
        assertNull(ex1.getCause());
        ex1 = new LogminerSqlParserException((Throwable) null);
        assertNull(ex1.getCause());
    }

}
