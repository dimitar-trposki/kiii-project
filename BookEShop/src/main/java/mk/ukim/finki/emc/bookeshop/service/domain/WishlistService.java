package mk.ukim.finki.emc.bookeshop.service.domain;

import mk.ukim.finki.emc.bookeshop.model.domain.Book;
import mk.ukim.finki.emc.bookeshop.model.domain.Wishlist;

import java.util.List;
import java.util.Optional;

public interface WishlistService {

    List<Book> listAllBooksInWishlist(Long wishlistId);

    Optional<Wishlist> getActiveShoppingCart(String username);

    Optional<Wishlist> addBookToWishlist(String username, Long bookId);

}
