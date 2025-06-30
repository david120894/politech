package store.politech.supplier.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SupplierBodyDTO {
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
