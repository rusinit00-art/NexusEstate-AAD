package org.ijse.nexusestate_aad.entity.Interactions;

import jakarta.persistence.*;
import lombok.*;
import org.ijse.nexusestate_aad.entity.SecurityandUsers.User;
import org.ijse.nexusestate_aad.entity.PropertyFoundation.Property;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Property property;
}