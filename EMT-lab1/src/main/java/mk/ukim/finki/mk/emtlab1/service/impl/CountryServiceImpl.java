package mk.ukim.finki.mk.emtlab1.service.impl;

import mk.ukim.finki.mk.emtlab1.model.Country;
import mk.ukim.finki.mk.emtlab1.repository.CountryRepository;
import mk.ukim.finki.mk.emtlab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }



    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }
}
