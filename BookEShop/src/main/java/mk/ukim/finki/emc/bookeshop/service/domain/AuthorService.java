package mk.ukim.finki.emc.bookeshop.service.domain;

import mk.ukim.finki.emc.bookeshop.model.domain.Author;
import mk.ukim.finki.emc.bookeshop.dto.AuthorDto;
import mk.ukim.finki.emc.bookeshop.model.projections.AuthorProjection;
import mk.ukim.finki.emc.bookeshop.model.views.AuthorsPerCountryView;
import mk.ukim.finki.emc.bookeshop.model.views.BooksPerAuthorView;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> save(Author author);

    Optional<Author> findById(Long id);

    Optional<Author> update(Long id, Author author);

    void deleteById(Long id);

    List<BooksPerAuthorView> findAllBooksByAuthor();

    List<AuthorProjection> getAuthorsByNameAndSurname();

    List<AuthorsPerCountryView> findAuthorsPerCountry();

    void refreshMaterializedView();

}