package org.ijse.nexusestate_aad.service;

import org.ijse.nexusestate_aad.dto.PaymentDTO;
import java.util.List;

public interface PaymentService {
    String makePayment(PaymentDTO dto);
    List<PaymentDTO> getAllPayments();
    PaymentDTO getPaymentById(Long id);
}