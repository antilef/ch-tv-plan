package dev.antilef.chtvplan.entity;

import dev.antilef.chtvplan.utils.ChangePlanResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePlanResultFilter {


    private ChangePlanResult status;
    private String reason;
    private String cbpId;
    private String fixedChargeOrigin;
    private String fixedChargeDestiny;
    private String shortReason;

    public static ChangePlanResultFilter toNextFilter(){
        return new ChangePlanResultFilter(ChangePlanResult.NEXT,null,null,null,null,null);
    }

}
