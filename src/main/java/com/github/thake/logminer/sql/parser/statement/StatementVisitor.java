/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package com.github.thake.logminer.sql.parser.statement;

import com.github.thake.logminer.sql.parser.statement.delete.Delete;
import com.github.thake.logminer.sql.parser.statement.insert.Insert;
import com.github.thake.logminer.sql.parser.statement.update.Update;

public interface StatementVisitor {

    void visit(Delete delete);

    void visit(Update update);

    void visit(Insert insert);

}
