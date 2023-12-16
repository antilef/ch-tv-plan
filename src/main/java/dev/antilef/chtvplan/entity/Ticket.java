package dev.antilef.chtvplan.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;

    private String accountId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false ,updatable = false)
    private Date createdAt;




}
