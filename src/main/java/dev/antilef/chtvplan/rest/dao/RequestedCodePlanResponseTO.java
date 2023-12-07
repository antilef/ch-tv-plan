package dev.antilef.chtvplan.rest.dao;

public class RequestedCodePlanResponseTO {
    public String getCode(){
        return new CodePlanResponseTO().toString();
    }

    public boolean isValid() {
        return true;
    }
}
