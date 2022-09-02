package br.com.ApiSistemaDeAtas.controller;

import br.com.ApiSistemaDeAtas.dto.FuncionarioDto;
import br.com.ApiSistemaDeAtas.model.FuncionarioModel;
import br.com.ApiSistemaDeAtas.model.SetorModel;
import br.com.ApiSistemaDeAtas.service.FuncionarioService;
import br.com.ApiSistemaDeAtas.service.SetorService;
import br.com.ApiSistemaDeAtas.util.GeradorMatricula;
import br.com.ApiSistemaDeAtas.util.VerificadorCpf;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FuncionarioController {

    final FuncionarioService funcionarioService;
    final SetorService setorService;
    final GeradorMatricula geradorMatricula;
    public FuncionarioController(FuncionarioService funcionarioService, SetorService setorService, GeradorMatricula geradorMatricula) {
        this.funcionarioService = funcionarioService;
        this.setorService = setorService;
        this.geradorMatricula = geradorMatricula;
    }



    @PostMapping
    public ResponseEntity<Object> saveFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto){

        if(funcionarioService.existsByCpf(funcionarioDto.getCpf()) ||
                !VerificadorCpf.isCpfValid(funcionarioDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: CPF já cadastrado ou CPF em formato incorreto");
        }

        if(funcionarioService.existsByEmail(funcionarioDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: Email já cadastrado");
        }

        if(!setorService.existsByNomeSetor(funcionarioDto.getSetor().toUpperCase())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NÃO ACHAMOS: Setor não existe");
        }

        SetorModel setorModel = setorService.findByNomeSetor(funcionarioDto.getSetor().toUpperCase()).get();
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        BeanUtils.copyProperties(funcionarioDto, funcionarioModel);
        funcionarioModel.setSetor(setorModel);
        funcionarioModel.setMatricula(geradorMatricula.setMatriculaAleatoria());

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.saveFuncionario(funcionarioModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAllFuncionarios(){
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findAll());
    }

    @GetMapping(value = "/{cpf}")
    public ResponseEntity<Object> getFuncionarioByCpf(@PathVariable String cpf){

        if(!funcionarioService.existsByCpf(cpf)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF não existe em nossa base de dados");
        }

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findByCpf(cpf));
    }


    @DeleteMapping("/{cpf}")
    public ResponseEntity<Object> deleteById(@PathVariable String cpf){

        if(!funcionarioService.existsByCpf(cpf)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ERROR: CPF não existe em nossa base de dados");
        }

        funcionarioService.deleteByCpf(cpf);
        return ResponseEntity.status(HttpStatus.OK).body("FUNCIONARIO DELETADO");
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Object> update(@PathVariable String cpf, @RequestBody @Valid FuncionarioDto funcionarioDto){
        Optional<FuncionarioModel> optionalFuncionarioModel = funcionarioService.findByCpf(cpf);
        if(!optionalFuncionarioModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: CPF não existe em nossa base de dados");
        }

        FuncionarioModel funcionarioModel = optionalFuncionarioModel.get();

        funcionarioModel.setNome(funcionarioDto.getNome());
        funcionarioModel.setSobrenome(funcionarioDto.getSobrenome());
        funcionarioModel.setSetor(setorService.findByNomeSetor(funcionarioDto.getSetor()).get());
        funcionarioModel.setCpf(funcionarioDto.getCpf());
        funcionarioModel.setEmail(funcionarioDto.getEmail());
        funcionarioModel.setSenha(funcionarioDto.getSenha());


        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.saveFuncionario(funcionarioModel));
    }


}
