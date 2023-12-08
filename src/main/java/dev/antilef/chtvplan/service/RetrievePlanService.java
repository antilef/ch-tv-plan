package dev.antilef.chtvplan.service;

import dev.antilef.chtvplan.dto.RetrievePlanRequest;
import dev.antilef.chtvplan.dto.RetrievePlanResponse;
import dev.antilef.chtvplan.entity.DestinyPlan;
import dev.antilef.chtvplan.entity.ProductDetail;
import dev.antilef.chtvplan.entity.UserInfo;
import dev.antilef.chtvplan.exception.ProductDetailNotFoundException;
import dev.antilef.chtvplan.repository.DestinyPlanRepository;
import dev.antilef.chtvplan.repository.PropertiesRepository;
import dev.antilef.chtvplan.rest.InformationGetter;
import dev.antilef.chtvplan.rest.dao.RequestedCodePlanResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import dev.antilef.chtvplan.utils.Constant;
import dev.antilef.chtvplan.entity.PlanType;
import dev.antilef.chtvplan.dto.Plan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RetrievePlanService {

    @Autowired
    private final InformationGetter informationGetter;

    @Autowired
    private final PropertiesRepository propertiesRepository;

    @Autowired
    private final DestinyPlanRepository destinyPlanRepository;

    public RetrievePlanService(InformationGetter informationGetter, PropertiesRepository propertiesRepository, DestinyPlanRepository destinyPlanRepository) {
        this.informationGetter = informationGetter;
        this.propertiesRepository = propertiesRepository;
        this.destinyPlanRepository = destinyPlanRepository;
    }

    public RetrievePlanResponse retrievePlans(RetrievePlanRequest request) throws ProductDetailNotFoundException {

        RetrievePlanResponse response = new RetrievePlanResponse();
        ProductDetail productDetail = informationGetter.listProductAndServices(request.getRut(), request.getNumId());

        if(null == productDetail){
            throw new ProductDetailNotFoundException("The product was not found");
        }

        List<PlanType> planTypeList = informationGetter.retrievePlans(
                productDetail.getAccountNumber(), request.getRut());

        List<Plan> planDTOList = getPlans(planTypeList);

        response.setPlans(planDTOList);

        String property = propertiesRepository.getProperty("ipc.cargo.fijo");
        response.setPlanActualDescription(productDetail
                .getDescription(), property);

        RequestedCodePlanResponseTO contract = informationGetter.additionalContract(productDetail.getPlanId());

        if (contract.isValid()) {
            String code = contract.getCode();
            response.setCurrentPlanInfo(code);
        }

        response.setCurrentFixedCharge(request.getOriginFixedCharge());

        UserInfo userInfo1 = informationGetter.getUserInfo(request.getClientId(),Constant.CHANNEL_SV);
        if(userInfo1 != null){
            String accountType = userInfo1.getAccountType();
            if (accountType.equals("2") || accountType.equals("3") || accountType.equals("4")) {
                response.setPlanType("ExactAccount");
            } else {
                response.setPlanType("PostBilling");
            }
        }
        response.setCode(HttpStatus.OK);
        response.setMessage(Constant.MESSAGE_DEFAULT_SUCCESS);

        return response;

    }


    public  List<Plan> getPlans(List<PlanType> planTypeList) {
        List<Plan> planDTOList = new ArrayList<>();

        if(planTypeList == null || planTypeList.isEmpty()){
            return Collections.emptyList();
        }

        Plan planDTO;
        for (PlanType planType : planTypeList) {
            planDTO = new Plan();
            planDTO.setBannerUrl(planType.getPlanBannerURL());
            planDTO.setId(planType.getPlanID());
            planDTO.setName(planType.getPlanName());

            Long id = (long) planType.getPlanID();

            Optional<DestinyPlan> plan = destinyPlanRepository.findById(id);

            if (plan.isPresent()) {
                Integer fixedCharge = plan.get().getFixedCharge();
                planDTO.setFixedCharge(fixedCharge);

            }
            planDTOList.add(planDTO);

        }
        return planDTOList;
    }
}
