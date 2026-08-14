package org.ijse.nexusestate_aad.entity.Transactions;

import jakarta.persistence.*;
import lombok.*;
import org.ijse.nexusestate_aad.enumiration.PaymentMethod;
import org.ijse.nexusestate_aad.enumiration.PaymentStatus;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @ManyToOne
    private SaleTransaction transaction;
}