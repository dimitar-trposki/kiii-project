package mk.ukim.finki.emc.bookeshop.service.application.impl;

import mk.ukim.finki.emc.bookeshop.dto.CreateBookDto;
import mk.ukim.finki.emc.bookeshop.dto.DisplayBookDto;
import mk.ukim.finki.emc.bookeshop.model.domain.Author;
import mk.ukim.finki.emc.bookeshop.model.domain.Book;
import mk.ukim.finki.emc.bookeshop.repository.BooksPerAuthorRepository;
import mk.ukim.finki.emc.bookeshop.service.application.BookApplicationService;
import mk.ukim.finki.emc.bookeshop.service.domain.AuthorService;
import mk.ukim.finki.emc.bookeshop.service.domain.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookService bookService;
    private final AuthorService authorService;
    private final BooksPerAuthorRepository booksPerAuthorRepository;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService, BooksPerAuthorRepository booksPerAuthorRepository) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.booksPerAuthorRepository = booksPerAuthorRepository;
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.from(bookService.findAll());
    }

    @Override
    public Page<DisplayBookDto> findAll(Pageable pageable) {
        return bookService.findAll(pageable)
                .map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto book) {
        Optional<Author> author = authorService.findById(book.authorId());

        if (author.isEmpty()) {
            return Optional.empty();
        }
        return bookService.save(book.toBook(author.get())).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto book) {
        Optional<Author> author = authorService.findById(book.authorId());

        return bookService.update(id, book.toBook(author.orElse(null))).map(DisplayBookDto::from);
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    @Override
    public void rented(Long id) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID"));

        if (book.getAvailableCopies() >= 1 /*&& !book.isIsDeleted()*/) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookService.save(book);
        }
    }

    @Override
    public List<DisplayBookDto> findTopTenBooks() {
        return DisplayBookDto.from(bookService.findTopTenBooks());
    }

    @Override
    public void refreshMaterializedView() {
        booksPerAuthorRepository.refreshMaterializedView();
    }

}
