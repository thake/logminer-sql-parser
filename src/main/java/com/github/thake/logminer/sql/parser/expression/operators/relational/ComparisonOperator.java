/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package com.github.thake.logminer.sql.parser.expression.operators.relational;

import com.github.thake.logminer.sql.parser.expression.BinaryExpression;
import com.github.thake.logminer.sql.parser.expression.ExpressionVisitor;

public abstract class ComparisonOperator extends BinaryExpression {

    private final String operator;

    public ComparisonOperator(String operator) {
        this.operator = operator;
    }

    public String getStringExpression() {
        return operator;
    }

    public abstract void accept(ExpressionVisitor expressionVisitor);
}
