package com.Onboarding3.AMS.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "maintenance")
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maintenanceId;

    @Column(name = "Owner_Id")
    private Integer ownerId;

    private Double amount;

    @Column(columnDefinition = "DATE")
    private LocalDate dateCreated;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.NOT_PAID;

    private Integer charge;

    private Integer amountPayable;
}