package store.politech.supplier;

import store.politech.supplier.DTO.SupplierBodyDTO;
import store.politech.supplier.DTO.SupplierResponseDTO;

import java.util.List;

public interface SupplierService {
    List<SupplierResponseDTO> getSupplier();
    SupplierResponseDTO getSupplierById(Long id);

    SupplierResponseDTO createSupplier(SupplierBodyDTO supplierBodyDTO);

    SupplierResponseDTO updateSupplier(Long id, SupplierResponseDTO supplierResponseDTO);

    void deleteSupplier(Long id);
}
