package store.politech.product.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.politech.category.CategoryRepository;
import store.politech.product.DTO.ProductResponseDTO;
import store.politech.product.Product;
import store.politech.product.ProductRepository;
import store.politech.product.ProductService;
import store.politech.supplier.SupplierRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private SupplierRepository supplierRepository;

    @Override
    public List<ProductResponseDTO> getAllProduct() {
        return productRepository.findAll().stream().map(p ->
                new ProductResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getStock(),
                        p.getCategory().getId(),
                        p.getSupplier().getId()
                )).toList();
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(p -> new ProductResponseDTO(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getStock(),
                        p.getCategory().getId(),
                        p.getSupplier().getId()
                )).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public ProductResponseDTO createProduct(ProductResponseDTO productResponseDTO) {
        if (productRepository.existsByName((productResponseDTO.getName()))) {
            throw new RuntimeException("Product with name already exists");
        }
        var category = categoryRepository.findById(productResponseDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + productResponseDTO.getCategoryId()));
        var supplier = supplierRepository.findById(productResponseDTO.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + productResponseDTO.getSupplierId()));
        Product product = new Product();
        product.setName(productResponseDTO.getName());
        product.setDescription(productResponseDTO.getDescription());
        product.setPrice(productResponseDTO.getPrice());
        product.setStock(productResponseDTO.getStock());
        product.setCategory(category);
        ;
        product.setSupplier(supplier);
        Product product1 = productRepository.save(product);
        return new ProductResponseDTO(
                product1.getId(),
                product1.getName(),
                product1.getDescription(),
                product1.getPrice(),
                product1.getStock(),
                product1.getCategory().getId(),
                product1.getSupplier().getId()
        );
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductResponseDTO productResponseDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
