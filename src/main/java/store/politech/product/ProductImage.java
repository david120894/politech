package store.politech.product;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl;
    private Boolean mainImage;
    @Column(name = "image_order")
    private Integer order;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
