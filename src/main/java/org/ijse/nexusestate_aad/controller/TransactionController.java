package org.ijse.nexusestate_aad.controller;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.TransactionDTO;
import org.ijse.nexusestate_aad.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@CrossOrigin
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/pay")
    public ResponseEntity<?> makePayment(@RequestBody TransactionDTO dto) {
        try {
            String result = transactionService.processTransaction(dto);
            return ResponseEntity.ok(Map.of("message", result));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransactionDTO>> getAll() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
}