package org.ijse.nexusestate_aad.service.impl;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.WishlistDTO;
import org.ijse.nexusestate_aad.entity.CustomerExperienceandAI.WishList;
import org.ijse.nexusestate_aad.repository.*;
import org.ijse.nexusestate_aad.service.WishlistService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Override
    public String addToWishlist(WishlistDTO dto) {
        WishList wish = new WishList();
        wish.setProperty(propertyRepository.findById(dto.getPropertyId()).orElseThrow());
        wish.setUser(userRepository.findById(dto.getUserId()).orElseThrow());
        wishlistRepository.save(wish);
        return "Added to Wishlist!";
    }

    @Override
    public List<WishlistDTO> getWishlistByUser(Long userId) {
        return wishlistRepository.findAll().stream()
                .filter(w -> w.getUser().getId().equals(userId))
                .map(w -> new WishlistDTO(w.getId(), w.getUser().getId(), w.getProperty().getId(), w.getProperty().getTitle(), w.getProperty().getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public String removeFromWishlist(Long id) {
        wishlistRepository.deleteById(id);
        return "Removed!";
    }
}