package mk.ukim.finki.emc.bookeshop.repository;

import mk.ukim.finki.emc.bookeshop.model.domain.User;
import mk.ukim.finki.emc.bookeshop.model.domain.Wishlist;
import mk.ukim.finki.emc.bookeshop.model.enumerations.WishlistStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    Optional<Wishlist> findByUserAndWishlistStatus(User user, WishlistStatus wishlistStatus);

}
