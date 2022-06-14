package com.myproject.pronajdimajstor.web.controller;

import com.myproject.pronajdimajstor.model.Category;
import com.myproject.pronajdimajstor.model.Subcategory;
import com.myproject.pronajdimajstor.model.exceptions.InvalidCategoryIdException;
import com.myproject.pronajdimajstor.service.CategoryService;
import com.myproject.pronajdimajstor.service.SubCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SubcategoryController {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    public SubcategoryController(CategoryService categoryService, SubCategoryService subCategoryService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }


    @GetMapping("/subCategory/{id}")
    public String getSubcategoryPage(@PathVariable Long id, Model model){

        List<Subcategory> subcategoryList = this.subCategoryService.findAllByCategoryId(id);

        List<Subcategory> allSubcategoryList = this.subCategoryService.listAll();

        model.addAttribute("allSubcategoryList", allSubcategoryList);

        model.addAttribute("subcategoryList", subcategoryList);
        model.addAttribute("bodyContent", "podKategorii");
        return "master-template";

    }

    @GetMapping("/addSubCategory")
    public String getAddSubCategoryPage(Model model){

        List<Category> categoryList = this.categoryService.listAll();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("bodyContent", "addSubcategory");
        return "master-template";
    }


    @GetMapping("/editSubCategory/{id}")
    public String getAddSubCategoryPage(@PathVariable Long id, Model model){

        Subcategory subcategory = this.subCategoryService.findById(id);
        List<Category> categoryList = this.categoryService.listAll();

        model.addAttribute("categoryList", categoryList);

        model.addAttribute("subcategory", subcategory);
        model.addAttribute("bodyContent", "addSubCategory");
        return "master-template";
    }


    @PostMapping("/createSubCategory")
    public String create(@RequestParam String name, @RequestParam(required = false)String imgRef, Long categoryId){
        this.subCategoryService.create(name,categoryId,imgRef);
        return "redirect:/category";
    }

    @PostMapping("/createSubCategory/{id}")
    public String update(@PathVariable Long id, @RequestParam String name, @RequestParam(required = false)String imgRef, Long categoryId){


        this.subCategoryService.update(id,name,categoryId,imgRef);

        return "redirect:/category";
    }




}
