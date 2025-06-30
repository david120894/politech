package store.politech.supplier;

import store.politech.supplier.DTO.SupplierBodyDTO;
import store.politech.supplier.DTO.SupplierResponseDTO;

public interface SupplierService {
    SupplierResponseDTO getSupplierById(Long id);

    SupplierResponseDTO createSupplier(SupplierBodyDTO supplierBodyDTO);

    SupplierResponseDTO updateSupplier(Long id, SupplierBodyDTO supplierBodyDTO);

    void deleteSupplier(Long id);
}
