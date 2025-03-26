package mk.ukim.finki.mk.emtlab1.config;

import mk.ukim.finki.mk.emtlab1.model.*;
import mk.ukim.finki.mk.emtlab1.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(CountryRepository countryRepository,
                                      HostRepository hostRepository,
                                      AccommodationRepository accommodationRepository,
                                      ReviewRepository reviewRepository) {
        return args -> {
            // Initialize Countries
            Country macedonia = new Country(1l,"North Macedonia", "Europe");
            Country usa = new Country(2l,"United States", "North America");
            Country japan = new Country(3l,"Japan", "Asia");

            countryRepository.save(macedonia);
            countryRepository.save(usa);
            countryRepository.save(japan);

            // Initialize Hosts
            Host john = new Host(1l,"John", "Doe", macedonia);
            Host jane = new Host(2l,"Jane", "Smith", usa);
            Host takeshi = new Host(3l,"Takeshi", "Yamamoto", japan);

            hostRepository.save(john);
            hostRepository.save(jane);
            hostRepository.save(takeshi);

            // Initialize Accommodations using the proper constructor
            Accomodation acc1 = new Accomodation("Cozy Apartment in Skopje",
                    AccommodationCategory.APARTMENT, john, 2);
            Accomodation acc2 = new Accomodation("Luxury Villa in New York",
                    AccommodationCategory.HOUSE, jane, 5);
            Accomodation acc3 = new Accomodation("Traditional Room in Tokyo",
                    AccommodationCategory.ROOM, takeshi, 1);
            Accomodation acc4 = new Accomodation("Modern Flat in Ohrid",
                    AccommodationCategory.FLAT, john, 3);
            Accomodation acc5 = new Accomodation("Boutique Hotel in San Francisco",
                    AccommodationCategory.HOTEL, jane, 20);

            // Mark one accommodation as rented
            acc3.setRented(true);

            // Save accommodations
            accommodationRepository.save(acc1);
            accommodationRepository.save(acc2);
            accommodationRepository.save(acc3);
            accommodationRepository.save(acc4);
            accommodationRepository.save(acc5);

            // Initialize Reviews
            createReview(acc1, "Great location and very clean!", 4.5, reviewRepository);
            createReview(acc1, "Could use better WiFi", 3.8, reviewRepository);
            createReview(acc2, "Absolutely stunning property", 5.0, reviewRepository);
            createReview(acc3, "Authentic Japanese experience", 4.7, reviewRepository);
            createReview(acc4, "Perfect for a family vacation", 4.2, reviewRepository);
            createReview(acc5, "Excellent service and amenities", 4.9, reviewRepository);

            System.out.println("Database initialized with:");
            System.out.println("- 3 countries");
            System.out.println("- 3 hosts");
            System.out.println("- 5 accommodations (1 rented)");
            System.out.println("- 6 reviews");
        };
    }

    private void createReview(Accomodation accommodation, String comment,
                              Double rating, ReviewRepository reviewRepository) {
        Review review = new Review();
        review.setComment(comment);
        review.setRating(rating);
        review.setAccommodation(accommodation);
        reviewRepository.save(review);

        // Add to accommodation's review list (managed by JPA but we do it explicitly here)
        accommodation.getReviews().add(review);
    }
}