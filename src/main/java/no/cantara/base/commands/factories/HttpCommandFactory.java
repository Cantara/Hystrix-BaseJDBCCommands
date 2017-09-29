package no.cantara.base.commands.factories;

import com.netflix.hystrix.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpCommandFactory {
    private static final Logger LOG = LoggerFactory.getLogger(HttpCommandFactory.class);


    public <Command extends HystrixCommand> Command createCommand(Class<Command> targetClass) {
        try {
            Command command = targetClass.newInstance();

            return command;

        } catch (Exception exception) {
            LOG.error(String.format("Failed to create command %s", targetClass.getSimpleName()), exception);
            return null;
        }
    }

}
