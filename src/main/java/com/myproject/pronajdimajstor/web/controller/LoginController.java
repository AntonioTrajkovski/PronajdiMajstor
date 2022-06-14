package com.myproject.pronajdimajstor.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {


    @GetMapping
    public String getLoginPage(@RequestParam(required = false) String error, Model model){
        model.addAttribute("bodyContent", "login");
        return "master-template";
    }



}
