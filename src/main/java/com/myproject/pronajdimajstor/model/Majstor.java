package com.myproject.pronajdimajstor.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Majstor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Korisnik korisnik;
    private String telNumber;
    private String city;
    @ManyToMany
    private List<Subcategory> subcategories;
    private String address;
    private String nameOfFirm;
    private int ratingSum;
    private int ratingCount;
    private String shortDescription;

    public Majstor(){
    }

    public Majstor(Korisnik korisnik, String telNumber,
                   String city, List<Subcategory> subcategories,
                   String address, String nameOfFirm, String shortDescription) {
        this.korisnik = korisnik;
        this.telNumber = telNumber;
        this.city = city;
        this.subcategories = subcategories;
        this.address = address;
        this.nameOfFirm = nameOfFirm;
        this.ratingSum = 0;
        this.ratingCount = 0;
        this.shortDescription = shortDescription;
    }
}
