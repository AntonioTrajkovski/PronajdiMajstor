package com.myproject.pronajdimajstor.service;

import com.myproject.pronajdimajstor.model.Category;
import com.myproject.pronajdimajstor.model.exceptions.InvalidCategoryIdException;
import com.myproject.pronajdimajstor.model.exceptions.InvalidSubCategoryIdException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    /**
     * returns the entity with the given id
     *
     * @param id The id of the entity that we want to obtain
     * @return
     * @throws InvalidCategoryIdException when there is no entity with the given id
     */
    Optional<Category> findById(Long id);
    /**
     * @return List of all entities in the database
     */
    List<Category> listAll();
    /**
     * This method is used to create a new entity, and save it in the database.
     * @param name
     * @param imgRef
     * @return The entity that is created. The id should be generated when the entity is created.
     */
    Category create(String name, String imgRef);
    /**
     * This method is used to modify an entity, and save it in the database.
     *
     * @param id The id of the entity that is being edited
     * @param name
     * @param imgRef
     * @return The entity that is updated.
     * @throws InvalidCategoryIdException when there is no category with the given id
     */
    Category update(Long id, String name, String imgRef);
    /**
     * Method that should delete an entity. If the id is invalid, it should throw InvalidSubCategoryIdException.
     *
     * @param id
     * @return The entity that is deleted.
     * @throws InvalidCategoryIdException when there is no entity with the given id
     */
    void deleteById(Long id);
}
