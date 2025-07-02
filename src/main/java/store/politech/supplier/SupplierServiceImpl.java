package store.politech.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.politech.auth.exceptions.ConflictException;
import store.politech.auth.exceptions.NotFoundException;
import store.politech.supplier.DTO.SupplierBodyDTO;
import store.politech.supplier.DTO.SupplierResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;


    @Override
    public List<SupplierResponseDTO> getSupplier() {
         return supplierRepository.findAll().stream()
                 .map(SupplierServiceImpl::convertDto).collect(Collectors.toList());
    }

    @Override
    public SupplierResponseDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));

        return new SupplierResponseDTO(
                supplier.getId(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getPhone(),
                supplier.getAddress(),
                supplier.getWebsite(),
                supplier.getContactPerson(),
                supplier.getRuc(),
                supplier.getBankAccountNumber(),
                supplier.getDescription()
        );
    }

    @Override
    public SupplierResponseDTO createSupplier(SupplierBodyDTO supplierBodyDTO) {

        if (supplierRepository.existsByName(supplierBodyDTO.getName())) {
            throw new ConflictException("Supplier with name already exists");
        }
        Supplier supplier =  new Supplier();
        supplier.setName(supplierBodyDTO.getName());
        supplier.setEmail(supplierBodyDTO.getEmail());
        supplier.setPhone(supplierBodyDTO.getPhone());
        supplier.setAddress(supplierBodyDTO.getAddress());
        supplier.setWebsite(supplierBodyDTO.getWebsite());
        supplier.setContactPerson(supplierBodyDTO.getContactPerson());
        supplier.setRuc(supplierBodyDTO.getRuc());
        supplier.setBankAccountNumber(supplierBodyDTO.getBankAccountNumber());
        supplier.setDescription(supplierBodyDTO.getDescription());
        supplierRepository.save(supplier);
        return convertDto(supplier);
    }

    @Override
    public SupplierResponseDTO updateSupplier(Long id, SupplierResponseDTO supplierResponseDTO) {
        Supplier supplier = supplierRepository.save(convertEntity(
                supplierRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Supplier not found")
        ),supplierResponseDTO));
        return convertDto(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        // Implementation logic to delete a supplier by ID
    }

    private static SupplierResponseDTO convertDto(Supplier supplier) {
        return new SupplierResponseDTO(
                supplier.getId(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getPhone(),
                supplier.getAddress(),
                supplier.getWebsite(),
                supplier.getContactPerson(),
                supplier.getRuc(),
                supplier.getBankAccountNumber(),
                supplier.getDescription()
        );
    }
    private static Supplier convertEntity(Supplier supplier1,SupplierResponseDTO supplier) {
        supplier1.setName(supplier.getName());
        supplier1.setEmail(supplier.getEmail());
        supplier1.setPhone(supplier.getPhone());
        supplier1.setAddress(supplier.getAddress());
        supplier1.setWebsite(supplier.getWebsite());
        supplier1.setContactPerson(supplier.getContactPerson());
        supplier1.setRuc(supplier.getRuc());
        supplier1.setBankAccountNumber(supplier.getBankAccountNumber());
        supplier1.setDescription(supplier.getDescription());
        return supplier1;
    }
}
