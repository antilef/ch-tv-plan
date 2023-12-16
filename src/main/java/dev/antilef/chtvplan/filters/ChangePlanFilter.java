package dev.antilef.chtvplan.filters;

import dev.antilef.chtvplan.dto.TryToChangePlanRequest;
import dev.antilef.chtvplan.entity.ChangePlanResultFilter;

public interface ChangePlanFilter {



    ChangePlanResultFilter run(TryToChangePlanRequest requestDTO);
}
