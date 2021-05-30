package com.mms.CamsEcommerceReviews.Review;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    List<Review> findAll() {
        return reviewRepository.findAll();
    }

    Review findById(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isEmpty()) {
            throw new IllegalArgumentException("Review with ID=" + id + " doesn't exist");
        }
        return reviewOptional.get();
    }

    Review save(Review review) {
        return reviewRepository.save(review);
    }

    void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}
