package org.singidunum.backend.dto;

import java.util.List;

public class MeDTO {

    private Long id;
    private String username;
    private List<String> roles;

    private TeacherMeDTO teacher;

    public MeDTO() {}

    public MeDTO(Long id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public TeacherMeDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherMeDTO teacher) {
        this.teacher = teacher;
    }
}
