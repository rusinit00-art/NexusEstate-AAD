package org.ijse.nexusestate_aad.service.impl;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.TransactionDTO;
import org.ijse.nexusestate_aad.entity.PropertyFoundation.Property;
import org.ijse.nexusestate_aad.entity.SecurityandUsers.User;
import org.ijse.nexusestate_aad.entity.Transactions.Payment;
import org.ijse.nexusestate_aad.entity.Transactions.SaleTransaction;
import org.ijse.nexusestate_aad.enumiration.PaymentMethod;
import org.ijse.nexusestate_aad.enumiration.PaymentStatus;
import org.ijse.nexusestate_aad.enumiration.PropertyStatus;
import org.ijse.nexusestate_aad.repository.PaymentRepository;
import org.ijse.nexusestate_aad.repository.PropertyRepository;
import org.ijse.nexusestate_aad.repository.TransactionRepository;
import org.ijse.nexusestate_aad.repository.UserRepository;
import org.ijse.nexusestate_aad.service.NotificationService;
import org.ijse.nexusestate_aad.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final PaymentRepository paymentRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    @Override
    @Transactional
    public String processTransaction(TransactionDTO dto) {
        Property property = propertyRepository.findById(dto.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Property not found with ID: " + dto.getPropertyId()));

        User buyer = userRepository.findById(dto.getBuyerId())
                .orElseThrow(() -> new RuntimeException("Buyer not found with ID: " + dto.getBuyerId()));

        User seller = property.getSeller();

        // Property SOLD
        property.setStatus(PropertyStatus.SOLD);
        propertyRepository.save(property);

        // SaleTransaction Record
        SaleTransaction transaction = new SaleTransaction();
        transaction.setProperty(property);
        transaction.setBuyer(buyer);
        transaction.setFinalPrice(dto.getAmount());
        transaction.setSaleDate(LocalDateTime.now());
        SaleTransaction savedTx = transactionRepository.save(transaction);

        //  Payment Record
        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(PaymentMethod.CARD);
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setTransaction(savedTx);
        paymentRepository.save(payment);

        //  Notifications
        try {
            notificationService.createNotification(
                    buyer.getId(),
                    "Payment Successful! You acquired asset: '" + property.getTitle() + "' for LKR " + String.format("%,.2f", dto.getAmount())
            );
            if (seller != null) {
                notificationService.createNotification(
                        seller.getId(),
                        "Asset Sold! Your property '" + property.getTitle() + "' was purchased by " + buyer.getUsername()
                );
            }
        } catch (Exception ignored) {}

        return "Transaction and Payment completed successfully!";
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream().map(tx -> new TransactionDTO(
                tx.getId(),
                tx.getProperty() != null ? tx.getProperty().getId() : null,
                tx.getBuyer() != null ? tx.getBuyer().getId() : null,
                (tx.getProperty() != null && tx.getProperty().getSeller() != null) ? tx.getProperty().getSeller().getId() : null,
                tx.getFinalPrice(),
                "CARD",
                tx.getSaleDate()
        )).collect(Collectors.toList());
    }
}