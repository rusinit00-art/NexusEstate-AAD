package org.ijse.nexusestate_aad.service;

import org.ijse.nexusestate_aad.dto.ReviewDTO;
import java.util.List;

public interface ReviewService {
    String saveReview(ReviewDTO dto);
    List<ReviewDTO> getReviewsByProperty(Long propertyId);
    void deleteReview(Long id);
}