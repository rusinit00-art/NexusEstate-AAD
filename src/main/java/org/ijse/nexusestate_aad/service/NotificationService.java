package org.ijse.nexusestate_aad.service;
import org.ijse.nexusestate_aad.dto.NotificationDTO;
import java.util.List;

public interface NotificationService {
    void createNotification(Long userId, String message);
    List<NotificationDTO> getNotificationsByUser(Long userId);
    void markAsRead(Long id);
}