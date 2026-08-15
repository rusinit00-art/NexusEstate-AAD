package org.ijse.nexusestate_aad.repository;

import org.ijse.nexusestate_aad.entity.Interactions.Appoinment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppoinmentRepository extends JpaRepository<Appoinment, Long> {
}