package org.singidunum.backend.controller;

import org.singidunum.backend.dto.CreateExaminationPeriodDTO;
import org.singidunum.backend.dto.ErrorResponseDTO;
import org.singidunum.backend.dto.ExaminationPeriodDTO;
import org.singidunum.backend.dto.UpdateExaminationPeriodDTO;
import org.singidunum.backend.model.ExaminationPeriod;
import org.singidunum.backend.service.ExaminationPeriodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin/examination-periods")
@PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT_SERVICE')")
public class ExaminationPeriodController {

    private final ExaminationPeriodService examinationPeriodService;

    public ExaminationPeriodController(ExaminationPeriodService examinationPeriodService) {
        this.examinationPeriodService = examinationPeriodService;
    }

    @GetMapping
    public ResponseEntity<List<ExaminationPeriodDTO>> getAllExaminationPeriods() {
        List<ExaminationPeriodDTO> examinationPeriodDTOs = new ArrayList<>();
        Iterable<ExaminationPeriod> examinationPeriods = examinationPeriodService.findAll();

        for (ExaminationPeriod examinationPeriod : examinationPeriods) {
            examinationPeriodDTOs.add(convertToDTO(examinationPeriod));
        }

        return ResponseEntity.status(HttpStatus.OK).body(examinationPeriodDTOs);
    }

    @GetMapping("/active")
    public ResponseEntity<List<ExaminationPeriodDTO>> getActiveExaminationPeriods() {
        List<ExaminationPeriodDTO> examinationPeriodDTOs = new ArrayList<>();
        List<ExaminationPeriod> examinationPeriods = examinationPeriodService.findAllActive();

        for (ExaminationPeriod examinationPeriod : examinationPeriods) {
            examinationPeriodDTOs.add(convertToDTO(examinationPeriod));
        }

        return ResponseEntity.status(HttpStatus.OK).body(examinationPeriodDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExaminationPeriodDTO> getExaminationPeriodById(@PathVariable Long id) {
        try {
            ExaminationPeriod examinationPeriod = examinationPeriodService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(convertToDTO(examinationPeriod));
        } catch (RuntimeException ex) {
            if (ex.getMessage() != null && ex.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            throw ex;
        }
    }

    @PostMapping
    public ResponseEntity<ExaminationPeriodDTO> createExaminationPeriod(
            @RequestBody CreateExaminationPeriodDTO dto) {

        ExaminationPeriod createdExaminationPeriod = examinationPeriodService.create(
                dto.getName(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getActive()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(createdExaminationPeriod));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExaminationPeriodDTO> updateExaminationPeriod(
            @PathVariable Long id,
            @RequestBody UpdateExaminationPeriodDTO dto) {

        ExaminationPeriod updatedExaminationPeriod = examinationPeriodService.update(
                id,
                dto.getName(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getActive()
        );

        return ResponseEntity.status(HttpStatus.OK).body(convertToDTO(updatedExaminationPeriod));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExaminationPeriod(@PathVariable Long id) {
        try {
            examinationPeriodService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException ex) {
            if (ex.getMessage() != null && ex.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            throw ex;
        }
    }

    private ExaminationPeriodDTO convertToDTO(ExaminationPeriod examinationPeriod) {
        ExaminationPeriodDTO dto = new ExaminationPeriodDTO();
        dto.setId(examinationPeriod.getId());
        dto.setName(examinationPeriod.getName());
        dto.setStartDate(examinationPeriod.getStartDate());
        dto.setEndDate(examinationPeriod.getEndDate());
        dto.setActive(examinationPeriod.getActive());
        return dto;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleExaminationPeriodException(RuntimeException ex) {
        String message = ex.getMessage();

        if (message != null) {
            if (message.contains("Examination Period not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseDTO(message));
            }
            if (message.contains("is required") ||
                    message.contains("Start date must be before end date") ||
                    message.contains("Cannot delete examination period with associated knowledge evaluations")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponseDTO(message));
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO("An error occurred"));
    }
}
