package org.ijse.nexusestate_aad.entity.Transactions;

import jakarta.persistence.*;
import lombok.*;
import org.ijse.nexusestate_aad.entity.SecurityandUsers.User;
import org.ijse.nexusestate_aad.entity.PropertyFoundation.Property;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double finalPrice;

    private LocalDateTime saleDate = LocalDateTime.now();

    @OneToOne
    private Property property;

    @ManyToOne
    private User buyer;
}