package org.ijse.nexusestate_aad.repository;

import org.ijse.nexusestate_aad.entity.Interactions.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}