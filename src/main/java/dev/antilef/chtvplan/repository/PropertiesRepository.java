package dev.antilef.chtvplan.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository {
    String getProperty(String s);
}
