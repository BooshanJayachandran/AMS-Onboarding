package com.Onboarding3.AMS.entity;
import lombok.*;
import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vendorId;

    private String name;

    private String email;

//    @OneToOne(cascade =CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "amenity_name")
    //private String amenityName;
//    private Amenity amenityName;
    private String amenityName;

    public String getAmenityName() {
        return amenityName;
    }

    public void setAmenityName(String amenityName) {
        this.amenityName = amenityName;
    }

}

