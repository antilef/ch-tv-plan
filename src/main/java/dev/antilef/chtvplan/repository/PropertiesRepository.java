package dev.antilef.chtvplan.repository;

import dev.antilef.chtvplan.entity.Property;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository extends CrudRepository<Property,Long> {

    @Query(value = "SELECT p.value FROM Property p WHERE p.key = :key LIMIT 1", nativeQuery = true)
    String getProperty(@Param("key") String key);

}
