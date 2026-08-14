package org.ijse.nexusestate_aad.dto;

import lombok.Data;
import java.util.Set;
@Data
public class RegisterRequestDTO {
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
}