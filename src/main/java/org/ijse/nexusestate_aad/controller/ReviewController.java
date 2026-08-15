package org.ijse.nexusestate_aad.controller;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.ReviewDTO;
import org.ijse.nexusestate_aad.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<String> addReview(@RequestBody ReviewDTO dto) {
        return ResponseEntity.ok(reviewService.saveReview(dto));
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<ReviewDTO>> getByProperty(@PathVariable Long propertyId) {
        return ResponseEntity.ok(reviewService.getReviewsByProperty(propertyId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review deleted!");
    }
}