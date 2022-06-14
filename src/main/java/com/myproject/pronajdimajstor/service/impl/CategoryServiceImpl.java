package com.myproject.pronajdimajstor.service.impl;

import com.myproject.pronajdimajstor.model.Category;
import com.myproject.pronajdimajstor.model.exceptions.InvalidCategoryIdException;
import com.myproject.pronajdimajstor.repository.CategoryRepository;
import com.myproject.pronajdimajstor.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Optional<Category> findById(Long id) {
        return this.categoryRepository.findById(id);
    }

    @Override
    public List<Category> listAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category create(String name, String imgRef) {
        Category category = new Category(name, imgRef);
        return this.categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, String name, String imgRef) {

        Category category = this.categoryRepository.findById(id).orElseThrow(InvalidCategoryIdException::new);

        category.setName(name);
        category.setImgReference(imgRef);
        return this.categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        this.categoryRepository.deleteById(id);
    }


}
