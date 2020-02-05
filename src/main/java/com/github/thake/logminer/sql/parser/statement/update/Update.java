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

import com.github.thake.logminer.sql.parser.expression.Expression;
import com.github.thake.logminer.sql.parser.schema.Column;
import com.github.thake.logminer.sql.parser.schema.Table;
import com.github.thake.logminer.sql.parser.statement.Statement;
import com.github.thake.logminer.sql.parser.statement.StatementVisitor;

import java.util.List;

public class Update implements Statement {

    private Table table;
    private Expression where;
    private List<Column> columns;
    private List<Expression> expressions;
    private boolean useColumnsBrackets = true;
    private boolean useSelect = false;
    private boolean returningAllColumns = false;

    @Override public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Expression getWhere() {
        return where;
    }

    public void setWhere(Expression expression) {
        where = expression;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> list) {
        columns = list;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> list) {
        expressions = list;
    }

    public boolean isUseColumnsBrackets() {
        return useColumnsBrackets;
    }

    public void setUseColumnsBrackets(boolean useColumnsBrackets) {
        this.useColumnsBrackets = useColumnsBrackets;
    }

    public boolean isUseSelect() {
        return useSelect;
    }

    public void setUseSelect(boolean useSelect) {
        this.useSelect = useSelect;
    }

    public boolean isReturningAllColumns() {
        return returningAllColumns;
    }

    public void setReturningAllColumns(boolean returningAllColumns) {
        this.returningAllColumns = returningAllColumns;
    }

    @Override public String toString() {
        StringBuilder b = new StringBuilder("UPDATE ");
        b.append(table);
        b.append(" SET ");

        for (int i = 0; i < getColumns().size(); i++) {
            if (i != 0) {
                b.append(", ");
            }
            b.append(columns.get(i)).append(" = ");
            b.append(expressions.get(i));
        }

        if (where != null) {
            b.append(" WHERE ");
            b.append(where);
        }

        if (isReturningAllColumns()) {
            b.append(" RETURNING *");
        }

        return b.toString();
    }
}
