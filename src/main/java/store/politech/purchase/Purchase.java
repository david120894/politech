package store.politech.purchase;

import jakarta.persistence.*;
import lombok.Data;
import store.politech.product.Product;
import store.politech.supplier.Supplier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "purchases")
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private BigDecimal total;
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @OneToMany(mappedBy = "purchase")
    private Set<PurchaseDetail> purchaseDetails = new HashSet<>();
}
