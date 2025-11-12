package org.singidunum.backend.repository;

import org.singidunum.backend.model.TeachingMaterial;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeachingMaterialRepository extends CrudRepository<TeachingMaterial, Long>, PagingAndSortingRepository<TeachingMaterial, Long>{

}
