package mk.ukim.finki.mk.emtlab1.web;


import mk.ukim.finki.mk.emtlab1.model.DTOs.ReviewDto;
import mk.ukim.finki.mk.emtlab1.model.Review;
import mk.ukim.finki.mk.emtlab1.service.ReviewService;
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
    public ResponseEntity<Review> addReview(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.create(reviewDto));
    }
}