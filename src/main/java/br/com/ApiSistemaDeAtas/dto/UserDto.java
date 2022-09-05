package br.com.ApiSistemaDeAtas.dto;

import br.com.ApiSistemaDeAtas.model.RoleModel;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class UserDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private List<RoleModel> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleModel> roles) {
        this.roles = roles;
    }
}
