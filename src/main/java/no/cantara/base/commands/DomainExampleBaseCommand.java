package no.cantara.base.commands;

import no.cantara.base.commands.http.BaseHystrixHttpCommand;

public abstract class DomainExampleBaseCommand<Result> extends BaseHystrixHttpCommand<Result> {
    protected DomainConfiguration configuration;
    protected boolean fallback=false;

    public DomainExampleBaseCommand() {
        super("DomainBaseCommand", 2500);
    }

    public DomainExampleBaseCommand withConfiguration(DomainConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    public DomainExampleBaseCommand<Result> withFallback(boolean fallback) {
        this.fallback=fallback;
        return this;
    }
}
