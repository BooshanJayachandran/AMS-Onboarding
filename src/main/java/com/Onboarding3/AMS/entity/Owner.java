package com.Onboarding3.AMS.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owner")
@SequenceGenerator(name="owner_id_seq", initialValue=1001, allocationSize=100)
public class Owner {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="owner_id_seq")
    private Integer ownerId;

    private String name;

    private String email;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "flat")
    @PrimaryKeyJoinColumn(name = "flat_no")
    private Integer flatNo;


}

