package org.ijse.nexusestate_aad.service;

import org.ijse.nexusestate_aad.dto.UserDTO;
import java.util.List;

public interface UserService {
    void updateUser(Long id, UserDTO dto);
    void deleteUser(Long id);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
}