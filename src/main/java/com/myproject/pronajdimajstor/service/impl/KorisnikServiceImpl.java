package com.myproject.pronajdimajstor.service.impl;

import com.myproject.pronajdimajstor.model.Korisnik;
import com.myproject.pronajdimajstor.model.enumerations.Role;
import com.myproject.pronajdimajstor.model.exceptions.InvalidKorisnikUsernameException;
import com.myproject.pronajdimajstor.model.exceptions.InvalidUsernameOrPasswordException;
import com.myproject.pronajdimajstor.model.exceptions.PasswordsDoNotMatchException;
import com.myproject.pronajdimajstor.model.exceptions.UsernameAlreadyExistsException;
import com.myproject.pronajdimajstor.repository.KorisnikRepository;
import com.myproject.pronajdimajstor.service.KorisnikService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikServiceImpl implements KorisnikService {

    private final KorisnikRepository korisnikRepository;
    private final PasswordEncoder passwordEncoder;

    public KorisnikServiceImpl(KorisnikRepository korisnikRepository, PasswordEncoder passwordEncoder) {
        this.korisnikRepository = korisnikRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Korisnik> listAll() {
        return this.korisnikRepository.findAll();
    }

    @Override
    public Korisnik findByUsername(String username) {
        return this.korisnikRepository.findByUsername(username).orElseThrow(InvalidKorisnikUsernameException::new);
    }

    @Override
    public Korisnik create(String name, String surname, String username, String email, String password, String repeatPassword, Role role) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty())
            throw  new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.korisnikRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        Korisnik korisnik = new Korisnik(name, surname, username, email, passwordEncoder.encode(password), role);

        return this.korisnikRepository.save(korisnik);
    }

    @Override
    public Korisnik update(String name, String surname, String username, String email) {

        Korisnik korisnik = this.findByUsername(username);
        korisnik.setName(name);
        korisnik.setSurname(surname);
        korisnik.setEmail(email);

        return this.korisnikRepository.save(korisnik);
    }

    @Override
    public Korisnik delete(String username) {
        Korisnik korisnik = this.findByUsername(username);
        this.korisnikRepository.delete(korisnik);
        return korisnik;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.korisnikRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(username));
    }
}
