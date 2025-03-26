package mk.ukim.finki.mk.emtlab1.model.DTOs;

import lombok.Data;

@Data
public class ReviewDto {
    private String comment;
    private Double rating;
    private Long accommodationId;
}
