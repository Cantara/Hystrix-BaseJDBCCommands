package no.cantara.base.commands.sql;

import java.util.Map;

public class SqlBatchUpdateCommand extends BaseHystrixSqlCommand<SqlBatchUpdateCommand, int[]> {
    protected Map<String, Object>[] parameters;

    public SqlBatchUpdateCommand withParameters(Map<String, Object>[] parameters) {
        this.parameters = parameters;
        return this;
    }

    @Override
    protected int[] executeQuery() {
        return jdbcTemplate.batchUpdate(sql, parameters);
    }

    @Override
    protected void validate() throws Exception {
        super.validate();

        boolean isParametersSet = parameters != null;
        if (!isParametersSet) {
            throw new Exception("Invalid state - No parameters is set");
        }
    }
}
