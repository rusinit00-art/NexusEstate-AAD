package org.ijse.nexusestate_aad.config;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.entity.SecurityandUsers.Role;
import org.ijse.nexusestate_aad.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleRepository roleRepo;

    @Override
    public void run(String... args) {
        if(roleRepo.count() == 0) {
            roleRepo.saveAll(List.of(
                    new Role(null, "ADMIN"),
                    new Role(null, "SELLER"),
                    new Role(null, "BUYER")
            ));
            System.out.println("✅ Database Initialized with System Roles!");
        }
    }
}