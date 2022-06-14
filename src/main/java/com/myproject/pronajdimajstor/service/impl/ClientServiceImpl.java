package com.myproject.pronajdimajstor.service.impl;

import com.myproject.pronajdimajstor.model.Client;
import com.myproject.pronajdimajstor.model.Korisnik;
import com.myproject.pronajdimajstor.model.enumerations.Role;
import com.myproject.pronajdimajstor.model.exceptions.InvalidCategoryIdException;
import com.myproject.pronajdimajstor.model.exceptions.InvalidClientIdException;
import com.myproject.pronajdimajstor.model.exceptions.UsernameAlreadyExistsException;
import com.myproject.pronajdimajstor.repository.ClientRepository;
import com.myproject.pronajdimajstor.service.ClientService;
import com.myproject.pronajdimajstor.service.KorisnikService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final KorisnikService korisnikService;

    public ClientServiceImpl(ClientRepository clientRepository, KorisnikService korisnikService) {
        this.clientRepository = clientRepository;
        this.korisnikService = korisnikService;
    }

    @Override
    public List<Client> listAll() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return this.clientRepository.findById(id).orElseThrow(InvalidClientIdException::new);
    }

    @Override
    public Client create(LocalDate dateOfBirth, String sex, Korisnik korisnik) {

        Client clientExist = this.clientRepository.findClientByKorisnikUsername(korisnik.getUsername());

        if(clientExist != null){
            throw new UsernameAlreadyExistsException(korisnik.getUsername());
        }

        Client client = new Client(dateOfBirth, sex, korisnik);

        return this.clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client update(Long id, LocalDate dateOfBirth, String sex, String name, String surname, String username, String email) {

        Client client = this.findById(id);
        client.setDateOfBirth(dateOfBirth);
        client.setSex(sex);
        this.korisnikService.update(name,surname,username, email);

        return this.clientRepository.save(client);
    }

    @Override
    public Client delete(Long id) {
        return null;
    }

    @Override
    public Client findClientByKorisnikUsername(String username) {
        return this.clientRepository.findClientByKorisnikUsername(username);
    }
}
