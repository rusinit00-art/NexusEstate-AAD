package org.ijse.nexusestate_aad.entity.PropertyFoundation;

import jakarta.persistence.*;
import lombok.*;
import org.ijse.nexusestate_aad.entity.SecurityandUsers.User;
import org.ijse.nexusestate_aad.entity.PropertyDetails.Amenity;
import org.ijse.nexusestate_aad.enumiration.PropertyStatus;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double price;

    private Double areaSqft;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private PropertyCategory category;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    @ManyToMany
    @JoinTable(
            name = "property_amenities",
            joinColumns = @JoinColumn(name = "property_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private Set<Amenity> amenities;
}