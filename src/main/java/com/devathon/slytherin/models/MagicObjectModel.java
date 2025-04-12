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
@Table(name = "magicobject")
@EntityListeners(AuditingEntityListener.class)
public class MagicObjectModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name ;
    @Column(nullable = false, length = 250)
    private String short_description;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(nullable = true)
    private String long_description;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private CategoryModel category;
    @ManyToOne
    @JoinColumn(name = "rarity_id", nullable = false)
    @JsonBackReference
    private RarityModel rarity;
    @Column(nullable = false)
    private Integer price_galeon;
    @Column(nullable = false)
    private Integer price_sickle;
    @Column(nullable = false)
    private Integer price_knut;
    @Column(nullable = false, length = 255)
    private String url_image;
    @Builder.Default
    private boolean purchased = false;

    @PrePersist
    @PreUpdate
    private void setDefaultValues() {
        if (price_galeon == null) price_galeon = 0;
        if (price_sickle == null) price_sickle = 0;
        if (price_knut == null) price_knut = 0;
    }
}
