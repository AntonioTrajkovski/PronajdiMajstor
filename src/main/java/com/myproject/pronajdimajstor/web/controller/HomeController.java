package com.myproject.pronajdimajstor.web.controller;

import com.myproject.pronajdimajstor.model.Category;
import com.myproject.pronajdimajstor.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CategoryService categoryService;

    public HomeController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping(value ={"/","/home"})
    public String getHomePage(Model model){

        List<Category> categoryList = this.categoryService.listAll();

       model.addAttribute("categoryList", categoryList);
       model.addAttribute("bodyContent", "home");

       return "master-template";
    }
}
