package br.com.ApiSistemaDeAtas.controller;

import br.com.ApiSistemaDeAtas.dto.SetorDto;
import br.com.ApiSistemaDeAtas.model.SetorModel;
import br.com.ApiSistemaDeAtas.service.SetorService;
import br.com.ApiSistemaDeAtas.util.GeradorNumeroSetor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/setores")
public class SetorController {

    final SetorService setorService;
    final GeradorNumeroSetor geradorNumeroSetor;
    public SetorController(SetorService setorService, GeradorNumeroSetor geradorNumeroSetor) {
        this.setorService = setorService;
        this.geradorNumeroSetor = geradorNumeroSetor;
    }


    @PostMapping
    public ResponseEntity<Object> saveSetor(@RequestBody @Valid SetorDto setor){

        if(setorService.existsByNomeSetor(setor.getNomeSetor().toUpperCase())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: Setor já cadastrado");
        }
        SetorModel setorModel = new SetorModel();
        BeanUtils.copyProperties(setor, setorModel);
        setorModel.setNomeSetor(setor.getNomeSetor().toUpperCase());
        setorModel.setNumeroSetor(geradorNumeroSetor.setNumeroSetor());

        return ResponseEntity.status(HttpStatus.CREATED).body(setorService.save(setorModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(setorService.findAll());
    }

    @PutMapping("/{numeroSetor}")
    public ResponseEntity<Object> editSetor(@RequestBody @Valid SetorDto setorDto, @PathVariable String numeroSetor){
        if(!setorService.existsByNumeroSetor(numeroSetor)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Setor não existem em nossa base de dados");
        }

        SetorModel setorModel = setorService.findByNumeroSetor(numeroSetor).get();
        setorModel.setNomeSetor(setorDto.getNomeSetor().toUpperCase());

        return ResponseEntity.status(HttpStatus.OK).body(setorService.save(setorModel));
    }

    @DeleteMapping("/{numeroSetor}")
    public ResponseEntity<Object> deleteSetor(@PathVariable String numeroSetor){

        if(!setorService.existsByNumeroSetor(numeroSetor)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Setor não pertence a nossa base de dados");
        }

        setorService.delete(numeroSetor);

        return ResponseEntity.status(HttpStatus.OK).body("Setor deletado");
    }

}
