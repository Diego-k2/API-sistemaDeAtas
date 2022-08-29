package br.com.ApiSistemaDeAtas.controller;

import br.com.ApiSistemaDeAtas.dto.AtaDto;
import br.com.ApiSistemaDeAtas.model.AtaModel;
import br.com.ApiSistemaDeAtas.model.FuncionarioModel;
import br.com.ApiSistemaDeAtas.service.AtaService;
import br.com.ApiSistemaDeAtas.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class AtaController {

    final AtaService ataService;
    final FuncionarioService funcionarioService;
    public AtaController(AtaService ataService, FuncionarioService funcionarioService) {
        this.ataService = ataService;
        this.funcionarioService = funcionarioService;
    }

    @PostMapping(value = "/ata")
    public ResponseEntity<Object> setAta(@RequestBody @Valid AtaDto ataDto) throws ParseException {

        for (String f: ataDto.getParticipantes()) {
            if(!funcionarioService.existsByCpf(f)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro participante" );
            }
        }

        if(!funcionarioService.existsByCpf(ataDto.getEmissor())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO EMISSOR");
        }

        AtaModel ataModel = new AtaModel();
        List<FuncionarioModel> funcionarioModels = new ArrayList<>();
        Date horaInicio = new SimpleDateFormat("HH:mm:ss").parse(ataDto.getHoraInicio());

        ataModel.setHoraInicio(horaInicio);
        ataModel.setEmissor(funcionarioService.findByCpf(ataDto.getEmissor()).get());
        ataModel.setPautas(ataDto.getPautas());
        ataModel.setTitulo(ataDto.getTitulo());

        for (String f: ataDto.getParticipantes()) {
            FuncionarioModel addFuncionario = funcionarioService.findByCpf(f).get();
            funcionarioModels.add(addFuncionario);
        }

        ataModel.setParticipantes(funcionarioModels);
        ataModel.setHoraFim( new Date("HH:mm:ss"));
        ataModel.setData(new Date("yyyy/MM/dd"));

        return ResponseEntity.status(HttpStatus.CREATED).body(ataService.save(ataModel));
    }


}
