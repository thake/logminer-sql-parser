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
 * @author Tobias Warneke (t.warneke@gmx.net)
 */
public class TableTest {

    @Test public void tableIndexException() {
        Table table = new Table();
        table.setName("bla");
        table.setDatabase(new Database(new Server("server", "instance"), "db"));
    }

    @Test
    public void tableSetDatabase() {
        Table table = new Table();
        table.setName("testtable");
        Database database = new Database("default");
        table.setDatabase(database);
        assertEquals("default..testtable", table.toString());
    }

}
