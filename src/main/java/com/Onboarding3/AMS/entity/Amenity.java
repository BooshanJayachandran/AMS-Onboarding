package com.Onboarding3.AMS.entity;
import lombok.*;
import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "amenity")
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer amenityId;

    @Column(unique=true)
    private String amenityName;

//    @OneToOne(mappedBy = "amenity", cascade = CascadeType.ALL)
//    private Vendor vendorName;
}
