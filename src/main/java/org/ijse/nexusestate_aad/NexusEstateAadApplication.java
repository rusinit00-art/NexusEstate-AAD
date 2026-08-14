package org.ijse.nexusestate_aad;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ijse.nexusestate_aad.entity.SecurityandUsers.Role;
import org.ijse.nexusestate_aad.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class NexusEstateAadApplication {

    public static void main(String[] args) {
        SpringApplication.run(NexusEstateAadApplication.class, args);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner init(RoleRepository roleRepo) {
        return args -> {
            if(roleRepo.count() == 0) {
                roleRepo.saveAll(List.of(
                        new Role(null, "ADMIN"),
                        new Role(null, "SELLER"),
                        new Role(null, "BUYER")
                ));
                System.out.println("✅ Database & System Roles Initialized Successfully!");
            }
        };
    }
}