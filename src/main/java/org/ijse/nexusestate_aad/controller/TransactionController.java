package org.ijse.nexusestate_aad.controller;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.*;
import org.ijse.nexusestate_aad.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/complete")
    public ResponseEntity<String> complete(@RequestBody TransactionRequest wrapper) {
        return ResponseEntity.ok(transactionService.completeSale(wrapper.getTransaction(), wrapper.getPayment()));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
}

@lombok.Data
class TransactionRequest {
    private TransactionDTO transaction;
    private PaymentDTO payment;
}