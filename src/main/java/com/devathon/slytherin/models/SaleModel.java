package com.devathon.slytherin.models;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sale")
@EntityListeners(AuditingEntityListener.class)
public class SaleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Relación con UserModel
    private UserModel user;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Integer price_galeon;

    @Column(nullable = false)
    private Integer price_sickle;

    @Column(nullable = false)
    private Integer price_knut;

    @PrePersist
    @PreUpdate
    private void setDefaultValues() {
        if (date == null) date = new Date(System.currentTimeMillis());
        if (price_galeon == null) price_galeon = 0;
        if (price_sickle == null) price_sickle = 0;
        if (price_knut == null) price_knut = 0;
    }
}
