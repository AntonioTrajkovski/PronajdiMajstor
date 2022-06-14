package com.myproject.pronajdimajstor.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutUs {


    @GetMapping("/aboutUs")
    public String getAuboutUsPage(Model model){

        model.addAttribute("bodyContent", "zaNas");
        return "master-template";
    }

}
