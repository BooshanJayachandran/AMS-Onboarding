package com.Onboarding3.AMS.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;


    //@ManyToOne
    @JoinColumn(name = "amenity_id")
    private Integer amenityId;


    //@ManyToOne
    @JoinColumn(name = "owner_id")
    private Integer ownerId;

    private LocalDateTime requestDateTime;

    private LocalDateTime eta;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;




}

