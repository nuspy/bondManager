package io.bounds4all.bondsmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "client")
    private List <Purchase> purchases;

    @Column
    private boolean convalidated;

    @Column
    private String token;

}
