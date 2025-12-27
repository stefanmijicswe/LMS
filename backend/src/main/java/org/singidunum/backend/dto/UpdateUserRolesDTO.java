package org.singidunum.backend.dto;

import java.util.List;

public class UpdateUserRolesDTO {
    private List<String> roles;

    public UpdateUserRolesDTO() {
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}