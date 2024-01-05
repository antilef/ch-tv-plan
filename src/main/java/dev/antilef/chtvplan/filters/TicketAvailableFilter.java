package dev.antilef.chtvplan.filters;

import dev.antilef.chtvplan.dto.TryToChangePlanRequest;
import dev.antilef.chtvplan.entity.ChangePlanResultFilter;
import dev.antilef.chtvplan.entity.Ticket;
import dev.antilef.chtvplan.repository.PropertiesRepository;
import dev.antilef.chtvplan.repository.TicketRepository;
import dev.antilef.chtvplan.utils.ChangePlanResult;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
public class TicketAvailableFilter implements ChangePlanFilter{

    @Autowired
    private PropertiesRepository propertiesRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public ChangePlanResultFilter run(TryToChangePlanRequest requestDTO) {
        if (notAvailableCreateTicket(requestDTO)) {
            String reason = propertiesRepository.getProperty("OPEN_TICKET");
            return new ChangePlanResultFilter(ChangePlanResult.ERROR, reason, null, null, null,
                    "TICKET ACTIVATE");
        }
        return ChangePlanResultFilter.toNextFilter();
    }

    private boolean notAvailableCreateTicket(TryToChangePlanRequest request){
        Ticket ticket = ticketRepository.findLastTicketByUser(request.getAccountId());
        return ticket.isOpen();
    }
}
