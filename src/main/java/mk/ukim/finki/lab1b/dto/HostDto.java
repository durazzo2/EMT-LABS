package mk.ukim.finki.lab1b.dto;

public record HostDto(
        Long id,
        String firstName,
        String lastName,
        Long countryId
) {
    // Secondary constructor for creating new hosts (without ID)
    public HostDto(String firstName, String lastName, Long countryId) {
        this(null, firstName, lastName, countryId);
    }
}