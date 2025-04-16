package mk.ukim.finki.lab1b.service.domain.impl;

import mk.ukim.finki.lab1b.model.domain.Accommodation;
import mk.ukim.finki.lab1b.model.domain.Host;
import mk.ukim.finki.lab1b.model.enumerations.Category;
import mk.ukim.finki.lab1b.repository.AccommodationRepository;
import mk.ukim.finki.lab1b.service.domain.AccommodationService;
import mk.ukim.finki.lab1b.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if(accommodation.getName()!=null && accommodation.getCategory()!=null
        && accommodation.getNumRooms()!=null &&
                hostService.findById(accommodation.getHost().getId()).isPresent()){
            return Optional.of(accommodationRepository.save(new Accommodation(
                    accommodation.getName(),
                    accommodation.getCategory(),
                    hostService.findById(accommodation.getHost().getId()).get(),
                    accommodation.getNumRooms()
            )));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return accommodationRepository.findById(id).map(existingAccommodation->{
           if(accommodation.getName()!=null)
               existingAccommodation.setName(accommodation.getName());
           if(accommodation.getCategory()!=null)
               existingAccommodation.setCategory(accommodation.getCategory());
           if(accommodation.getNumRooms()!=null)
               existingAccommodation.setNumRooms(accommodation.getNumRooms());
           if(accommodation.getHost()!=null && hostService.findById(accommodation.getHost().getId()).isPresent())
               hostService.findById(accommodation.getHost().getId()).get();
           return accommodationRepository.save(existingAccommodation);
        });
    }

    @Override
    public void deleteById(Long id) {
        Accommodation accommodation=accommodationRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Accommodation not found with id"+id));
        accommodationRepository.delete(accommodation);
    }

//    @Override
//    public Optional<Accommodation> rentRoom(Long id, AccommodationDto accommodationDto) {
//        return accommodationRepository.findById(id).map(existingAccommodation->{
//            if(accommodationDto.getNumRooms()>0)
//                existingAccommodation.setNumRooms(existingAccommodation.getNumRooms()-1);
//            return accommodationRepository.save(existingAccommodation);
//        });
//    }
    @Override
    public Optional<Accommodation> rentRoom(Long id) {
        return accommodationRepository.findById(id).map(existingAccommodation->{
            if(existingAccommodation.getNumRooms()>0)
                existingAccommodation.setNumRooms(existingAccommodation.getNumRooms()-1);
            return accommodationRepository.save(existingAccommodation);
        });
    }


    @Override
    public List<Accommodation> searchAccommodations(String name, Category category, Host host, Integer numRooms) {
        return accommodationRepository.findAll().stream()
                .filter(acc -> name == null || acc.getName().contains(name))
                .filter(acc -> category == null || acc.getCategory() == category)
                .filter(acc -> host == null || acc.getHost().equals(host))
                .filter(acc -> numRooms == null || acc.getNumRooms().equals(numRooms))
                .collect(Collectors.toList());
    }
}
