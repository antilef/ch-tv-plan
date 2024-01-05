package dev.antilef.chtvplan.repository;

import dev.antilef.chtvplan.entity.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface TicketRepository extends CrudRepository<Long, Ticket> {

    @Query("SELECT count(t.id) FROM Ticket t WHERE t.accountId=:accountId and t.createdAt BETWEEN (:from,:to)")
    long countBetweenDate(String accountId, Date from, Date to);

    @Query("SELECT t FROM Ticket t WHERE t.accountId=:accountId ORDER BY (t.createdAt) DESC LIMIT 1 " )
    Ticket findLastTicketByUser(String accountId);
}
