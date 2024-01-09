package dev.antilef.chtvplan.filters;

import dev.antilef.chtvplan.dto.TryToChangePlanRequest;
import dev.antilef.chtvplan.entity.ChangePlanResultFilter;
import dev.antilef.chtvplan.utils.ChangePlanResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FilterRunnerTest {

    private FiltersRunner runner;

    @Test
    void testRunUntilFailedFilterResult(){

        TryToChangePlanRequest request = new TryToChangePlanRequest();

        List<ChangePlanFilter> filters = new ArrayList<>();

        filters.add(requestDTO -> new ChangePlanResultFilter(ChangePlanResult.FAILED,"reason","1010","10000","10000","SUCCESS"));


        runner = new FiltersRunner(filters);
        ChangePlanResultFilter result = runner.run(request);

        assertNotNull(result);
        assertEquals(ChangePlanResult.FAILED,result.getStatus());

    }

    @Test
    void shouldPassOneAndFail(){
        TryToChangePlanRequest request = new TryToChangePlanRequest();

        List<ChangePlanFilter> filters = new ArrayList<>();

        filters.add(requestDTO -> new ChangePlanResultFilter(ChangePlanResult.NEXT,"reason","1010","10000","10000","SUCCESS"));

        filters.add(requestDTO -> new ChangePlanResultFilter(ChangePlanResult.MANUAL,"reason","101","100","100","ERROR"));


        runner = new FiltersRunner(filters);
        ChangePlanResultFilter result = runner.run(request);

        assertNotNull(result);
        assertEquals(ChangePlanResult.MANUAL,result.getStatus());
    }

}

