package store.politech.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.politech.product.DTO.ProductResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<ProductResponseDTO> getAllProduct() {
        Product product = productRepository.findAll().stream().map(p ->
                new ProductResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getStock(),
                        p.getCategory().getId(),
                        p.getSupplier().getId(),
                )).collect(Collectors.toList());
        return List.of();
    }

    @Override
    public ProductResponseDTO getProductById() {
        return null;
    }

    @Override
    public ProductResponseDTO createProduct(ProductResponseDTO productResponseDTO) {
        return null;
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductResponseDTO productResponseDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
