package org.singidunum.backend.controller;


import org.singidunum.backend.dto.TeachingTypeDTO;
import org.singidunum.backend.model.TeachingType;
import org.singidunum.backend.service.TeachingTypeService;
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
@RequestMapping("/api/admin/teaching-types")
public class TeachingTypeController {

    private final TeachingTypeService teachingTypeService;

    public TeachingTypeController(TeachingTypeService teachingTypeService) {
        this.teachingTypeService = teachingTypeService;
    }

    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TeachingTypeDTO>> findAllTeachingTypes() {

        Iterable<TeachingType> teachingTypes = teachingTypeService.findAll();
        List<TeachingTypeDTO> dtoList = new ArrayList<>();
        for (TeachingType teachingType : teachingTypes) {
            TeachingTypeDTO dto = new TeachingTypeDTO();
            dto.setId(teachingType.getId());
            dto.setName(teachingType.getName());
            dtoList.add(dto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(dtoList);

    }
}
