package org.ijse.nexusestate_aad.service.impl;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.*;
import org.ijse.nexusestate_aad.entity.Transactions.*;
import org.ijse.nexusestate_aad.enumiration.*;
import org.ijse.nexusestate_aad.repository.*;
import org.ijse.nexusestate_aad.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepo;
    private final PaymentRepository paymentRepo;
    private final PropertyRepository propertyRepo;
    private final UserRepository userRepo;

    @Override
    @Transactional
    public String completeSale(TransactionDTO tDto, PaymentDTO pDto) {
        SaleTransaction transaction = new SaleTransaction();
        transaction.setFinalPrice(tDto.getFinalPrice());
        transaction.setSaleDate(LocalDateTime.now());
        transaction.setProperty(propertyRepo.findById(tDto.getPropertyId()).orElse(null));
        transaction.setBuyer(userRepo.findById(tDto.getBuyerId()).orElse(null));
        SaleTransaction savedT = transactionRepo.save(transaction);

        Payment payment = new Payment();
        payment.setAmount(pDto.getAmount());
        payment.setPaymentMethod(pDto.getMethod());
        payment.setStatus(pDto.getStatus());
        payment.setTransaction(savedT);
        paymentRepo.save(payment);

        return "Sale and Payment completed successfully!";
    }

    @Override
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepo.findAll().stream().map(t -> new TransactionDTO(
                t.getId(), t.getFinalPrice(), t.getSaleDate(),
                t.getProperty().getId(), t.getBuyer().getId()
        )).collect(Collectors.toList());
    }
}