package store.politech.user.service;

import store.politech.dto.JwtResponseDto;
import store.politech.dto.LoginDTO;
import store.politech.user.entity.User;

public interface UserService {

    JwtResponseDto login(LoginDTO loginDTO);
    
    User registerUser(User user);

    User getUserByUsername(String username);

    Boolean existsByUsername(String username);

    User updateUser(User user);

    void deleteUser(Long id);

    User getUserById(Long id);
}
