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
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class UserModel {

    @Id
    private String id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private Integer price_galeon;

    @Column(nullable = false)
    private Integer price_sickle;

    @Column(nullable = false)
    private Integer price_knut;
    /*@ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    @JsonBackReference
    private HouseModel houseModel;*/
    
    @PrePersist
    @PreUpdate
    private void setDefaultValues() {
        if (price_galeon == null) price_galeon = 0;
        if (price_sickle == null) price_sickle = 0;
        if (price_knut == null) price_knut = 0;
    }
}
