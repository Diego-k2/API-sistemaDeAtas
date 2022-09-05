package br.com.ApiSistemaDeAtas.controller;

import br.com.ApiSistemaDeAtas.dto.RoleDto;
import br.com.ApiSistemaDeAtas.dto.UserDto;
import br.com.ApiSistemaDeAtas.model.RoleModel;
import br.com.ApiSistemaDeAtas.model.UserModel;
import br.com.ApiSistemaDeAtas.securityConfig.WebSecurityConfig;
import br.com.ApiSistemaDeAtas.service.FuncionarioService;
import br.com.ApiSistemaDeAtas.service.RoleService;
import br.com.ApiSistemaDeAtas.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;
    final RoleService roleService;
    final FuncionarioService funcionarioService;
    final WebSecurityConfig webSecurityConfig;
    public UserController(UserService userService, RoleService roleService, FuncionarioService funcionarioService, WebSecurityConfig webSecurityConfig) {
        this.userService = userService;
        this.roleService = roleService;
        this.funcionarioService = funcionarioService;
        this.webSecurityConfig = webSecurityConfig;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> saveNewUser(@RequestBody @Valid UserDto userDto){

        if(!funcionarioService.existsByEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("E-mail não pertence a nenhum de nossos funcionrios");
        }

        if(userDto.getRoles().isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Voce deve cadastrar ao menos uma ROLE");
        }

        List<RoleModel> roleModelList = new ArrayList();
        UserModel userModel = new UserModel();

        for (RoleDto role: userDto.getRoles()) {
            RoleModel roleModel = roleService.findByRoleName(role.getRoleName()).get();
            roleModelList.add(roleModel);
        }

        userModel.setEmail(userDto.getEmail());
        userModel.setPassword(webSecurityConfig.passwordEncoder().encode(userDto.getPassword()));
        userModel.setRoles(roleModelList);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }

}
