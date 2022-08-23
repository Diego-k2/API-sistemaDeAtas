package br.com.ApiSistemaDeAtas.controller;

import br.com.ApiSistemaDeAtas.dto.FuncionarioDto;
import br.com.ApiSistemaDeAtas.model.FuncionarioModel;
import br.com.ApiSistemaDeAtas.service.FuncionarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FuncionarioController {

    final FuncionarioService funcionarioService;
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }


    @PostMapping
    public ResponseEntity<Object> saveFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto){

        if(funcionarioService.existsByCpf(funcionarioDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: CPF já cadastrado");
        }

        if(funcionarioService.existsByEmail(funcionarioDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: Email já cadastrado");
        }

        if(funcionarioService.existsByMatricula(funcionarioDto.getMatricula())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: Matricula já cadastrado");
        }

        FuncionarioModel funcionarioModel = new FuncionarioModel();
        String formatCpf = funcionarioDto.getCpf().replaceAll("[^0-9]+" ,"");
        funcionarioDto.setCpf(formatCpf);
        BeanUtils.copyProperties(funcionarioDto, funcionarioModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.saveFuncionario(funcionarioModel));
    }

}
