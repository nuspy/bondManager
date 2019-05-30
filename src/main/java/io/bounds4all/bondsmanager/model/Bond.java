package io.bounds4all.bondsmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Bond {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "bond")
    private List<BondHistory> bondHistory;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "emission_id")
    private Emission emission;

}
