package org.singidunum.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

public class UniversityDTO {

    private Long id;
    private String name;
    private LocalDateTime foundingDate;
    private AddressDTO address;
    private List<FacultyDTO> faculties;
    private TeacherDTO rector;
    private String description;
    private String phoneNumber;
    private String officialEmail;
    private String website;


    public UniversityDTO(Long id, String name, LocalDateTime foundingDate, AddressDTO address, List<FacultyDTO> faculties) {
        this.id = id;
        this.name = name;
        this.foundingDate = foundingDate;
        this.address = address;
        this.faculties = faculties;
    }

    public UniversityDTO() {
    }

    public TeacherDTO getRector() {
        return rector;
    }

    public void setRector(TeacherDTO rector) {
        this.rector = rector;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(LocalDateTime foundingDate) {
        this.foundingDate = foundingDate;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<FacultyDTO> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<FacultyDTO> faculties) {
        this.faculties = faculties;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOfficialEmail() {
        return officialEmail;
    }

    public void setOfficialEmail(String officialEmail) {
        this.officialEmail = officialEmail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
