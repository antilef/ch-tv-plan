package dev.antilef.chtvplan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Property {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "property_value")
    private String value;

    @Column(name = "property_key")
    private String key;

}
