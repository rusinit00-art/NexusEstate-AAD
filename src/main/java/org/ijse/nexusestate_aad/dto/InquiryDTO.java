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
}