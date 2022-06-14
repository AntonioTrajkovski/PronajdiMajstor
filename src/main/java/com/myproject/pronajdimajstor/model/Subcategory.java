package com.myproject.pronajdimajstor.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 4000 )
    private String imgReference;
    @ManyToOne
    private Category category;

    public Subcategory() {

    }

    public Subcategory(String name, Category category, String imgReference) {
        this.name = name;
        this.category = category;
        this.imgReference = imgReference;
    }

}
