package mk.ukim.finki.emc.bookeshop.config.init;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emc.bookeshop.model.domain.Author;
import mk.ukim.finki.emc.bookeshop.model.domain.Book;
import mk.ukim.finki.emc.bookeshop.model.domain.User;
import mk.ukim.finki.emc.bookeshop.model.enumerations.Category;
import mk.ukim.finki.emc.bookeshop.model.domain.Country;
import mk.ukim.finki.emc.bookeshop.model.enumerations.Role;
import mk.ukim.finki.emc.bookeshop.repository.AuthorRepository;
import mk.ukim.finki.emc.bookeshop.repository.BookRepository;
import mk.ukim.finki.emc.bookeshop.repository.CountryRepository;
import mk.ukim.finki.emc.bookeshop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AuthorRepository authorRepository,
                           BookRepository bookRepository,
                           CountryRepository countryRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostConstruct
    public void init() {
        Country USA = new Country("United States of America", "South America");
        Country UK = new Country("United Kingdom", "Europe");
        Country Spain = new Country("Spain", "Europe");

        countryRepository.save(USA);
        countryRepository.save(UK);
        countryRepository.save(Spain);

//        countryRepository.saveAll(List.of(USA, UK, Spain));

        Author HarperLee = new Author("Harper", "Lee", USA);
        Author JaneAusten = new Author("Jane", "Austen", UK);
        Author MigueldeCervantes = new Author("Miguel", "de Cervantes", Spain);

        authorRepository.save(HarperLee);
        authorRepository.save(JaneAusten);
        authorRepository.save(MigueldeCervantes);

//        authorRepository.saveAll(List.of(HarperLee, JaneAusten, MigueldeCervantes));

        Book ToKillAMockingbird = new Book("To Kill a Mockingbird", Category.NOVEL, HarperLee, 150, LocalDateTime.of(1920, 7, 8, 12, 0));
        Book GoSetaWatchman = new Book("Go Set a Watchman", Category.NOVEL, HarperLee, 120, LocalDateTime.of(1925, 7, 8, 12, 0));
        Book PrideAndPrejudice = new Book("To Kill a Mockingbird", Category.NOVEL, JaneAusten, 200, LocalDateTime.of(1970, 7, 8, 12, 0));
        Book Emma = new Book("Emma", Category.DRAMA, JaneAusten, 230, LocalDateTime.of(1965, 7, 8, 12, 0));
        Book DonQuixote = new Book("Don Quixote", Category.NOVEL, MigueldeCervantes, 170, LocalDateTime.of(1999, 7, 8, 12, 0));
        Book Lagitanilla = new Book("La gitanilla", Category.NOVEL, MigueldeCervantes, 50, LocalDateTime.of(1900, 7, 8, 12, 0));

        bookRepository.save(ToKillAMockingbird);
        bookRepository.save(GoSetaWatchman);
        bookRepository.save(PrideAndPrejudice);
        bookRepository.save(Emma);
        bookRepository.save(DonQuixote);
        bookRepository.save(Lagitanilla);

//        bookRepository.saveAll(List.of(ToKillAMockingbird, GoSetaWatchman, PrideAndPrejudice, Emma, DonQuixote, Lagitanilla));

        userRepository.save(new User(
                "dt",
                passwordEncoder.encode("dt"),
                "Dimitar",
                "Trposki",
                Role.ROLE_USER
        ));

        userRepository.save(new User(
                "librarian",
                passwordEncoder.encode("librarian"),
                "Dimitar",
                "Trposki",
                Role.ROLE_LIBRARIAN
        ));
    }

}