package dev.antilef.chtvplan.dto;

import dev.antilef.chtvplan.utils.ChangePlanResult;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TryChangePlanResponse {
    private String reason;
    private ChangePlanResult result;
    private BigDecimal ticketNumber;
}
