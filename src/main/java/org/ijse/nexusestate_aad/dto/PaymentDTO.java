package org.ijse.nexusestate_aad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ijse.nexusestate_aad.enumiration.PaymentMethod;
import org.ijse.nexusestate_aad.enumiration.PaymentStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long id;
    private Double amount;
    private PaymentMethod method;
    private PaymentStatus status;
    private Long transactionId;
}