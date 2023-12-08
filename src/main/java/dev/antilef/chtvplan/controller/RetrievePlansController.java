package dev.antilef.chtvplan.controller;

import dev.antilef.chtvplan.dto.RetrievePlanRequest;
import dev.antilef.chtvplan.dto.RetrievePlanResponse;
import dev.antilef.chtvplan.exception.ProductDetailNotFoundException;
import dev.antilef.chtvplan.service.RetrievePlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class RetrievePlansController {

    @Autowired
    private RetrievePlanService retrievePlanService;

    Logger logger = LoggerFactory.getLogger(RetrievePlansController.class);

    public RetrievePlansController(RetrievePlanService retrievePlanService) {
        this.retrievePlanService = retrievePlanService;
    }

    @GetMapping("/retrievePlans")
    public RetrievePlanResponse retrievePlanEntryPoint(@RequestBody RetrievePlanRequest request){
        RetrievePlanResponse response;
        try {
            response = this.retrievePlanService.retrievePlans(request);
        }catch(ProductDetailNotFoundException e){
            response = new RetrievePlanResponse();
            response.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            response.setMessage("Sorry,has occurred a problem, try later");

        }catch(Exception e){
            logger.info(e.getMessage());
            response = new RetrievePlanResponse();
            response.setCode(HttpStatus.NOT_FOUND);
            response.setMessage("Sorry, try later");
        }
        return response;
    }

}
