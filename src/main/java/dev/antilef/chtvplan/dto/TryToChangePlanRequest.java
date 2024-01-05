package dev.antilef.chtvplan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TryToChangePlanRequest{
    private String accountId;
    private String emailContactAddress;
    private String mobileContactNumber;
    private String name;
    private String originalPlanId;
    private String requestedPlanId;
    private String rut;
    private String selectedLine;
    private String msisdn;
    private String originFixedCharge;
    private String secretInfo;

}
