package br.com.ApiSistemaDeAtas.controller;

import br.com.ApiSistemaDeAtas.dto.UserDto;
import br.com.ApiSistemaDeAtas.model.UserModel;
import br.com.ApiSistemaDeAtas.service.FuncionarioService;
import br.com.ApiSistemaDeAtas.service.UserService;
import org.apache.catalina.mbeans.MBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;
    final FuncionarioService funcionarioService;
    public UserController(UserService userService, FuncionarioService funcionarioService) {
        this.userService = userService;
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> saveNewUser(@RequestBody @Valid UserDto userDto){

        if(!funcionarioService.existsByEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("E-mail não pertence a nenhum de nossos funcionrios");
        }

        if(userDto.getRoles().isEmpty()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Voce deve cadastrar ao menos uma ROLE");
        }

        UserModel userModel = new UserModel();

        BeanUtils.copyProperties(userDto, userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }

}
