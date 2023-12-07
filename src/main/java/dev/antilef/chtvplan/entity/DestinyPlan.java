package dev.antilef.chtvplan.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class DestinyPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer fixedCharge;
    private String name;

    @Override
    public String toString() {
        return "DestinyPlan{" +
                "id=" + id +
                ", fixedCharge=" + fixedCharge +
                '}';
    }
}
