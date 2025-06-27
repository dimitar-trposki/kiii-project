package mk.ukim.finki.emc.bookeshop.service.application;

import mk.ukim.finki.emc.bookeshop.dto.CreateAuthorDto;
import mk.ukim.finki.emc.bookeshop.dto.DisplayAuthorDto;
import mk.ukim.finki.emc.bookeshop.model.projections.AuthorProjection;
import mk.ukim.finki.emc.bookeshop.model.views.AuthorsPerCountryView;
import mk.ukim.finki.emc.bookeshop.model.views.BooksPerAuthorView;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {

    List<DisplayAuthorDto> findAll();

    Optional<DisplayAuthorDto> save(CreateAuthorDto author);

    Optional<DisplayAuthorDto> findById(Long id);

    Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto author);

    void deleteById(Long id);

    List<BooksPerAuthorView> findAllBooksByAuthor();

    List<AuthorProjection> getAuthorsByNameAndSurname();

    void refreshMaterializedView();

    List<AuthorsPerCountryView> findAuthorsPerCountry();

}
