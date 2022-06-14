package com.myproject.pronajdimajstor.repository;

import com.myproject.pronajdimajstor.model.Majstor;
import com.myproject.pronajdimajstor.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajstorRepository extends JpaRepository<Majstor, Long> {

    Majstor findMajstorByKorisnikUsername(String username);

    List<Majstor> findAllBySubcategoriesContaining(Subcategory subcategory);

    List<Majstor> findAllByCity(String city);

    List<Majstor> findAllByCityAndSubcategoriesContaining(String city, Subcategory subcategory);

}

