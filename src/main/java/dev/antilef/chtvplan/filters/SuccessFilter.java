package dev.antilef.chtvplan.filters;

import dev.antilef.chtvplan.dto.TryToChangePlanRequest;
import dev.antilef.chtvplan.entity.ChangePlanResultFilter;
import dev.antilef.chtvplan.utils.ChangePlanResult;

public class SuccessFilter implements ChangePlanFilter{
    @Override
    public ChangePlanResultFilter run(TryToChangePlanRequest requestDTO) {
        return new ChangePlanResultFilter(ChangePlanResult.SUCCESS, "OK", null, null, null,
                "AUTOMATIC");
    }
}
