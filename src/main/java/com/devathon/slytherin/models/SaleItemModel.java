package com.devathon.slytherin.models;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sale_item")
@EntityListeners(AuditingEntityListener.class)
public class SaleItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    @JsonBackReference
    private SaleModel sale;
    @Column(nullable = false)
    private Long object_id;
    @Column(nullable = false)
    private Integer price_galeon;
    @Column(nullable = false)
    private Integer price_sickle;
    @Column(nullable = false)
    private Integer price_knut;
    
    @PrePersist
    @PreUpdate
    private void setDefaultValues() {
        if (price_galeon == null) price_galeon = 0;
        if (price_sickle == null) price_sickle = 0;
        if (price_knut == null) price_knut = 0;
    }
}
