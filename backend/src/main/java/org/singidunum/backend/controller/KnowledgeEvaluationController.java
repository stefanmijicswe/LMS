package org.singidunum.backend.controller;

import org.singidunum.backend.dto.CreateKnowledgeEvaluationDTO;
import org.singidunum.backend.dto.ErrorResponseDTO;
import org.singidunum.backend.dto.KnowledgeEvaluationDTO;
import org.singidunum.backend.dto.UpdateKnowledgeEvaluationDTO;
import org.singidunum.backend.model.KnowledgeEvaluation;
import org.singidunum.backend.service.KnowledgeEvaluationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/knowledge-evaluations")
@PreAuthorize("hasRole('ADMIN') or hasRole('STUDENT_SERVICE')")
public class KnowledgeEvaluationController {

    private final KnowledgeEvaluationService knowledgeEvaluationService;

    public KnowledgeEvaluationController(KnowledgeEvaluationService knowledgeEvaluationService) {
        this.knowledgeEvaluationService = knowledgeEvaluationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<KnowledgeEvaluationDTO> getKnowledgeEvaluationById(@PathVariable Long id) {
        KnowledgeEvaluation knowledgeEvaluation = knowledgeEvaluationService.findById(id);

        if (knowledgeEvaluation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        KnowledgeEvaluationDTO knowledgeEvaluationDTO = new KnowledgeEvaluationDTO();
        knowledgeEvaluationDTO.setId(knowledgeEvaluation.getId());
        knowledgeEvaluationDTO.setStartTime(knowledgeEvaluation.getStartTime());
        knowledgeEvaluationDTO.setEndTime(knowledgeEvaluation.getEndTime());
        knowledgeEvaluationDTO.setPoints(knowledgeEvaluation.getPoints());

        if (knowledgeEvaluation.getEvaluationType() != null) {
            knowledgeEvaluationDTO.setEvaluationTypeId(knowledgeEvaluation.getEvaluationType().getId());
        }

        if (knowledgeEvaluation.getSubjectRealisation() != null) {
            knowledgeEvaluationDTO.setSubjectRealisationId(knowledgeEvaluation.getSubjectRealisation().getId());
        }

        return ResponseEntity.status(HttpStatus.OK).body(knowledgeEvaluationDTO);
    }

    @PostMapping
    public ResponseEntity<KnowledgeEvaluationDTO> createKnowledgeEvaluation(
            @RequestBody CreateKnowledgeEvaluationDTO dto) {

        KnowledgeEvaluation createdKnowledgeEvaluation = knowledgeEvaluationService.create(
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getPoints(),
                dto.getEvaluationTypeId(),
                dto.getSubjectRealisationId()
        );

        KnowledgeEvaluationDTO knowledgeEvaluationDTO = new KnowledgeEvaluationDTO();
        knowledgeEvaluationDTO.setId(createdKnowledgeEvaluation.getId());
        knowledgeEvaluationDTO.setStartTime(createdKnowledgeEvaluation.getStartTime());
        knowledgeEvaluationDTO.setEndTime(createdKnowledgeEvaluation.getEndTime());
        knowledgeEvaluationDTO.setPoints(createdKnowledgeEvaluation.getPoints());

        if (createdKnowledgeEvaluation.getEvaluationType() != null) {
            knowledgeEvaluationDTO.setEvaluationTypeId(createdKnowledgeEvaluation.getEvaluationType().getId());
        }

        if (createdKnowledgeEvaluation.getSubjectRealisation() != null) {
            knowledgeEvaluationDTO.setSubjectRealisationId(createdKnowledgeEvaluation.getSubjectRealisation().getId());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(knowledgeEvaluationDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KnowledgeEvaluationDTO> updateKnowledgeEvaluation(
            @PathVariable Long id,
            @RequestBody UpdateKnowledgeEvaluationDTO dto) {

        KnowledgeEvaluation updatedKnowledgeEvaluation = knowledgeEvaluationService.update(
                id,
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getPoints(),
                dto.getEvaluationTypeId(),
                dto.getSubjectRealisationId()
        );

        KnowledgeEvaluationDTO knowledgeEvaluationDTO = new KnowledgeEvaluationDTO();
        knowledgeEvaluationDTO.setId(updatedKnowledgeEvaluation.getId());
        knowledgeEvaluationDTO.setStartTime(updatedKnowledgeEvaluation.getStartTime());
        knowledgeEvaluationDTO.setEndTime(updatedKnowledgeEvaluation.getEndTime());
        knowledgeEvaluationDTO.setPoints(updatedKnowledgeEvaluation.getPoints());

        if (updatedKnowledgeEvaluation.getEvaluationType() != null) {
            knowledgeEvaluationDTO.setEvaluationTypeId(updatedKnowledgeEvaluation.getEvaluationType().getId());
        }

        if (updatedKnowledgeEvaluation.getSubjectRealisation() != null) {
            knowledgeEvaluationDTO.setSubjectRealisationId(updatedKnowledgeEvaluation.getSubjectRealisation().getId());
        }

        return ResponseEntity.status(HttpStatus.OK).body(knowledgeEvaluationDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleKnowledgeEvaluationException(RuntimeException ex) {
        String message = ex.getMessage();

        if (message != null) {
            if (message.contains("Knowledge Evaluation not found") ||
                    message.contains("Evaluation Type not found") ||
                    message.contains("Subject Realisation not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseDTO(message));
            }
            if (message.contains("is required") ||
                    message.contains("Start time must be before end time")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponseDTO(message));
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO("An error occurred"));
    }
}