package org.ijse.nexusestate_aad.service;

import org.ijse.nexusestate_aad.dto.TransactionDTO;
import org.ijse.nexusestate_aad.dto.PaymentDTO;
import java.util.List;

public interface TransactionService {
    String completeSale(TransactionDTO tDto, PaymentDTO pDto);
    List<TransactionDTO> getAllTransactions();
}