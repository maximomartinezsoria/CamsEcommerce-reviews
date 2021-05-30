package com.mms.CamsEcommerceReviews.Review;

import com.mms.CamsEcommerceReviews.Product.Product;
import com.mms.CamsEcommerceReviews.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;
    private ReviewService reviewService;

    @BeforeEach
    void setUp() {
        this.reviewService = new ReviewService(reviewRepository);
    }

    @Test
    void shouldCallFindAllOnce() {
        reviewService.findAll();
        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void shouldCallSaveOnce() {
        Long id = (long) 1;
        User user = new User(id, "Name", "intro", "email", null);
        Product product = new Product(id, "title", null);
        Review review = new Review(id, "title", "text", user, product);
        reviewService.save(review);
        ArgumentCaptor<Review> reviewArgumentCaptor = ArgumentCaptor.forClass(Review.class);
        verify(reviewRepository, times(1)).save(reviewArgumentCaptor.capture());
        assertThat(reviewArgumentCaptor.getValue()).isEqualTo(review);
    }

    @Test
    void shouldCallFindByIdOnce() {
        Long id = (long) 1;
        User user = new User(id, "Name", "intro", "email", null);
        Product product = new Product(id, "title", null);
        Review review = new Review(id, "title", "text", user, product);
        Optional<Review> reviewOptional = Optional.of(review);
        given(reviewRepository.findById(anyLong())).willReturn(reviewOptional);
        reviewService.findById(id);
        ArgumentCaptor<Long> reviewIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(reviewRepository, times(1)).findById(reviewIdArgumentCaptor.capture());
        assertThat(reviewIdArgumentCaptor.getValue()).isEqualTo(id);
    }

    @Test
    void shouldCallFindByIdOnceAndThrowException() {
        Long id = (long) 1;
        assertThatThrownBy(() -> reviewService.findById(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Review with ID=" + id + " doesn't exist");
        ArgumentCaptor<Long> reviewIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(reviewRepository, times(1)).findById(reviewIdArgumentCaptor.capture());
        assertThat(reviewIdArgumentCaptor.getValue()).isEqualTo(id);
    }

    @Test
    void shouldCallDeleteOnce() {
        Long id = (long) 1;
        reviewService.delete(id);
        ArgumentCaptor<Long> reviewIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(reviewRepository, times(1)).deleteById(reviewIdArgumentCaptor.capture());
        assertThat(reviewIdArgumentCaptor.getValue()).isEqualTo(id);
    }
}