package com.myproject.pronajdimajstor.repository;

import com.myproject.pronajdimajstor.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, String> {

    Optional<Korisnik> findByUsername(String username);
    Optional<Korisnik> findByUsernameAndPassword(String userName, String password);

}
