package mk.ukim.finki.emc.bookeshop.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emc.bookeshop.model.enumerations.WishlistStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany
    private List<Book> books;

    @Enumerated(value = EnumType.STRING)
    private WishlistStatus wishlistStatus;

    public Wishlist() {
    }

    public Wishlist(User user) {
        this.date = LocalDateTime.now();
        this.user = user;
        this.books = new ArrayList<>();
        this.wishlistStatus = WishlistStatus.CREATED;
    }

}
