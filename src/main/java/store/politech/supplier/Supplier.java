package store.politech.supplier;

import jakarta.persistence.*;
import lombok.Data;
import store.politech.product.Product;
import store.politech.purchase.Purchase;

import java.util.HashSet;
import java.util.Set;

@Table(name = "suppliers")
@Data
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String website;
    private String contactPerson;
    private String ruc;
    private String bankAccountNumber;
    private String description;
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new HashSet<>();
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Purchase> purchases = new HashSet<>();
}
