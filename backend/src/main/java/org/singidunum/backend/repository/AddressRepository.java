package org.singidunum.backend.repository;

import org.singidunum.backend.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AddressRepository extends CrudRepository<Address, Long>, PagingAndSortingRepository<Address, Long> {
}
