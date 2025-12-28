package org.singidunum.backend.controller;

import org.singidunum.backend.dto.CreateStudyProgrammeDTO;
import org.singidunum.backend.dto.ErrorResponseDTO;
import org.singidunum.backend.dto.StudyProgrammeDTO;
import org.singidunum.backend.dto.UpdateStudyProgrammeDTO;
import org.singidunum.backend.model.StudyProgramme;
import org.singidunum.backend.service.StudyProgrammeService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/study-programmes")
@PreAuthorize("hasRole('ADMIN')")
public class StudyProgrammeController {

    private final StudyProgrammeService studyProgrammeService;

    public StudyProgrammeController(StudyProgrammeService studyProgrammeService) {
        this.studyProgrammeService = studyProgrammeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyProgrammeDTO> getStudyProgrammeById(@PathVariable Long id) {
        StudyProgramme studyProgramme = studyProgrammeService.findById(id);

        if (studyProgramme == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        StudyProgrammeDTO studyProgrammeDTO = new StudyProgrammeDTO();
        studyProgrammeDTO.setId(studyProgramme.getId());
        studyProgrammeDTO.setName(studyProgramme.getName());
        studyProgrammeDTO.setDescription(studyProgramme.getDescription());
        
        if (studyProgramme.getFaculty() != null) {
            studyProgrammeDTO.setFacultyId(studyProgramme.getFaculty().getId());
        }

        return ResponseEntity.status(HttpStatus.OK).body(studyProgrammeDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudyProgrammeDTO> updateStudyProgramme(
            @PathVariable Long id,
            @RequestBody UpdateStudyProgrammeDTO dto) {

        StudyProgramme updatedStudyProgramme = studyProgrammeService.update(
                id,
                dto.getName(),
                dto.getDescription(),
                dto.getCoordinatorId(),
                dto.getFacultyId()
        );

        StudyProgrammeDTO studyProgrammeDTO = new StudyProgrammeDTO();
        studyProgrammeDTO.setId(updatedStudyProgramme.getId());
        studyProgrammeDTO.setName(updatedStudyProgramme.getName());
        studyProgrammeDTO.setDescription(updatedStudyProgramme.getDescription());
        
        if (updatedStudyProgramme.getFaculty() != null) {
            studyProgrammeDTO.setFacultyId(updatedStudyProgramme.getFaculty().getId());
        }

        return ResponseEntity.status(HttpStatus.OK).body(studyProgrammeDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleStudyProgrammeException(RuntimeException ex) {
        String message = ex.getMessage();

        if (message != null) {
            if (message.contains("StudyProgramme not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseDTO(message));
            }
            if (message.contains("Teacher not found") ||
                    message.contains("Faculty not found") ||
                    message.contains("Name is required") ||
                    message.contains("Faculty ID is required") ||
                    message.contains("already coordinator") ||
                    message.contains("Database constraint violation")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponseDTO(message));
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO("An error occurred"));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = ex.getMessage();
        if (message != null && message.contains("coordinator_id")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDTO("Teacher is already coordinator of another study programme"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO("Database constraint violation"));
    }

    @PostMapping
    public ResponseEntity<StudyProgrammeDTO> createStudyProgramme(
            @RequestBody CreateStudyProgrammeDTO dto) {

        StudyProgramme createdStudyProgramme = studyProgrammeService.create(
                dto.getName(),
                dto.getDescription(),
                dto.getCoordinatorId(),
                dto.getFacultyId()
        );

        StudyProgrammeDTO studyProgrammeDTO = new StudyProgrammeDTO();
        studyProgrammeDTO.setId(createdStudyProgramme.getId());
        studyProgrammeDTO.setName(createdStudyProgramme.getName());
        studyProgrammeDTO.setDescription(createdStudyProgramme.getDescription());
        
        if (createdStudyProgramme.getFaculty() != null) {
            studyProgrammeDTO.setFacultyId(createdStudyProgramme.getFaculty().getId());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(studyProgrammeDTO);
    }
}