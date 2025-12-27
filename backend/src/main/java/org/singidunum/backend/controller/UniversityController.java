package org.singidunum.backend.controller;

import org.singidunum.backend.dto.ErrorResponseDTO;
import org.singidunum.backend.dto.UniversityDTO;
import org.singidunum.backend.dto.UpdateUniversityDTO;
import org.singidunum.backend.model.University;
import org.singidunum.backend.service.UniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/universities")
@PreAuthorize("hasRole('ADMIN')")
public class UniversityController {

    private final UniversityService universityService;

    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UniversityDTO> getUniversityById(@PathVariable Long id) {
        University university = universityService.findById(id);

        if (university == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UniversityDTO universityDTO = new UniversityDTO();
        universityDTO.setId(university.getId());
        universityDTO.setName(university.getName());
        universityDTO.setDescription(university.getDescription());
        universityDTO.setPhoneNumber(university.getPhoneNumber());
        universityDTO.setOfficialEmail(university.getOfficialEmail());
        universityDTO.setWebsite(university.getWebsite());

        return ResponseEntity.status(HttpStatus.OK).body(universityDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UniversityDTO> updateUniversity(
            @PathVariable Long id,
            @RequestBody UpdateUniversityDTO dto) {

        University updatedUniversity = universityService.update(
                id,
                dto.getName(),
                dto.getDescription(),
                dto.getPhoneNumber(),
                dto.getOfficialEmail(),
                dto.getWebsite(),
                dto.getAddressId(),
                dto.getRectorId()
        );

        UniversityDTO universityDTO = new UniversityDTO();
        universityDTO.setId(updatedUniversity.getId());
        universityDTO.setName(updatedUniversity.getName());
        universityDTO.setDescription(updatedUniversity.getDescription());
        universityDTO.setPhoneNumber(updatedUniversity.getPhoneNumber());
        universityDTO.setOfficialEmail(updatedUniversity.getOfficialEmail());
        universityDTO.setWebsite(updatedUniversity.getWebsite());

        return ResponseEntity.status(HttpStatus.OK).body(universityDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleUniversityException(RuntimeException ex) {
        String message = ex.getMessage();

        if (message != null) {
            if (message.contains("University not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseDTO(message));
            }
            if (message.contains("Address not found") ||
                    message.contains("Teacher not found")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponseDTO(message));
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO("An error occurred"));
    }

}