package finki.emt.lab2.service;




import finki.emt.lab2.model.Book;
import finki.emt.lab2.model.BookDto;
import finki.emt.lab2.model.enumaration.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long bookId);

    Optional<Book> save(String name, Category category, Long  authorId, Integer availableCopies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long bookId, String name, Category category, Long authorId, Integer availableCopies);

    public Optional<Book> edit(Long bookId, BookDto bookDto);

    void deleteById(Long bookId);
}