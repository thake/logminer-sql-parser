/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package com.github.thake.logminer.sql.parser;

import com.github.thake.logminer.sql.parser.expression.Expression;
import com.github.thake.logminer.sql.parser.statement.Statement;

import java.io.InputStream;
import java.io.Reader;
import java.util.function.Consumer;

/**
 * Toolfunctions to start and use JSqlParser.
 *
 * @author toben
 */
public final class LogminerSqlParserUtil {

    private LogminerSqlParserUtil() {
    }

    public static Statement parse(Reader statementReader) throws LogminerSqlParserException {
        LogminerSqlParser parser = new LogminerSqlParser(new StreamProvider(statementReader));
        try {
            return parser.Statement();
        } catch (Exception ex) {
            throw new LogminerSqlParserException(ex);
        }
    }

    public static Statement parse(String sql) throws LogminerSqlParserException {
        return parse(sql, null);
    }

    /**
     * Parses an sql statement while allowing via consumer to configure the used parser before.
     * <p>
     * For instance to activate SQLServer bracket quotation on could use:
     * <p>
     * {@code
     * CCJSqlParserUtil.parse("select * from [mytable]", parser -> parser.withSquareBracketQuotation(true));
     * }
     *
     * @param sql
     * @param consumer
     * @return
     * @throws LogminerSqlParserException
     */
    public static Statement parse(String sql, Consumer<LogminerSqlParser> consumer) throws LogminerSqlParserException {
        LogminerSqlParser parser = new LogminerSqlParser(new StringProvider(sql));
        if (consumer != null) {
            consumer.accept(parser);
        }
        try {
            return parser.Statement();
        } catch (Exception ex) {
            throw new LogminerSqlParserException(ex);
        }
    }

    public static Node parseAST(String sql) throws LogminerSqlParserException {
        LogminerSqlParser parser = new LogminerSqlParser(new StringProvider(sql));
        try {
            parser.Statement();
            return parser.jjtree.rootNode();
        } catch (Exception ex) {
            throw new LogminerSqlParserException(ex);
        }
    }

    public static Statement parse(InputStream is) throws LogminerSqlParserException {
        try {
            LogminerSqlParser parser = new LogminerSqlParser(new StreamProvider(is));
            return parser.Statement();
        } catch (Exception ex) {
            throw new LogminerSqlParserException(ex);
        }
    }

    public static Statement parse(InputStream is, String encoding) throws LogminerSqlParserException {
        try {
            LogminerSqlParser parser = new LogminerSqlParser(new StreamProvider(is, encoding));
            return parser.Statement();
        } catch (Exception ex) {
            throw new LogminerSqlParserException(ex);
        }
    }

    public static Expression parseExpression(String expression) throws LogminerSqlParserException {
        return parseExpression(expression, true);
    }

    public static Expression parseExpression(String expression, boolean allowPartialParse)
            throws LogminerSqlParserException {
        LogminerSqlParser parser = new LogminerSqlParser(new StringProvider(expression));
        try {
            Expression expr = parser.SimpleExpression();
            if (!allowPartialParse && parser.getNextToken().kind != LogminerSqlParserTokenManager.EOF) {
                throw new LogminerSqlParserException("could only parse partial expression " + expr.toString());
            }
            return expr;
        } catch (LogminerSqlParserException ex) {
            throw ex;
        } catch (ParseException ex) {
            throw new LogminerSqlParserException(ex);
        }
    }

    public static Expression parseCondExpression(String condExpr) throws LogminerSqlParserException {
        return parseCondExpression(condExpr, true);
    }

    /**
     * Parse an conditional expression. This is the expression after a where
     * clause.
     *
     * @param condExpr
     * @param allowPartialParse false: needs the whole string to be processed.
     * @return
     */
    public static Expression parseCondExpression(String condExpr, boolean allowPartialParse)
            throws LogminerSqlParserException {
        LogminerSqlParser parser = new LogminerSqlParser(new StringProvider(condExpr));
        try {
            Expression expr = parser.Expression();
            if (!allowPartialParse && parser.getNextToken().kind != LogminerSqlParserTokenManager.EOF) {
                throw new LogminerSqlParserException("could only parse partial expression " + expr.toString());
            }
            return expr;
        } catch (LogminerSqlParserException ex) {
            throw ex;
        } catch (ParseException ex) {
            throw new LogminerSqlParserException(ex);
        }
    }

    public static void streamStatements(StatementListener listener, InputStream is, String encoding)
            throws LogminerSqlParserException {
        try {
            LogminerSqlParser parser = new LogminerSqlParser(new StreamProvider(is, encoding));
            while (true) {
                Statement stmt = parser.SingleStatement();
                listener.accept(stmt);
                if (parser.getToken(1).kind == LogminerSqlParserTokenManager.ST_SEMICOLON) {
                    parser.getNextToken();
                }

                if (parser.getToken(1).kind == LogminerSqlParserTokenManager.EOF) {
                    break;
                }
            }
        } catch (Exception ex) {
            throw new LogminerSqlParserException(ex);
        }
    }

}
