package org.ijse.nexusestate_aad.repository;
import org.ijse.nexusestate_aad.entity.SecurityandUsers.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}