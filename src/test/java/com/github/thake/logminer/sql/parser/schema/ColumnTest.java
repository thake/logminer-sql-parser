/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package com.github.thake.logminer.sql.parser.schema;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 
 * @author tw
 */
public class ColumnTest {

    @Test
    public void testCheckNonFinalClass() {
        Column myColumn = new Column(null, "myColumn") {
            @Override
            public String toString() {
                return "anonymous class";
            }
                
        };
        assertEquals("anonymous class", myColumn.toString());
    }

}
