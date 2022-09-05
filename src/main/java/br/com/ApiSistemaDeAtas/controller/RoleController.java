package br.com.ApiSistemaDeAtas.controller;

import br.com.ApiSistemaDeAtas.dto.RoleDto;
import br.com.ApiSistemaDeAtas.model.RoleModel;
import br.com.ApiSistemaDeAtas.service.RoleService;
import net.bytebuddy.asm.Advice;
import org.apache.catalina.mbeans.MBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/role")
public class RoleController {

    final RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> saveRole(@RequestBody @Valid RoleDto roleDto){

        if(roleService.existsByRoleName(roleDto.getRoleName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: ROLE JA CADASTRADA");
        }
        RoleModel roleModel = new RoleModel();
        BeanUtils.copyProperties(roleDto, roleModel);

        return ResponseEntity.status(HttpStatus.OK).body(roleService.save(roleModel));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAll());
    }

}
