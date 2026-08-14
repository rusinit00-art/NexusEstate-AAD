package org.ijse.nexusestate_aad.entity.PropertyDetails;

import jakarta.persistence.*;
import lombok.*;
import org.ijse.nexusestate_aad.entity.PropertyFoundation.Property;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Propertydocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentName;

    private String documentType;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}