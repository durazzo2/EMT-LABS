package mk.ukim.finki.mk.emtlab1.model.DTOs;

import lombok.Data;
import mk.ukim.finki.mk.emtlab1.model.AccommodationCategory;

@Data
public class BookingDto {

    private String name;

    private AccommodationCategory category;

    private Long hostId;

    private int numOfRooms;
}
