package org.singidunum.backend.repository;


import org.singidunum.backend.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long>, PagingAndSortingRepository<Country, Long> {
}
