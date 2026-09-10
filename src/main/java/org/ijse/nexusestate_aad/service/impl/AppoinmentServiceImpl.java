package org.ijse.nexusestate_aad.service.impl;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.AppoinmentDTO;
import org.ijse.nexusestate_aad.entity.Interactions.Appoinment;
import org.ijse.nexusestate_aad.enumiration.AppoinmentStatus;
import org.ijse.nexusestate_aad.repository.AppoinmentRepository;
import org.ijse.nexusestate_aad.repository.PropertyRepository;
import org.ijse.nexusestate_aad.repository.UserRepository;
import org.ijse.nexusestate_aad.service.AppoinmentService;
import org.ijse.nexusestate_aad.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppoinmentServiceImpl implements AppoinmentService {

    private final AppoinmentRepository appoinmentRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;


    private void notifyAllAdmins(String message) {
        try {
            userRepository.findAll().stream()
                    .filter(u -> {String userInfo = u.toString().toUpperCase();
                        return userInfo.contains("ADMIN") ||
                                (u.getUsername() != null && u.getUsername().equalsIgnoreCase("Rusini"));
                    })
                    .forEach(admin -> notificationService.createNotification(admin.getId(), "[ADMIN ALERT] " + message));
        } catch (Exception ignored) {}
    }

    @Override
    public String saveAppoinment(AppoinmentDTO dto) {
        Appoinment appoinment = new Appoinment();
        appoinment.setAppoinmentDate(dto.getAppoinmentDate());
        appoinment.setStatus(AppoinmentStatus.PENDING);

        var property = propertyRepository.findById(dto.getPropertyId()).orElseThrow();
        var buyer = userRepository.findById(dto.getUserId()).orElseThrow();

        appoinment.setProperty(property);
        appoinment.setUser(buyer);

        appoinmentRepository.save(appoinment);

        try {
            if (property.getSeller() != null) {
                Long sellerId = property.getSeller().getId();
                notificationService.createNotification(
                        sellerId,
                        "New viewing booked by " + buyer.getUsername() + " for Asset #" + property.getId() + " on " + dto.getAppoinmentDate()
                );
            }
        } catch (Exception ignored) {}

        try {
            notifyAllAdmins("New viewing booked by " + buyer.getUsername() + " for Asset #" + property.getId());
        } catch (Exception ignored) {}

        return "Appoinment booked successfully!";
    }

    @Override
    public List<AppoinmentDTO> getAllAppoinments() {
        return appoinmentRepository.findAll().stream().map(a -> new AppoinmentDTO(
                a.getId(), a.getAppoinmentDate(), a.getStatus(),
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

        try {
            if (a.getUser() != null) {
                notificationService.createNotification(
                        a.getUser().getId(),
                        "Your viewing #N0" + a.getId() + " status was updated to: " + status.toUpperCase()
                );
            }
        } catch (Exception ignored) {}

        return "Status updated to " + status;
    }
}