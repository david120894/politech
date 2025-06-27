package store.politech.user.dto;

import lombok.Data;
import store.politech.person.PersonDTO;

import java.util.Set;
@Data

public class UserDto {
    private String username;
    private String password;
    private Set<String> roles;
    private PersonDTO person;
}
