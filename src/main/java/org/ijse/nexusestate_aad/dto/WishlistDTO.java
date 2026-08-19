package org.ijse.nexusestate_aad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ijse.nexusestate_aad.enumiration.AppoinmentStatus;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDTO {
    private Long id;
    private Long userId;
    private Long propertyId;
}