package org.singidunum.backend.service;

import org.singidunum.backend.model.Faculty;
import org.singidunum.backend.model.StudyProgramme;
import org.singidunum.backend.model.Teacher;
import org.singidunum.backend.repository.FacultyRepository;
import org.singidunum.backend.repository.StudyProgrammeRepository;
import org.singidunum.backend.repository.TeacherRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StudyProgrammeService {

    private final StudyProgrammeRepository studyProgrammeRepository;
    private final FacultyRepository facultyRepository;
    private final TeacherRepository teacherRepository;

    public StudyProgrammeService(StudyProgrammeRepository studyProgrammeRepository, FacultyRepository facultyRepository, TeacherRepository teacherRepository) {
        this.studyProgrammeRepository = studyProgrammeRepository;
        this.facultyRepository = facultyRepository;
        this.teacherRepository = teacherRepository;
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

    @Transactional
    public StudyProgramme update(Long id,
                                 String name,
                                 String description,
                                 Long coordinatorId,
                                 Long facultyId) {
        StudyProgramme studyProgramme = studyProgrammeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("StudyProgramme not found"));

        if (name != null) {
            studyProgramme.setName(name);
        }
        if (description != null) {
            studyProgramme.setDescription(description);
        }

        if (coordinatorId != null) {
            Teacher coordinator = teacherRepository.findById(coordinatorId)
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            studyProgramme.setCoordinator(coordinator);
        }

        if (facultyId != null) {
            Faculty faculty = facultyRepository.findById(facultyId)
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            studyProgramme.setFaculty(faculty);
        }

        return studyProgrammeRepository.save(studyProgramme);
    }

    @Transactional
    public StudyProgramme create(String name,
                                 String description,
                                 Long coordinatorId,
                                 Long facultyId) {
        if (name == null || name.trim().isEmpty()) {
            throw new RuntimeException("Name is required");
        }

        if (facultyId == null) {
            throw new RuntimeException("Faculty ID is required");
        }

        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        StudyProgramme studyProgramme = new StudyProgramme();
        studyProgramme.setName(name);
        studyProgramme.setDescription(description);
        studyProgramme.setFaculty(faculty);

        if (coordinatorId != null) {
            Teacher coordinator = teacherRepository.findById(coordinatorId)
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            
            Iterable<StudyProgramme> allProgrammes = studyProgrammeRepository.findAll();
            for (StudyProgramme sp : allProgrammes) {
                if (sp.getCoordinator() != null && sp.getCoordinator().getId().equals(coordinatorId)) {
                    throw new RuntimeException("Teacher is already coordinator of another study programme");
                }
            }
            
            studyProgramme.setCoordinator(coordinator);
        }

        try {
            return studyProgrammeRepository.save(studyProgramme);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage() != null && e.getMessage().contains("coordinator_id")) {
                throw new RuntimeException("Teacher is already coordinator of another study programme");
            }
            throw new RuntimeException("Database constraint violation");
        }
    }

}
