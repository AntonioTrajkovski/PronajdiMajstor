package com.myproject.pronajdimajstor.web.controller;

import com.myproject.pronajdimajstor.model.Client;
import com.myproject.pronajdimajstor.model.ConcludedDeals;
import com.myproject.pronajdimajstor.model.Majstor;
import com.myproject.pronajdimajstor.service.ClientService;
import com.myproject.pronajdimajstor.service.ConcludedDealsService;
import com.myproject.pronajdimajstor.service.MajstorService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;

@Controller
public class ConcludedDealsController {

    private final MajstorService majstorService;
    private final  JavaMailSender javaMailSender;
    private final ClientService clientService;
    private final ConcludedDealsService concludedDealsService;

    public ConcludedDealsController(MajstorService majstorService, JavaMailSender javaMailSender, ClientService clientService, ConcludedDealsService concludedDealsService) {
        this.majstorService = majstorService;
        this.javaMailSender = javaMailSender;
        this.clientService = clientService;
        this.concludedDealsService = concludedDealsService;
    }


    @PostMapping("/requestQuote/{id}")
    public String requestQuote(@RequestParam String name,
                               @RequestParam String lastname,
                               @RequestParam String email,
                               @RequestParam String subject,
                               @RequestParam String description,
                               @PathVariable Long id,
                               Authentication authentication,
                               Model model){

        Majstor majstor = this.majstorService.findById(id);

        String username = authentication.getName();
        Client client = this.clientService.findClientByKorisnikUsername(username);

        this.concludedDealsService.save(subject, description, client, majstor);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("antoniotrajkovski99@gmail.com");
        message.setTo(majstor.getKorisnik().getEmail());

        String fullName = name + " " + lastname;
        String mailSubject = fullName + " has sent a message";
        String mailContent = "Sender Name: " + fullName + "\n";
        mailContent += "Sender Email: " + email + "\n";
        mailContent += "Subject: " + subject + "\n";
        mailContent += "Content: " + description + "\n";

        message.setSubject(mailSubject);
        message.setText(mailContent);

        javaMailSender.send(message);

        model.addAttribute("bodyContent", "message");
        return "master-template";
    }

}
