package store.politech.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import store.politech.auth.exceptions.ConflictException;
import store.politech.auth.exceptions.NotFoundException;
import store.politech.user.dto.UserDto;
import store.politech.user.entity.Role;
import store.politech.user.entity.User;
import store.politech.user.repository.RolRepository;
import store.politech.user.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PeronServiceImpl implements PersonService{
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public PersonDTO createPerson(UserDto userDto) {
        if(userRepository.existsByUsername(userDto.getUsername())){
            throw new ConflictException("Username already exists");
        }
        Set<Role> roles = userDto.getRoles().stream()
                .map(roleName -> rolRepository.findByName(roleName)
                        .orElseThrow(() -> new NotFoundException("Role not found: " + roleName)))
                .collect(Collectors.toSet());
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(roles);

        Person person = getPerson(new Person(), userDto.getPerson());
        user.setPerson(person);
        User savedUser = userRepository.save(user);
        return new PersonDTO(
                savedUser.getPerson().getId(),
                savedUser.getPerson().getFirstName(),
                savedUser.getPerson().getLastName(),
                savedUser.getPerson().getEmail(),
                savedUser.getPerson().getDni(),
                savedUser.getPerson().getPhone(),
                savedUser.getPerson().getAddress(),
                savedUser.getPerson().getCity(),
                savedUser.getPerson().getImageUrl()
        );


    }

    private static Person getPerson(Person person, PersonDTO userDto) {
        if (userDto.getImageUrl().isEmpty()) {
            person.setImageUrl("/images/user.png");
        } else {
            person.setImageUrl(userDto.getImageUrl());
        }
        person.setFirstName(userDto.getFirstName());
        person.setLastName(userDto.getLastName());
        person.setEmail(userDto.getEmail());
        person.setDni(userDto.getDni());
        person.setPhone(userDto.getPhone());
        person.setAddress(userDto.getAddress());
        person.setCity(userDto.getCity());
        return person;
    }

    @Override
    public PersonDTO updatePerson(Long id, PersonDTO personDTO) {
        Person person = getPerson(personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id)), personDTO);
        personRepository.save(person);
        return new PersonDTO(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getDni(),
                person.getPhone(),
                person.getAddress(),
                person.getCity(),
                person.getImageUrl()
        );
    }

    @Override
    public String uploadImage(Long id, MultipartFile imageUrl) throws IOException {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));

        if (imageUrl != null && !imageUrl.isEmpty()) {

            String oldImageUrl = person.getImageUrl();
            if (oldImageUrl != null && !oldImageUrl.isBlank()) {
                String oldImageName = oldImageUrl.replaceFirst("/images/", "");
                Path oldImagePath = Path.of("upload", oldImageName);
                if (Files.exists(oldImagePath)) {
                    Files.delete(oldImagePath);
                }
            }

            String imageName = System.currentTimeMillis() + "_" + imageUrl.getOriginalFilename();
            Path imagePath = Path.of("upload", imageName);
            Files.copy(imageUrl.getInputStream(), imagePath);

            String imageExtension = "/images/" + imageName;
            person.setImageUrl(imageExtension);
            personRepository.save(person);

            return person.getImageUrl();
        }

        return "";
    }

    @Override
    public void deletePerson(Long id) {

    }

    @Override
    public PersonDTO getPersonById(Long id) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        return new PersonDTO(
                person.getId(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getDni(),
                person.getPhone(),
                person.getAddress(),
                person.getCity(),
                person.getImageUrl()
        );
    }

    @Override
    public List<PersonDTO> getPersonAll() {
        return personRepository.findAll().stream()
                .map(p -> new PersonDTO(
                        p.getId(),
                        p.getFirstName(),
                        p.getLastName(),
                        p.getEmail(),
                        p.getDni(),
                        p.getPhone(),
                        p.getAddress(),
                        p.getCity(),
                        p.getImageUrl()
                )).collect(Collectors.toList());
    }
}
