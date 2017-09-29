package no.cantara.base.commands.sql;

import java.util.HashMap;
import java.util.Map;

public abstract class SqlNamedParameterCommand<CommandType extends SqlNamedParameterCommand, ReturnType> extends BaseHystrixSqlCommand<CommandType, ReturnType> {
    protected Map<String, Object> parameters;

    @SuppressWarnings("unchecked")
    public CommandType withParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
        return (CommandType) this;
    }

    @SuppressWarnings("unchecked")
    public CommandType addParameter(String key, Object value) {
        boolean isParametersSet = parameters != null;
        if (!isParametersSet) {
            parameters = new HashMap<String, Object>();
        }

        this.parameters.put(key, value);
        return (CommandType) this;
    }

    @Override
    protected void validate() throws Exception {
        super.validate();

        boolean isParametersSet = parameters != null;
        if (!isParametersSet) {
            throw new Exception("Invalid state - No parameters is set");
        } else {
            LOG.trace("SQL command executed with parameters: \n{}", parameters);
        }
    }
}
