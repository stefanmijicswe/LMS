package org.singidunum.backend.dto;

public class TeacherMeDTO {

    private Long id;
    private String name;
    private String surname;
    private String biography;
    private String pin;

    public TeacherMeDTO() {}

    public TeacherMeDTO(Long id, String name, String surname, String biography, String pin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.biography = biography;
        this.pin = pin;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
