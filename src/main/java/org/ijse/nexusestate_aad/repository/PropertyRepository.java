package org.ijse.nexusestate_aad.repository;

import org.ijse.nexusestate_aad.entity.PropertyFoundation.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}