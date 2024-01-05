package dev.antilef.chtvplan.filters;

import dev.antilef.chtvplan.dto.TryToChangePlanRequest;
import dev.antilef.chtvplan.entity.ChangePlanResultFilter;
import dev.antilef.chtvplan.entity.GuaranteedIncomeUser;
import dev.antilef.chtvplan.repository.GuaranteedRepository;
import dev.antilef.chtvplan.utils.ChangePlanResult;

import java.util.Optional;

public class InGuaranteedIncomeFilter implements ChangePlanFilter{

    private final GuaranteedRepository guaranteedRepository;

    public InGuaranteedIncomeFilter(GuaranteedRepository guaranteedRepository) {
        this.guaranteedRepository = guaranteedRepository;
    }

    @Override
    public ChangePlanResultFilter run(TryToChangePlanRequest requestDTO) {

        Optional<GuaranteedIncomeUser> userOptional = guaranteedRepository.findByRut(requestDTO.getRut());
        if(userOptional.isPresent()){
            return new ChangePlanResultFilter(ChangePlanResult.MANUAL,"MANUAL",null,null,null,"MANUAL");
        }

        return ChangePlanResultFilter.toNextFilter();
    }
}
