package org.ijse.nexusestate_aad.repository;

import org.ijse.nexusestate_aad.entity.Transactions.SaleTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<SaleTransaction, Long> {
}