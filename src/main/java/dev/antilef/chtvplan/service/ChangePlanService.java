package dev.antilef.chtvplan.service;

import dev.antilef.chtvplan.dto.ChangePlanOperationResult;
import dev.antilef.chtvplan.dto.TryToChangePlanRequest;
import dev.antilef.chtvplan.dto.TryChangePlanResponse;
import dev.antilef.chtvplan.dto.CreateTicketChangePlanDTO;
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

    private Logger logger = LoggerFactory.getLogger(ChangePlanService.class);

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
        if (result.getStatus() != null && result.getStatus().equals(ChangePlanResult.SUCCESS)) {
            operationResult = cambioPlanAutomatico(requestDTO, result);
        }


        BigDecimal createdTicket = null;

        if (!operationResult.isError()) {
            createdTicket = crearTicket(requestDTO, result);
            response.setTicketNumber(createdTicket);
        }


        response.setReason(result.getReason());
        response.setResult(result.getStatus());


        //sendMailCambioPlanAutomatico(requestDTO, result, createdTicket);


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
            logger.error("El plan destino no existe en la tabla cd06, cargo fijo es nulo");
        } else {
            fixedChargeDestiny = destinyPlan.get().getFixedCharge().toString();
        }

        String cbpId = informationGetter.identifyCaller(number);

        result.setCbpId(cbpId);
        result.setFixedChargeOrigin(requestDTO.getOriginFixedCharge());
        result.setFixedChargeDestiny(fixedChargeDestiny);

        return result;


//        if (!isAvailableCreateTicket(requestDTO)) {
//            reason = propertiesRepository.getProperty(CPO_TICKET_ABIERTO);
//            result = new ChangePlanResultFilter(ChangePlanResult.ERROR, reason, cbpID, fixedChargeOrigin, fixedChargeDestiny,
//                    "TICKET ACTIVO");
//            return result;
//        }
//
//        String isActiveChange = propertiesRepository.getProperty("cpo.activo");
//        if (!isActiveChange.equals(Constants.CPO_ACTIVE_VALID)) {
//            reason = propertiesRepository.getProperty(CPO_OK_MANUAL);
//            result = new ChangePlanResultFilter(ChangePlanResult.MANUAL, reason, cbpID, fixedChargeOrigin, fixedChargeDestiny,
//                    "CPO INACTIVO");
//            return result;
//        }


//        reason = propertiesRepository.getProperty("cpo.OK_AUTOMATICO");
//        result = new ChangePlanResultFilter(ChangePlanResult.SUCCESS, reason, cbpID, fixedChargeOrigin, fixedChargeDestiny,
//                "AUTOMATICO");
    }


    public void sendMailCambioPlanAutomatico(){

    }

    public ChangePlanOperationResult cambioPlanAutomatico(TryToChangePlanRequest request, ChangePlanResultFilter filterResult) {
        return new ChangePlanOperationResult();
//        logger.info("Inicia Cambio de plan automatico ReplaceOffer");
//        response.put(RESPUESTA, MSG_NOK);
//        try {
//            Cd105CpoPlanDestino planDestino = destinyPlanDAO.findBy(request.getRequestedPlan()).get(0);
//            String cBPID = filterResult.getCpbId();
//            String mSISDN = request.getMsisdn();
//            String targetOffer = planDestino.getPlanId();
//
//            ReplaceOfferResponse replaceOffer = wsGet.replaceOfferWS(cBPID, mSISDN, targetOffer);
//            logger.info("Respuesta ReplaceOffer");
//            if (logger.isInfoEnabled() && replaceOffer != null) {
//                logger.info(replaceOffer.toString());
//            }
//            String offerID = null;
//            if (replaceOffer != null && replaceOffer.getOfferInfo() != null) {
//                offerID = replaceOffer.getOfferInfo().getOfferID();
//            }
//
//            if (offerID != null && !offerID.isEmpty() && replaceOffer.getOfferInfo() != null) {
//                offerID = replaceOffer.getOfferInfo().getOfferID();
//                String descripcion = replaceOffer.getOfferInfo().getDescripcion();
//                String offerPrice = replaceOffer.getOfferInfo().getOfferPrice();
//                String mainBOPlan = replaceOffer.getOfferInfo().getMainBOPlan();
//                logger.info("offerID {} ", offerID);
//                logger.info("descripcion {} ", descripcion);
//                logger.info("offerPrice {}", offerPrice);
//                logger.info("mainBOPlan {} ", mainBOPlan);
//                response.put(RESPUESTA, MSG_OK);
//                response.put(MENSAJE, "el cambio HA IDO BIEN !!!");
//                logger.info("el cambio HA IDO BIEN !!!");
//            } else {
//                if (replaceOffer != null && replaceOffer.getResponseFailure() != null) {
//                    logger.info("getResponseFailure {}", replaceOffer.getResponseFailure());
//
//                }
//                String errorDescription = null;
//                if (replaceOffer != null && replaceOffer.getResponseFailure() != null) {
//                    errorDescription = replaceOffer.getResponseFailure().getErrorDescription();
//                }
//                logger.info(errorDescription);
//                if (errorDescription != null && errorDescription.equals("PRODUCT HAS A PENDING ORDER IN OMS DB")) {
//                    logger.info("Cambio de plan en curso ReplaceOffer");
//                    response.put(ERROR, propertieDAO.getPropertie(SUCVIRDASH, WEB, "cpo.CHANGE_PLAN_ENCURSO"));
//                } else {
//                    logger.info("Error al hacer el cambio de plan ReplaceOffer");
//                    response.put(ERROR, propertieDAO.getPropertie(SUCVIRDASH, WEB, "cpo.CHANGE_PLAN_ERROR"));
//                }
//                response.put(MENSAJE, "el cambio AQUI NO HA IDO BIEN " + errorDescription);
//                response.put("errorDescription", errorDescription);
//
//            }
//        } catch (Exception e) {
//            logger.info("Exception ReplaceOffer {} ", e.getMessage());
//            logger.error("Error [cambioPlanAutomatico]: {} ", e.getMessage());
//        }
//        logger.info("Fin Cambio de plan automatico ReplaceOffer");
//        return response;
    }
}
