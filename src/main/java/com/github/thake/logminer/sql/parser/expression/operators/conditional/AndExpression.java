/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package com.github.thake.logminer.sql.parser.expression.operators.conditional;

import com.github.thake.logminer.sql.parser.expression.BinaryExpression;
import com.github.thake.logminer.sql.parser.expression.Expression;
import com.github.thake.logminer.sql.parser.expression.ExpressionVisitor;

public class AndExpression extends BinaryExpression {

    public AndExpression(Expression leftExpression, Expression rightExpression) {
        setLeftExpression(leftExpression);
        setRightExpression(rightExpression);
    }

    @Override public void accept(ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override public String getStringExpression() {
        return "AND";
    }
}
