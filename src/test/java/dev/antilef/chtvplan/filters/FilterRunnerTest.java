package dev.antilef.chtvplan.filters;

import dev.antilef.chtvplan.dto.TryToChangePlanRequest;
import dev.antilef.chtvplan.entity.ChangePlanResultFilter;
import dev.antilef.chtvplan.utils.ChangePlanResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class FilterRunnerTest {

    private FiltersRunner runner;

    @Test
    void testRunUntilNullFilterResult(){

        TryToChangePlanRequest request = new TryToChangePlanRequest();

        List<ChangePlanFilter> filters = new ArrayList<>();

        filters.add(new ChangePlanFilter() {
            @Override
            public ChangePlanResultFilter run(TryToChangePlanRequest requestDTO) {
                return null;
            }
        });


        runner = new FiltersRunner(filters);
        ChangePlanResultFilter result = runner.run(request);

        assertNull(result);

    }

    @Test
    void shouldPassOneAndFail(){
        TryToChangePlanRequest request = new TryToChangePlanRequest();

        List<ChangePlanFilter> filters = new ArrayList<>();

        filters.add(new ChangePlanFilter() {
            @Override
            public ChangePlanResultFilter run(TryToChangePlanRequest requestDTO) {
                return new ChangePlanResultFilter(ChangePlanResult.SUCCESS,"reason",null,"10000","10000","SUCESS");
            }
        });

        filters.add(new ChangePlanFilter() {
            @Override
            public ChangePlanResultFilter run(TryToChangePlanRequest requestDTO) {
                return null;
            }
        });


        runner = new FiltersRunner(filters);
        ChangePlanResultFilter result = runner.run(request);

        assertNull(result);
    }

}

