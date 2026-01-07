package org.singidunum.backend.controller;

import org.singidunum.backend.dto.*;
import org.singidunum.backend.model.*;
import org.singidunum.backend.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;
    private final AddressService addressService;
    private final PlaceService placeService;
    private final CountryService countryService;
    private final AuthService authService;

    public StudentController(StudentService studentService, AddressService addressService,
                             PlaceService placeService, CountryService countryService, AuthService authService) {
        this.studentService = studentService;
        this.addressService = addressService;
        this.placeService = placeService;
        this.countryService = countryService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<Iterable<StudentDTO>> getAllStudents() {
        ArrayList<StudentDTO> studentsDTO = new ArrayList<>();
        Iterable<Student> students = studentService.findAll();
        
        for (Student s : students) {
            studentsDTO.add(convertStudentToDTO(s));
        }
        
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        Student s = studentService.findById(id);
        if (s != null) {
            return new ResponseEntity<>(convertStudentToDTO(s), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO newStudent) {
        try {
            if (newStudent.getAddress() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            
            Student student = new Student();
            student.setName(newStudent.getName());
            student.setSurname(newStudent.getSurname());
            student.setPin(newStudent.getPin());
            
            Address address = convertAddressToEntity(newStudent.getAddress());
            if (address == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            student.setAddress(address);
            
            Student savedStudent = studentService.save(student);
            return new ResponseEntity<>(convertStudentToDTO(savedStudent), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO newStudent) {
        Student s = studentService.findById(id);
        if (s != null) {
            s.setName(newStudent.getName());
            s.setSurname(newStudent.getSurname());
            s.setPin(newStudent.getPin());
            
            if (newStudent.getAddress() != null) {
                Address address = convertAddressToEntity(newStudent.getAddress());
                s.setAddress(address);
            }
            
            Student updatedStudent = studentService.save(s);
            return new ResponseEntity<>(convertStudentToDTO(updatedStudent), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        Student student = studentService.findById(id);
        if (student != null) {
            studentService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/me/subjects")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<StudentSubjectDTO>> getMySubjects(Authentication authentication) {
        try {
            String username = authentication.getName();
            Long userId = authService.findDomainUserByUsername(username).getId();
            Student student = studentService.findByUserId(userId);

            List<StudentSubjectDTO> subjects = studentService.getStudentSubjects(student.getId());
            return ResponseEntity.ok(subjects);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/me/activities")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<StudentActivityDTO>> getMyActivities(Authentication authentication) {
        try {
            String username = authentication.getName();
            Long userId = authService.findDomainUserByUsername(username).getId();
            Student student = studentService.findByUserId(userId);

            List<StudentActivityDTO> activities = studentService.getStudentActivities(student.getId());
            return ResponseEntity.ok(activities);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/me/register-exam")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ExaminationDTO> registerForExam(
            @RequestBody RegisterExamDTO dto,
            Authentication authentication) {
        try {
            String username = authentication.getName();
            Long userId = authService.findDomainUserByUsername(username).getId();
            Student student = studentService.findByUserId(userId);

            Examination examination = studentService.registerForExam(
                    student.getId(),
                    dto.getKnowledgeEvaluationId()
            );

            ExaminationDTO examinationDTO = new ExaminationDTO();
            examinationDTO.setId(examination.getId());
            examinationDTO.setPoints(examination.getPoints());
            examinationDTO.setNote(examination.getNote());
            examinationDTO.setStudentOnYearId(examination.getStudentOnYear().getId());
            examinationDTO.setKnowledgeEvaluationId(examination.getKnowledgeEvaluation().getId());

            return ResponseEntity.status(HttpStatus.CREATED).body(examinationDTO);
        } catch (RuntimeException ex) {
            String message = ex.getMessage();
            if (message != null && message.contains("already registered")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if (message != null && message.contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private StudentDTO convertStudentToDTO(Student s) {
        Address address = s.getAddress();
        AddressDTO addressDTO = convertAddressToDTO(address);
        
        StudentDTO studentDTO = new StudentDTO(
            s.getId(),
            s.getName(),
            s.getSurname(),
            s.getPin(),
            addressDTO
        );
        
        return studentDTO;
    }

    private AddressDTO convertAddressToDTO(Address address) {
        if (address == null || address.getPlace() == null) {
            return null;
        }
        
        Place place = address.getPlace();
        CountryDTO countryDTO = null;
        
        if (place.getCountry() != null) {
            Country country = place.getCountry();
            countryDTO = new CountryDTO(
                country.getId(),
                country.getName()
            );
        }
        
        PlaceDTO placeDTO = new PlaceDTO(
            place.getId(),
            place.getName(),
            countryDTO
        );
        
        AddressDTO addressDTO = new AddressDTO(
            address.getId(),
            address.getStreetName(),
            address.getStreetNumber(),
            placeDTO
        );
        
        return addressDTO;
    }

    private Address convertAddressToEntity(AddressDTO dto) {
        if (dto.getId() != null) {
            Address existingAddress = addressService.findAddressById(dto.getId());
            if (existingAddress == null) {
                throw new IllegalArgumentException("Address with id " + dto.getId() + " not found");
            }
            return existingAddress;
        }
        Address address = new Address();
        address.setStreetName(dto.getStreetName());
        address.setStreetNumber(dto.getStreetNumber());
        if (dto.getPlace() == null) {
            throw new IllegalArgumentException("Address must have a place");
        }
        address.setPlace(convertPlaceToEntity(dto.getPlace()));
        return addressService.save(address);
    }

    private Place convertPlaceToEntity(PlaceDTO dto) {
        if (dto.getId() != null) {
            return placeService.getPlaceById(dto.getId());
        }
        if (dto.getName() == null) {
            throw new IllegalArgumentException("Place must have a name");
        }
        
        Iterable<Place> existingPlaces = placeService.getAllPlaces();
        for (Place p : existingPlaces) {
            if (p.getName().equals(dto.getName())) {
                return p;
            }
        }
        
        Place place = new Place();
        place.setName(dto.getName());
        if (dto.getCountry() != null) {
            place.setCountry(convertCountryToEntity(dto.getCountry()));
        }
        return placeService.save(place);
    }

    private Country convertCountryToEntity(CountryDTO dto) {
        if (dto.getId() != null) {
            return countryService.getCountryById(dto.getId());
        }
        if (dto.getName() == null) {
            return null;
        }
        
        Iterable<Country> existingCountries = countryService.getAllCountries();
        for (Country c : existingCountries) {
            if (c.getName().equals(dto.getName())) {
                return c;
            }
        }
        
        Country country = new Country();
        country.setName(dto.getName());
        return countryService.saveCountry(country);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleStudentException(RuntimeException ex) {
        String message = ex.getMessage();
        if (message != null && message.contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseDTO(message));
        }
        if (message != null && (message.contains("already registered") ||
                message.contains("not enrolled") ||
                message.contains("not active"))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseDTO(message));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO("An error occurred"));
    }
}