package store.politech.user.dto;

import lombok.Data;
import store.politech.person.PersonDTO;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private Set<String> roles = new HashSet<>();;
    private PersonDTO person;
}
