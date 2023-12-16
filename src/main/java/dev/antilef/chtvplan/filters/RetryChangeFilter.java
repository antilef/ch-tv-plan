package dev.antilef.chtvplan.filters;

import dev.antilef.chtvplan.dto.TryToChangePlanRequest;
import dev.antilef.chtvplan.entity.ChangePlanResultFilter;
import dev.antilef.chtvplan.repository.PropertiesRepository;
import dev.antilef.chtvplan.repository.TicketRepository;
import dev.antilef.chtvplan.utils.ChangePlanResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;

public class RetryChangeFilter implements ChangePlanFilter{

    @Autowired
    private PropertiesRepository propertiesRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public RetryChangeFilter(PropertiesRepository propertiesRepository,TicketRepository ticketRepository){
        this.propertiesRepository = propertiesRepository;
        this.ticketRepository = ticketRepository;
    }


    @Override
    public ChangePlanResultFilter run(TryToChangePlanRequest request) {

        if (!hasChangeRetry(request)) {
            String reason = propertiesRepository.getProperty("cpo.ERROR_INTENTOS");
            return new ChangePlanResultFilter(ChangePlanResult.FAILED, reason, null, null, null,
                    "REINTENTOS");
        }


        return ChangePlanResultFilter.toNextFilter();
    }

    private boolean hasChangeRetry(TryToChangePlanRequest request) {

        int days = Integer.parseInt( propertiesRepository.getProperty("retry.days"));
        long maxRetry = Long.parseLong(propertiesRepository.getProperty("retry.maxRetry"));
        int notLimitedRetry = Integer.parseInt(propertiesRepository.getProperty("retry.notLimitedRetry"));

        if( notLimitedRetry == 1 ) {
            return true;
        }
        Date fromDate = new Date();


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, days);
        Date toDate = calendar.getTime();

        long count = ticketRepository.countBetweenDate(request.getAccountId(),fromDate,toDate);

        return count <= maxRetry;
    }
}
