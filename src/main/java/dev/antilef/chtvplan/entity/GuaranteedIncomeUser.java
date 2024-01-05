package dev.antilef.chtvplan.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class GuaranteedIncomeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String rut;
}
