package com.pe.insalud.backend.iam.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SignUpResource {
    private String username;
    private String password;
    private String email;
    private List<String> roles; // List<String> de nombres de roles
}
