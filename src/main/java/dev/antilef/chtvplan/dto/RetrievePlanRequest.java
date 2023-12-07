package dev.antilef.chtvplan.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetrievePlanRequest {

    private String rut;

    private String numId;
    private String originFixedCharge;
    private String clientId;

}
