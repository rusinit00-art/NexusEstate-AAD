package org.ijse.nexusestate_aad.entity.Interactions;

import jakarta.persistence.*;
import lombok.*;
import org.ijse.nexusestate_aad.entity.SecurityandUsers.User;
import org.ijse.nexusestate_aad.entity.PropertyFoundation.Property;
import org.ijse.nexusestate_aad.enumiration.AppoinmentStatus;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appoinment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime appoinmentDate;

    @Enumerated(EnumType.STRING)
    private AppoinmentStatus status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Property property;
}