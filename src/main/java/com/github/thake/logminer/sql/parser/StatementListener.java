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

import com.github.thake.logminer.sql.parser.statement.Statement;

/**
 *
 * @author Tobias Warneke (t.warneke@gmx.net)
 */
public interface StatementListener {

    void accept(Statement statement);
}
