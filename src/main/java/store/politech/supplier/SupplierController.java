package store.politech.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import store.politech.dto.ApiResponse;
import store.politech.supplier.DTO.SupplierBodyDTO;
import store.politech.supplier.DTO.SupplierResponseDTO;

import javax.naming.NamingEnumeration;
import java.util.List;

@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SupplierResponseDTO>>> getSupplier() {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Get all supplier",
                        HttpStatus.OK.value(),
                        supplierService.getSupplier()
                )
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierResponseDTO>> getSupplierById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Supplier get Successfully",
                        HttpStatus.OK.value(),
                        supplierService.getSupplierById(id)
                )
        );
    }
    @PostMapping
    public ResponseEntity<ApiResponse<SupplierResponseDTO>> createSupplier(@RequestBody SupplierBodyDTO supplierBodyDTO) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Supplier create successfully",
                        HttpStatus.OK.value(),
                        supplierService.createSupplier(supplierBodyDTO)
                )
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SupplierResponseDTO>> updateSupplier(@PathVariable Long id,
                                                                           @RequestBody SupplierResponseDTO supplierResponseDTO ) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Supplier update successfully",
                        HttpStatus.OK.value(),
                        supplierService.updateSupplier(id,supplierResponseDTO)
                )
        );
    }
}
