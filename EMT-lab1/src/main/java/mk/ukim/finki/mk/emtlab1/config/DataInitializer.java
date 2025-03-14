package mk.ukim.finki.mk.emtlab1.config;


import mk.ukim.finki.mk.emtlab1.model.AccommodationCategory;
import mk.ukim.finki.mk.emtlab1.model.Accomodation;
import mk.ukim.finki.mk.emtlab1.model.Country;
import mk.ukim.finki.mk.emtlab1.model.Host;
import mk.ukim.finki.mk.emtlab1.repository.AccommodationRepository;
import mk.ukim.finki.mk.emtlab1.repository.CountryRepository;
import mk.ukim.finki.mk.emtlab1.repository.HostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(CountryRepository countryRepository,
                                      HostRepository hostRepository,
                                      AccommodationRepository accommodationRepository) {
        return args -> {
            // Create countries
            Country macedonia = new Country(1L, "Macedonia", "Europe");
            Country usa = new Country(2L, "USA", "North America");
            Country japan = new Country(3L, "Japan", "Asia");

            countryRepository.save(macedonia);
            countryRepository.save(usa);
            countryRepository.save(japan);

            // Create hosts
            Host john = new Host(1L, "John", "Doe", macedonia);
            Host jane = new Host(2L, "Jane", "Smith", usa);
            Host takeshi = new Host(3L, "Takeshi", "Yamamoto", japan);

            hostRepository.save(john);
            hostRepository.save(jane);
            hostRepository.save(takeshi);

            // Create accommodations
            Accomodation acc1 = new Accomodation(1L, "Cozy Apartment in Skopje",
                    AccommodationCategory.APARTMENT, john, 2, false);
            Accomodation acc2 = new Accomodation(2L, "Luxury Villa in New York",
                    AccommodationCategory.HOUSE, jane, 5, false);
            Accomodation acc3 = new Accomodation(3L, "Traditional Room in Tokyo",
                    AccommodationCategory.ROOM, takeshi, 1, true);
            Accomodation acc4 = new Accomodation(4L, "Modern Flat in Ohrid",
                    AccommodationCategory.FLAT, john, 3, false);
            Accomodation acc5 = new Accomodation(5L, "Boutique Hotel in San Francisco",
                    AccommodationCategory.HOTEL, jane, 20, false);

            accommodationRepository.save(acc1);
            accommodationRepository.save(acc2);
            accommodationRepository.save(acc3);
            accommodationRepository.save(acc4);
            accommodationRepository.save(acc5);

            System.out.println("Data initialization completed!");
        };
    }
}