package org.ijse.nexusestate_aad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ijse.nexusestate_aad.enumiration.PropertyStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {
    private Long id;
    private String title;
    private String description;
    private Double price;
    private Double areaSqft;
    private PropertyStatus status;
    private String categoryName; // ID වෙනුවට නම
    private String cityName;     // ID වෙනුවට නම
    private Long sellerId;
}