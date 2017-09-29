package no.cantara.base.commands.sql;

public class SqlUpdateCommand extends SqlNamedParameterCommand<SqlUpdateCommand, Integer> {

    @Override
    protected Integer executeQuery() {
        return jdbcTemplate.update(sql, parameters);
    }
}
