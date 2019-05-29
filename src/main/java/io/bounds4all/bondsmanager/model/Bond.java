package io.bounds4all.bondsmanager.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Bond {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "bond")
    private List<BondHistory> bondHistory;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "emission_id")
    private Emission emission;

}
