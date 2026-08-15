package org.ijse.nexusestate_aad.dto;

import lombok.Data;

@Data
public class TransactionRequestDTO {
    private TransactionDTO transaction;
    private PaymentDTO payment;
}