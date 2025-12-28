package org.singidunum.backend.service;

import org.singidunum.backend.model.Subject;
import org.singidunum.backend.model.YearOfStudy;
import org.singidunum.backend.repository.SubjectRepository;
import org.singidunum.backend.repository.YearOfStudyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final YearOfStudyRepository yearOfStudyRepository;

    public SubjectService(SubjectRepository subjectRepository, YearOfStudyRepository yearOfStudyRepository) {
        this.subjectRepository = subjectRepository;
        this.yearOfStudyRepository = yearOfStudyRepository;
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

    @Transactional
    public Subject create(
            String name,
            Integer ects,
            Boolean mandatory,
            Integer numberOfLectures,
            Integer numberOfExercises,
            Long yearOfStudyId,
            Long prerequisiteId,
            Integer otherTeachingTypes,
            Integer research,
            Integer otherClasses,
            String syllabus
    ) {
        if (name == null || name.trim().isEmpty()) {
            throw new RuntimeException("Name is required");
        }

        if (ects == null) {
            throw new RuntimeException("ECTS is required");
        }

        if (mandatory == null) {
            throw new RuntimeException("Mandatory is required");
        }

        if (numberOfLectures == null) {
            throw new RuntimeException("Number of lectures is required");
        }

        if (numberOfExercises == null) {
            throw new RuntimeException("Number of exercises is required");
        }

        if (yearOfStudyId == null) {
            throw new RuntimeException("YearOfStudy ID is required");
        }

        YearOfStudy yearOfStudy = yearOfStudyRepository.findById(yearOfStudyId)
                .orElseThrow(() -> new RuntimeException("YearOfStudy not found"));

        Subject subject = new Subject();
        subject.setName(name);
        subject.setEcts(ects);
        subject.setMandatory(mandatory);
        subject.setNumberOfLectures(numberOfLectures);
        subject.setNumberOfExercises(numberOfExercises);
        subject.setYearOfStudy(yearOfStudy);

        if (prerequisiteId != null) {
            Subject prerequisite = subjectRepository.findById(prerequisiteId)
                    .orElseThrow(() -> new RuntimeException("Prerequisite not found"));
            subject.setPrerequisite(prerequisite);
        }

        if (otherTeachingTypes != null) {
            subject.setOtherTeachingTypes(otherTeachingTypes);
        }

        if (research != null) {
            subject.setResearch(research);
        }

        if (otherClasses != null) {
            subject.setOtherClasses(otherClasses);
        }

        if (syllabus != null) {
            subject.setSyllabus(syllabus);
        }

        return subjectRepository.save(subject);
    }

    @Transactional
    public Subject update(
            Long id,
            String name,
            Integer ects,
            Boolean mandatory,
            Integer numberOfLectures,
            Integer numberOfExercises,
            Long yearOfStudyId,
            Long prerequisiteId,
            Integer otherTeachingTypes,
            Integer research,
            Integer otherClasses,
            String syllabus
    ) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        if (name != null) {
            subject.setName(name);
        }

        if (ects != null) {
            subject.setEcts(ects);
        }

        if (mandatory != null) {
            subject.setMandatory(mandatory);
        }

        if (numberOfLectures != null) {
            subject.setNumberOfLectures(numberOfLectures);
        }

        if (numberOfExercises != null) {
            subject.setNumberOfExercises(numberOfExercises);
        }

        if (yearOfStudyId != null) {
            YearOfStudy yearOfStudy = yearOfStudyRepository.findById(yearOfStudyId)
                    .orElseThrow(() -> new RuntimeException("YearOfStudy not found"));
            subject.setYearOfStudy(yearOfStudy);
        }

        if (prerequisiteId != null) {
            Subject prerequisite = subjectRepository.findById(prerequisiteId)
                    .orElseThrow(() -> new RuntimeException("Prerequisite not found"));
            subject.setPrerequisite(prerequisite);
        }

        if (otherTeachingTypes != null) {
            subject.setOtherTeachingTypes(otherTeachingTypes);
        }

        if (research != null) {
            subject.setResearch(research);
        }

        if (otherClasses != null) {
            subject.setOtherClasses(otherClasses);
        }

        if (syllabus != null) {
            subject.setSyllabus(syllabus);
        }

        return subjectRepository.save(subject);
    }
}
