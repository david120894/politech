package store.politech.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import store.politech.dto.ApiResponse;
import store.politech.user.dto.UserDto;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PersonDTO>>> getPersonAll() {
        return ResponseEntity.ok(new ApiResponse<>(
                "Person retrieved successfully",
                HttpStatus.OK.value(),
                personService.getPersonAll()
        ));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonDTO>> getPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(
                "Person retrieved successfully",
                HttpStatus.OK.value(),
                personService.getPersonById(id)
        ));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<PersonDTO>> createPerson(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(new ApiResponse<>(
                "Person created successfully",
                HttpStatus.CREATED.value(),
                personService.createPerson(userDto)
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonDTO>> updatePerson(@PathVariable Long id, @RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(new ApiResponse<>(
                "Person updated successfully",
                HttpStatus.OK.value(),
                personService.updatePerson(id, personDTO)
        ));
    }

    @PutMapping("/upload/img/{id}")
    public ResponseEntity<ApiResponse<String>> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file)
    {
        try {
            return ResponseEntity.ok(new ApiResponse<>(
                    "Image uploaded successfully",
                    HttpStatus.OK.value(),
                    personService.uploadImage(id, file)
            ));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(
                    "Failed to upload image: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    null
            ));
        }
    }

}
