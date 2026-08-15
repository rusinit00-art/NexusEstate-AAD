package org.ijse.nexusestate_aad.service.impl;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.ReviewDTO;
import org.ijse.nexusestate_aad.entity.Interactions.Review;
import org.ijse.nexusestate_aad.repository.ReviewRepository;
import org.ijse.nexusestate_aad.repository.PropertyRepository;
import org.ijse.nexusestate_aad.repository.UserRepository;
import org.ijse.nexusestate_aad.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Override
    public String saveReview(ReviewDTO dto) {
        Review review = new Review();
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setProperty(propertyRepository.findById(dto.getPropertyId()).orElse(null));
        review.setUser(userRepository.findById(dto.getUserId()).orElse(null));

        reviewRepository.save(review);
        return "Review submitted successfully!";
    }

    @Override
    public List<ReviewDTO> getReviewsByProperty(Long propertyId) {
        return reviewRepository.findByPropertyId(propertyId).stream()
                .map(r -> new ReviewDTO(r.getId(), r.getRating(), r.getComment(),
                        r.getProperty().getId(), r.getUser().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}