package mk.ukim.finki.emc.bookeshop.service.domain.impl;

import mk.ukim.finki.emc.bookeshop.dto.DisplayBookDto;
import mk.ukim.finki.emc.bookeshop.model.domain.Book;
import mk.ukim.finki.emc.bookeshop.repository.BookRepository;
import mk.ukim.finki.emc.bookeshop.repository.BooksPerAuthorRepository;
import mk.ukim.finki.emc.bookeshop.service.domain.AuthorService;
import mk.ukim.finki.emc.bookeshop.service.domain.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BooksPerAuthorRepository booksPerAuthorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, BooksPerAuthorRepository booksPerAuthorRepository) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.booksPerAuthorRepository = booksPerAuthorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
        //return bookRepository.findAll().stream().filter(book -> !book.isIsDeleted()).toList();
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> save(Book book) {
        if (book.getName() != null
                && book.getAvailableCopies() != null
                && book.getCategory() != null
                && book.getAuthor().getId() != null
                && authorService.findById(book.getAuthor().getId()).isPresent()) {
            return Optional.of(bookRepository.save(new Book(book.getName(),
                    book.getCategory(),
                    book.getAuthor(),
                    book.getAvailableCopies(),
                    book.getDateCreated())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
//        Book book = bookRepository.findById(id).get();
//        if (!book.isIsDeleted()) {
//            return Optional.of(book);
//        }
//        return Optional.empty();
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id).map(existingBook -> {
//            if (book.isIsDeleted()) {
//                return bookRepository.save(existingBook);
//            }
            if (book.getName() != null) {
                existingBook.setName(book.getName());
            }
            if (book.getAuthor().getId() != null && authorService.findById(book.getAuthor().getId()).isPresent()) {
                existingBook.setAuthor(book.getAuthor());
            }
            if (book.getCategory() != null) {
                existingBook.setCategory(book.getCategory());
            }
            if (book.getAvailableCopies() != null) {
                existingBook.setAvailableCopies(book.getAvailableCopies());
            }
            if (book.getDateCreated() != null) {
                existingBook.setDateCreated(book.getDateCreated());
            }
//            if (!book.isIsDeleted()) {
//                existingBook.setIsDeleted(false);
//            }
            return bookRepository.save(existingBook);
        });
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
//        Book book = bookRepository.findById(id).get();
//        book.setIsDeleted(true);
//        bookRepository.save(book);
    }

    @Override
    public void rented(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID"));

        if (book.getAvailableCopies() >= 1 /*&& !book.isIsDeleted()*/) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
        }
    }

    @Override
    public List<Book> findTopTenBooks() {
        return this.findAll().stream()
                .sorted(Comparator.comparing(Book::getDateCreated).reversed())
                .limit(10).toList();
    }

    @Override
    public void refreshMaterializedView() {
        booksPerAuthorRepository.refreshMaterializedView();
    }

}