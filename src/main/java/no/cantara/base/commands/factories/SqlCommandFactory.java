package no.cantara.base.commands.factories;

import no.cantara.base.commands.sql.BaseHystrixSqlCommand;
import no.cantara.base.commands.sql.SqlQueryCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.sql.DataSource;

public class SqlCommandFactory {
    private static final Logger LOG = LoggerFactory.getLogger(SqlCommandFactory.class);

    private SingleConnectionDataSource singleConnectionDataSource;
    private NamedParameterJdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate singleConnectionTemplate;

    public SqlCommandFactory(DataSource dataSource, SingleConnectionDataSource singleConnectionDataSource) {
        this.singleConnectionDataSource = singleConnectionDataSource;

        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.singleConnectionTemplate = new NamedParameterJdbcTemplate(singleConnectionDataSource);
    }

    public <Command extends BaseHystrixSqlCommand> Command createCommand(Class<Command> targetClass) {
        return createCommand(targetClass, jdbcTemplate);
    }

    public <Command extends BaseHystrixSqlCommand> Command createSingleConnectionCommand(Class<Command> targetClass) {
        return createCommand(targetClass, singleConnectionTemplate);
    }

    public <T> SqlQueryCommand<T> createCommand(RowMapper<T> rowMapper) {
        SqlQueryCommand<T> command = new SqlQueryCommand<T>(rowMapper);
        command.withJdbcTemplate(jdbcTemplate);

        return command;
    }


    private <Command extends BaseHystrixSqlCommand> Command createCommand(Class<Command> targetClass, NamedParameterJdbcTemplate jdbcTemplate) {
        try {
            Command command = targetClass.newInstance();
            command.withJdbcTemplate(jdbcTemplate);

            return command;
        } catch (Exception exception) {
            LOG.error("Failed to create SQL command", exception);
            return null;
        }
    }
}
