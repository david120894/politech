package store.politech.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.politech.dto.ApiResponse;
import store.politech.supplier.DTO.SupplierBodyDTO;
import store.politech.supplier.DTO.SupplierResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SupplierResponseDTO>>> getAllSuppliers() {
        return ResponseEntity.ok(new ApiResponse<>(
                "Suppliers retrieved successfully",
                HttpStatus.OK.value(),
                supplierService.getSupplier()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierResponseDTO>> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(
                "Supplier retrieved successfully",
                HttpStatus.OK.value(),
                supplierService.getSupplierById(id)
        ));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SupplierResponseDTO>> createSupplier(@RequestBody SupplierBodyDTO supplierBodyDTO) {
        return ResponseEntity.ok(new ApiResponse<>(
                "Supplier created successfully",
                HttpStatus.CREATED.value(),
                supplierService.createSupplier(supplierBodyDTO)
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierResponseDTO>> updateSupplier(@PathVariable Long id, @RequestBody SupplierResponseDTO supplierResponseDTO) {
        return ResponseEntity.ok(new ApiResponse<>(
                "Supplier updated successfully",
                HttpStatus.OK.value(),
                supplierService.updateSupplier(id, supplierResponseDTO)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok(new ApiResponse<>(
                "Supplier deleted successfully",
                HttpStatus.OK.value(),
                null
        ));
    }
}
