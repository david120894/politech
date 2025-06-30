package store.politech.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.politech.supplier.DTO.SupplierBodyDTO;
import store.politech.supplier.DTO.SupplierResponseDTO;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public SupplierResponseDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        return supplierMapper.toDTO(supplier);
    }

    @Override
    public SupplierResponseDTO createSupplier(SupplierBodyDTO supplierBodyDTO) {
        // Implementation logic to create a new supplier
        return null; // Placeholder return statement
    }

    @Override
    public SupplierResponseDTO updateSupplier(Long id, SupplierBodyDTO supplierBodyDTO) {
        // Implementation logic to update an existing supplier
        return null; // Placeholder return statement
    }

    @Override
    public void deleteSupplier(Long id) {
        // Implementation logic to delete a supplier by ID
    }
}
