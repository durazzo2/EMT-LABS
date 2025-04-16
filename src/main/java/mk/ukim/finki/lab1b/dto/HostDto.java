package mk.ukim.finki.lab1b.dto;

public record HostDto(
        Long id,
        String firstName,
        String lastName,
        Long countryId
) {
    public HostDto(String firstName, String lastName, Long countryId) {
        this(null, firstName, lastName, countryId);
    }
}