package dev.antilef.chtvplan.rest;

import dev.antilef.chtvplan.entity.PlanType;
import dev.antilef.chtvplan.entity.ProductDetail;
import dev.antilef.chtvplan.entity.UserInfo;
import dev.antilef.chtvplan.rest.dao.CodePlanListTO;
import dev.antilef.chtvplan.rest.dao.CodePlanResponseTO;
import dev.antilef.chtvplan.rest.dao.RequestedCodePlanResponseTO;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class InformationGetter {
    public ProductDetail listProductAndServices(String rut, String numId) {
        return new ProductDetail();
    }

    public List<PlanType> retrievePlans(String accountNumber, String rut) {
        return Collections.emptyList();
    }

    public RequestedCodePlanResponseTO additionalContract(CodePlanListTO listaPlanesIn) {
        return new RequestedCodePlanResponseTO();
    }
    public CodePlanResponseTO getCodePlanResponseTO(){
        return new CodePlanResponseTO();
    }

    public UserInfo getUserInfo(String clientId, String channelSv) {
        return new UserInfo();
    }
}
