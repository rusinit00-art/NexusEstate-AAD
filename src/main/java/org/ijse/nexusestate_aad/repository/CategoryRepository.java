package org.ijse.nexusestate_aad.repository;

import org.ijse.nexusestate_aad.entity.PropertyFoundation.PropertyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<PropertyCategory, Long> {
}