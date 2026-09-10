package org.ijse.nexusestate_aad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatbotDTO {
    private Long id;
    private String prompt;
    private String reply;
    private Long userId;
    private LocalDateTime timestamp;
}