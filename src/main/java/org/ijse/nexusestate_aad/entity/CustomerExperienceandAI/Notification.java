package org.ijse.nexusestate_aad.entity.CustomerExperienceandAI;

import jakarta.persistence.*;
import lombok.*;
import org.ijse.nexusestate_aad.entity.SecurityandUsers.User;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private Boolean isRead = false;

    @ManyToOne
    private User user;
}