package mk.ukim.finki.emc.bookeshop.repository;

import mk.ukim.finki.emc.bookeshop.model.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
