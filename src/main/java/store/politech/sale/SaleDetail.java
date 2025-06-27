package store.politech.sale;

import jakarta.persistence.*;
import lombok.Data;
import store.politech.product.Product;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "sales_details")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantity;
    private BigDecimal unitPrice;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
