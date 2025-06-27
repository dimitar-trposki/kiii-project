package mk.ukim.finki.emc.bookeshop.service.application;

import mk.ukim.finki.emc.bookeshop.dto.DisplayBookDto;
import mk.ukim.finki.emc.bookeshop.dto.WishlistDto;

import java.util.List;
import java.util.Optional;

public interface WishlistApplicationService {

    List<DisplayBookDto> listAllBooksInWishlist(Long wishlistId);

    Optional<WishlistDto> getActiveShoppingCart(String username);

    Optional<WishlistDto> addBookToWishlist(String username, Long bookId);

}
