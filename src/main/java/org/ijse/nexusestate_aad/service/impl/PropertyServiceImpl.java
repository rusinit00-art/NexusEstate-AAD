package org.ijse.nexusestate_aad.service.impl;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.PropertyDTO;
import org.ijse.nexusestate_aad.entity.PropertyFoundation.Property;
import org.ijse.nexusestate_aad.exception.ResourceNotFoundException;
import org.ijse.nexusestate_aad.repository.*;
import org.ijse.nexusestate_aad.service.PropertyService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;

    @Override
    public String saveProperty(PropertyDTO dto) {
        Property property = mapToEntity(dto, new Property());
        propertyRepository.save(property);
        return "Property saved successfully!";
    }

    @Override
    public String updateProperty(Long id, PropertyDTO dto) {
        Property existing = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        Property updated = mapToEntity(dto, existing);
        propertyRepository.save(updated);
        return "Property updated successfully!";
    }

    @Override
    public String deleteProperty(Long id) {
        if(!propertyRepository.existsById(id)) throw new ResourceNotFoundException("Property not found");
        propertyRepository.deleteById(id);
        return "Property deleted successfully!";
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public PropertyDTO getPropertyById(Long id) {
        Property p = propertyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Property not found"));
        return mapToDTO(p);
    }

    private Property mapToEntity(PropertyDTO dto, Property entity) {
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setAreaSqft(dto.getAreaSqft());
        entity.setStatus(dto.getStatus());
        entity.setCategory(categoryRepository.findById(dto.getCategoryId()).orElse(null));
        entity.setLocation(locationRepository.findById(dto.getLocationId()).orElse(null));
        entity.setSeller(userRepository.findById(dto.getSellerId()).orElse(null));
        return entity;
    }

    private PropertyDTO mapToDTO(Property p) {
        return new PropertyDTO(p.getId(), p.getTitle(), p.getDescription(), p.getPrice(),
                p.getAreaSqft(), p.getStatus(),
                p.getCategory() != null ? p.getCategory().getId() : null,
                p.getLocation() != null ? p.getLocation().getId() : null,
                p.getSeller() != null ? p.getSeller().getId() : null);
    }
}