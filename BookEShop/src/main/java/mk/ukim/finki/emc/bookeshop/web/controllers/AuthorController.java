package mk.ukim.finki.emc.bookeshop.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.emc.bookeshop.dto.CreateAuthorDto;
import mk.ukim.finki.emc.bookeshop.dto.DisplayAuthorDto;
import mk.ukim.finki.emc.bookeshop.model.projections.AuthorProjection;
import mk.ukim.finki.emc.bookeshop.model.views.AuthorsPerCountryView;
import mk.ukim.finki.emc.bookeshop.service.application.AuthorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Author API", description = "Endpoints for managing authors")
public class AuthorController {

    private final AuthorApplicationService authorApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @Operation(
            summary = "Get all authors",
            description = "Retrieves a list of all available authors."
    )
    @GetMapping
    public List<DisplayAuthorDto> findAll() {
        return authorApplicationService.findAll();
    }

    @Operation(summary = "Add a new author", description = "Creates a new author.")
    @PostMapping("/add")
    public ResponseEntity<DisplayAuthorDto> save(@RequestBody CreateAuthorDto author) {
        return authorApplicationService.save(author)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get author by ID", description = "Finds a author by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id) {
        return authorApplicationService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an existing author", description = "Updates a author by ID.")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAuthorDto> update(@PathVariable Long id, @RequestBody CreateAuthorDto author) {
        return authorApplicationService.update(id, author)
                .map(a -> ResponseEntity.ok().body(a))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a author", description = "Deletes a author by its ID.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (authorApplicationService.findById(id).isPresent()) {
            authorApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Get authors' name and surname",
            description = "Retrieves a list of all authors by their name and surname."
    )
    @GetMapping("/names")
    public List<AuthorProjection> getAuthorsByNamesAndSurnames() {
        return authorApplicationService.getAuthorsByNameAndSurname();
    }

    @Operation(
            summary = "Get authors by country",
            description = "Retrieves a list of all authors grouped by country."
    )
    @GetMapping("/by-country")
    public List<AuthorsPerCountryView> getAuthorsByCountry() {
        return authorApplicationService.findAuthorsPerCountry();
    }

}
