package io.bounds4all.bondsmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "user")
    private List <Order> orders;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles;

    @Column
    private boolean convalidated;

    @Column
    private String token;

    @Column
    private boolean enabled;



}
