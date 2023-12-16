package dev.antilef.chtvplan.filters;

import dev.antilef.chtvplan.dto.TryToChangePlanRequest;
import dev.antilef.chtvplan.entity.ChangePlanResultFilter;
import dev.antilef.chtvplan.utils.ChangePlanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FiltersRunner {

    @Autowired
    private List<ChangePlanFilter> filters;
    public FiltersRunner(List<ChangePlanFilter> filters){
        this.filters = filters;
    }

    public ChangePlanResultFilter run(TryToChangePlanRequest requestDTO){
        ChangePlanResultFilter result = new DefaultFilterResult().run(requestDTO);
        for(ChangePlanFilter filter : filters){
            result = filter.run(requestDTO);
            if(result.getStatus() != ChangePlanResult.NEXT) {
                return result;
            }
        }
        return result;
    }

}

class DefaultFilterResult implements ChangePlanFilter{
    @Override
    public ChangePlanResultFilter run(TryToChangePlanRequest requestDTO) {
        return new ChangePlanResultFilter();
    }
}
