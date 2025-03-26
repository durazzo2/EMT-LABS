package mk.ukim.finki.mk.emtlab1.model.DTOs;

import lombok.Data;

@Data
public class ReviewResponseDto {
    private Long id;
    private String comment;
    private Double rating;
    private AccommodationSimpleDto accommodation;

    @Data
    public static class AccommodationSimpleDto {
        private Long id;
        private String name;
    }
}