package mk.ukim.finki.mk.emtlab1.service.impl;

import mk.ukim.finki.mk.emtlab1.model.Accomodation;
import mk.ukim.finki.mk.emtlab1.model.DTOs.BookingDto;
import mk.ukim.finki.mk.emtlab1.model.Host;
import mk.ukim.finki.mk.emtlab1.repository.AccommodationRepository;
import mk.ukim.finki.mk.emtlab1.repository.HostRepository;
import mk.ukim.finki.mk.emtlab1.service.AccomodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccomodationService {

    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostRepository hostRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Accomodation> findAll() {
        return this.accommodationRepository.findAll();
    }

    @Override
    public Accomodation create(BookingDto bookingDto) throws Exception {
        Host host_obj = this.hostRepository.findById(bookingDto.getHostId()).orElseThrow(Exception::new);
        Accomodation accomodation_obj = new Accomodation(bookingDto.getName(), bookingDto.getCategory(), host_obj, bookingDto.getNumOfRooms());
        return this.accommodationRepository.save(accomodation_obj);
    }

    @Override
    public Accomodation update(Long id, BookingDto bookingDto) throws Exception {

        Accomodation accomodation_obj = this.accommodationRepository.findById(id).orElseThrow(Exception::new);
        Host host_obj = this.hostRepository.findById(id).orElseThrow(Exception::new);

        accomodation_obj.setName(bookingDto.getName());
        accomodation_obj.setCategory(bookingDto.getCategory());
        accomodation_obj.setHost(host_obj);
        accomodation_obj.setNumRooms(bookingDto.getNumOfRooms());

        return this.accommodationRepository.save(accomodation_obj);
    }

    @Override
    public void delete(Long ID) {
        this.accommodationRepository.deleteById(ID);
    }

    @Override
    public Accomodation reservation(Long ID) throws Exception {
        Accomodation accomodation_obj = this.accommodationRepository.findById(ID).orElseThrow(Exception::new);

        if (accomodation_obj.isRented()) {
            return accomodation_obj;
        } else {
            accomodation_obj.setNumRooms(accomodation_obj.getNumRooms() - 1);
            return this.accommodationRepository.save(accomodation_obj);
        }
    }
}
