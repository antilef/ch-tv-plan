package dev.antilef.chtvplan.filters;

import dev.antilef.chtvplan.dto.TryToChangePlanRequest;
import dev.antilef.chtvplan.entity.ChangePlanResultFilter;
import dev.antilef.chtvplan.repository.PropertiesRepository;
import dev.antilef.chtvplan.repository.TicketRepository;
import dev.antilef.chtvplan.utils.ChangePlanResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RetryChangeFilterTest {


    public RetryChangeFilter filter ;

    @Mock
    private PropertiesRepository propertiesRepository;
    @Mock
    private TicketRepository ticketRepository;

    @Test
    void hasRetry(){

        TryToChangePlanRequest request = new TryToChangePlanRequest();
        request.setAccountId("12312");

        lenient().when(propertiesRepository.getProperty("cpo.ERROR_INTENTOS"))
                .thenReturn("Error reintentos");

        when(propertiesRepository.getProperty("retry.days"))
                .thenReturn(String.valueOf(3));

        when(propertiesRepository.getProperty("retry.maxRetry"))
                .thenReturn(String.valueOf(Long.parseLong("1")));

        when(propertiesRepository.getProperty("retry.notLimitedRetry"))
                .thenReturn(String.valueOf(1));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        Date toDate = calendar.getTime();
        lenient().when(ticketRepository.countBetweenDate("12312", new Date(), toDate))
                .thenReturn(Long.parseLong("1"));

        filter = new RetryChangeFilter(propertiesRepository,ticketRepository);
        ChangePlanResultFilter result = filter.run(request);
        assertEquals(ChangePlanResult.NEXT,result.getStatus());

    }
}
