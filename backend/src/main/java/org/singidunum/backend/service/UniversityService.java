package org.singidunum.backend.service;

import org.singidunum.backend.model.University;
import org.singidunum.backend.repository.UniversityRepository;
import org.springframework.stereotype.Service;


@Service
public class UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public Iterable<University> findAll()
    {
        return universityRepository.findAll();
    }

    public University findById(Long id)
    {
        return universityRepository.findById(id).orElse(null);
    }

    public University save(University university)
    {
        return universityRepository.save(university);
    }

    public void  delete(Long id)
    {
        universityRepository.deleteById(id);
    }

    public void deleteAll()
    {
         universityRepository.deleteAll();
    }

}
