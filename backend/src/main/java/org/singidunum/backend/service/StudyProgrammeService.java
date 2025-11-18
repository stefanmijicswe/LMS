package org.singidunum.backend.service;

import org.singidunum.backend.model.StudyProgramme;
import org.singidunum.backend.repository.StudyProgrammeRepository;
import org.springframework.stereotype.Service;

@Service
public class StudyProgrammeService {

    private final StudyProgrammeRepository studyProgrammeRepository;

    public StudyProgrammeService(StudyProgrammeRepository studyProgrammeRepository) {
        this.studyProgrammeRepository = studyProgrammeRepository;
    }

    public Iterable<StudyProgramme> findAll() {
        return studyProgrammeRepository.findAll();
    }

    public StudyProgramme findById(Long id) {
        return studyProgrammeRepository.findById(id).orElse(null);
    }

    public StudyProgramme save(StudyProgramme studyProgramme) {
        return studyProgrammeRepository.save(studyProgramme);
    }

    public void deleteById(Long id) {
        studyProgrammeRepository.deleteById(id);
    }

    public void deleteAll() {
        studyProgrammeRepository.deleteAll();
    }

}
