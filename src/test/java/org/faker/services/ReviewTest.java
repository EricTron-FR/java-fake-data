package org.faker.services;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class ReviewTest {
    @RepeatedTest(50)
    void getRandomRating_ShouldReturnValueBetweenOneAndFive() {
        // When
        int rating = Review.getRandomRating();

        // Then
        assertTrue(rating >= 1 && rating <= 5);
    }

    @Test
    void getRandomComment_ShouldReturnNonNullComment() {
        // When
        String comment = Review.getRandomComment();

        // Then
        assertNotNull(comment);
        assertFalse(comment.isEmpty());
    }

    @Test
    void getRandomCommentByRating_ShouldReturnPositiveComment() {
        // When
        String comment = Review.getRandomCommentByRating("positive");

        // Then
        assertNotNull(comment);
        assertFalse(comment.isEmpty());
    }

    @Test
    void getRandomCommentByRating_ShouldReturnNeutralComment() {
        // When
        String comment = Review.getRandomCommentByRating("neutral");

        // Then
        assertNotNull(comment);
        assertFalse(comment.isEmpty());
    }

    @Test
    void getRandomCommentByRating_ShouldReturnNegativeComment() {
        // When
        String comment = Review.getRandomCommentByRating("negative");

        // Then
        assertNotNull(comment);
        assertFalse(comment.isEmpty());
    }

    @Test
    void getRandomCommentByRating_ShouldThrowExceptionForInvalidRating() {
        // Then
        assertThrows(IllegalArgumentException.class, () -> {
            Review.getRandomCommentByRating("invalid");
        });
    }

    @Test
    void getRandomCommentByRating_ShouldBeCaseInsensitive() {
        // When
        String comment1 = Review.getRandomCommentByRating("POSITIVE");
        String comment2 = Review.getRandomCommentByRating("Neutral");
        String comment3 = Review.getRandomCommentByRating("negative");

        // Then
        assertAll(
                () -> assertNotNull(comment1),
                () -> assertNotNull(comment2),
                () -> assertNotNull(comment3)
        );
    }
}
