package mk.ukim.finki.lab1b.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.lab1b.model.enumerations.Category;
import mk.ukim.finki.lab1b.model.domain.Country;
import mk.ukim.finki.lab1b.model.domain.Host;
import mk.ukim.finki.lab1b.repository.CountryRepository;
import mk.ukim.finki.lab1b.repository.HostRepository;
import mk.ukim.finki.lab1b.web.AccommodationController;
import org.springframework.stereotype.Component;
import mk.ukim.finki.lab1b.dto.AccommodationDto;

@Component
public class DataInitializer {
    private final AccommodationController accommodationController;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(AccommodationController accommodationController, HostRepository hostRepository, CountryRepository countryRepository) {
        this.accommodationController = accommodationController;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }
    @PostConstruct
    public void init() {
        Country c1=countryRepository.save(new Country("Macedonia","Europe"));
        Country c2= countryRepository.save(new Country("Japan","Asia"));

        Host h1= hostRepository.save(new Host("Teodor","Duracoski",c1));
        Host h2=hostRepository.save(new Host("Mila","Gjorgoska",c2));

        accommodationController.save(new AccommodationDto("Family", Category.APARTMENT,h1,4));
        accommodationController.save(new AccommodationDto("Solo", Category.FLAT,h2,1));

    }

}
