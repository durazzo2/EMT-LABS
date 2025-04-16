package mk.ukim.finki.lab1b.web;

import mk.ukim.finki.lab1b.dto.AccommodationDto;
import mk.ukim.finki.lab1b.model.domain.Accommodation;
import mk.ukim.finki.lab1b.model.domain.Host;
import mk.ukim.finki.lab1b.model.enumerations.Category;
import mk.ukim.finki.lab1b.service.domain.AccommodationService;
import mk.ukim.finki.lab1b.service.domain.HostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationController(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @GetMapping
    public List<Accommodation> findAll() {
        return accommodationService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Accommodation> findById(@PathVariable Long id) {
        return accommodationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Accommodation> saveAccommodation(@RequestBody AccommodationDto accommodationDto) {
        Accommodation accommodation = convertToEntity(accommodationDto);
        return accommodationService.save(accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Accommodation> update(@PathVariable Long id, @RequestBody AccommodationDto accommodationDto) {
        Accommodation accommodation = convertToEntity(accommodationDto);
        return accommodationService.update(id, accommodation)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (accommodationService.findById(id).isPresent()) {
            accommodationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/rent/{id}")
    public ResponseEntity<Accommodation> rent(@PathVariable Long id) {
        return accommodationService.rentRoom(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Helper method to convert DTO to Entity
    private Accommodation convertToEntity(AccommodationDto dto) {
        Long hostId = dto.host().getId();
        return hostService.findById(hostId)
                .map(host -> new Accommodation(dto.name(), dto.category(), host, dto.numRooms()))
                .orElseThrow(() -> new RuntimeException("Host not found"));
    }

    // Method for programmatic use with a different name to avoid signature clash
    public Accommodation save(AccommodationDto accommodationDto) {
        Accommodation accommodation = convertToEntity(accommodationDto);
        return accommodationService.save(accommodation).orElse(null);
    }
    @GetMapping("/search")
    public List<Accommodation> searchAccommodations(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Long hostId,
            @RequestParam(required = false) Integer numRooms) {

        Host host = null;
        if (hostId != null) {
            host = hostService.findById(hostId).orElse(null);
        }

        return accommodationService.searchAccommodations(name, category, host, numRooms);
    }
}