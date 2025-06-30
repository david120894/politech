package store.politech.supplier.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SupplierResponseDTO {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String website;
    private String contactPerson;
    private String ruc;
    private String bankAccountNumber;
    private String description;
}
