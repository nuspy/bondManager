package io.bounds4all.bondsmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    private List <Bond> bonds;

    @Column
    private String notes;

    @Column
    private String payment;
}
