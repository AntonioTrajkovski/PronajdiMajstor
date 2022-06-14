package com.myproject.pronajdimajstor.web.controller;

import com.myproject.pronajdimajstor.model.Client;
import com.myproject.pronajdimajstor.model.Majstor;
import com.myproject.pronajdimajstor.model.Subcategory;
import com.myproject.pronajdimajstor.service.ClientService;
import com.myproject.pronajdimajstor.service.MajstorService;
import com.myproject.pronajdimajstor.service.SubCategoryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ClientMajsorController {


    private final ClientService clientService;
    private final MajstorService majstorService;
    private final SubCategoryService subCategoryService;


    public ClientMajsorController(ClientService clientService, MajstorService majstorService, SubCategoryService subCategoryService) {
        this.clientService = clientService;
        this.majstorService = majstorService;
        this.subCategoryService = subCategoryService;
    }


    @GetMapping("/clientProfile")
    public String getClientPage (Model model, HttpServletRequest req){

        String username = req.getRemoteUser().toString();

        Client client = this.clientService.findClientByKorisnikUsername(username);

        model.addAttribute("client", client);
        model.addAttribute("bodyContent", "clientProfile");

        return "master-template";
    }

    @GetMapping("/majstorProfile")
    public String getMajstorPage (Model model, HttpServletRequest req){

        String username = req.getRemoteUser().toString();

        Majstor majsotr = this.majstorService.findMajstorByKorisnikUsername(username);

        List<Subcategory> subcategoryList = this.subCategoryService.listAll();

        float ratingCount = majsotr.getRatingCount();
        float raitingSum = majsotr.getRatingSum();
        float raiting = 0;
        if(ratingCount > 0){
            raiting = raitingSum / ratingCount;
        }

        model.addAttribute("subcategoryList", subcategoryList);
        model.addAttribute("raiting", raiting);
        model.addAttribute("majstor", majsotr);
        model.addAttribute("bodyContent", "majstorProfile");

        return "master-template";
    }

    @PostMapping("/update/{id}/client")
    public String updateClient(@PathVariable Long id,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String email,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfBirth,
                               @RequestParam String sex,
                                HttpServletRequest req){

        String username = req.getRemoteUser().toString();

        this.clientService.update(id,dateOfBirth,sex,name,surname,username,email);

        return "redirect:/clientProfile";
    }

    @PostMapping("/update/{id}/majstor")
    public String updateMajstor(@PathVariable Long id,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String email,
                               @RequestParam String telNumber,
                               @RequestParam String city,
                               @RequestParam String address,
                               @RequestParam (required = false) String nameOfFirm,
                               @RequestParam String shortDesc,
                               @RequestParam List<Long> subCatIds,
                               HttpServletRequest req){

        String username = req.getRemoteUser().toString();



        this.majstorService.update(id,telNumber,city,subCatIds,address,nameOfFirm,shortDesc,name,surname,username, email);

        return "redirect:/majstorProfile";
    }

    @GetMapping("/majstori/{id}")
    public String getAllMajstoriPage(@PathVariable Long id, Model model){

        Subcategory subcategory = this.subCategoryService.findById(id);

        List<Majstor> majstorList = this.majstorService.findAllBySubcategoriesContaining(subcategory);
        List<Subcategory> subcategoryList = this.subCategoryService.listAll();

        model.addAttribute("subcategoryList", subcategoryList);
        model.addAttribute("majstorList", majstorList);
        model.addAttribute("bodyContent", "majstori");

        return "master-template";
    }


    @GetMapping("/majstori")
    public String getFilterMajstoriPage(@RequestParam (required = false) Long id,
                                        @RequestParam (required = false) String city,
                                        Model model){
        List<Majstor> majstorList = this.majstorService.listMajstoriBySubCategoryAndCity(id, city);
        List<Subcategory> subcategoryList = this.subCategoryService.listAll();

        model.addAttribute("subcategoryList", subcategoryList);
        model.addAttribute("majstorList", majstorList);
        model.addAttribute("bodyContent", "majstori");

        return "master-template";
    }


    @GetMapping("/profile/{id}")
    public String getMajstorProfile(@PathVariable Long id, Model model){

        Majstor majstor = this.majstorService.findById(id);
        int rSum = majstor.getRatingSum();
        int br = majstor.getRatingCount();
        double rate = 0.0;
        if(br > 0){
            rate = rSum * 1.0 / br;
        }
        List<Subcategory> subcategoryList = this.subCategoryService.listAll();
        model.addAttribute("subcategoryList", subcategoryList);
        model.addAttribute("rate", rate);
        model.addAttribute("majstor", majstor);
        model.addAttribute("bodyContent", "profil");

        return "master-template";
    }

    @PostMapping("/updateRating/{id}")
    public String updateRating(@PathVariable Long id, Model model, @RequestParam(required = false)int star){

       this.majstorService.updateRating(id,star);


        return "redirect:/profile/{id}";
    }


}
