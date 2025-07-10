package store.politech.product.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductImageResponseDTO {
    private Long id;
    private String imageUrl;
    private Boolean mainImage;
    private Integer order;
    private Long productId;
}
