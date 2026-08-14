package org.ijse.nexusestate_aad.service;
import org.ijse.nexusestate_aad.dto.PropertyDTO;
import java.util.List;

public interface PropertyService {
    String saveProperty(PropertyDTO dto);
    String updateProperty(Long id, PropertyDTO dto);
    String deleteProperty(Long id);
    List<PropertyDTO> getAllProperties();
    PropertyDTO getPropertyById(Long id);
}