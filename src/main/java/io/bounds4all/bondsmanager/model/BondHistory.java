package io.bounds4all.bondsmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "bond_id")
    @JsonIgnore
    private Bond bond;

    @Column
    private double coupon;

    @Column(nullable = false)
    private LocalDateTime termEndDate;

    @Column
    private int termLenghtMonts;
}
