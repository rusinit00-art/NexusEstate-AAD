package org.ijse.nexusestate_aad.entity.CustomerExperienceandAI;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Column(length = 1000)
    private String userPrompt;

    @Column(length = 2000)
    private String aiReply;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private User user;
}