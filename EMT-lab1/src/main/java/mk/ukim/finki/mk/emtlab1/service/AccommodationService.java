package mk.ukim.finki.mk.emtlab1.service;

import mk.ukim.finki.mk.emtlab1.model.Accomodation;
import mk.ukim.finki.mk.emtlab1.repository.AccommodationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;

    public AccommodationService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    public List<Accomodation> findAll() {
        return accommodationRepository.findAll();
    }

    public Optional<Accomodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    public Accomodation save(Accomodation accommodation) {
        return accommodationRepository.save(accommodation);
    }

    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    public List<Accomodation> findByRentalStatus(boolean isRented) {
        return accommodationRepository.findByIsRented(isRented);
    }

    public Accomodation markAsRented (Long id){
        Optional<Accomodation> accomodation = accommodationRepository.findById(id);
        if (accomodation.isPresent()) {
            Accomodation a = accomodation.get();
            a.setRented(true);
            return accommodationRepository.save(a);
        }
        return null;
    }

    public Accomodation markAsAvailable (Long id){
        Optional<Accomodation> accomodation = accommodationRepository.findById(id);
        if (accomodation.isPresent()) {
            Accomodation a = accomodation.get();
            a.setRented(false);
            return accommodationRepository.save(a);
        }
        return null;
    }

}
