/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package com.github.thake.logminer.sql.parser.statement.update;

import com.github.thake.logminer.sql.parser.LogminerSqlParserException;
import com.github.thake.logminer.sql.parser.LogminerSqlParserUtil;
import com.github.thake.logminer.sql.parser.expression.LongValue;
import com.github.thake.logminer.sql.parser.expression.StringValue;
import com.github.thake.logminer.sql.parser.expression.operators.relational.EqualsTo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UpdateTest {

    @Test public void testUpdate() throws LogminerSqlParserException {
        String statement = "UPDATE mytable set col1='as', col2='def', col3=565 Where o = 3";
        Update update = (Update) LogminerSqlParserUtil.parse(statement);
        assertEquals("mytable", update.getTable().toString());
        assertEquals(3, update.getColumns().size());
        assertEquals("col1", update.getColumns().get(0).getColumnName());
        assertEquals("col2", update.getColumns().get(1).getColumnName());
        assertEquals("col3", update.getColumns().get(2).getColumnName());
        assertEquals("as", ((StringValue) update.getExpressions().get(0)).getValue());
        assertEquals("def", ((StringValue) update.getExpressions().get(1)).getValue());
        assertEquals(565, ((LongValue) update.getExpressions().get(2)).getValue());

        assertTrue(update.getWhere() instanceof EqualsTo);
    }





}
