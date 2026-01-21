package org.singidunum.backend.controller;

import org.singidunum.backend.dto.EvaluationTypeDTO;
import org.singidunum.backend.model.EvaluationType;
import org.singidunum.backend.service.EvaluationTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/admin/evaluation-types")
public class EvaluationTypeController {

    private final EvaluationTypeService evaluationTypeService;

    public EvaluationTypeController(EvaluationTypeService evaluationTypeService) {
        this.evaluationTypeService = evaluationTypeService;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EvaluationTypeDTO>> findAllEvaluationTypes() {

        Iterable<EvaluationType> evaluationTypes = evaluationTypeService.findAll();
        List<EvaluationTypeDTO> dtoList = new ArrayList<>();
        for (EvaluationType evaluationType : evaluationTypes) {
            EvaluationTypeDTO dto = new EvaluationTypeDTO();
            dto.setId(evaluationType.getId());
            dto.setName(evaluationType.getName());
            dtoList.add(dto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(dtoList);

    }
}

