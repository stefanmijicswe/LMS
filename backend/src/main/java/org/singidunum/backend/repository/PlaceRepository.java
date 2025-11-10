package org.singidunum.backend.repository;

import org.singidunum.backend.model.Place;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlaceRepository extends CrudRepository<Place, Long>, PagingAndSortingRepository<Place, Long> {
}
