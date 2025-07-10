package store.politech.category;

import store.politech.category.DTO.CategoryBodyDto;
import store.politech.category.DTO.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategory();
    CategoryResponseDto getCategoryById(Long id);
    CategoryResponseDto createCategory(CategoryBodyDto categoryBodyDto);
    CategoryResponseDto updateCategory(Long id, CategoryResponseDto categoryResponseDto);
    void deleteCategory(Long id);
}
