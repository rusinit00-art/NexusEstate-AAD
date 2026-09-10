package org.ijse.nexusestate_aad.repository;

import org.ijse.nexusestate_aad.entity.Transactions.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}