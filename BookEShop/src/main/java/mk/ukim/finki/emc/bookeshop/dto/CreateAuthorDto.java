package mk.ukim.finki.emc.bookeshop.dto;

import mk.ukim.finki.emc.bookeshop.model.domain.Author;
import mk.ukim.finki.emc.bookeshop.model.domain.Country;

import java.util.List;

public record CreateAuthorDto(String name, String surname, Long countryId) {

    public static CreateAuthorDto from(Author author) {
        return new CreateAuthorDto(author.getName(), author.getSurname(), author.getCountry().getId());
    }

    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }

    public static List<CreateAuthorDto> from(List<Author> authors) {
        return authors.stream().map(CreateAuthorDto::from).toList();
    }

}
