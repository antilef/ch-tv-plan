package dev.antilef.chtvplan.config;

import dev.antilef.chtvplan.filters.ChangePlanFilter;
import dev.antilef.chtvplan.filters.DebtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class FiltersConfiguration {

    @Bean
    public List<ChangePlanFilter> filters() {

        ChangePlanFilter debtFilter = new DebtFilter();
        return Arrays.asList(debtFilter );

    }
}
