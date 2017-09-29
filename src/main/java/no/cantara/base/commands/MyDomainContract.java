package no.cantara.base.commands;


import java.util.UUID;

public class MyDomainContract {

    private UUID firstParameter;
    private MyDomainObject myDomainObject;

    public MyDomainContract(UUID firstParameter, MyDomainObject myDomainObject) {
        this.firstParameter = firstParameter;
        this.myDomainObject = myDomainObject;
    }

    public UUID getFirstParameter() {
        return firstParameter;
    }

    public MyDomainObject getMyDomainObject() {
        return myDomainObject;
    }
}
