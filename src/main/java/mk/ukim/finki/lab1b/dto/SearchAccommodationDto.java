// Create a new file SearchAccommodationDto.java
package mk.ukim.finki.lab1b.dto;

import mk.ukim.finki.lab1b.model.domain.Host;
import mk.ukim.finki.lab1b.model.enumerations.Category;

public record SearchAccommodationDto(
        String name,
        Category category,
        Long hostId,
        Integer numRooms
) {}