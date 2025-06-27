package store.politech.client;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import store.politech.sale.Sale;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String phone;
    private String address;

    @OneToMany(mappedBy = "client")
    private Set<Sale> sales = new HashSet<>();
}
