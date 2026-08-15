package org.ijse.nexusestate_aad.repository;

import org.ijse.nexusestate_aad.entity.Transactions.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}