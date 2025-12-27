package org.singidunum.backend.service;


import org.singidunum.backend.model.Address;
import org.singidunum.backend.model.Faculty;
import org.singidunum.backend.model.Teacher;
import org.singidunum.backend.model.University;
import org.singidunum.backend.repository.AddressRepository;
import org.singidunum.backend.repository.FacultyRepository;
import org.singidunum.backend.repository.TeacherRepository;
import org.singidunum.backend.repository.UniversityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;
    private final UniversityRepository universityRepository;

    public FacultyService(FacultyRepository facultyRepository, TeacherRepository teacherRepository, AddressRepository addressRepository, UniversityRepository universityRepository) {
        this.facultyRepository = facultyRepository;
        this.teacherRepository = teacherRepository;
        this.addressRepository = addressRepository;
        this.universityRepository = universityRepository;
    }

    public Iterable<Faculty> findAll()
    {
        return facultyRepository.findAll();
    }

    public Faculty findById(Long id)
    {
        return facultyRepository.findById(id).orElse(null);
    }

    public void  save(Faculty faculty)
    {
        facultyRepository.save(faculty);
    }

    public void delete(Long id)
    {
        facultyRepository.deleteById(id);
    }

    public void deleteAll()
    {
        facultyRepository.deleteAll();
    }

    @Transactional
    public Faculty update(Long id,
                          String name,
                          String description,
                          String phoneNumber,
                          String officialEmail,
                          String website,
                          Long addressId,
                          Long deanId,
                          Long universityId) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        if (name != null) {
            faculty.setName(name);
        }
        if (description != null) {
            faculty.setDescription(description);
        }
        if (phoneNumber != null) {
            faculty.setPhoneNumber(phoneNumber);
        }
        if (officialEmail != null) {
            faculty.setOfficialEmail(officialEmail);
        }
        if (website != null) {
            faculty.setWebsite(website);
        }

        if (addressId != null) {
            Address address = addressRepository.findById(addressId)
                    .orElseThrow(() -> new RuntimeException("Address not found"));
            faculty.setAddress(address);
        }

        if (deanId != null) {
            Teacher dean = teacherRepository.findById(deanId)
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            faculty.setDean(dean);
        }

        if (universityId != null) {
            University university = universityRepository.findById(universityId)
                    .orElseThrow(() -> new RuntimeException("University not found"));
            faculty.setUniversity(university);
        }

        return facultyRepository.save(faculty);
    }
}
