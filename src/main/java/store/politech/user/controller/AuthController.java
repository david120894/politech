package store.politech.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.politech.dto.ApiResponse;
import store.politech.dto.JwtResponseDto;
import store.politech.dto.LoginDTO;
import store.politech.user.service.UserService;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponseDto>> login(@RequestBody LoginDTO loginDto) {
        return ResponseEntity.ok(new ApiResponse<>(
                "Login successful",
                HttpStatus.OK.value(),
                userService.login(loginDto)
        ));
    }
}
