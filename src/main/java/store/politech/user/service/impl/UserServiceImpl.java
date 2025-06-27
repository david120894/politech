package store.politech.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import store.politech.auth.exceptions.NotFoundException;
import store.politech.auth.security.JwtGenerator;
import store.politech.dto.JwtResponseDto;
import store.politech.dto.LoginDTO;
import store.politech.person.PersonDTO;
import store.politech.user.dto.UserResponseDTO;
import store.politech.user.entity.Role;
import store.politech.user.entity.User;
import store.politech.user.repository.RolRepository;
import store.politech.user.repository.UserRepository;
import store.politech.user.service.UserService;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public JwtResponseDto login(LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
            );
                        User user = userRepository.findByUsername(loginDTO.getUsername())
                    .orElseThrow(() -> new NotFoundException("User not found"));
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(user.getId());
            userResponseDTO.setUsername(user.getUsername());
            userResponseDTO.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
            PersonDTO personDTO = new PersonDTO(
                    user.getPerson().getId(),
                    user.getPerson().getFirstName(),
                    user.getPerson().getLastName(),
                    user.getPerson().getDni(),
                    user.getPerson().getEmail(),
                    user.getPerson().getPhone(),
                    user.getPerson().getAddress(),
                    user.getPerson().getCity(),
                    user.getPerson().getImageUrl()
            );
            userResponseDTO.setPerson(personDTO);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtGenerator.generateToken(authentication);
            JwtResponseDto jwtResponseDto = new JwtResponseDto();
            jwtResponseDto.setToken(jwt);
            jwtResponseDto.setUser(userResponseDTO);
            return jwtResponseDto;
        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage());
        }
    }
    @Override
    public User registerUser(User user) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public Boolean existsByUsername(String username) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User getUserById(Long id) {
        return null;
    }
}
