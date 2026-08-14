package org.ijse.nexusestate_aad.entity.CustomerExperienceandAI;

import jakarta.persistence.*;
import lombok.*;
import org.ijse.nexusestate_aad.entity.SecurityandUsers.User;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chatbot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String userQuery;

    @Column(columnDefinition = "TEXT")
    private String aiResponse;

    private LocalDateTime timestamp = LocalDateTime.now();

    @ManyToOne
    private User user;
}