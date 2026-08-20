package org.ijse.nexusestate_aad.controller;
import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.NotificationDTO;
import org.ijse.nexusestate_aad.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationDTO>> get(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getNotificationsByUser(userId));
    }

    @PutMapping("/read/{id}")
    public ResponseEntity<Void> read(@PathVariable Long id) { notificationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }
}