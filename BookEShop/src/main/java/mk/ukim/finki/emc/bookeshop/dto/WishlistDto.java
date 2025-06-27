package mk.ukim.finki.emc.bookeshop.dto;

import mk.ukim.finki.emc.bookeshop.model.domain.Wishlist;
import mk.ukim.finki.emc.bookeshop.model.enumerations.WishlistStatus;

import java.time.LocalDateTime;
import java.util.List;

public record WishlistDto(Long id,
                          LocalDateTime dateCreated,
                          DisplayUserDto user,
                          List<DisplayBookDto> books,
                          WishlistStatus wishlistStatus) {

    public static WishlistDto from(Wishlist wishlist) {
        return new WishlistDto(wishlist.getId(),
                wishlist.getDate(),
                DisplayUserDto.from(wishlist.getUser()),
                DisplayBookDto.from(wishlist.getBooks()),
                wishlist.getWishlistStatus());
    }

}
