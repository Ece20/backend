package finki.emt.lab2.service.IMPL;



import finki.emt.lab2.model.Author;
import finki.emt.lab2.model.Book;
import finki.emt.lab2.model.BookDto;
import finki.emt.lab2.model.enumaration.Category;
import finki.emt.lab2.model.exceptions.AuthorNotFoundException;
import finki.emt.lab2.model.exceptions.BookNotFoundException;
import finki.emt.lab2.repository.AuthorRepository;
import finki.emt.lab2.repository.BookRepository;
import finki.emt.lab2.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {

        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long bookId) {

        return Optional.of(this.bookRepository.findById(bookId))
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies) {

        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = new Book(name, category, author, availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {

        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));

        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long bookId, String name, Category category, Long authorId, Integer availableCopies) {

        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long bookId, BookDto bookDto) {

        Book book = this.bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(book));

    }


    @Override
    public void deleteById(Long bookId) {

        this.bookRepository.deleteById(bookId);
    }
}

