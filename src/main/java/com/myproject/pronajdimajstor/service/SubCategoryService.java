package com.myproject.pronajdimajstor.service;

import com.myproject.pronajdimajstor.model.Category;
import com.myproject.pronajdimajstor.model.Subcategory;
import com.myproject.pronajdimajstor.model.exceptions.InvalidCategoryIdException;
import com.myproject.pronajdimajstor.model.exceptions.InvalidSubCategoryIdException;

import java.util.List;

public interface SubCategoryService {


    /**
     * returns the entity with the given id
     *
     * @param id The id of the entity that we want to obtain
     * @return
     * @throws InvalidSubCategoryIdException when there is no entity with the given id
     */
    Subcategory findById(Long id);
    /**
     * @return List of all entities in the database
     */
    List<Subcategory> listAll();
    /**
     * This method is used to create a new entity, and save it in the database.
     * @param name
     * @param categoryId The category id to which the subCategory belong.
     * @param imgRef
     * @return The entity that is created. The id should be generated when the entity is created.
     * @throws InvalidCategoryIdException when there is no category with the given id
     */
    Subcategory create(String name, Long categoryId, String imgRef);
    /**
     * This method is used to modify an entity, and save it in the database.
     *
     * @param id The id of the entity that is being edited
     * @param name
     * @param categoryId The category id to which the subCategory belong.
     * @param imgRef
     * @return The entity that is updated.
     * @throws InvalidSubCategoryIdException when there is no subcategory with the given id
     * @throws InvalidCategoryIdException when there is no category with the given id
     */
    Subcategory update(Long id, String name, Long categoryId, String imgRef);
    /**
     * Method that should delete an entity. If the id is invalid, it should throw InvalidSubCategoryIdException.
     *
     * @param id
     * @return The entity that is deleted.
     * @throws InvalidSubCategoryIdException when there is no entity with the given id
     */
    Subcategory delete(Long id);

    /**
     * Method that should list all entities in the database with given categoryId
     *
     * @param categoryId
     * @return The entities with categoryId
     * @throws InvalidCategoryIdException when there is no entity with the given categoryId
     */
    List<Subcategory> findAllByCategoryId(Long categoryId);

}
