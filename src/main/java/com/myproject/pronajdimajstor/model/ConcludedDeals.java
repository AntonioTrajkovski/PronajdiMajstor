package com.myproject.pronajdimajstor.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ConcludedDeals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Majstor majstor;

    public ConcludedDeals(){
    }

    public ConcludedDeals(String name, String description, Client client, Majstor majstor) {
        this.name = name;
        this.description = description;
        this.client = client;
        this.majstor = majstor;
    }
}
