package org.singidunum.backend.dto;

import java.util.List;

public class FacultyDTO {

    private Long id;
    private String name;
    private AddressDTO address;
    private Long university;
    private List<StudyProgrammeDTO> studyProgrammes;
    private TeacherDTO dean;
    private String description;
    private String phoneNumber;
    private String officialEmail;
    private String website;

    public FacultyDTO(Long id, String name, AddressDTO address, Long university, List<StudyProgrammeDTO> studyProgrammes) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.university = university;
        this.studyProgrammes = studyProgrammes;
    }

    public FacultyDTO() {
    }

    public TeacherDTO getDean() {
        return dean;
    }

    public void setDean(TeacherDTO dean) {
        this.dean = dean;
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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Long getUniversity() {
        return university;
    }

    public void setUniversity(Long university) {
        this.university = university;
    }

    public List<StudyProgrammeDTO> getStudyProgrammes() {
        return studyProgrammes;
    }

    public void setStudyProgrammes(List<StudyProgrammeDTO> studyProgrammes) {
        this.studyProgrammes = studyProgrammes;
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
