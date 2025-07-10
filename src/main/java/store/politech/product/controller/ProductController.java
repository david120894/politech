package store.politech.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.politech.dto.ApiResponse;
import store.politech.product.DTO.ProductResponseDTO;
import store.politech.product.ProductService;

import java.util.List;

@RestController()
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getAllProduct() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Products retrieved successfully",
                        HttpStatus.OK.value(),
                        productService.getAllProduct()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Product retrieved successfully",
                        HttpStatus.OK.value(),
                        productService.getProductById(id)
                )
        );

    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDTO>> createProduct(@RequestBody ProductResponseDTO productResponseDTO) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Product created successfully",
                        HttpStatus.CREATED.value(),
                        productService.createProduct(productResponseDTO)
                )
        );
    }
    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> updateProduct(@PathVariable Long id, @RequestBody ProductResponseDTO productResponseDTO) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Product updated successfully",
                        HttpStatus.OK.value(),
                        productService.updateProduct(id, productResponseDTO)
                )
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Product deleted successfully",
                        HttpStatus.OK.value(),
                        "Product with ID " + id + " has been deleted."
                )
        );
    }

}

