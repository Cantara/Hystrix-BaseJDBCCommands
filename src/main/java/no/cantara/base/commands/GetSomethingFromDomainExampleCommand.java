package no.cantara.base.commands;

import com.github.kevinsawicki.http.HttpRequest;
import no.cantara.base.commands.http.HttpResponse;

import java.net.MalformedURLException;
import java.util.UUID;

public class GetSomethingFromDomainExampleCommand extends DomainExampleBaseCommand<MyDomainContract> {
    private UUID firstParameter;
    private UUID secondparameter;


    public GetSomethingFromDomainExampleCommand withFallback(boolean fallback) {
        return (GetSomethingFromDomainExampleCommand) super.withFallback(fallback);
    }

    public GetSomethingFromDomainExampleCommand withConfiguration(DomainConfiguration configuration) {
        return (GetSomethingFromDomainExampleCommand) super.withConfiguration(configuration);
    }

    public GetSomethingFromDomainExampleCommand withUserId(UUID userId) {
        this.firstParameter = userId;
        return this;
    }

    public GetSomethingFromDomainExampleCommand withOwnerId(UUID ownerId) {
        this.secondparameter = ownerId;
        return this;
    }

    @Override
    protected HttpRequest getRequest() throws MalformedURLException {
        return null;
    }

    @Override
    protected MyDomainContract dealWithResponse(HttpResponse response) {
        return new MyDomainContract(firstParameter, populateMyDomainObject());
    }

    @Override
    protected MyDomainContract getFallback(){
        if (fallback){
            return new MyDomainContract(firstParameter, populateMyDomainObject());
        }
        return null;
    }

    private MyDomainObject populateMyDomainObject() {
        return new MyDomainObject();
    }
}
