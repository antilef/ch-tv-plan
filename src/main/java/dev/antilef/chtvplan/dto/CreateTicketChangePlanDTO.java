package dev.antilef.chtvplan.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateTicketChangePlanDTO {
    private String accountId;
    private String emailContactAddress;
    private String mobileContactNumber;
    private String name;
    private String originalPlanId;
    private String requestedPlanId;
    private String rut;
    private String selectedLine;

}
