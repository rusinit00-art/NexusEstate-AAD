package org.ijse.nexusestate_aad.entity.Interactions;

import jakarta.persistence.*;
import lombok.*;
import org.ijse.nexusestate_aad.entity.SecurityandUsers.User;
import org.ijse.nexusestate_aad.entity.PropertyFoundation.Property;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    private User user;

    @ManyToOne
    private Property property;
}