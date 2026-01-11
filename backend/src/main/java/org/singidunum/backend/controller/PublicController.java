package org.singidunum.backend.controller;

import org.singidunum.backend.dto.*;
import org.singidunum.backend.model.*;
import org.singidunum.backend.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    private final UniversityService universityService;
    private final FacultyService facultyService;
    private final StudyProgrammeService studyProgrammeService;
    private final SubjectService subjectService;
    private final AddressService addressService;

    public PublicController(UniversityService universityService, FacultyService facultyService, StudyProgrammeService studyProgrammeService, SubjectService subjectService, AddressService addressService) {
        this.universityService = universityService;
        this.facultyService = facultyService;
        this.studyProgrammeService = studyProgrammeService;
        this.subjectService = subjectService;
        this.addressService = addressService;
    }

    private TeacherDTO convertTeacherToDTO(Teacher teacher) {
        if (teacher == null) {
            return null;
        }

        TeacherDTO teacherDTO = new TeacherDTO(
                teacher.getId(),
                teacher.getName(),
                teacher.getSurname(),
                teacher.getBiography(),
                teacher.getUser() != null ? teacher.getUser().getId() : null
        );

        return teacherDTO;
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

    private StudyProgrammeDTO convertStudyProgrammeToDTO(StudyProgramme sp) {
        Long facultyId = null;
        if (sp.getFaculty() != null) {
            facultyId = sp.getFaculty().getId();
        }
        
        StudyProgrammeDTO studyProgrammeDTO = new StudyProgrammeDTO(
            sp.getId(),
            sp.getName(),
            facultyId
        );
        
        return studyProgrammeDTO;
    }

    private FacultyDTO convertFacultyToDTO(Faculty f) {
        AddressDTO addressDTO = convertAddressToDTO(f.getAddress());
        
        Long universityId = null;
        if (f.getUniversity() != null) {
            universityId = f.getUniversity().getId();
        }
        
        List<StudyProgrammeDTO> studyProgrammeDTOs = null;
        if (f.getStudyProgramme() != null) {
            studyProgrammeDTOs = new ArrayList<>();
            for (StudyProgramme sp : f.getStudyProgramme()) {
                studyProgrammeDTOs.add(convertStudyProgrammeToDTO(sp));
            }
        }
        
        FacultyDTO facultyDTO = new FacultyDTO(
            f.getId(),
            f.getName(),
            addressDTO,
            universityId,
            studyProgrammeDTOs
        );
        
        if (f.getDean() != null) {
            facultyDTO.setDean(convertTeacherToDTO(f.getDean()));
        }
        
        facultyDTO.setDescription(f.getDescription());
        facultyDTO.setPhoneNumber(f.getPhoneNumber());
        facultyDTO.setOfficialEmail(f.getOfficialEmail());
        facultyDTO.setWebsite(f.getWebsite());
        
        return facultyDTO;
    }

    @GetMapping("/universities")
    public ResponseEntity<Iterable<UniversityDTO>> getAllUniversities() {
        ArrayList<UniversityDTO> universitiesDTOS = new ArrayList<>();
        Iterable<University> universities = universityService.findAll();

        for (University u : universities) {
            List<Faculty> faculties = u.getFaculties();
            List<FacultyDTO> facultyDTOs = new ArrayList<>();
            
            if (faculties != null) {
                for (Faculty f : faculties) {
                    FacultyDTO facultyDTO = convertFacultyToDTO(f);
                    facultyDTOs.add(facultyDTO);
                }
            }
            
            UniversityDTO universityDTO = new UniversityDTO(
                    u.getId(),
                    u.getName(),
                    u.getFoundingDate(),
                    this.convertAddressToDTO(u.getAddress()),
                    facultyDTOs
            );
            
            if (u.getRector() != null) {
                universityDTO.setRector(convertTeacherToDTO(u.getRector()));
            }
            
            universityDTO.setDescription(u.getDescription());
            universityDTO.setPhoneNumber(u.getPhoneNumber());
            universityDTO.setOfficialEmail(u.getOfficialEmail());
            universityDTO.setWebsite(u.getWebsite());
            
            universitiesDTOS.add(universityDTO);
        }

        return new ResponseEntity<>(universitiesDTOS, HttpStatus.OK);
    }

    private OutcomeDTO convertOutcomeToDTO(Outcome outcome) {
        if (outcome == null) {
            return null;
        }

        OutcomeDTO outcomeDTO = new OutcomeDTO(
                outcome.getId(),
                outcome.getDescription()
        );

        return outcomeDTO;
    }

    private TeachingMaterialDTO convertTeachingMaterialToDTO(TeachingMaterial tm) {
        if (tm == null) {
            return null;
        }

        TeachingMaterialDTO teachingMaterialDTO = new TeachingMaterialDTO(
                tm.getId(),
                tm.getName(),
                tm.getAuthors(),
                tm.getYearPublished()
        );

        return teachingMaterialDTO;
    }

    private SubjectBasicDTO convertSubjectToBasicDTO(Subject subject) {
        if (subject == null) {
            return null;
        }

        Integer yearOfStudy = null;
        Integer academicYear = null;
        if (subject.getYearOfStudy() != null) {
            yearOfStudy = subject.getYearOfStudy().getYearNumber();
            if (subject.getYearOfStudy().getStartDate() != null) {
                academicYear = subject.getYearOfStudy().getStartDate().getYear();
            }
        }

        SubjectBasicDTO subjectBasicDTO = new SubjectBasicDTO(
                subject.getId(),
                subject.getName(),
                subject.getEcts(),
                subject.isMandatory(),
                yearOfStudy,
                academicYear
        );

        return subjectBasicDTO;
    }

    private SubjectDetailDTO convertSubjectToDetailDTO(Subject subject) {
        if (subject == null) {
            return null;
        }

        Integer yearOfStudy = null;
        Integer academicYear = null;
        LocalDate startDate = null;
        LocalDate endDate = null;
        if (subject.getYearOfStudy() != null) {
            yearOfStudy = subject.getYearOfStudy().getYearNumber();
            if (subject.getYearOfStudy().getStartDate() != null) {
                academicYear = subject.getYearOfStudy().getStartDate().getYear();
                startDate = subject.getYearOfStudy().getStartDate();
            }
            if (subject.getYearOfStudy().getEndDate() != null) {
                endDate = subject.getYearOfStudy().getEndDate();
            }
        }

        List<TeachingMaterialDTO> teachingMaterials = new ArrayList<>();
        if (subject.getOutcomes() != null) {
            for (Outcome outcome : subject.getOutcomes()) {
                if (outcome.getTeachingMaterial() != null) {
                    for (TeachingMaterial tm : outcome.getTeachingMaterial()) {
                        teachingMaterials.add(convertTeachingMaterialToDTO(tm));
                    }
                }
            }
        }

        List<OutcomeDTO> outcomes = new ArrayList<>();
        if (subject.getOutcomes() != null) {
            for (Outcome outcome : subject.getOutcomes()) {
                outcomes.add(convertOutcomeToDTO(outcome));
            }
        }

        SubjectDetailDTO subjectDetailDTO = new SubjectDetailDTO(
                subject.getId(),
                subject.getName(),
                subject.getEcts(),
                subject.isMandatory(),
                yearOfStudy,
                academicYear,
                startDate,
                endDate,
                subject.getNumberOfLectures(),
                subject.getNumberOfExercises(),
                subject.getSyllabus(),
                teachingMaterials,
                outcomes
        );

        return subjectDetailDTO;
    }

    @GetMapping("/study-programmes/{id}")
    public ResponseEntity<StudyProgrammeDTO> getStudyProgrammeById(@PathVariable Long id) {
        StudyProgramme studyProgramme = studyProgrammeService.findById(id);
        
        if (studyProgramme == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        StudyProgrammeDTO studyProgrammeDTO = new StudyProgrammeDTO(
                studyProgramme.getId(),
                studyProgramme.getName(),
                studyProgramme.getFaculty() != null ? studyProgramme.getFaculty().getId() : null
        );

        studyProgrammeDTO.setDescription(studyProgramme.getDescription());

        if (studyProgramme.getCoordinator() != null) {
            studyProgrammeDTO.setCoordinator(convertTeacherToDTO(studyProgramme.getCoordinator()));
        }

        List<SubjectBasicDTO> subjects = new ArrayList<>();
        if (studyProgramme.getYearOfStudy() != null) {
            for (YearOfStudy yearOfStudy : studyProgramme.getYearOfStudy()) {
                if (yearOfStudy.getSubjects() != null) {
                    for (Subject subject : yearOfStudy.getSubjects()) {
                        subjects.add(convertSubjectToBasicDTO(subject));
                    }
                }
            }
        }
        studyProgrammeDTO.setSubjects(subjects);

        return new ResponseEntity<>(studyProgrammeDTO, HttpStatus.OK);
    }

    @GetMapping("/subjects/{id}")
    public ResponseEntity<SubjectDetailDTO> getSubjectById(@PathVariable Long id) {
        Subject subject = subjectService.findById(id);
        
        if (subject == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        SubjectDetailDTO subjectDetailDTO = convertSubjectToDetailDTO(subject);

        return new ResponseEntity<>(subjectDetailDTO, HttpStatus.OK);
    }

    @GetMapping("/faculties")
    public ResponseEntity<Iterable<FacultyDTO>> getFaculties() {
        Iterable<Faculty> faculties = facultyService.findAll();
        ArrayList<FacultyDTO> facultiesDTO = new ArrayList<>();
        for (Faculty faculty : faculties) {
            facultiesDTO.add(convertFacultyToDTO(faculty));
        }
        return new ResponseEntity<>(facultiesDTO, HttpStatus.OK);
    }

    @GetMapping("/faculties/{id}")
    public ResponseEntity<FacultyDTO> getFacultyById(@PathVariable Long id) {
        Faculty faculty = facultyService.findById(id);
        if (faculty == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        FacultyDTO facultyDTO = convertFacultyToDTO(faculty);

        return new ResponseEntity<>(facultyDTO, HttpStatus.OK);
    }

    @GetMapping("/study-programmes")
    public ResponseEntity<Iterable<StudyProgrammeDTO>> getStudyProgrammes() {
        Iterable<StudyProgramme> studyProgrammes = studyProgrammeService.findAll();
        ArrayList<StudyProgrammeDTO> studyProgrammeDTOs = new ArrayList<>();
        for (StudyProgramme studyProgramme : studyProgrammes) {
            studyProgrammeDTOs.add(convertStudyProgrammeToDTO(studyProgramme));
        }
        return new ResponseEntity<>(studyProgrammeDTOs, HttpStatus.OK);
    }

    @GetMapping("/addresses")
    public ResponseEntity<Iterable<AddressDTO>> getAddresses() {
        Iterable<Address> addresses = addressService.findAllAddresses();
        ArrayList<AddressDTO> addressesDTO = new ArrayList<>();
        for (Address address : addresses) {
            addressesDTO.add(convertAddressToDTO(address));
        }
        return new ResponseEntity<>(addressesDTO, HttpStatus.OK);
    }
}
