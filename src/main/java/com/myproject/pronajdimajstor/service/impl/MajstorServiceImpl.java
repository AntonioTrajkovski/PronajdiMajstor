package com.myproject.pronajdimajstor.service.impl;

import com.myproject.pronajdimajstor.model.Korisnik;
import com.myproject.pronajdimajstor.model.Majstor;
import com.myproject.pronajdimajstor.model.Subcategory;
import com.myproject.pronajdimajstor.model.exceptions.InvalidMajstorIdException;
import com.myproject.pronajdimajstor.model.exceptions.InvalidSubCategoryIdException;
import com.myproject.pronajdimajstor.model.exceptions.UsernameAlreadyExistsException;
import com.myproject.pronajdimajstor.repository.MajstorRepository;
import com.myproject.pronajdimajstor.repository.SubCategoryRepository;
import com.myproject.pronajdimajstor.service.KorisnikService;
import com.myproject.pronajdimajstor.service.MajstorService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MajstorServiceImpl implements MajstorService {

    private final MajstorRepository majstorRepository;
    private final KorisnikService korisnikService;
    private final SubCategoryRepository subCategoryRepository;

    public MajstorServiceImpl(MajstorRepository majstorRepository, KorisnikService korisnikService, SubCategoryRepository subCategoryRepository) {
        this.majstorRepository = majstorRepository;
        this.korisnikService = korisnikService;
        this.subCategoryRepository = subCategoryRepository;
    }


    @Override
    public List<Majstor> listAll() {
        return this.majstorRepository.findAll();
    }

    @Override
    public Majstor findById(Long id) {
        return this.majstorRepository.findById(id).orElseThrow(InvalidMajstorIdException::new);
    }

    @Override
    public Majstor create(Korisnik korisnik, String telNumber, String city, List<Long> subcategoriId, String address, String nameOfFirm, String shortDescription) {

        Majstor majstorExist = this.majstorRepository.findMajstorByKorisnikUsername(korisnik.getUsername());

        if(majstorExist != null){
            throw new UsernameAlreadyExistsException(korisnik.getUsername());
        }

        List<Subcategory> subcategories = this.subCategoryRepository.findAllById(subcategoriId);

        Majstor majstor = new Majstor(korisnik, telNumber, city, subcategories, address, nameOfFirm, shortDescription);


        return this.majstorRepository.save(majstor);
    }

    @Override
    @Transactional
    public Majstor update(Long id, String telNumber, String city, List<Long> subcategoriId, String address, String nameOfFirm, String shortDescription, String name, String surname, String username, String email) {
        List<Subcategory> subcategories = this.subCategoryRepository.findAllById(subcategoriId);

        Majstor majstor = this.findById(id);
        majstor.setTelNumber(telNumber);
        majstor.setCity(city);
        majstor.setSubcategories(subcategories);
        majstor.setAddress(address);
        majstor.setNameOfFirm(nameOfFirm);
        majstor.setShortDescription(shortDescription);

        this.korisnikService.update(name,surname,username, email);

        return this.majstorRepository.save(majstor);
    }

    @Override
    public Majstor delete(Long id) {

        Majstor majstor = this.findById(id);
        this.majstorRepository.delete(majstor);

        return majstor;
    }

    @Override
    public Majstor findMajstorByKorisnikUsername(String username) {
        return this.majstorRepository.findMajstorByKorisnikUsername(username);
    }

    @Override
    public List<Majstor> findAllBySubcategoriesContaining(Subcategory subcategory) {
        return  this.majstorRepository.findAllBySubcategoriesContaining(subcategory);
    }

    @Override
    public Majstor updateRating(Long id, int rate) {

      Majstor majstor = this.findById(id);

      int rSum = majstor.getRatingSum();
      rSum += rate;
      int br = majstor.getRatingCount();
      br += 1;

      majstor.setRatingSum(rSum);
      majstor.setRatingCount(br);

      return  this.majstorRepository.save(majstor);
    }

    @Override
    public List<Majstor> listMajstoriBySubCategoryAndCity(Long id, String city) {

       if(id != null && city != null){

           Subcategory subcategory = this.subCategoryRepository.findById(id).orElseThrow(InvalidSubCategoryIdException::new);

           return this.majstorRepository.findAllByCityAndSubcategoriesContaining(city, subcategory);

       }
       else if(id != null){
           Subcategory subcategory = this.subCategoryRepository.findById(id).orElseThrow(InvalidSubCategoryIdException::new);
           return this.majstorRepository.findAllBySubcategoriesContaining(subcategory);
       }
       else if(city != null){
           return this.majstorRepository.findAllByCity(city);
       }
       else
           return this.listAll();
    }


}
