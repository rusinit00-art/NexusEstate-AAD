package org.ijse.nexusestate_aad.repository;
import org.ijse.nexusestate_aad.entity.PropertyFoundation.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}