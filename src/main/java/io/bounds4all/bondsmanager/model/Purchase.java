package io.bounds4all.bondsmanager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Purchase {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "purchase")
    private List <Bond> bonds;

    @Column
    private String notes;

    @Column
    private String payment;
}
