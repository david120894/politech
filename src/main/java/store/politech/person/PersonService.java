package store.politech.person;

import org.springframework.web.multipart.MultipartFile;
import store.politech.user.dto.UserDto;

import java.io.IOException;
import java.util.List;

public interface PersonService {
    PersonDTO createPerson(UserDto userDto);

    PersonDTO updatePerson(Long id, PersonDTO personDTO);

    String uploadImage(Long id, MultipartFile imageUrl) throws IOException;

    void deletePerson(Long id);

    PersonDTO getPersonById(Long id);

    List<PersonDTO> getPersonAll();
}
