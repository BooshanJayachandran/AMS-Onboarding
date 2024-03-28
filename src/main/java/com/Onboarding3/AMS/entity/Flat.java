package com.Onboarding3.AMS.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flat")
@SequenceGenerator(name="flat_no_seq", initialValue=100, allocationSize=100)
public class Flat {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="flat_no_seq")
    private Integer flatNo;

    @JoinColumn(name = "admin_id")
    private Integer adminId;

    private Integer floor;

    private String block;

}
