/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package com.github.thake.logminer.sql.parser.expression;

import com.github.thake.logminer.sql.parser.expression.operators.conditional.AndExpression;
import com.github.thake.logminer.sql.parser.expression.operators.relational.EqualsTo;
import com.github.thake.logminer.sql.parser.expression.operators.relational.IsNullExpression;
import com.github.thake.logminer.sql.parser.schema.Column;

public class ExpressionVisitorAdapter implements ExpressionVisitor {

    @Override public void visit(NullValue value) {

    }

    @Override public void visit(SignedExpression expr) {
        expr.getExpression().accept(this);
    }


    @Override
    public void visit(DoubleValue value) {

    }

    @Override
    public void visit(LongValue value) {

    }

    @Override
    public void visit(DateValue value) {

    }

    @Override
    public void visit(TimeValue value) {

    }

    @Override
    public void visit(TimestampValue value) {

    }

    @Override
    public void visit(Parenthesis parenthesis) {
        parenthesis.getExpression().accept(this);
    }

    @Override
    public void visit(StringValue value) {

    }


    @Override
    public void visit(AndExpression expr) {
        visitBinaryExpression(expr);
    }

    @Override
    public void visit(EqualsTo expr) {
        visitBinaryExpression(expr);
    }


    @Override
    public void visit(IsNullExpression expr) {
        expr.getLeftExpression().accept(this);
    }

    @Override
    public void visit(Column column) {

    }



    @Override
    public void visit(ExtractExpression expr) {
        expr.getExpression().accept(this);
    }




    protected void visitBinaryExpression(BinaryExpression expr) {
        expr.getLeftExpression().accept(this);
        expr.getRightExpression().accept(this);
    }


    @Override
    public void visit(NumericBind bind) {

    }


    @Override
    public void visit(HexValue hexValue) {

    }

    @Override
    public void visit(TimeKeyExpression timeKeyExpression) {

    }

    @Override
    public void visit(DateTimeLiteralExpression literal) {
    }


}
