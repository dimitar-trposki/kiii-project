package mk.ukim.finki.emc.bookeshop.service.domain.impl;

import mk.ukim.finki.emc.bookeshop.events.AuthorChangedEvent;
import mk.ukim.finki.emc.bookeshop.events.AuthorCreatedEvent;
import mk.ukim.finki.emc.bookeshop.events.AuthorDeletedEvent;
import mk.ukim.finki.emc.bookeshop.model.domain.Author;
import mk.ukim.finki.emc.bookeshop.model.projections.AuthorProjection;
import mk.ukim.finki.emc.bookeshop.model.views.AuthorsPerCountryView;
import mk.ukim.finki.emc.bookeshop.model.views.BooksPerAuthorView;
import mk.ukim.finki.emc.bookeshop.repository.AuthorRepository;
import mk.ukim.finki.emc.bookeshop.repository.AuthorsPerCountryRepository;
import mk.ukim.finki.emc.bookeshop.repository.BooksPerAuthorRepository;
import mk.ukim.finki.emc.bookeshop.service.domain.AuthorService;
import mk.ukim.finki.emc.bookeshop.service.domain.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;
    private final BooksPerAuthorRepository booksPerAuthorRepository;
    private final AuthorsPerCountryRepository authorsPerCountryRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService, BooksPerAuthorRepository booksPerAuthorRepository, AuthorsPerCountryRepository authorsPerCountryRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
        this.booksPerAuthorRepository = booksPerAuthorRepository;
        this.authorsPerCountryRepository = authorsPerCountryRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(Author author) {
        if (author.getName() != null
                && author.getSurname() != null
                && author.getCountry().getId() != null
                && countryService.findById(author.getCountry().getId()).isPresent()) {
            this.applicationEventPublisher.publishEvent(new AuthorCreatedEvent(author));
            return Optional.of(authorRepository.save(new Author(author.getName(),
                    author.getSurname(),
                    author.getCountry())));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> update(Long id, Author author) {
        return authorRepository.findById(id).map(existingAuthor -> {
            if (author.getName() != null) {
                existingAuthor.setName(author.getName());
            }
            if (author.getSurname() != null) {
                existingAuthor.setSurname(author.getSurname());
            }
            if (author.getCountry().getId() != null && countryService.findById(author.getCountry().getId()).isPresent()) {
                existingAuthor.setCountry(author.getCountry());
            }
//            this.refreshMaterializedView();
            this.applicationEventPublisher.publishEvent(new AuthorChangedEvent(author));
            return authorRepository.save(existingAuthor);
        });
    }

    @Override
    public void deleteById(Long id) {
        this.applicationEventPublisher.publishEvent(new AuthorDeletedEvent(authorRepository.findById(id).get()));
        authorRepository.deleteById(id);
    }

    @Override
    public List<BooksPerAuthorView> findAllBooksByAuthor() {
        return booksPerAuthorRepository.findAll();
    }

    @Override
    public List<AuthorProjection> getAuthorsByNameAndSurname() {
        return authorRepository.getAuthorsByNameAndSurname();
    }

    @Override
    public void refreshMaterializedView() {
        authorsPerCountryRepository.refreshMaterializedView();
    }

    @Override
    public List<AuthorsPerCountryView> findAuthorsPerCountry() {
        return authorsPerCountryRepository.findAll();
    }

}