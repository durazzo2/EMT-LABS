package mk.ukim.finki.mk.emtlab1.service;

import mk.ukim.finki.mk.emtlab1.model.DTOs.ReviewDto;
import mk.ukim.finki.mk.emtlab1.model.Review;

public interface ReviewService {
    Review create (ReviewDto reviewDto);
}
