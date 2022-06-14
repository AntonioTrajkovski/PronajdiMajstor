package com.myproject.pronajdimajstor.service.impl;

import com.myproject.pronajdimajstor.model.Category;
import com.myproject.pronajdimajstor.model.Subcategory;
import com.myproject.pronajdimajstor.model.exceptions.InvalidSubCategoryIdException;
import com.myproject.pronajdimajstor.repository.SubCategoryRepository;
import com.myproject.pronajdimajstor.service.CategoryService;
import com.myproject.pronajdimajstor.service.SubCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryService categoryService;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryService categoryService) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryService = categoryService;
    }


    @Override
    public Subcategory findById(Long id) {
        return this.subCategoryRepository.findById(id).orElseThrow(InvalidSubCategoryIdException::new);
    }

    @Override
    public List<Subcategory> listAll() {
        return this.subCategoryRepository.findAll();
    }

    @Override
    public Subcategory create(String name, Long categoryId, String imgRef) {

        Category category = this.categoryService.findById(categoryId).orElseThrow(InvalidSubCategoryIdException::new);
        Subcategory subcategory = new Subcategory(name,category,imgRef);

        return this.subCategoryRepository.save(subcategory);
    }

    @Override
    public Subcategory update(Long id, String name, Long categoryId, String imgRef) {

        Category category = this.categoryService.findById(categoryId).orElseThrow(InvalidSubCategoryIdException::new);
        Subcategory subcategory = this.findById(id);

        subcategory.setName(name);
        subcategory.setImgReference(imgRef);

        return this.subCategoryRepository.save(subcategory);
    }

    @Override
    public Subcategory delete(Long id) {

        Subcategory subcategory = this.findById(id);
        this.subCategoryRepository.delete(subcategory);
        return subcategory;
    }

    @Override
    public List<Subcategory> findAllByCategoryId(Long id) {
        return this.subCategoryRepository.findAllByCategoryId(id);
    }
}
