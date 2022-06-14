package com.myproject.pronajdimajstor.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String imgReference;
  //  @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
   // private List<Subcategory> subcategoryList;

    public Category() {

    }

    public Category(String name, String imgReference) {
        this.name = name;
        this.imgReference = imgReference;
    }

}
