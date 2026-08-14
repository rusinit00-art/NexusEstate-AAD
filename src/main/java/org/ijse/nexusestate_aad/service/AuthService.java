package org.ijse.nexusestate_aad.service;
import org.ijse.nexusestate_aad.dto.*;
public interface AuthService {
    String register(RegisterRequestDTO dto);
    AuthResponseDTO login(AuthRequestDTO dto);
}