package mk.ukim.finki.mk.emtlab1.web;

import mk.ukim.finki.mk.emtlab1.model.Accomodation;
import mk.ukim.finki.mk.emtlab1.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    public List<Accomodation> getAllAccommodations() {
        return accommodationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accomodation> getAccommodationById(@PathVariable Long id) {
        return accommodationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status")
    public List<Accomodation> getStatus(@RequestParam boolean rented) {
        return accommodationService.findByRentalStatus(rented);
    }

    @PostMapping
    public Accomodation createAccommodation(@RequestBody Accomodation accommodation) {
        return accommodationService.save(accommodation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accomodation> updateAccommodation(@PathVariable Long id, @RequestBody Accomodation accommodation) {
        if (!accommodationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        accommodation.setId(id);
        return ResponseEntity.ok(accommodationService.save(accommodation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccommodation(@PathVariable Long id) {
        if (!accommodationService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        accommodationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/rent")
    public ResponseEntity<Accomodation> markAsRented(@PathVariable Long id) {
        Accomodation accommodation = accommodationService.markAsRented(id);
        if (accommodation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accommodation);
    }

    @PatchMapping("/{id}/available")
    public ResponseEntity<Accomodation> markAsAvailable(@PathVariable Long id) {
        Accomodation accommodation = accommodationService.markAsAvailable(id);
        if (accommodation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accommodation);
    }

}
