package com.myproject.pronajdimajstor.service;

import com.myproject.pronajdimajstor.model.Korisnik;
import com.myproject.pronajdimajstor.model.Majstor;
import com.myproject.pronajdimajstor.model.Subcategory;
import com.myproject.pronajdimajstor.model.exceptions.InvalidCategoryIdException;
import com.myproject.pronajdimajstor.model.exceptions.InvalidSubCategoryIdException;

import java.util.List;

public interface MajstorService {

    /**
     * @return List of all entities in the database
     */
    List<Majstor> listAll();
    /**
     * returns the entity with the given id
     *
     * @param id The id of the entity that we want to obtain
     * @return
     * @throws InvalidCategoryIdException when there is no entity with the given id
     */
    Majstor findById(Long id);
    /**
     * This method is used to create a new entity, and save it in the database.
     * @param korisnik
     * @param telNumber
     * @param city
     * @param subcategoriId The list of subcategory ids to which the entity belongs.
     * @param address
     * @param nameOfFirm
     * @param shortDescription
     * @return The entity that is created. The id should be generated when the entity is created.
     * @throws InvalidSubCategoryIdException when there is no subcategory with the given subcategoriId
     */
    Majstor create(Korisnik korisnik, String telNumber,
                   String city, List<Long> subcategoriId,
                   String address, String nameOfFirm, String shortDescription);
    /**
     * This method is used to modify an entity, and save it in the database.
     *
     * @param id The id of the entity that is being edited
     * @param telNumber
     * @param city
     * @param subcategoriId The list of subcategory ids to which the entity belongs.
     * @param address
     * @param nameOfFirm
     * @param shortDescription
     * @param name
     * @param surname
     * @param username
     * @param email
     * @return The entity that is created. The id should be generated when the entity is created.
     * @throws InvalidSubCategoryIdException when there is no subcategory with the given subcategoriId
     */
    Majstor update(Long id, String telNumber,
                   String city, List<Long> subcategoriId,
                   String address, String nameOfFirm, String shortDescription,
                   String name, String surname, String username,
                   String email);
    /**
     * Method that should delete an entity. If the id is invalid, it should throw InvalidMajstorIdException.
     *
     * @param id
     * @return The entity that is deleted.
     * @throws com.myproject.pronajdimajstor.model.exceptions.InvalidMajstorIdException when there is no entity with the given id
     */
    Majstor delete(Long id);
    /**
     * Method that should return the entity with the given username
     *
     * @param username
     * @return The entity with the given username
     * @throws com.myproject.pronajdimajstor.model.exceptions.InvalidKorisnikUsernameException when there is no entity with the given categoryId
     */
    Majstor findMajstorByKorisnikUsername(String username);
    /**
     * Method that should return list of all entities with specific subcategory
     *
     * @param subcategory
     * @return List of all entities with specific subcategory
     * @throws InvalidSubCategoryIdException when there is no entity with the given subcategoryId
     */
    List<Majstor> findAllBySubcategoriesContaining(Subcategory subcategory);
    /**
     * Method that should update an entity and save it in the database.
     *
     * @param id
     * @param rate
     * @return The entity that is updated.
     * @throws com.myproject.pronajdimajstor.model.exceptions.InvalidMajstorIdException when there is no entity with the given id
     */
    Majstor updateRating (Long id, int rate);
    /**
     * This method is used to filter all entities with specific filtering criteria
     * All arguments are nullable. When an argument is null, we should not filter by that attribute
     *
     * @return The entities that meet the filtering criteria
     */
    List<Majstor> listMajstoriBySubCategoryAndCity(Long id, String city);
}
