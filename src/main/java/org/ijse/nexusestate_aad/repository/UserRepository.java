package org.ijse.nexusestate_aad.repository;

import org.ijse.nexusestate_aad.entity.SecurityandUsers.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}