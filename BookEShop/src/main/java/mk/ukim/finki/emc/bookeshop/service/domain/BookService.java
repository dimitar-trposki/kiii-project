package mk.ukim.finki.emc.bookeshop.service.domain;

import mk.ukim.finki.emc.bookeshop.dto.DisplayBookDto;
import mk.ukim.finki.emc.bookeshop.model.domain.Book;
import mk.ukim.finki.emc.bookeshop.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Page<Book> findAll(Pageable pageable);

    Optional<Book> save(Book book);

    Optional<Book> findById(Long id);

    Optional<Book> update(Long id, Book book);

    void deleteById(Long id);

    void rented(Long id);

    List<Book> findTopTenBooks();

    void refreshMaterializedView();

}
