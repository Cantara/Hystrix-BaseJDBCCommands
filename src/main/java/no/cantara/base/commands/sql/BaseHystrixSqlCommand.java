package no.cantara.base.commands.sql;

import no.cantara.base.commands.BaseHystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class BaseHystrixSqlCommand<CommandType extends BaseHystrixSqlCommand, ReturnType> extends BaseHystrixCommand<ReturnType> {
    protected static final Logger LOG = LoggerFactory.getLogger(BaseHystrixSqlCommand.class);

    protected String sql;
    protected NamedParameterJdbcTemplate jdbcTemplate;

    public BaseHystrixSqlCommand() {
        super("sql", 3000);
    }

    public BaseHystrixSqlCommand withJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        return this;
    }

    @SuppressWarnings("unchecked")
    public CommandType withStatement(String sql) {
        this.sql = sql;
        return (CommandType) this;
    }

    @Override
    protected ReturnType run() throws Exception {
        try {
            validate();
            return executeQuery();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw e;
        }
    }

    protected abstract ReturnType executeQuery();

    protected void validate() throws Exception {
        boolean isSqlStatementSet = StringUtils.isNotBlank(sql);
        if (isSqlStatementSet) {
            throw new Exception("Invalid state - No SQL statement is set");
        } else {
            LOG.trace("SQL command executed with statement: \n{}", sql);
        }

        boolean isJdbcTemplateSet = jdbcTemplate != null;
        if (!isJdbcTemplateSet) {
            throw new Exception("Invalid state - No JDBC template is set");
        }
    }
}
