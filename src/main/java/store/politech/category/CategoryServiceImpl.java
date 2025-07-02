package store.politech.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.politech.auth.exceptions.ConflictException;
import store.politech.auth.exceptions.NotFoundException;
import store.politech.category.DTO.CategoryBodyDto;
import store.politech.category.DTO.CategoryResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<CategoryResponseDto> getAllCategory() {
        return categoryRepository.findAll().stream().map(CategoryServiceImpl::convertDto).collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        return convertDto(categoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Category no found")
        ));
    }

    @Override
    public CategoryResponseDto createCategory(CategoryBodyDto categoryBodyDto) {
        if (categoryRepository.existsByName(categoryBodyDto.getName())) {
             throw new ConflictException("Category with name already exists");
        }
        Category category = categoryRepository.save(setCategory(categoryBodyDto));
        return convertDto(category);
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryResponseDto categoryResponseDto) {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Category not found")
        );

        category.setName(categoryResponseDto.getName());
        category.setDescription(categoryResponseDto.getDescription());

        return convertDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {

    }

    public static CategoryResponseDto convertDto(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
    public static Category setCategory(CategoryBodyDto categoryBodyDto) {
        Category category = new Category();
        category.setName(categoryBodyDto.getName());
        category.setDescription(category.getDescription());
        return category;
    }
}
