package mk.ukim.finki.emc.bookeshop.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.emc.bookeshop.model.enumerations.Category;

import java.time.LocalDateTime;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;

    private Integer availableCopies;

    private boolean isDeleted;

    private LocalDateTime dateCreated;

    public Book() {
    }

    public Book(String name, Category category, Author author, Integer availableCopies, LocalDateTime dateCreated) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.isDeleted = false;
        this.dateCreated = LocalDateTime.now();
    }

    public Book(Long id, String name, Category category, Author author, Integer availableCopies, LocalDateTime dateCreated) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.isDeleted = false;
        this.dateCreated = dateCreated;
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public Author getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(Author author) {
//        this.author = author;
//    }
//
//    public Integer getAvailableCopies() {
//        return availableCopies;
//    }
//
//    public void setAvailableCopies(Integer availableCopies) {
//        this.availableCopies = availableCopies;
//    }
//
//    public LocalDateTime getDateCreated() {
//        return dateCreated;
//    }
//
//    public void setDateCreated(LocalDateTime dateCreated) {
//        this.dateCreated = dateCreated;
//    }
}
