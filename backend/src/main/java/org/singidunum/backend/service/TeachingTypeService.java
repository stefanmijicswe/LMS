package org.singidunum.backend.service;
import org.singidunum.backend.model.TeachingType;
import org.singidunum.backend.repository.TeachingTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class TeachingTypeService {

    private final TeachingTypeRepository teachingTypeRepository;

    public TeachingTypeService(TeachingTypeRepository teachingTypeRepository) {
        this.teachingTypeRepository = teachingTypeRepository;
    }

    public Iterable<TeachingType> findAll() {
        return teachingTypeRepository.findAll();
    }
}
