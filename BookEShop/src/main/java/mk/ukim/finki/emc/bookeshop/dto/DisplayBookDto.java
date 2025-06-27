package mk.ukim.finki.emc.bookeshop.dto;

import mk.ukim.finki.emc.bookeshop.model.domain.Book;
import mk.ukim.finki.emc.bookeshop.model.enumerations.Category;

import java.time.LocalDateTime;
import java.util.List;

public record DisplayBookDto(Long id, String name, Category category, Long authorId, Integer availableCopies,
                             LocalDateTime dateCreated) {

    public static DisplayBookDto from(Book book) {
        return new DisplayBookDto(book.getId(), book.getName(), book.getCategory(), book.getAuthor().getId(), book.getAvailableCopies(), book.getDateCreated());
    }

    public static List<DisplayBookDto> from(List<Book> books) {
        return books.stream().map(DisplayBookDto::from).toList();
    }

}
