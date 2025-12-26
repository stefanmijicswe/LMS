package org.singidunum.backend.dto;

public class EnrolStudentRequestDTO {

    private String username;
    private Long yearOfStudyId;

    private String name;
    private String surname;
    private String pin;
    private Long addressId;

    public EnrolStudentRequestDTO() {
    }

    public EnrolStudentRequestDTO(String username,
                                  Long yearOfStudyId,
                                  String name,
                                  String surname,
                                  String pin,
                                  Long addressId) {
        this.username = username;
        this.yearOfStudyId = yearOfStudyId;
        this.name = name;
        this.surname = surname;
        this.pin = pin;
        this.addressId = addressId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getYearOfStudyId() {
        return yearOfStudyId;
    }

    public void setYearOfStudyId(Long yearOfStudyId) {
        this.yearOfStudyId = yearOfStudyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
