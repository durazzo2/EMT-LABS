package mk.ukim.finki.mk.emtlab1.service.impl;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import mk.ukim.finki.mk.emtlab1.model.Accomodation;
import mk.ukim.finki.mk.emtlab1.model.DTOs.ReviewDto;
import mk.ukim.finki.mk.emtlab1.model.Review;
import mk.ukim.finki.mk.emtlab1.repository.AccommodationRepository;
import mk.ukim.finki.mk.emtlab1.repository.ReviewRepository;
import mk.ukim.finki.mk.emtlab1.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final AccommodationRepository accommodationRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, AccommodationRepository accommodationRepository) {
        this.reviewRepository = reviewRepository;
        this.accommodationRepository = accommodationRepository;
    }


    @Override
    public Review create(ReviewDto reviewDto) {

        Accomodation accommodation = accommodationRepository.findById(reviewDto.getAccommodationId())
                .orElseThrow(() -> new RuntimeException("Accommodation not found"));

        Review review = new Review();
        review.setComment(reviewDto.getComment());
        review.setRating(reviewDto.getRating());
      //  review.setAccommodation(accommodation);
        return reviewRepository.save(review);
    }
}


