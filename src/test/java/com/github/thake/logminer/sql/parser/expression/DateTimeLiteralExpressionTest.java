/*-
 * #%L
 * Parser for SQL statements produced by oracle logminer
 * %%
 * Copyright (C) 2020 thake
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package com.github.thake.logminer.sql.parser.expression;

import com.github.thake.logminer.sql.parser.LogminerSqlParserException;
import com.github.thake.logminer.sql.parser.LogminerSqlParserUtil;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DateTimeLiteralExpressionTest {
    @Test public void testTimestampValue() throws LogminerSqlParserException {
        DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.of(2018, 01, 23, 12, 2, 3);
        String timestampStr = timestamp.format(timestampFormat);
        String input = "TIMESTAMP ' "+timestampStr+"'";
        Expression exp = LogminerSqlParserUtil.parseExpression(input);
        assertTrue(exp instanceof DateTimeLiteralExpression);
        DateTimeLiteralExpression dateTimeLiteral = (DateTimeLiteralExpression) exp;
        assertEquals(timestampStr, dateTimeLiteral.getValue());
        assertEquals(DateTimeLiteralExpression.DateTime.TIMESTAMP,dateTimeLiteral.getType());
    }
}
