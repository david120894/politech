package store.politech.purchase;

import jakarta.persistence.*;
import lombok.Data;
import store.politech.product.Product;

import java.math.BigDecimal;

@Table(name = "purchase_details")
@Entity
@Data
public class PurchaseDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
