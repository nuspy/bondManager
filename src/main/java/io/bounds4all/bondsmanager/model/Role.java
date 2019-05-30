package io.bounds4all.bondsmanager.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "role_table")
public class Role {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
