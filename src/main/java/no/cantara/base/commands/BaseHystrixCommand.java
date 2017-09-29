package no.cantara.base.commands;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

public abstract class BaseHystrixCommand<Response> extends HystrixCommand<Response> {
    private final static int DEFAULT_TIMEOUT = 2000;

    protected BaseHystrixCommand(String groupKey) {
        this(groupKey, DEFAULT_TIMEOUT);
    }

    protected BaseHystrixCommand(String groupKey, int timeout) {
        super(initializeCommand(groupKey, timeout));
        HystrixRequestContext.initializeContext();
    }

    protected static HystrixCommand.Setter initializeCommand(String key, int timeout) {
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey(key);
        HystrixCommandProperties.Setter properties = HystrixCommandProperties.Setter()
            .withExecutionTimeoutInMilliseconds(timeout);

        return HystrixCommand.Setter
            .withGroupKey(groupKey)
            .andCommandPropertiesDefaults(properties);
    }
}
