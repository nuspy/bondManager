package io.bounds4all.bondsmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class BondHistory {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "bond_id")
    protected Bond bond;
}
