package org.ijse.nexusestate_aad.service.impl;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.PaymentDTO;
import org.ijse.nexusestate_aad.entity.Transactions.Payment;
import org.ijse.nexusestate_aad.enumiration.PaymentMethod;
import org.ijse.nexusestate_aad.enumiration.PaymentStatus;
import org.ijse.nexusestate_aad.repository.PaymentRepository;
import org.ijse.nexusestate_aad.repository.TransactionRepository;
import org.ijse.nexusestate_aad.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public String makePayment(PaymentDTO dto) {
        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());

        // String PaymentMethod Enum
        try {
            payment.setPaymentMethod(PaymentMethod.valueOf(dto.getPaymentMethod().toUpperCase()));
        } catch (Exception e) {
            payment.setPaymentMethod(PaymentMethod.CARD);
        }

        // Status SUCCESS
        payment.setStatus(PaymentStatus.SUCCESS);

        if (dto.getSaleTransactionId() != null) {
            transactionRepository.findById(dto.getSaleTransactionId())
                    .ifPresent(payment::setTransaction);
        }

        paymentRepository.save(payment);
        return "Payment processed successfully!";
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream().map(p -> new PaymentDTO(
                p.getId(),
                p.getAmount(),
                p.getPaymentMethod() != null ? p.getPaymentMethod().name() : null,
                p.getStatus() != null ? p.getStatus().name() : null,
                null,
                p.getTransaction() != null ? p.getTransaction().getId() : null
        )).collect(Collectors.toList());
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Payment p = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        return new PaymentDTO(
                p.getId(),
                p.getAmount(),
                p.getPaymentMethod() != null ? p.getPaymentMethod().name() : null,
                p.getStatus() != null ? p.getStatus().name() : null,
                null,
                p.getTransaction() != null ? p.getTransaction().getId() : null
        );
    }
}