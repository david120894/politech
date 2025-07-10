package store.politech.category.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryBodyDto {
    private String name;
    private String description;
}
