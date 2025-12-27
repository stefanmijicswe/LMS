package org.singidunum.backend.controller;

import org.singidunum.backend.dto.ErrorResponseDTO;
import org.singidunum.backend.dto.FacultyDTO;
import org.singidunum.backend.dto.UpdateFacultyDTO;
import org.singidunum.backend.model.Faculty;
import org.singidunum.backend.service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/faculties")
@PreAuthorize("hasRole('ADMIN')")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDTO> getFacultyById(@PathVariable Long id) {
        Faculty faculty = facultyService.findById(id);

        if (faculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        FacultyDTO  facultyDTO = new FacultyDTO();
        facultyDTO.setId(faculty.getId());
        facultyDTO.setName(faculty.getName());
        facultyDTO.setDescription(faculty.getDescription());
        facultyDTO.setPhoneNumber(faculty.getPhoneNumber());
        facultyDTO.setOfficialEmail(faculty.getOfficialEmail());
        facultyDTO.setWebsite(faculty.getWebsite());

        return ResponseEntity.status(HttpStatus.OK).body(facultyDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyDTO> updateFaculty(
            @PathVariable Long id,
            @RequestBody UpdateFacultyDTO dto) {

        Faculty updatedFaculty = facultyService.update(
                id,
                dto.getName(),
                dto.getDescription(),
                dto.getPhoneNumber(),
                dto.getOfficialEmail(),
                dto.getWebsite(),
                dto.getAddressId(),
                dto.getDeanId(),
                dto.getUniversityId()
        );

        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setId(updatedFaculty.getId());
        facultyDTO.setName(updatedFaculty.getName());
        facultyDTO.setDescription(updatedFaculty.getDescription());
        facultyDTO.setPhoneNumber(updatedFaculty.getPhoneNumber());
        facultyDTO.setOfficialEmail(updatedFaculty.getOfficialEmail());
        facultyDTO.setWebsite(updatedFaculty.getWebsite());

        return ResponseEntity.status(HttpStatus.OK).body(facultyDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleFacultyException(RuntimeException ex) {
        String message = ex.getMessage();

        if (message != null) {
            if (message.contains("Faculty not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponseDTO(message));
            }
            if (message.contains("Address not found") ||
                    message.contains("Teacher not found") ||
                    message.contains("University not found")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponseDTO(message));
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO("An error occurred"));
    }
}
