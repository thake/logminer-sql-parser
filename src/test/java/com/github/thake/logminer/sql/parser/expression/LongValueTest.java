/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package com.github.thake.logminer.sql.parser.expression;

import org.junit.*;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author tw
 */
public class LongValueTest {

    public LongValueTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSimpleNumber() {
        LongValue value = new LongValue("123");

        assertEquals("123", value.getStringValue());
        assertEquals(123L, value.getValue());
        assertEquals(new BigInteger("123"), value.getBigIntegerValue());
    }

    @Test
    public void testLargeNumber() {
        final String largeNumber = "20161114000000035001";
        LongValue value = new LongValue(largeNumber);

        assertEquals(largeNumber, value.getStringValue());
        try {
            value.getValue();
            fail("should not work");
        } catch (Exception e) {
            //expected to fail
        }
        assertEquals(new BigInteger(largeNumber), value.getBigIntegerValue());
    }
}
