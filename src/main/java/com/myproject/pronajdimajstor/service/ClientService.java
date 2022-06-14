package com.myproject.pronajdimajstor.service;

import com.myproject.pronajdimajstor.model.Client;
import com.myproject.pronajdimajstor.model.Korisnik;
import com.myproject.pronajdimajstor.model.exceptions.InvalidCategoryIdException;
import com.myproject.pronajdimajstor.model.exceptions.InvalidSubCategoryIdException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ClientService {

    /**
     * @return List of all entities in the database
     */
    List<Client> listAll();
    /**
     * returns the entity with the given id
     *
     * @param id The id of the entity that we want to obtain
     * @return
     * @throws com.myproject.pronajdimajstor.model.exceptions.InvalidClientIdException when there is no entity with the given id
     */
    Client findById(Long id);
    /**
     * This method is used to create a new entity, and save it in the database.
     * @param dateOfBirth
     * @param sex
     * @param korisnik
     * @return The entity that is created. The id should be generated when the entity is created.
     */
    Client create(LocalDate dateOfBirth, String sex, Korisnik korisnik);
    /**
     * This method is used to modify an entity, and save it in the database.
     *
     * @param id
     * @param dateOfBirth
     * @param sex
     * @param name
     * @param surname
     * @param username
     * @param email
     * @return The entity that is created. The id should be generated when the entity is created.
     */
    Client update(Long id, LocalDate dateOfBirth, String sex, String name, String surname, String username, String email);
    /**
     * Method that should delete an entity. If the id is invalid, it should throw InvalidMajstorIdException.
     *
     * @param id
     * @return The entity that is deleted.
     * @throws com.myproject.pronajdimajstor.model.exceptions.InvalidClientIdException when there is no entity with the given id
     */
    Client delete(Long id);
    /**
     * Method that should return the entity with the given username
     *
     * @param username
     * @return The entity with the given username
     * @throws com.myproject.pronajdimajstor.model.exceptions.InvalidKorisnikUsernameException when there is no entity with the given categoryId
     */
    Client findClientByKorisnikUsername (String username);



}
