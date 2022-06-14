package com.myproject.pronajdimajstor.service;

import com.myproject.pronajdimajstor.model.Korisnik;
import com.myproject.pronajdimajstor.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface KorisnikService extends UserDetailsService {

    /**
     * @return List of all entities in the database
     */
    List<Korisnik> listAll();
    /**
     * returns the entity with the given username
     *
     * @param username Username of the entity that we want to obtain
     * @return
     * @throws com.myproject.pronajdimajstor.model.exceptions.InvalidKorisnikUsernameException when there is no entity with the given username
     */
    Korisnik findByUsername(String username);
    /**
     * This method is used to create a new entity, and save it in the database. The password should be encrypted before saving.
     * @param name
     * @param surname
     * @param username
     * @param email
     * @param password
     * @param repeatPassword
     * @param role
     * @return The entity that is created. The id should be generated when the entity is created.
     */
    Korisnik create (String name, String surname, String username, String email, String password, String repeatPassword, Role role);
    /**
     * This method is used to modify an entity, and save it in the database.
     * @param name
     * @param surname
     * @param username
     * @param email
     * @return The entity that is created. The id should be generated when the entity is created.
     */
    Korisnik update(String name, String surname, String username, String email);
    /**
     * Method that should delete an entity. If the id is invalid, it should throw InvalidKorisnikUsernameException.
     *
     * @param username
     * @return The entity that is deleted.
     * @throws com.myproject.pronajdimajstor.model.exceptions.InvalidKorisnikUsernameException when there is no entity with the given id
     */
    Korisnik delete(String username);

}
