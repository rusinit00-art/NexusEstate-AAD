package org.ijse.nexusestate_aad.controller;
import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.WishlistDTO;
import org.ijse.nexusestate_aad.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/wishlist")
@RequiredArgsConstructor

public class WishlistController {
    private final WishlistService wishlistService;

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody WishlistDTO dto) {
        return ResponseEntity.ok(wishlistService.addToWishlist(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WishlistDTO>> get(@PathVariable Long userId) {
        return ResponseEntity.ok(wishlistService.getWishlistByUser(userId));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistService.removeFromWishlist(id));
    }

}