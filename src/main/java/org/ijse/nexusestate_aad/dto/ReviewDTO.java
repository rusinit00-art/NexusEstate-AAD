package org.ijse.nexusestate_aad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private Integer rating;
    private String comment;
    private Long propertyId;
    private Long userId;
}