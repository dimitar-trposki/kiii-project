package mk.ukim.finki.emc.bookeshop.dto;

import mk.ukim.finki.emc.bookeshop.model.domain.Author;

import java.util.List;

public record DisplayAuthorDto(Long id, String name, String surname, Long countryId) {

    public static DisplayAuthorDto from(Author author) {
        return new DisplayAuthorDto(author.getId(), author.getName(), author.getSurname(), author.getCountry().getId());
    }

    public static List<DisplayAuthorDto> from(List<Author> authors) {
        return authors.stream().map(DisplayAuthorDto::from).toList();
    }

}
