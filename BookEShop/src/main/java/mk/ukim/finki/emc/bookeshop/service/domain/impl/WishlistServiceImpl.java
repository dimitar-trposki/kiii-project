package mk.ukim.finki.emc.bookeshop.service.domain.impl;

import mk.ukim.finki.emc.bookeshop.model.domain.Book;
import mk.ukim.finki.emc.bookeshop.model.domain.User;
import mk.ukim.finki.emc.bookeshop.model.domain.Wishlist;
import mk.ukim.finki.emc.bookeshop.model.enumerations.WishlistStatus;
import mk.ukim.finki.emc.bookeshop.repository.WishlistRepository;
import mk.ukim.finki.emc.bookeshop.service.domain.BookService;
import mk.ukim.finki.emc.bookeshop.service.domain.UserService;
import mk.ukim.finki.emc.bookeshop.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;
    private final UserService userService;
    private final BookService bookService;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, UserService userService, BookService bookService) {
        this.wishlistRepository = wishlistRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public List<Book> listAllBooksInWishlist(Long wishlistId) {
        if (wishlistRepository.findById(wishlistId).isPresent()) {
            return wishlistRepository.findById(wishlistId).get().getBooks();
        }
        throw new RuntimeException();
    }

    @Override
    public Optional<Wishlist> getActiveShoppingCart(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(wishlistRepository.findByUserAndWishlistStatus(user, WishlistStatus.CREATED)
                .orElseGet(() -> wishlistRepository.save(new Wishlist(user))));
    }

    @Override
    public Optional<Wishlist> addBookToWishlist(String username, Long bookId) {
        if (getActiveShoppingCart(username).isPresent()) {
            Wishlist wishlist = getActiveShoppingCart(username).get();

            if (bookService.findById(bookId).isEmpty()) {
                throw new RuntimeException();
            }

            Book book = bookService.findById(bookId).get();

            if (wishlist.getBooks().contains(book)) {
                throw new RuntimeException();
            }

            if (book.getAvailableCopies() <= 0) {
                System.out.println("Not available copies");
                return Optional.empty();
            }

            wishlist.getBooks().add(book);
            return Optional.of(wishlistRepository.save(wishlist));
        }
        return Optional.empty();
    }

}
