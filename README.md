# Logminer SQL Parser

[![Build Status](https://travis-ci.com/thake/logminer-sql-parser.svg?branch=master)](https://travis-ci.com/thake/logminer-sql-parser)

Logminer SQL Parser is a performance trimmed SQL parser to read SQL produced by Oracle Logminer (SQL_UNDO and SQL_REDO columns of
 [V$LOGMNR_CONTENTS](https://docs.oracle.com/cd/B28359_01/server.111/b28320/dynviews_2033.htm]) ). Therefore only statements 
and formats that Oracle Logminer produces are supported. Logminer SQL parser IS NO general purpose SQL parser. 

Logminer SQL Parser is based on JSqlParser (https://github.com/JSQLParser). So if you need a general purpose SQL parser, please
consider JSqlParser.

## Supported Logminer Statements

Currently the SQL statements of the following Operation Types (OPERATION column) are supported:
- INSERT
- UPDATE
- DELETE

## License

Logminer SQL Parser is dual licensed under **LGPL V2.1** or **Apache Software License, Version 2.0**.