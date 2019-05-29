package io.bounds4all.bondsmanager.dtos;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class ClientLoginDto implements Serializable {
    private String userName;
    private String passwordHash;
    private String email;
}
