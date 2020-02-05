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

public interface ExpressionVisitor {

    void visit(NullValue nullValue);

    void visit(SignedExpression signedExpression);

    void visit(DoubleValue doubleValue);

    void visit(LongValue longValue);

    void visit(HexValue hexValue);

    void visit(DateValue dateValue);

    void visit(TimeValue timeValue);

    void visit(TimestampValue timestampValue);

    void visit(Parenthesis parenthesis);

    void visit(StringValue stringValue);

    void visit(AndExpression andExpression);

    void visit(EqualsTo equalsTo);

    void visit(IsNullExpression isNullExpression);

    void visit(Column tableColumn);

    void visit(ExtractExpression eexpr);

    void visit(NumericBind bind);

    void visit(TimeKeyExpression timeKeyExpression);

    void visit(DateTimeLiteralExpression literal);

}
