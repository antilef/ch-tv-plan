package dev.antilef.chtvplan.filters;

import dev.antilef.chtvplan.dto.TryToChangePlanRequest;
import dev.antilef.chtvplan.entity.ChangePlanResultFilter;
import dev.antilef.chtvplan.repository.PropertiesRepository;
import dev.antilef.chtvplan.utils.ChangePlanResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DebtFilterTest {

    @Mock
    private PropertiesRepository propertiesRepository;

    private DebtFilter debtFilter ;

    @Test
    void testWithDebt(){

        when(propertiesRepository.getProperty("cpo.ERROR_DEBT"))
                .thenReturn("ERROR POR DEUDA");

        TryToChangePlanRequest request = new TryToChangePlanRequest();
        request.setSecretInfo("true");

        debtFilter = new DebtFilter(propertiesRepository);
        ChangePlanResultFilter result = debtFilter.run(request);
        assertNotNull(result);
        assertEquals(ChangePlanResult.FAILED,result.getStatus() );
        assertNotEquals(ChangePlanResult.SUCCESS,result.getStatus());

    }

    @Test
    void testWithoutDebt(){
        lenient().when(propertiesRepository.getProperty("cpo.ERROR_DEBT"))
                .thenReturn("ERROR POR DEUDA");

        TryToChangePlanRequest request = new TryToChangePlanRequest();
        request.setSecretInfo("false");

        debtFilter = new DebtFilter(propertiesRepository);
        ChangePlanResultFilter result = debtFilter.run(request);
        assertEquals(ChangePlanResult.NEXT,result.getStatus());
        assertNotEquals(ChangePlanResult.SUCCESS,result.getStatus());
    }
}
