package mk.ukim.finki.emc.bookeshop.service.domain;

import mk.ukim.finki.emc.bookeshop.model.domain.Country;
import mk.ukim.finki.emc.bookeshop.dto.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    List<Country> findAll();

    Optional<Country> save(Country country);

    Optional<Country> findById(Long id);

    Optional<Country> update(Long id, Country country);

    void deleteById(Long id);

}