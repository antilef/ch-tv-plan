package dev.antilef.chtvplan.repository;

import dev.antilef.chtvplan.entity.DestinyPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DestinyPlanRepository extends CrudRepository<DestinyPlan,Long> {
    List<DestinyPlan> findByName(String planName);
}
