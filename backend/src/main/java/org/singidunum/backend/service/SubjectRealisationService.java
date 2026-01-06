package org.singidunum.backend.service;

import org.springframework.transaction.annotation.Transactional;
import org.singidunum.backend.model.Subject;
import org.singidunum.backend.model.SubjectRealisation;
import org.singidunum.backend.repository.SubjectRealisationRepository;
import org.singidunum.backend.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectRealisationService {

    private final SubjectRealisationRepository subjectRealisationRepository;
    private final SubjectRepository subjectRepository;

    public SubjectRealisationService(SubjectRealisationRepository subjectRealisationRepository, SubjectRepository subjectRepository) {
        this.subjectRealisationRepository = subjectRealisationRepository;
        this.subjectRepository = subjectRepository;
    }

    public Iterable<SubjectRealisation> findAll() {
        return subjectRealisationRepository.findAll();
    }

    public SubjectRealisation findById(Long id) {
        return subjectRealisationRepository.findById(id).orElse(null);
    }

    public SubjectRealisation save(SubjectRealisation subjectRealisation) {
        return subjectRealisationRepository.save(subjectRealisation);
    }

    public void deleteById(Long id) {
        subjectRealisationRepository.deleteById(id);
    }


    @Transactional
    public SubjectRealisation create(Long subjectId) {

        if(subjectId == null) throw new RuntimeException("Subject ID is required");
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        SubjectRealisation subjectRealisation = new SubjectRealisation();
        subjectRealisation.setSubject(subject);
        return subjectRealisationRepository.save(subjectRealisation);
    }

    public Iterable<SubjectRealisation> findByStudyProgrammeId(Long studyProgrammeId) {
        if (studyProgrammeId == null) {
            throw new RuntimeException("Study Programme ID is required");
        }
        return subjectRealisationRepository.findBySubject_YearOfStudy_StudyProgramme_Id(studyProgrammeId);
    }

    @Transactional
    public SubjectRealisation update(Long id, Long subjectId) {
        SubjectRealisation subjectRealisation = subjectRealisationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("SubjectRealisation not found")
        );

        if (subjectId != null) {
            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() -> new RuntimeException("Subject not found"));
            subjectRealisation.setSubject(subject);
        }
        return subjectRealisationRepository.save(subjectRealisation);
    }

}
