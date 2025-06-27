package mk.ukim.finki.emc.bookeshop.dto;

import lombok.Data;

@Data
public class BookDto {

    private String name;

    private String category;

    private Long authorId;

    private Integer availableCopies;

    private boolean isDeleted;

    public BookDto() {
    }

    public BookDto(String name, String category, Long authorId, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
        this.isDeleted = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}