package mk.ukim.finki.mk.emtlab1.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.mk.emtlab1.model.Accomodation;
import mk.ukim.finki.mk.emtlab1.model.DTOs.BookingDto;
import mk.ukim.finki.mk.emtlab1.service.AccomodationService;
import mk.ukim.finki.mk.emtlab1.service.impl.AccommodationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {

    private final AccomodationService accomodationService;

    public AccommodationController(AccomodationService accomodationService) {
        this.accomodationService = accomodationService;
    }

    @GetMapping
    @Operation(summary = "Get all accommodations", description = "Retrieves a list of all available accommodations")
    public ResponseEntity<List<Accomodation>> findAll() {
        return ResponseEntity.ok(this.accomodationService.findAll());
    }


    @PostMapping("/add-booking")
    @Operation(summary = "Create a new accommodation booking", description = "Adds a new accommodation booking to       the system")
    public ResponseEntity<Accomodation> addAcomodation(@RequestBody BookingDto bookingDto) throws Exception {
        return ResponseEntity.ok(this.accomodationService.create(bookingDto));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Update an accommodation", description = "Updates an existing accommodation by ID")
    public ResponseEntity<Accomodation> updateAccommodation(@PathVariable Long id, @RequestBody BookingDto bookingDto) throws Exception {
        return ResponseEntity.ok(this.accomodationService.update(id, bookingDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an accommodation", description = "Deletes an accommodation by ID")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        this.accomodationService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}")
    @Operation(summary = "Make a reservation", description = "Creates a reservation for a specific accommodation")
    public ResponseEntity<Accomodation> reservation(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.accomodationService.reservation(id));
    }

}
