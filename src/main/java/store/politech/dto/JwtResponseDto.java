package store.politech.dto;

import lombok.Data;
import store.politech.user.dto.UserResponseDTO;

@Data
public class JwtResponseDto {
    private String token;
    private UserResponseDTO user;
}
