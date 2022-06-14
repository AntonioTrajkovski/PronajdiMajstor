package com.myproject.pronajdimajstor.web.controller;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {


    private final JavaMailSender javaMailSender;

    public ContactController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @GetMapping("/contact")
    public String getContactPage(Model model){

        model.addAttribute("bodyContent", "kontakt");
        return "master-template";
    }

    @PostMapping("/contact")
    public String submitContact(@RequestParam String name,
                                @RequestParam String lastName,
                                @RequestParam String email,
                                @RequestParam String subject,
                                @RequestParam String message,
                                Model model){

        SimpleMailMessage message1 = new SimpleMailMessage();

        message1.setFrom("antoniotrajkovski99@gmail.com");
        message1.setTo("trajkovskiantonio666@gmail.com");

        String fullName = name + " " + lastName;
        String mailSubject = fullName + " has sent a message";
        String mailContent = "Sender Name: " + fullName + "\n";
        mailContent += "Sender Email: " + email + "\n";
        mailContent += "Subject: " + subject + "\n";
        mailContent += "Content: " + message + "\n";

        message1.setSubject(mailSubject);
        message1.setText(mailContent);

        javaMailSender.send(message1);

        model.addAttribute("bodyContent", "message");
        return "master-template";
    }


}
