package store.politech.product;

import jakarta.persistence.*;
import lombok.Data;
import store.politech.category.Category;
import store.politech.purchase.PurchaseDetail;
import store.politech.sale.SaleDetail;
import store.politech.supplier.Supplier;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductImage> images = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    private Set<PurchaseDetail> purchaseDetails = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<SaleDetail> saleDetails = new HashSet<>();

}
