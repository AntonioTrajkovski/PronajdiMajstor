package com.myproject.pronajdimajstor.service;

import com.myproject.pronajdimajstor.model.Client;
import com.myproject.pronajdimajstor.model.ConcludedDeals;
import com.myproject.pronajdimajstor.model.Majstor;
import com.myproject.pronajdimajstor.model.exceptions.InvalidCategoryIdException;
import com.myproject.pronajdimajstor.model.exceptions.InvalidConcludedDealsIdException;
import com.myproject.pronajdimajstor.model.exceptions.InvalidSubCategoryIdException;

import java.util.List;

public interface ConcludedDealsService {

    /**
     * @return List of all entities in the database
     */
    List<ConcludedDeals> listAll();
    /**
     * returns the entity with the given id
     *
     * @param id The id of the entity that we want to obtain
     * @return
     * @throws com.myproject.pronajdimajstor.model.exceptions.InvalidConcludedDealsIdException when there is no entity with the given id
     */
    ConcludedDeals findById(Long id);
    /**
     * This method is used to create a new entity, and save it in the database.
     * @param name
     * @param description
     * @param client
     * @param majstor
     * @return The entity that is created. The id should be generated when the entity is created.
     */
    ConcludedDeals save(String name, String description, Client client, Majstor majstor);
    /**
     * Method that should delete an entity. If the id is invalid, it should throw InvalidConcludedDealsIdException.
     *
     * @param id
     * @return The entity that is deleted.
     * @throws InvalidConcludedDealsIdException when there is no entity with the given id
     */
    ConcludedDeals delete(Long id);
}
