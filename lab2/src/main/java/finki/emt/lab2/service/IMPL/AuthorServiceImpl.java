package finki.emt.lab2.service.IMPL;


import finki.emt.lab2.model.Author;
import finki.emt.lab2.model.Country;
import finki.emt.lab2.model.exceptions.AuthorNotFoundException;
import finki.emt.lab2.model.exceptions.CountryNotFoundException;
import finki.emt.lab2.repository.AuthorRepository;
import finki.emt.lab2.repository.CountryRepository;
import finki.emt.lab2.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long authorId) {
        return this.authorRepository.findById(authorId);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {

        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
        Author author = new Author(name, surname, country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> edit(Long authorId, String name, String surname, Long countryId) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));

        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long authorId) {
        this.authorRepository.deleteById(authorId);
    }
}
