package org.singidunum.backend.service;


import org.singidunum.backend.model.Subject;
import org.singidunum.backend.repository.SubjectRepository;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject findById(Long id){
        return subjectRepository.findById(id).orElse(null);
    }

    public void delete(Long id){
        subjectRepository.deleteById(id);
    }

    public Subject save(Subject subject){
        return subjectRepository.save(subject);
    }
    public Iterable<Subject> findAll(){
        return subjectRepository.findAll();
    }
}
