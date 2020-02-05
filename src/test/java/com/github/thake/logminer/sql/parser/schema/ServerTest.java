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
import static org.junit.Assert.assertNull;

public class ServerTest {

    @Test public void testServerNameParsing() {
        final String serverName = "LOCALHOST";

        final String fullServerName = String.format("[%s]", serverName);
        final Server server = new Server(fullServerName);

        assertEquals(serverName, server.getServerName());
        assertEquals(fullServerName, server.toString());
    }

    @Test public void testServerNameAndInstanceParsing() {
        final String serverName = "LOCALHOST";
        final String serverInstanceName = "SQLSERVER";

        final String fullServerName = String.format("[%s\\%s]", serverName, serverInstanceName);
        final Server server = new Server(fullServerName);

        assertEquals(serverName, server.getServerName());
        assertEquals(serverInstanceName, server.getInstanceName());
        assertEquals(fullServerName, server.toString());

    }

    @Test public void testServerNameAndInstanceParsing2() {
        String simpleName = "LOCALHOST";
        final Server server = new Server(simpleName);
        assertEquals(simpleName, server.getFullyQualifiedName());
    }

    @Test public void testServerNameAndInstanceParsingNull() {
        final Server server = new Server(null);
        assertEquals("", server.getFullyQualifiedName());
    }

    @Test public void testServerNameAndInstancePassValues() {
        final Server server = new Server("SERVER", "INSTANCE");
        assertEquals("SERVER", server.getServerName());
        assertEquals("INSTANCE", server.getInstanceName());
        assertEquals(String.format("[%s\\%s]", "SERVER", "INSTANCE"), server.getFullyQualifiedName());
    }

    @Test public void testServerNameNull() {
        final Server server = new Server(null, "INSTANCE");
        assertNull(server.getServerName());
        assertEquals("INSTANCE", server.getInstanceName());
        assertEquals("", server.getFullyQualifiedName());
    }

    @Test public void testServerNameEmpty() {
        final Server server = new Server("", "INSTANCE");
        assertEquals("", server.getServerName());
        assertEquals("INSTANCE", server.getInstanceName());
        assertEquals("", server.getFullyQualifiedName());
    }

    @Test public void testInstanceNameNull() {
        final Server server = new Server("LOCALHOST", null);
        assertEquals("LOCALHOST", server.getServerName());
        assertNull(server.getInstanceName());
        assertEquals("[LOCALHOST]", server.getFullyQualifiedName());
    }

    @Test public void testInstanceNameEmpty() {
        final Server server = new Server("LOCALHOST", "");
        assertEquals("LOCALHOST", server.getServerName());
        assertEquals("", server.getInstanceName());
        assertEquals("[LOCALHOST]", server.getFullyQualifiedName());
    }
}
