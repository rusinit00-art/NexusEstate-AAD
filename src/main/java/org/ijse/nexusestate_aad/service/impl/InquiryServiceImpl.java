package org.ijse.nexusestate_aad.service.impl;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.InquiryDTO;
import org.ijse.nexusestate_aad.entity.Interactions.Inquiry;
import org.ijse.nexusestate_aad.repository.InquiryRepository;
import org.ijse.nexusestate_aad.repository.PropertyRepository;
import org.ijse.nexusestate_aad.repository.UserRepository;
import org.ijse.nexusestate_aad.service.InquiryService;
import org.ijse.nexusestate_aad.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    private void notifyAllAdmins(String message) {
        try {
            userRepository.findAll().stream()
                    .filter(u -> {
                        String userInfo = u.toString().toUpperCase();
                        return userInfo.contains("ADMIN") ||
                                (u.getUsername() != null && u.getUsername().equalsIgnoreCase("Rusini"));
                    })
                    .forEach(admin -> notificationService.createNotification(admin.getId(), "[ADMIN ALERT] " + message));
        } catch (Exception ignored) {}
    }

    @Override
    public String saveInquiry(InquiryDTO dto) {
        Inquiry inquiry = new Inquiry();
        inquiry.setMessage(dto.getMessage());
        inquiry.setDate(LocalDateTime.now());

        var property = propertyRepository.findById(dto.getPropertyId()).orElse(null);
        var user = userRepository.findById(dto.getUserId()).orElse(null);

        inquiry.setProperty(property);
        inquiry.setUser(user);
        inquiryRepository.save(inquiry);

        // 🔔 1. Seller ට Notification යැවීම
        try {
            if (property != null && property.getSeller() != null) {
                String senderName = (user != null) ? user.getUsername() : "A Buyer";
                notificationService.createNotification(
                        property.getSeller().getId(),
                        "New inquiry from " + senderName + " on Asset #" + property.getId() + ": \"" + dto.getMessage() + "\""
                );
            }
        } catch (Exception ignored) {}

        // 🔔 2. Admin ට Notification යැවීම
        try {
            String senderName = (user != null) ? user.getUsername() : "A Buyer";
            notifyAllAdmins("New Inquiry received from " + senderName + " for Asset #" + dto.getPropertyId());
        } catch (Exception ignored) {}

        return "Inquiry sent successfully!";
    }

    @Override
    public void updateReply(Long id, String reply) {
        Inquiry inquiry = inquiryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));
        inquiry.setReply(reply);
        inquiryRepository.save(inquiry);

        // 🔔 3. Buyer ට Notification යැවීම
        try {
            if (inquiry.getUser() != null) {
                notificationService.createNotification(
                        inquiry.getUser().getId(),
                        "Seller replied to your inquiry on Asset #" + (inquiry.getProperty() != null ? inquiry.getProperty().getId() : "") + ": \"" + reply + "\""
                );
            }
        } catch (Exception ignored) {}

        // 🔔 4. Admin ට Notification යැවීම
        try {
            notifyAllAdmins("Inquiry #" + id + " was replied: \"" + reply + "\"");
        } catch (Exception ignored) {}
    }

    @Override
    public List<InquiryDTO> getAllInquiries() {
        return inquiryRepository.findAll().stream().map(i -> new InquiryDTO(
                i.getId(),
                i.getMessage(),
                i.getDate(),
                i.getProperty() != null ? i.getProperty().getId() : null,
                i.getUser() != null ? i.getUser().getId() : null,
                i.getReply(),
                i.getUser() != null ? i.getUser().getUsername() : "Unknown Node"
        )).collect(Collectors.toList());
    }
}