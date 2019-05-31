package io.bounds4all.bondsmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Emission {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String emissionName;

    @Column(nullable = false)
    private double unitValue;

    @Column(nullable = false)
    private Date emissionDate;

    @OneToMany(mappedBy = "emission",fetch = FetchType.LAZY)
    private List<Bond> emittedBonds;

    @Column
    private double defaultCoupon;

    @Column
    private int maxPurchasePerDay;

    @Column
    private int maxUncontrolledPurchase;

    @Column
    private int minTerm;

}
