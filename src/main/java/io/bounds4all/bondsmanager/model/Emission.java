package io.bounds4all.bondsmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Emission {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    protected Date emissionDate;

    @OneToMany(mappedBy = "emission")
    private List<Bond> emittedBonds;

}
