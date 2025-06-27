package mk.ukim.finki.emc.bookeshop.service.application.impl;

import mk.ukim.finki.emc.bookeshop.dto.CreateAuthorDto;
import mk.ukim.finki.emc.bookeshop.dto.DisplayAuthorDto;
import mk.ukim.finki.emc.bookeshop.model.domain.Country;
import mk.ukim.finki.emc.bookeshop.model.projections.AuthorProjection;
import mk.ukim.finki.emc.bookeshop.model.views.AuthorsPerCountryView;
import mk.ukim.finki.emc.bookeshop.model.views.BooksPerAuthorView;
import mk.ukim.finki.emc.bookeshop.service.application.AuthorApplicationService;
import mk.ukim.finki.emc.bookeshop.service.domain.AuthorService;
import mk.ukim.finki.emc.bookeshop.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return DisplayAuthorDto.from(authorService.findAll());
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto author) {
        Optional<Country> country = countryService.findById(author.countryId());

        if (country.isEmpty()) {
            return Optional.empty();
        }
        return authorService.save(author.toAuthor(country.get())).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto author) {
        Optional<Country> country = countryService.findById(author.countryId());

        return authorService.update(id, author.toAuthor(country.orElse(null))).map(DisplayAuthorDto::from);
    }

    @Override
    public void deleteById(Long id) {
        authorService.deleteById(id);
    }

    @Override
    public List<BooksPerAuthorView> findAllBooksByAuthor() {
        return authorService.findAllBooksByAuthor();
    }

    @Override
    public List<AuthorProjection> getAuthorsByNameAndSurname() {
        return authorService.getAuthorsByNameAndSurname();
    }

    @Override
    public void refreshMaterializedView() {
        authorService.refreshMaterializedView();
    }

    @Override
    public List<AuthorsPerCountryView> findAuthorsPerCountry() {
        return authorService.findAuthorsPerCountry();
    }

}
