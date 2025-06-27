package mk.ukim.finki.emc.bookeshop.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.emc.bookeshop.dto.WishlistDto;
import mk.ukim.finki.emc.bookeshop.model.domain.User;
import mk.ukim.finki.emc.bookeshop.service.application.WishlistApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
@Tag(name = "Wishlist API", description = "Endpoints for managing the shopping cart")
public class WishlistController {

    private final WishlistApplicationService wishlistApplicationService;

    public WishlistController(WishlistApplicationService wishlistApplicationService) {
        this.wishlistApplicationService = wishlistApplicationService;
    }

    @Operation(
            summary = "Get active wishlist",
            description = "Retrieves the active wishlist for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Wishlist retrieved successfully"
            ), @ApiResponse(responseCode = "404", description = "Wishlist not found")}
    )
    @GetMapping
    public ResponseEntity<WishlistDto> getActiveShoppingCart(HttpServletRequest request) {
        String username = request.getRemoteUser();
        return wishlistApplicationService.getActiveShoppingCart(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Add product to wishlist",
            description = "Adds a product to the wishlist for the logged-in user"
    )
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200", description = "Product added to wishlist successfully"
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request"
            ), @ApiResponse(responseCode = "404", description = "Book not found")}
    )
    @PostMapping("/add-product/{id}")
    public ResponseEntity<WishlistDto> addProductToWishlist(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        try {
            return wishlistApplicationService.addBookToWishlist(user.getUsername(), id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

}
