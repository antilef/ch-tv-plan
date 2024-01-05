package dev.antilef.chtvplan.filters;

import dev.antilef.chtvplan.dto.TryToChangePlanRequest;
import dev.antilef.chtvplan.entity.ChangePlanResultFilter;
import dev.antilef.chtvplan.repository.PropertiesRepository;
import dev.antilef.chtvplan.utils.ChangePlanResult;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class DebtFilter implements ChangePlanFilter{

    @Autowired
    private PropertiesRepository propertiesRepository;
    public DebtFilter(PropertiesRepository propertiesRepository){
        this.propertiesRepository = propertiesRepository;
    }


    @Override
    public ChangePlanResultFilter run(TryToChangePlanRequest requestDTO) {
        String secret = requestDTO.getSecretInfo();

        if (secret.trim().equals("true")) {
            String reason = propertiesRepository.getProperty("cpo.ERROR_DEBT");
            return new ChangePlanResultFilter(ChangePlanResult.FAILED, reason, null, null, null,
                    "DEBT");
        }

        return ChangePlanResultFilter.toNextFilter();
    }
}
