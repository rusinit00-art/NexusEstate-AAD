package org.ijse.nexusestate_aad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ijse.nexusestate_aad.enumiration.AppoinmentStatus;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppoinmentDTO {
    private Long id;
    private LocalDateTime appoinmentDate;
    private AppoinmentStatus status;
    private Long propertyId;
    private Long userId;
}