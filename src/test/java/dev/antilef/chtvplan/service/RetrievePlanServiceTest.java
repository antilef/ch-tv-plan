package dev.antilef.chtvplan.service;

import dev.antilef.chtvplan.dto.RetrievePlanRequest;
import dev.antilef.chtvplan.dto.RetrievePlanResponse;
import dev.antilef.chtvplan.entity.ProductDetail;
import dev.antilef.chtvplan.entity.UserInfo;
import dev.antilef.chtvplan.exception.ProductDetailNotFoundException;
import dev.antilef.chtvplan.repository.PropertiesRepository;
import dev.antilef.chtvplan.rest.InformationGetter;
import dev.antilef.chtvplan.rest.dao.CodePlanListTO;
import dev.antilef.chtvplan.rest.dao.PlanREST;
import dev.antilef.chtvplan.rest.dao.RequestedCodePlanResponseTO;
import dev.antilef.chtvplan.utils.Constant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RetrievePlanServiceTest {

    @Mock
    private InformationGetter informationGetter;

    @Mock
    private PropertiesRepository propertiesRepository;

    private RetrievePlanService service;

    private RetrievePlanRequest request;

    @BeforeEach
    void init(){
        request = new RetrievePlanRequest();
        request.setRut("19862480-2");
        request.setNumId("19862480");
        request.setClientId("987654564645");
    }

    @Test
    void shouldFailFromNullProductDetail() {

        when(informationGetter.listProductAndServices(request.getRut(),request.getNumId())).thenReturn(
                null
        );
        service = new RetrievePlanService( informationGetter, null, null);


        Exception exception = Assertions.assertThrows(ProductDetailNotFoundException.class,() -> service.retrievePlans(request));
        Assertions.assertTrue(exception.getMessage().contains("The product was not found"));
    }

    @Test
    void shouldReturnEmptyListOfPlans(){

        ProductDetail pd = new ProductDetail();
        pd.setAccountNumber("0089734894");
        pd.setDescription("Random Description ");

        when(informationGetter.listProductAndServices(request.getRut(),request.getNumId())).thenReturn(
                pd
        );

        when(propertiesRepository.getProperty("ipc.cargo.fijo")).thenReturn(
                "Random Property"
        );

        RequestedCodePlanResponseTO contract = new RequestedCodePlanResponseTO();


        when(informationGetter.additionalContract(pd.getPlanId())).thenReturn(
                contract
        );

        service = new RetrievePlanService( informationGetter, propertiesRepository, null);
        RetrievePlanResponse response = service.retrievePlans(request);
        Assertions.assertIterableEquals(Collections.emptyList(), response.getPlans());;
    }

    @Test
    void shouldReturnExactAccountInAccountTypeInfo(){

        ProductDetail pd = new ProductDetail();
        pd.setAccountNumber("0089734894");
        pd.setDescription("Random Description ");

        when(informationGetter.listProductAndServices(request.getRut(),request.getNumId())).thenReturn(
                pd
        );

        when(propertiesRepository.getProperty("ipc.cargo.fijo")).thenReturn(
                "Random Property"
        );

        RequestedCodePlanResponseTO contract = new RequestedCodePlanResponseTO();


        when(informationGetter.additionalContract(pd.getPlanId())).thenReturn(
                contract
        );

        UserInfo fakeUserInfo = new UserInfo();
        fakeUserInfo.setAccountType("4");
        when(informationGetter.getUserInfo(request.getClientId(), Constant.CHANNEL_SV)).thenReturn(
                fakeUserInfo
        );

        service = new RetrievePlanService( informationGetter, propertiesRepository, null);
        RetrievePlanResponse response = service.retrievePlans(request);
        Assertions.assertIterableEquals(Collections.emptyList(), response.getPlans());
        Assertions.assertEquals("ExactAccount",response.getPlanType());
    }

    @Test
    void shouldReturnPostBillingInAccountTypeInfo(){

        ProductDetail pd = new ProductDetail();
        pd.setAccountNumber("0089734894");
        pd.setDescription("Random Description ");

        when(informationGetter.listProductAndServices(request.getRut(),request.getNumId())).thenReturn(
                pd
        );

        when(propertiesRepository.getProperty("ipc.cargo.fijo")).thenReturn(
                "Random Property"
        );

        RequestedCodePlanResponseTO contract = new RequestedCodePlanResponseTO();


        when(informationGetter.additionalContract(pd.getPlanId())).thenReturn(
                contract
        );

        UserInfo fakeUserInfo = new UserInfo();
        fakeUserInfo.setAccountType("1");
        when(informationGetter.getUserInfo(request.getClientId(), Constant.CHANNEL_SV)).thenReturn(
                fakeUserInfo
        );

        service = new RetrievePlanService( informationGetter, propertiesRepository, null);
        RetrievePlanResponse response = service.retrievePlans(request);
        Assertions.assertIterableEquals(Collections.emptyList(), response.getPlans());
        Assertions.assertEquals("PostBilling",response.getPlanType());
    }
}
