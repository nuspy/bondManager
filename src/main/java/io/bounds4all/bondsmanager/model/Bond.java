package io.bounds4all.bondsmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@Entity
public class Bond {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private long id;

    @OneToMany(mappedBy = "bond", cascade = CascadeType.PERSIST)
    @Getter
    @Setter
    private List<BondHistory> bondHistory;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    @Getter
    @Setter
    private Order order;

    @ManyToOne
    @JoinColumn(name = "emission_id")
    @Getter
    @Setter
    private Emission emission;

}
