package org.singidunum.backend.dto;

import java.util.List;

public class UserResponseDTO {
    private Long id;
    private String username;
    private List<String> roles;
    private Integer yearNumber;
    private String studyProgrammeName;

    public UserResponseDTO() {}

    public UserResponseDTO(Long id, String username, List<String> roles) {
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }

    public Integer getYearNumber() { return yearNumber; }
    public void setYearNumber(Integer yearNumber) { this.yearNumber = yearNumber; }

    public String getStudyProgrammeName() { return studyProgrammeName; }
    public void setStudyProgrammeName(String studyProgrammeName) { this.studyProgrammeName = studyProgrammeName; }
}
