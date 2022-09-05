package br.com.ApiSistemaDeAtas.dto;

import javax.validation.constraints.NotBlank;

public class RoleDto {

    @NotBlank
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
