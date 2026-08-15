package org.ijse.nexusestate_aad.config;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.entity.PropertyFoundation.Location;
import org.ijse.nexusestate_aad.entity.PropertyFoundation.PropertyCategory;
import org.ijse.nexusestate_aad.entity.SecurityandUsers.Role;
import org.ijse.nexusestate_aad.repository.CategoryRepository;
import org.ijse.nexusestate_aad.repository.LocationRepository;
import org.ijse.nexusestate_aad.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final RoleRepository roleRepo;
    private final CategoryRepository catRepo;
    private final LocationRepository locRepo;

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
        if (catRepo.count() == 0) {
            catRepo.saveAll(List.of(
                    new PropertyCategory(null, "House"),
                    new PropertyCategory(null, "Land"),
                    new PropertyCategory(null, "Apartment")
            ));
            System.out.println("✅ Property Categories Initialized!");
        }

        if (locRepo.count() == 0) {
            locRepo.saveAll(List.of(
                    new Location(null, "Colombo", "Western"),
                    new Location(null, "Kandy", "Central"),
                    new Location(null, "Galle", "Southern")
            ));
            System.out.println("✅ System Locations Initialized!");
        }
    }
}
