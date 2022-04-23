package finki.emt.lab2.service;



import finki.emt.lab2.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Optional<Country> findById(Long countryId);
}
