package no.cantara.base.commands.sql;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class SqlQueryCommand<ReturnType> extends SqlNamedParameterCommand<SqlQueryCommand<ReturnType>, List<ReturnType>> {
    private RowMapper<ReturnType> rowMapper;

    public SqlQueryCommand(RowMapper<ReturnType> rowMapper) {
        this.rowMapper = rowMapper;
    }

    @Override
    protected List<ReturnType> executeQuery() {
        return jdbcTemplate.query(sql, parameters, rowMapper);
    }
}
