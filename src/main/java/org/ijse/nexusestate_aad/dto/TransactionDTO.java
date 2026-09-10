package org.ijse.nexusestate_aad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long id;
    private Long propertyId;
    private Long buyerId;
    private Long sellerId;
    private Double amount;
    private String paymentMethod;
    private LocalDateTime transactionDate;
}