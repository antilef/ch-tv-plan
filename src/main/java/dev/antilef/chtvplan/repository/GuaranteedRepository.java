package dev.antilef.chtvplan.repository;

import dev.antilef.chtvplan.entity.GuaranteedIncomeUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GuaranteedRepository extends CrudRepository<Long, GuaranteedIncomeUser> {

    @Query("SELECT u from GuaranteedIncomeUser u WHERE u.rut=:rut LIMIT 1")
    Optional<GuaranteedIncomeUser> findByRut(String rut);
}
