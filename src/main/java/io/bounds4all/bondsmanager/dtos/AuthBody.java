package io.bounds4all.bondsmanager.dtos;

import lombok.Data;

@Data
public class AuthBody {
    private String email;
    private String password;
}
