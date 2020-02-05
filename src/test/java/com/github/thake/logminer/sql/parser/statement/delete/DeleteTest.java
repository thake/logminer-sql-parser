/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package com.github.thake.logminer.sql.parser.statement.delete;

import com.github.thake.logminer.sql.parser.LogminerSqlParserException;
import com.github.thake.logminer.sql.parser.LogminerSqlParserUtil;
import com.github.thake.logminer.sql.parser.expression.ExpressionVisitorAdapter;
import com.github.thake.logminer.sql.parser.expression.LongValue;
import com.github.thake.logminer.sql.parser.expression.operators.relational.EqualsTo;
import com.github.thake.logminer.sql.parser.schema.Column;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeleteTest {

    @Test public void testDelete() throws LogminerSqlParserException {
        String statement = "DELETE FROM mytable WHERE col = 9";

        Delete delete = (Delete) LogminerSqlParserUtil.parse(statement);
        assertEquals("mytable", delete.getTable().getName());
        assertEquals(statement, "" + delete);
    }

    @Test public void testDeleteWhereProblem1() throws LogminerSqlParserException {
        String stmt = "DELETE FROM \"tablename\" WHERE a = 2 AND b = 1";
        Delete delete = (Delete) LogminerSqlParserUtil.parse(stmt);
        assertEquals("\"tablename\"", delete.getTable().getName());
        assertNotNull(delete.getWhere());
        CollectingVisitor collVisitor = new CollectingVisitor();
        delete.getWhere().accept(collVisitor);
        assertEquals(2, collVisitor.equalStmts.size());
        EqualsTo firstStmt = collVisitor.equalStmts.get(0);
        assertTrue(firstStmt.getLeftExpression() instanceof Column);
        assertTrue(firstStmt.getRightExpression() instanceof LongValue);
        assertEquals("a", firstStmt.getLeftExpression().toString());
        assertEquals(2, ((LongValue) firstStmt.getRightExpression()).getValue());
        EqualsTo secondStmt = collVisitor.equalStmts.get(1);
        assertTrue(secondStmt.getLeftExpression() instanceof Column);
        assertTrue(secondStmt.getRightExpression() instanceof LongValue);
        assertEquals("b", secondStmt.getLeftExpression().toString());
        assertEquals(1, ((LongValue) secondStmt.getRightExpression()).getValue());

    }

    class CollectingVisitor extends ExpressionVisitorAdapter {
        List<EqualsTo> equalStmts = new ArrayList<>();

        @Override public void visit(EqualsTo expr) {
            equalStmts.add(expr);
        }
    }

}
