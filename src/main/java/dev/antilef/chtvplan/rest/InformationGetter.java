package dev.antilef.chtvplan.rest;

import dev.antilef.chtvplan.entity.PlanType;
import dev.antilef.chtvplan.entity.ProductDetail;
import dev.antilef.chtvplan.entity.UserInfo;
import dev.antilef.chtvplan.rest.dao.RequestedCodePlanResponseTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InformationGetter {


    Logger logger = LoggerFactory.getLogger(InformationGetter.class);

    private static final String IMG_URL =
            "https://s3.amazonaws.com/imagenes-sellers-mercado-ripley/2022/02/13140332/M190-Mockup-Mousepad-Mickey-V2.jpg";

    public ProductDetail listProductAndServices(String rut, String numId) {
        logger.info("Calling listProductAndServices {}" ,rut);
        logger.info("Calling listProductAndServices {}" ,numId);
        ProductDetail pd = new ProductDetail();
        pd.setDescription("This is a description of a product");
        pd.setAccountNumber("123549");
        pd.setPlanId(739573);
        return pd;
    }

    public List<PlanType> retrievePlans(String accountNumber, String rut) {

        logger.info("Calling retrieve Plan {}" , accountNumber);
        logger.info("Calling retrieve Plan {}" , rut);
        List<PlanType> list = new ArrayList<>();

        for(int i = 52;i <= 56; i++){
            PlanType t= new PlanType();
            t.setPlanID(i);
            t.setPlanName("Plan Name "+i +" "+ i);
            t.setPlanBannerURL(IMG_URL);
            list.add(t);
        }

        return list;
    }

    public RequestedCodePlanResponseTO additionalContract(int planId) {
        logger.info("calling additionalContract {}", planId);
        return new RequestedCodePlanResponseTO();
    }


    public UserInfo getUserInfo(String clientId, String channelSv) {
        logger.info("calling getUserInfo {}", clientId);
        logger.info("calling getUserInfo {}", channelSv);
        UserInfo ui = new UserInfo();
        ui.setAccountType("4");
        return ui;
    }
}
