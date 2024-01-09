package dev.antilef.chtvplan.service;

import dev.antilef.chtvplan.dto.ChangePlanOperationResult;
import dev.antilef.chtvplan.dto.TryToChangePlanRequest;
import dev.antilef.chtvplan.dto.TryChangePlanResponse;
import dev.antilef.chtvplan.entity.ChangePlanResultFilter;
import dev.antilef.chtvplan.entity.DestinyPlan;
import dev.antilef.chtvplan.filters.FiltersRunner;
import dev.antilef.chtvplan.repository.DestinyPlanRepository;
import dev.antilef.chtvplan.repository.PropertiesRepository;
import dev.antilef.chtvplan.rest.InformationGetter;
import dev.antilef.chtvplan.utils.ChangePlanResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

public class ChangePlanService {

    private final Logger logger = LoggerFactory.getLogger(ChangePlanService.class);

    @Autowired
    private final DestinyPlanRepository destinyPlanRepository;

    @Autowired
    private final PropertiesRepository propertiesRepository;

    @Autowired
    private final FiltersRunner filtersRunner;

    @Autowired
    private final InformationGetter informationGetter;


    public ChangePlanService(DestinyPlanRepository destinyPlanRepository, PropertiesRepository propertiesRepository,
                             FiltersRunner filtersRunner, InformationGetter informationGetter){
        this.destinyPlanRepository = destinyPlanRepository;
        this.propertiesRepository = propertiesRepository;
        this.filtersRunner = filtersRunner;
        this.informationGetter = informationGetter;
    }
    public boolean hasOccurredErrorInFilter(ChangePlanResultFilter result){
        return result.getStatus() ==ChangePlanResult.ERROR;
    }

    public TryChangePlanResponse changePlanOrFail(TryToChangePlanRequest requestDTO) {

        ChangePlanResultFilter result = filterToChangePlan(requestDTO);

        ChangePlanOperationResult operationResult = null;
        TryChangePlanResponse response = new TryChangePlanResponse();

        if (hasOccurredErrorInFilter(result)) {
            response.setReason(result.getReason());
            response.setResult(result.getStatus());
            return response;
        }
        BigDecimal createdTicket = null;
        if (result.getStatus() != null && result.getStatus().equals(ChangePlanResult.SUCCESS)) {
            operationResult = changeAutomaticPlan(requestDTO, result);
            if (operationResult.isError()) {
                response.setResult(ChangePlanResult.ERROR);
                response.setReason("One service is not available");
                return response;
            }
            createdTicket = crearTicket(requestDTO, result);
            response.setTicketNumber(createdTicket);
        }


        response.setReason(result.getReason());
        response.setResult(result.getStatus());

        sendMail(requestDTO, result, createdTicket);


        return response;

    }


    private boolean hasErrorChangePlan(ChangePlanResultFilter result, ChangePlanOperationResult pe) {
        return false;
    }

    private BigDecimal crearTicket(TryToChangePlanRequest requestDTO, ChangePlanResultFilter result) {
        return new BigDecimal(100);
    }

    private ChangePlanResultFilter filterToChangePlan(TryToChangePlanRequest requestDTO) {

        ChangePlanResultFilter result = filtersRunner.run(requestDTO);


        String number = requestDTO.getMsisdn();
        String fixedChargeDestiny = null;
        Long requestId = Long.parseLong(requestDTO.getRequestedPlanId());
        Optional<DestinyPlan> destinyPlan = destinyPlanRepository.findById(requestId);
        if (destinyPlan.isEmpty()) {
            logger.error("The destiny plan not exist in offerTable, fixed charge is null");
        } else {
            fixedChargeDestiny = destinyPlan.get().getFixedCharge().toString();
        }

        String cbpId = informationGetter.identifyCaller(number);

        result.setCbpId(cbpId);
        result.setFixedChargeOrigin(requestDTO.getOriginFixedCharge());
        result.setFixedChargeDestiny(fixedChargeDestiny);

        return result;

    }


    public void sendMail(TryToChangePlanRequest requestDTO, ChangePlanResultFilter result, BigDecimal createdTicket){

    }

    public ChangePlanOperationResult changeAutomaticPlan(TryToChangePlanRequest request, ChangePlanResultFilter filterResult) {
        logger.info("Calling to webServices to change plan");
        return new ChangePlanOperationResult();
    }
}
