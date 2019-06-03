package io.bounds4all.bondsmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private LocalDateTime purchaseDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List <Bond> bonds;

    @Column
    private double initialCoupon;

    @Column
    private int initialMonthsLenght;

    @Column
    private int value;

    @ManyToOne
    private Emission emission;

    @Column
    private int amount;

    @Column
    private String notes;

    @Column
    private String payment;
}
