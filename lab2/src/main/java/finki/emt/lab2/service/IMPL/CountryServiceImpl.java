package finki.emt.lab2.service.IMPL;


import finki.emt.lab2.model.Country;
import finki.emt.lab2.model.exceptions.CountryNotFoundException;
import finki.emt.lab2.repository.CountryRepository;
import finki.emt.lab2.service.CountryService;
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

    @Override
    public Optional<Country> findById(Long countryId) {
        return Optional.of(this.countryRepository.findById(countryId))
                .orElseThrow(() -> new CountryNotFoundException(countryId));
    }
}
