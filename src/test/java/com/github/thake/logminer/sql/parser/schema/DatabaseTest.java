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

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 *
 * @author schwitters
 */
public class DatabaseTest {

    public DatabaseTest() {
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
    public void testDatabaseSimple() {
        String databaseName = "db1";
        Database database = new Database(databaseName);
        assertEquals(databaseName, database.getFullyQualifiedName());
    }

    @Test
    public void testDatabaseAndServer() {
        final Server server = new Server("SERVER", "INSTANCE");
        String databaseName = "db1";
        Database database = new Database(server, databaseName);
        assertEquals("[SERVER\\INSTANCE].db1", database.getFullyQualifiedName());
        assertSame(server, database.getServer());
        assertEquals(databaseName, database.getDatabaseName());
        assertEquals("[SERVER\\INSTANCE].db1", database.toString());
    }

    @Test
    public void testNullDatabaseAndServer() {
        final Server server = new Server("SERVER", "INSTANCE");
        Database database = new Database(server, null);
        assertEquals("[SERVER\\INSTANCE].", database.getFullyQualifiedName());
        assertSame(server, database.getServer());
    }

}
