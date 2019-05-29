package io.bounds4all.bondsmanager.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class BondHistory {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "bond_id")
    protected Bond bond;
}
