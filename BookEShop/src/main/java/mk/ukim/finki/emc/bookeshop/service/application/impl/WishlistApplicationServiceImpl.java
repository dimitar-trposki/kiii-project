package mk.ukim.finki.emc.bookeshop.service.application.impl;

import mk.ukim.finki.emc.bookeshop.dto.DisplayBookDto;
import mk.ukim.finki.emc.bookeshop.dto.WishlistDto;
import mk.ukim.finki.emc.bookeshop.service.application.WishlistApplicationService;
import mk.ukim.finki.emc.bookeshop.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishlistApplicationServiceImpl implements WishlistApplicationService {

    private final WishlistService wishlistService;

    public WishlistApplicationServiceImpl(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Override
    public List<DisplayBookDto> listAllBooksInWishlist(Long wishlistId) {
        return DisplayBookDto.from(wishlistService.listAllBooksInWishlist(wishlistId));
    }

    @Override
    public Optional<WishlistDto> getActiveShoppingCart(String username) {
        return wishlistService.getActiveShoppingCart(username).map(WishlistDto::from);
    }

    @Override
    public Optional<WishlistDto> addBookToWishlist(String username, Long bookId) {
        return wishlistService.addBookToWishlist(username, bookId).map(WishlistDto::from);
    }

}
