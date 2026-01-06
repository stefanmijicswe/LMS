package org.singidunum.backend.dto;

public class TeacherDTO {

    private Long id;
    private String name;
    private String surname;
    private String biography;
    private Long userId;

    public TeacherDTO(Long id, String name, String surname, String biography, Long userId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.biography = biography;
        this.userId = userId;
    }

    public TeacherDTO() {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
