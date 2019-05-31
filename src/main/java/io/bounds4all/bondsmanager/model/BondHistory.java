package io.bounds4all.bondsmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class BondHistory {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Date insertDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bond_id")
    private Bond bond;

    @Column
    private double coupon;

    @Column(nullable = false)
    private LocalDateTime termEndDate;

    @Column
    private int termLenghtMonts;
}
