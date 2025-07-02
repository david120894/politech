package store.politech.category.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;
}
