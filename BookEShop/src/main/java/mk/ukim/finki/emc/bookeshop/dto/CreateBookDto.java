package mk.ukim.finki.emc.bookeshop.dto;

import mk.ukim.finki.emc.bookeshop.model.domain.Author;
import mk.ukim.finki.emc.bookeshop.model.domain.Book;
import mk.ukim.finki.emc.bookeshop.model.enumerations.Category;

import java.time.LocalDateTime;
import java.util.List;

public record CreateBookDto(String name, Category category, Long authorId, Integer availableCopies,
                            LocalDateTime dateCreated) {

    public static CreateBookDto from(Book book) {
        return new CreateBookDto(book.getName(), book.getCategory(), book.getAuthor().getId(), book.getAvailableCopies(), book.getDateCreated());
    }

    public Book toBook(Author author) {
        return new Book(name, category, author, availableCopies, dateCreated);
    }

    public static List<CreateBookDto> from(List<Book> books) {
        return books.stream().map(CreateBookDto::from).toList();
    }

}
