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
public class CategoryController {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    public CategoryController(CategoryService categoryService, SubCategoryService subCategoryService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }



    @GetMapping("/category")
    public String getCategoryPage(@RequestParam(required = false) String error, Model model){

        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }

        List<Category> categoryList = this.categoryService.listAll();

        List<Subcategory> subcategoryList = this.subCategoryService.listAll();

        model.addAttribute("subcategoryList", subcategoryList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("bodyContent", "kategorii");
        return "master-template";
    }



    @GetMapping("/addCategory")
    public String getAddCategoryPage(Model model){


        model.addAttribute("bodyContent", "addCategory");
        return "master-template";
    }


    @GetMapping("/editCategory/{id}")
    public String getAddCategoryPage(@PathVariable Long id, Model model){

        Category category = this.categoryService.findById(id).orElseThrow(InvalidCategoryIdException::new);
        model.addAttribute("category", category);
        model.addAttribute("bodyContent", "addCategory");
        return "master-template";
    }

    @PostMapping("/createCategory")
    public String create(@RequestParam String name, @RequestParam(required = false)String imgRef){
        this.categoryService.create(name, imgRef);
        return "redirect:/category";
    }

    @PostMapping("/createCategory/{id}")
    public String update(@PathVariable Long id, @RequestParam String name, @RequestParam(required = false)String imgRef){


        this.categoryService.update(id, name, imgRef);

        return "redirect:/category";
    }


}
