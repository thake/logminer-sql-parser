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

import com.github.thake.logminer.sql.parser.expression.Expression;
import com.github.thake.logminer.sql.parser.schema.Table;
import com.github.thake.logminer.sql.parser.statement.Statement;
import com.github.thake.logminer.sql.parser.statement.StatementVisitor;

public class Delete implements Statement {

    private Table table;

    private Expression where;

    @Override public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table name) {
        table = name;
    }

    public Expression getWhere() {
        return where;
    }

    public void setWhere(Expression expression) {
        where = expression;
    }

    @Override public String toString() {
        StringBuilder b = new StringBuilder("DELETE");

        b.append(" FROM ");
        b.append(table);

        if (where != null) {
            b.append(" WHERE ").append(where);
        }
        return b.toString();
    }
}
