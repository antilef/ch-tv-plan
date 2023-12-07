package dev.antilef.chtvplan.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;


@Setter
@Getter
public class RetrievePlanResponse {
    private HttpStatus code;
    private String message;
    private List<Plan> plans;
    private String currentPlanInfo;
    private String currentFixedCharge;
    private String planType;

    @Setter(AccessLevel.NONE)
    private String planActualDescription;

    public void setPlanActualDescription(String description, String prop){
        this.planActualDescription = description.concat(prop);
    }
}
