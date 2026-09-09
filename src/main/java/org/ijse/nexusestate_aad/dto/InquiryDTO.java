package org.ijse.nexusestate_aad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryDTO {
    private Long id;
    private String message;
    private LocalDateTime date;
    private Long propertyId;
    private Long userId;
    private String reply;
    private String askerName; // ප්‍රශ්නය ඇසූ Buyer ගේ නම
}