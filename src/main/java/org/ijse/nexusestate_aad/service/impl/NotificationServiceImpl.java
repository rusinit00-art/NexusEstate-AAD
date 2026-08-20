package org.ijse.nexusestate_aad.service.impl;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.NotificationDTO;
import org.ijse.nexusestate_aad.entity.CustomerExperienceandAI.Notification;
import org.ijse.nexusestate_aad.repository.NotificationRepository;
import org.ijse.nexusestate_aad.repository.UserRepository;
import org.ijse.nexusestate_aad.service.NotificationService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository NotificationRepository;
    private final UserRepository userRepository;

    @Override
    public void createNotification(Long userId, String message) {
        Notification n = new Notification();
        n.setMessage(message);
        n.setUser(userRepository.findById(userId).orElseThrow());
        n.setIsRead(false);
        NotificationRepository.save(n);
    }

    @Override
    public List<NotificationDTO> getNotificationsByUser(Long userId) {
        return NotificationRepository.findAll().stream()
                .filter(n -> n.getUser().getId().equals(userId))
                .map(n -> new NotificationDTO(n.getId(), n.getMessage(), n.getIsRead(), n.getUser().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void markAsRead(Long id) {
        Notification n = NotificationRepository.findById(id).orElseThrow();
        n.setIsRead(true);
        NotificationRepository.save(n);
    }
}