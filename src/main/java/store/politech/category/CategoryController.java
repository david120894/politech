package store.politech.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.politech.category.DTO.CategoryBodyDto;
import store.politech.category.DTO.CategoryResponseDto;
import store.politech.dto.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDto>>> getAllCategory() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Get all category",
                        HttpStatus.OK.value(),
                        categoryService.getAllCategory()
                )
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Get category successfully",
                        HttpStatus.OK.value(),
                        categoryService.getCategoryById(id)
                )
        );
    }
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDto>> createCategory(@RequestBody CategoryBodyDto categoryBodyDto) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Category create successfully",
                        HttpStatus.OK.value(),
                        categoryService.createCategory(categoryBodyDto)
                )
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> updateCategory(@PathVariable Long id,
                                                                           @RequestBody CategoryResponseDto categoryResponseDto)  {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Update category successfully",
                        HttpStatus.OK.value(),
                        categoryService.updateCategory(id,categoryResponseDto)
                )
        );
    }
}
