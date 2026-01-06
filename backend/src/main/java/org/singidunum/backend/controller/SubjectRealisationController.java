package org.singidunum.backend.controller;
import org.singidunum.backend.dto.CreateSubjectRealisationDTO;
import org.singidunum.backend.dto.ErrorResponseDTO;
import org.singidunum.backend.dto.SubjectRealisationDTO;
import org.singidunum.backend.dto.UpdateSubjectRealisationDTO;
import org.singidunum.backend.model.SubjectRealisation;
import org.singidunum.backend.service.SubjectRealisationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/subject-realisations")
@PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT_SERVICE')")

public class SubjectRealisationController {

    private final SubjectRealisationService subjectRealisationService;

    public SubjectRealisationController(SubjectRealisationService subjectRealisationService) {
        this.subjectRealisationService = subjectRealisationService;
    }


    @GetMapping
    public ResponseEntity<List<SubjectRealisationDTO>> getAllSubjectRealisations(
            @RequestParam(required = false) Long studyProgrammeId) {

        Iterable<SubjectRealisation> subjectRealisations;

        if (studyProgrammeId != null) {
            subjectRealisations = subjectRealisationService.findByStudyProgrammeId(studyProgrammeId);
        } else {
            subjectRealisations = subjectRealisationService.findAll();
        }

        List<SubjectRealisationDTO> subjectRealisationDTOs = new ArrayList<>();
        for (SubjectRealisation subjectRealisation : subjectRealisations) {
            SubjectRealisationDTO subjectRealisationDTO = new SubjectRealisationDTO();
            subjectRealisationDTO.setId(subjectRealisation.getId());
            
            if (subjectRealisation.getSubject() != null) {
                subjectRealisationDTO.setSubjectId(subjectRealisation.getSubject().getId());
                subjectRealisationDTO.setSubjectName(subjectRealisation.getSubject().getName());
            }
            
            subjectRealisationDTOs.add(subjectRealisationDTO);
        }

        return ResponseEntity.status(HttpStatus.OK).body(subjectRealisationDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SubjectRealisationDTO> getSubjectRealisationById(@PathVariable Long id) {
        SubjectRealisation subjectRealisation = subjectRealisationService.findById(id);

        if (subjectRealisation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        SubjectRealisationDTO subjectRealisationDTO = new SubjectRealisationDTO();
        subjectRealisationDTO.setId(subjectRealisation.getId());

        if (subjectRealisation.getSubject() != null) {
            subjectRealisationDTO.setSubjectId(subjectRealisation.getSubject().getId());
            subjectRealisationDTO.setSubjectName(subjectRealisation.getSubject().getName());
        }

        return ResponseEntity.status(HttpStatus.OK).body(subjectRealisationDTO);
    }

    @PostMapping
    public ResponseEntity<SubjectRealisationDTO> createSubjectRealisation(
            @RequestBody CreateSubjectRealisationDTO dto) {

        SubjectRealisation createdSubjectRealisation = subjectRealisationService.create(
                dto.getSubjectId()
        );

        SubjectRealisationDTO subjectRealisationDTO = new SubjectRealisationDTO();
        subjectRealisationDTO.setId(createdSubjectRealisation.getId());

        if (createdSubjectRealisation.getSubject() != null) {
            subjectRealisationDTO.setSubjectId(createdSubjectRealisation.getSubject().getId());
            subjectRealisationDTO.setSubjectName(createdSubjectRealisation.getSubject().getName());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(subjectRealisationDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectRealisationDTO> updateSubjectRealisation(
            @PathVariable Long id,
            @RequestBody UpdateSubjectRealisationDTO dto) {

        SubjectRealisation updatedSubjectRealisation = subjectRealisationService.update(
                id,
                dto.getSubjectId()
        );

        SubjectRealisationDTO subjectRealisationDTO = new SubjectRealisationDTO();
        subjectRealisationDTO.setId(updatedSubjectRealisation.getId());

        if (updatedSubjectRealisation.getSubject() != null) {
            subjectRealisationDTO.setSubjectId(updatedSubjectRealisation.getSubject().getId());
            subjectRealisationDTO.setSubjectName(updatedSubjectRealisation.getSubject().getName());
        }

        return ResponseEntity.status(HttpStatus.OK).body(subjectRealisationDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleSubjectRealisationException(RuntimeException ex) {
        String message = ex.getMessage();

        if (message != null) {
            if (message.contains("SubjectRealisation not found") ||
                    message.contains("Subject not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseDTO(message));
            }
            if (message.contains("Subject ID is required")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponseDTO(message));
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO("An error occurred"));
    }

}
