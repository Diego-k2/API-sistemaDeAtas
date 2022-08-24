package br.com.ApiSistemaDeAtas.controller;

import br.com.ApiSistemaDeAtas.dto.SetorDto;
import br.com.ApiSistemaDeAtas.model.SetorModel;
import br.com.ApiSistemaDeAtas.service.SetorService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/setores")
public class SetorController {

    final SetorService setorService;
    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }


    @PostMapping
    public ResponseEntity<Object> saveSetor(@RequestBody @Valid SetorDto setor){

        if(setorService.existsByNomeSetor(setor.getNomeSetor().toUpperCase())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLITO: Setor já cadastrado");
        }
        SetorModel setorModel = new SetorModel();
        BeanUtils.copyProperties(setor, setorModel);
        setorModel.setNomeSetor(setor.getNomeSetor().toUpperCase());

        return ResponseEntity.status(HttpStatus.CREATED).body(setorService.save(setorModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(setorService.findAll());
    }

}
