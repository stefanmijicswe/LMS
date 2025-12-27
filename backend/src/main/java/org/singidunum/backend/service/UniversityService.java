package org.singidunum.backend.service;

import org.springframework.transaction.annotation.Transactional;
import org.singidunum.backend.model.Address;
import org.singidunum.backend.model.Teacher;
import org.singidunum.backend.model.University;
import org.singidunum.backend.repository.AddressRepository;
import org.singidunum.backend.repository.TeacherRepository;
import org.singidunum.backend.repository.UniversityRepository;
import org.springframework.stereotype.Service;


@Service
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;

    public UniversityService(UniversityRepository universityRepository, TeacherRepository teacherRepository, AddressRepository addressRepository) {
        this.universityRepository = universityRepository;
        this.teacherRepository = teacherRepository;
        this.addressRepository = addressRepository;
    }

    public Iterable<University> findAll()
    {
        return universityRepository.findAll();
    }

    public University findById(Long id)
    {
        return universityRepository.findById(id).orElse(null);
    }

    public University save(University university)
    {
        return universityRepository.save(university);
    }

    public void  delete(Long id)
    {
        universityRepository.deleteById(id);
    }

    public void deleteAll()
    {
         universityRepository.deleteAll();
    }

    @Transactional
    public University update(
               Long id,
               String name,
               String description,
               String phoneNumber,
               String officialEmail,
               String website,
               Long addressId,
               Long rectorId) {
    University university = universityRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("University not found"));

    if (name != null) {
    university.setName(name);
    }
    if (description != null) {
    university.setDescription(description);
    }
    if (phoneNumber != null) {
    university.setPhoneNumber(phoneNumber);
    }
    if (officialEmail != null) {
    university.setOfficialEmail(officialEmail);
    }
    if (website != null) {
    university.setWebsite(website);
    }

    if (addressId != null) {
    Address address = addressRepository.findById(addressId)
    .orElseThrow(() -> new RuntimeException("Address not found"));
    university.setAddress(address);
    }

    if (rectorId != null) {
    Teacher rector = teacherRepository.findById(rectorId)
    .orElseThrow(() -> new RuntimeException("Teacher not found"));
    university.setRector(rector);
    }

    return universityRepository.save(university);
    }

}
