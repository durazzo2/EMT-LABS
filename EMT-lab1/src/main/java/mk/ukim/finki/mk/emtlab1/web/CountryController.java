package mk.ukim.finki.mk.emtlab1.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.mk.emtlab1.model.Country;
import mk.ukim.finki.mk.emtlab1.service.CountryService;
import mk.ukim.finki.mk.emtlab1.service.impl.CountryServiceImpl;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Country>> findAll() {
        return ResponseEntity.ok(this.countryService.findAll());
    }

}
