package com.myproject.pronajdimajstor.web.controller;

import com.myproject.pronajdimajstor.model.Korisnik;
import com.myproject.pronajdimajstor.model.Majstor;
import com.myproject.pronajdimajstor.model.ReCaptchaResponse;
import com.myproject.pronajdimajstor.model.Subcategory;
import com.myproject.pronajdimajstor.model.enumerations.Role;
import com.myproject.pronajdimajstor.service.ClientService;
import com.myproject.pronajdimajstor.service.KorisnikService;
import com.myproject.pronajdimajstor.service.MajstorService;
import com.myproject.pronajdimajstor.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class RegisterController {

    private final KorisnikService korisnikService;
    private final MajstorService majstorService;
    private final ClientService clientService;
    private final SubCategoryService subCategoryService;

    public RegisterController(KorisnikService korisnikService, MajstorService majstorService, ClientService clientService, SubCategoryService subCategoryService) {
        this.korisnikService = korisnikService;
        this.majstorService = majstorService;
        this.clientService = clientService;
        this.subCategoryService = subCategoryService;
    }


    @Value("${recaptcha.secrect}")
    private String recaptchaSecret;

    @Value("${recaptcha.url}")
    private String recaptchaServerUrl;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/register")
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Subcategory> subcategoryList = this.subCategoryService.listAll();

        model.addAttribute("subcategoryList", subcategoryList);
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping("/register/majstor")
    public String registerMajstor(@RequestParam String name, @RequestParam String lastName,
                                  @RequestParam String email, @RequestParam String phoneNumber,
                                  @RequestParam String username, @RequestParam String city,
                                  @RequestParam List<Long> subCategory, @RequestParam String address,
                                  @RequestParam(required = false) String nameOfFirm,
                                  @RequestParam String password, @RequestParam String confirmPassword,
                                  @RequestParam(required = false) String shortDesc,
                                  HttpServletRequest req,
                                  HttpServletResponse response) throws IOException{

        try {


            String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
            if(!verifyReCAPTCHA(gRecaptchaResponse)){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }

            Korisnik korisnik = this.korisnikService.create(name, lastName, username, email, password, confirmPassword, Role.MAJSTOR);
            Majstor majstor = this.majstorService.create(korisnik, phoneNumber, city, subCategory, address, nameOfFirm, shortDesc);
            return "redirect:/login";
        } catch (RuntimeException e) {
            return "redirect:/register?error=" + e.getMessage();
        }
    }

    private boolean verifyReCAPTCHA(String gRecaptchaResponse) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("secret",recaptchaSecret );
        map.add("response",gRecaptchaResponse );

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);



        ReCaptchaResponse response = restTemplate.postForObject(recaptchaServerUrl, request, ReCaptchaResponse.class);


        return response.isSuccess();

    }

    @PostMapping("/register/client")
    public String registerClient(@RequestParam String name2, @RequestParam String lastName2,
                                 @RequestParam String username2, @RequestParam String emailAddress2,
                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfBirth,
                                 @RequestParam String sex, @RequestParam String password2,
                                 @RequestParam String confirmPassword2,
                                 HttpServletRequest req,
                                 HttpServletResponse response) throws IOException {

         try{
            Korisnik korisnik = this.korisnikService.create(name2, lastName2, username2, emailAddress2, password2, confirmPassword2, Role.CLIENT);
            this.clientService.create(dateOfBirth, sex, korisnik);
            return "redirect:/login";

        } catch (RuntimeException e) {
            return "redirect:/register?error=" + e.getMessage();
        }

    }
}
