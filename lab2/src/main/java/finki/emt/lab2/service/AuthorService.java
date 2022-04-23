package finki.emt.lab2.service;



import finki.emt.lab2.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long authorId);

    Optional<Author> save(String name, String surname, Long countryId);

    Optional<Author> edit(Long authorId, String name, String surname, Long countryId);

    void deleteById(Long authorId);
}