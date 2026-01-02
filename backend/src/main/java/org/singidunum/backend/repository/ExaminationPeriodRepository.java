package org.singidunum.backend.repository;

import org.singidunum.backend.model.ExaminationPeriod;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ExaminationPeriodRepository extends CrudRepository<ExaminationPeriod, Long>, PagingAndSortingRepository<ExaminationPeriod, Long> {
    List<ExaminationPeriod> findByActiveTrue();
}
