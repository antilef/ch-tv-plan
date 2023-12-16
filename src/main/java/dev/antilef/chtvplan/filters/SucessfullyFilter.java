package dev.antilef.chtvplan.filters;

import dev.antilef.chtvplan.dto.TryToChangePlanRequest;
import dev.antilef.chtvplan.entity.ChangePlanResultFilter;
import dev.antilef.chtvplan.utils.ChangePlanResult;

public class SucessfullyFilter implements ChangePlanFilter{
    @Override
    public ChangePlanResultFilter run(TryToChangePlanRequest requestDTO) {
        return new ChangePlanResultFilter(ChangePlanResult.SUCCESS,"SUCESS",null,null,null,"SUCESS");
    }
}
