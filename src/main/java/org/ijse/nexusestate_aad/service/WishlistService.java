package org.ijse.nexusestate_aad.service;
import org.ijse.nexusestate_aad.dto.WishlistDTO;
import java.util.List;

public interface WishlistService {
    String addToWishlist(WishlistDTO dto);
    List<WishlistDTO> getWishlistByUser(Long userId);
    String removeFromWishlist(Long id);
}