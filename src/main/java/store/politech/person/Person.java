package store.politech.person;

import jakarta.persistence.*;
import lombok.Data;
import store.politech.user.entity.User;

@Entity
@Data
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String dni;
    private String phone;
    private String address;
    private String city;
    private String imageUrl;
    @OneToOne(mappedBy="person")
    private User user;
}
