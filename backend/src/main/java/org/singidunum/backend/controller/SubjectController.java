package org.singidunum.backend.controller;

import org.singidunum.backend.dto.CreateSubjectDTO;
import org.singidunum.backend.dto.ErrorResponseDTO;
import org.singidunum.backend.dto.SubjectDTO;
import org.singidunum.backend.dto.UpdateSubjectDTO;
import org.singidunum.backend.model.Subject;
import org.singidunum.backend.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/subjects")
@PreAuthorize("hasRole('ADMIN')")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable Long id) {
        Subject subject = subjectService.findById(id);

        if (subject == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setName(subject.getName());
        subjectDTO.setEcts(subject.getEcts());
        subjectDTO.setMandatory(subject.isMandatory());
        subjectDTO.setNumberOfLectures(subject.getNumberOfLectures());
        subjectDTO.setNumberOfExercises(subject.getNumberOfExercises());

        if (subject.getYearOfStudy() != null) {
            subjectDTO.setYearOfStudyId(subject.getYearOfStudy().getId());
        }

        if (subject.getPrerequisite() != null) {
            subjectDTO.setPrerequisiteId(subject.getPrerequisite().getId());
        }

        if (subject.getOtherTeachingTypes() != null) {
            subjectDTO.setOtherTeachingTypes(subject.getOtherTeachingTypes());
        }

        if (subject.getResearch() != null) {
            subjectDTO.setResearch(subject.getResearch());
        }

        if (subject.getOtherClasses() != null) {
            subjectDTO.setOtherClasses(subject.getOtherClasses());
        }

        if (subject.getSyllabus() != null) {
            subjectDTO.setSyllabus(subject.getSyllabus());
        }

        return ResponseEntity.status(HttpStatus.OK).body(subjectDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectDTO> updateSubject(
            @PathVariable Long id,
            @RequestBody UpdateSubjectDTO dto) {

        Subject updatedSubject = subjectService.update(
                id,
                dto.getName(),
                dto.getEcts(),
                dto.getMandatory(),
                dto.getNumberOfLectures(),
                dto.getNumberOfExercises(),
                dto.getYearOfStudyId(),
                dto.getPrerequisiteId(),
                dto.getOtherTeachingTypes(),
                dto.getResearch(),
                dto.getOtherClasses(),
                dto.getSyllabus()
        );

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(updatedSubject.getId());
        subjectDTO.setName(updatedSubject.getName());
        subjectDTO.setEcts(updatedSubject.getEcts());
        subjectDTO.setMandatory(updatedSubject.isMandatory());
        subjectDTO.setNumberOfLectures(updatedSubject.getNumberOfLectures());
        subjectDTO.setNumberOfExercises(updatedSubject.getNumberOfExercises());

        if (updatedSubject.getYearOfStudy() != null) {
            subjectDTO.setYearOfStudyId(updatedSubject.getYearOfStudy().getId());
        }

        if (updatedSubject.getPrerequisite() != null) {
            subjectDTO.setPrerequisiteId(updatedSubject.getPrerequisite().getId());
        }

        if (updatedSubject.getOtherTeachingTypes() != null) {
            subjectDTO.setOtherTeachingTypes(updatedSubject.getOtherTeachingTypes());
        }

        if (updatedSubject.getResearch() != null) {
            subjectDTO.setResearch(updatedSubject.getResearch());
        }

        if (updatedSubject.getOtherClasses() != null) {
            subjectDTO.setOtherClasses(updatedSubject.getOtherClasses());
        }

        if (updatedSubject.getSyllabus() != null) {
            subjectDTO.setSyllabus(updatedSubject.getSyllabus());
        }

        return ResponseEntity.status(HttpStatus.OK).body(subjectDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleSubjectException(RuntimeException ex) {
        String message = ex.getMessage();

        if (message != null) {
            if (message.contains("Subject not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseDTO(message));
            }
            if (message.contains("YearOfStudy not found") ||
                    message.contains("Prerequisite not found") ||
                    message.contains("Name is required") ||
                    message.contains("ECTS is required") ||
                    message.contains("Mandatory is required") ||
                    message.contains("Number of lectures is required") ||
                    message.contains("Number of exercises is required") ||
                    message.contains("YearOfStudy ID is required")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponseDTO(message));
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO("An error occurred"));
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(
            @RequestBody CreateSubjectDTO dto) {

        Subject createdSubject = subjectService.create(
                dto.getName(),
                dto.getEcts(),
                dto.getMandatory(),
                dto.getNumberOfLectures(),
                dto.getNumberOfExercises(),
                dto.getYearOfStudyId(),
                dto.getPrerequisiteId(),
                dto.getOtherTeachingTypes(),
                dto.getResearch(),
                dto.getOtherClasses(),
                dto.getSyllabus()
        );

        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(createdSubject.getId());
        subjectDTO.setName(createdSubject.getName());
        subjectDTO.setEcts(createdSubject.getEcts());
        subjectDTO.setMandatory(createdSubject.isMandatory());
        subjectDTO.setNumberOfLectures(createdSubject.getNumberOfLectures());
        subjectDTO.setNumberOfExercises(createdSubject.getNumberOfExercises());

        if (createdSubject.getYearOfStudy() != null) {
            subjectDTO.setYearOfStudyId(createdSubject.getYearOfStudy().getId());
        }

        if (createdSubject.getPrerequisite() != null) {
            subjectDTO.setPrerequisiteId(createdSubject.getPrerequisite().getId());
        }

        if (createdSubject.getOtherTeachingTypes() != null) {
            subjectDTO.setOtherTeachingTypes(createdSubject.getOtherTeachingTypes());
        }

        if (createdSubject.getResearch() != null) {
            subjectDTO.setResearch(createdSubject.getResearch());
        }

        if (createdSubject.getOtherClasses() != null) {
            subjectDTO.setOtherClasses(createdSubject.getOtherClasses());
        }

        if (createdSubject.getSyllabus() != null) {
            subjectDTO.setSyllabus(createdSubject.getSyllabus());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(subjectDTO);
    }
}
