package org.singidunum.backend.service;


import org.singidunum.backend.model.Faculty;
import org.singidunum.backend.repository.FacultyRepository;
import org.singidunum.backend.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Iterable<Faculty> findAll()
    {
        return facultyRepository.findAll();
    }

    public Faculty findById(Long id)
    {
        return facultyRepository.findById(id).orElse(null);
    }

    public void  save(Faculty faculty)
    {
        facultyRepository.save(faculty);
    }

    public void delete(Long id)
    {
        facultyRepository.deleteById(id);
    }

    public void deleteAll()
    {
        facultyRepository.deleteAll();
    }
}
