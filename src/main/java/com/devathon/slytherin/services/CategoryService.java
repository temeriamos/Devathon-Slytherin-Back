package com.devathon.slytherin.services;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;
import com.devathon.slytherin.models.CategoryModel;
import com.devathon.slytherin.repositories.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryModel store(CategoryModel categoryModel) {
        if (categoryModel.getName() == null || categoryModel.getName().isEmpty()) {
            throw new IllegalArgumentException("The name of the category cannot be null or empty.");
        }
        return categoryRepository.save(categoryModel);
    }   

    public List<CategoryModel> get() {
        return categoryRepository.findAll();
    }
}
