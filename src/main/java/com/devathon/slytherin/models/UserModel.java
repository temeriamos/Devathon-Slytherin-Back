package com.devathon.slytherin.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private Integer price_galeon;
    @Column(nullable = false)
    private Integer price_sickle;
    @Column(nullable = false)
    private Integer price_knut;

    @PrePersist
    @PreUpdate
    private void setDefaultValues() {
        if (price_galeon == null) {
            price_galeon = 0;
        }
        if (price_sickle == null) {
            price_sickle = 0;
        }
        if (price_knut == null) {
            price_knut = 0;
        }
    }

}
