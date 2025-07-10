package store.politech.product;

import store.politech.product.DTO.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    List<ProductResponseDTO> getAllProduct();
    ProductResponseDTO getProductById();
    ProductResponseDTO createProduct(ProductResponseDTO productResponseDTO);
    ProductResponseDTO updateProduct(Long id,ProductResponseDTO productResponseDTO);
    void delete(Long id);
}
