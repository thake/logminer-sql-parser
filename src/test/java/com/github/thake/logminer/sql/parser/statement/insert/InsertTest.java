/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package com.github.thake.logminer.sql.parser.statement.insert;

import com.github.thake.logminer.sql.parser.LogminerSqlParserException;
import com.github.thake.logminer.sql.parser.LogminerSqlParserUtil;
import com.github.thake.logminer.sql.parser.expression.DateTimeLiteralExpression;
import com.github.thake.logminer.sql.parser.expression.DoubleValue;
import com.github.thake.logminer.sql.parser.expression.LongValue;
import com.github.thake.logminer.sql.parser.expression.StringValue;
import com.github.thake.logminer.sql.parser.expression.operators.relational.ExpressionList;
import org.junit.Test;

import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InsertTest {

    @Test public void testRegularInsert() throws LogminerSqlParserException {
        String statement = "INSERT INTO mytable (col1, col2, col3) VALUES (TIMESTAMP '200030012', 'sadfsd', 234)";
        Insert insert = (Insert) LogminerSqlParserUtil.parse(new StringReader(statement));
        assertEquals("mytable", insert.getTable().getName());
        assertEquals(3, insert.getColumns().size());
        assertEquals("col1", insert.getColumns().get(0).getColumnName());
        assertEquals("col2", insert.getColumns().get(1).getColumnName());
        assertEquals("col3", insert.getColumns().get(2).getColumnName());
        assertEquals(3, ((ExpressionList) insert.getItemsList()).getExpressions().size());
        assertTrue(((ExpressionList) insert.getItemsList()).getExpressions().get(0) instanceof DateTimeLiteralExpression);
        assertEquals("sadfsd", ((StringValue) ((ExpressionList) insert.getItemsList()).getExpressions().get(1)).
                getValue());
        assertEquals(234, ((LongValue) ((ExpressionList) insert.getItemsList()).getExpressions().
                get(2)).getValue());
        assertEquals(statement, "" + insert);

        statement = "INSERT INTO myschema.mytable VALUES (1, 2, 2.3)";
        insert = (Insert) LogminerSqlParserUtil.parse(statement);
        assertEquals("myschema.mytable", insert.getTable().getFullyQualifiedName());
        assertEquals(3, ((ExpressionList) insert.getItemsList()).getExpressions().size());
        assertTrue(((ExpressionList) insert.getItemsList()).getExpressions().get(0) instanceof LongValue);
        assertEquals(2.3, ((DoubleValue) ((ExpressionList) insert.getItemsList()).getExpressions().
                get(2)).getValue(), 0.0);
        assertEquals(statement, "" + insert);

    }

    @Test public void testInsertWithKeywordValue() throws LogminerSqlParserException {
        String statement = "INSERT INTO mytable (col1) VALUES ('val1')";
        Insert insert = (Insert) LogminerSqlParserUtil.parse(statement);
        assertEquals("mytable", insert.getTable().getName());
        assertEquals(1, insert.getColumns().size());
        assertEquals("col1", insert.getColumns().get(0).getColumnName());
        assertEquals("val1", ((StringValue) ((ExpressionList) insert.getItemsList()).getExpressions().get(0)).
                getValue());
    }

}
