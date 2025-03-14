package mk.ukim.finki.mk.emtlab1.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.mk.emtlab1.model.Country;
import mk.ukim.finki.mk.emtlab1.service.CountryService;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Country API", description = "managing apis for country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    @Operation(summary = "return all countries")
    public List<Country> getAllCountries() {

        return countryService.findAll();
    }


    @GetMapping("/{id}")
    @Operation(summary = "get country by id")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        return countryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new country")
    public Country createCountry(@RequestBody Country country) {
        return countryService.save(country);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a country")
    public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country country) {
        if (!countryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            country.setId(id);
            return ResponseEntity.ok(countryService.save(country));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete country")
    public ResponseEntity<?> deleteCountry(@PathVariable Long id) {
        if (!countryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            countryService.deleteById(id);
            return ResponseEntity.ok().build();
        }


    }
}
