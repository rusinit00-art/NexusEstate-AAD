package org.ijse.nexusestate_aad.service;

import org.ijse.nexusestate_aad.dto.TransactionDTO;
import java.util.List;

public interface TransactionService {
    String processTransaction(TransactionDTO dto);
    List<TransactionDTO> getAllTransactions();
}