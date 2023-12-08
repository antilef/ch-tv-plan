package dev.antilef.chtvplan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDestinyPlanRequest {
    private String fixedCharge;
    private String name;
}
