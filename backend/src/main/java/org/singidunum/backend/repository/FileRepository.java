package org.singidunum.backend.repository;

import org.singidunum.backend.model.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FileRepository extends CrudRepository<File, Long>, PagingAndSortingRepository<File, Long>{

}
