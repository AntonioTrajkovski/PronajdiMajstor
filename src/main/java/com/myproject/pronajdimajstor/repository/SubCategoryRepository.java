package com.myproject.pronajdimajstor.repository;

import com.myproject.pronajdimajstor.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<Subcategory, Long> {

    List<Subcategory> findAllByCategoryId(Long id);

}

