package com.myproject.pronajdimajstor.repository;

import com.myproject.pronajdimajstor.model.ConcludedDeals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcludedDealsRepository extends JpaRepository<ConcludedDeals,Long> {
}
