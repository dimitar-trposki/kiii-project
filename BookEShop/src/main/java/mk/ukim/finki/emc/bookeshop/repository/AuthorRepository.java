package mk.ukim.finki.emc.bookeshop.repository;

import mk.ukim.finki.emc.bookeshop.model.domain.Author;
import mk.ukim.finki.emc.bookeshop.model.projections.AuthorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a")
    List<AuthorProjection> getAuthorsByNameAndSurname();

}