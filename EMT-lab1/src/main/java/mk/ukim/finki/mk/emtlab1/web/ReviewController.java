package mk.ukim.finki.mk.emtlab1.web;


import jakarta.validation.Valid;
import mk.ukim.finki.mk.emtlab1.model.DTOs.ReviewDto;
import mk.ukim.finki.mk.emtlab1.model.DTOs.ReviewResponseDto;
import mk.ukim.finki.mk.emtlab1.model.Review;
import mk.ukim.finki.mk.emtlab1.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @PostMapping("/add")
    public ResponseEntity<ReviewResponseDto> addReview(@Valid @RequestBody ReviewDto reviewDto) {
        Review review = reviewService.create(reviewDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(review));
    }

    private ReviewResponseDto convertToDto(Review review) {
        ReviewResponseDto dto = new ReviewResponseDto();
        dto.setId(review.getId());
        dto.setComment(review.getComment());
        dto.setRating(review.getRating());

        if (review.getAccommodation() != null) {
            ReviewResponseDto.AccommodationSimpleDto accDto =
                    new ReviewResponseDto.AccommodationSimpleDto();
            accDto.setId(review.getAccommodation().getId());
            accDto.setName(review.getAccommodation().getName());
            dto.setAccommodation(accDto);
        }

        return dto;
    }
}