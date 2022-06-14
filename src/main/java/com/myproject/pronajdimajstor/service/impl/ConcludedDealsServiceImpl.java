package com.myproject.pronajdimajstor.service.impl;

import com.myproject.pronajdimajstor.model.Client;
import com.myproject.pronajdimajstor.model.ConcludedDeals;
import com.myproject.pronajdimajstor.model.Majstor;
import com.myproject.pronajdimajstor.model.exceptions.InvalidConcludedDealsIdException;
import com.myproject.pronajdimajstor.repository.ConcludedDealsRepository;
import com.myproject.pronajdimajstor.service.ConcludedDealsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcludedDealsServiceImpl implements ConcludedDealsService {

    private final ConcludedDealsRepository concludedDealsRepository;

    public ConcludedDealsServiceImpl(ConcludedDealsRepository concludedDealsRepository) {
        this.concludedDealsRepository = concludedDealsRepository;
    }


    @Override
    public List<ConcludedDeals> listAll() {
        return this.concludedDealsRepository.findAll();
    }

    @Override
    public ConcludedDeals findById(Long id) {
        return this.concludedDealsRepository.findById(id).orElseThrow(InvalidConcludedDealsIdException::new);
    }

    @Override
    public ConcludedDeals save(String name, String description, Client client, Majstor majstor) {

        ConcludedDeals concludedDeal = new ConcludedDeals(name, description, client, majstor);

        return this.concludedDealsRepository.save(concludedDeal);
    }

    @Override
    public ConcludedDeals delete(Long id) {
        ConcludedDeals concludedDeals = this.findById(id);
        this.concludedDealsRepository.delete(concludedDeals);
        return concludedDeals;
    }
}
