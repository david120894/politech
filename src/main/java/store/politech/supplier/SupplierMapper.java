package store.politech.supplier;

import org.mapstruct.Mapper;
import store.politech.supplier.DTO.SupplierBodyDTO;
import store.politech.supplier.DTO.SupplierResponseDTO;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierResponseDTO toDTO(Supplier supplier);
    Supplier toEntity(SupplierBodyDTO dto);
}