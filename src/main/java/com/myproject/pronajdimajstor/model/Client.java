package com.myproject.pronajdimajstor.model;

import com.myproject.pronajdimajstor.model.enumerations.Role;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Korisnik korisnik;
    private LocalDate dateOfBirth;
    private String sex;

    public Client(){}

    public Client(LocalDate dateOfBirth, String sex, Korisnik korisnik) {
     //   super(name,surname, username, email, password, roleDate);
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.korisnik = korisnik;
    }
}
