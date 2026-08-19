package org.ijse.nexusestate_aad.service.impl;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.AppoinmentDTO;
import org.ijse.nexusestate_aad.entity.Interactions.Appoinment;
import org.ijse.nexusestate_aad.enumiration.AppoinmentStatus;
import org.ijse.nexusestate_aad.repository.AppoinmentRepository;
import org.ijse.nexusestate_aad.repository.PropertyRepository;
import org.ijse.nexusestate_aad.repository.UserRepository;
import org.ijse.nexusestate_aad.service.AppoinmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppoinmentServiceImpl implements AppoinmentService {

    private final AppoinmentRepository appoinmentRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Override
    public String saveAppoinment(AppoinmentDTO dto) {
        Appoinment appoinment = new Appoinment();
        appoinment.setAppoinmentDate(dto.getAppoinmentDate());
        appoinment.setStatus(AppoinmentStatus.PENDING);

        appoinment.setProperty(propertyRepository.findById(dto.getPropertyId()).orElse(null));
        appoinment.setUser(userRepository.findById(dto.getUserId()).orElse(null));

        appoinmentRepository.save(appoinment);
        return "Appoinment booked successfully!";
    }

    @Override
    public List<AppoinmentDTO> getAllAppoinments() {
        return appoinmentRepository.findAll().stream().map(a -> new AppoinmentDTO(
                a.getId(),
                a.getAppoinmentDate(),
                a.getStatus(),
                a.getProperty() != null ? a.getProperty().getId() : null,
                a.getUser() != null ? a.getUser().getId() : null
        )).collect(Collectors.toList());
    }

    @Override
    public String updateStatus(Long id, String status) {
        Appoinment a = appoinmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appoinment not found"));
        a.setStatus(AppoinmentStatus.valueOf(status.toUpperCase()));
        appoinmentRepository.save(a);
        return "Status updated to " + status;
    }
}