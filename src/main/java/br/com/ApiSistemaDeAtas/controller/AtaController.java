package br.com.ApiSistemaDeAtas.controller;

import br.com.ApiSistemaDeAtas.dto.AtaDto;
import br.com.ApiSistemaDeAtas.enuns.EstadoAta;
import br.com.ApiSistemaDeAtas.model.AtaModel;
import br.com.ApiSistemaDeAtas.model.FuncionarioModel;
import br.com.ApiSistemaDeAtas.service.AtaService;
import br.com.ApiSistemaDeAtas.service.FuncionarioService;
import br.com.ApiSistemaDeAtas.util.EmiteData;
import br.com.ApiSistemaDeAtas.util.ParticipanteParser;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.env.RandomValuePropertySourceEnvironmentPostProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ata")
public class AtaController {

    final AtaService ataService;
    final FuncionarioService funcionarioService;
    public AtaController(AtaService ataService, FuncionarioService funcionarioService) {
        this.ataService = ataService;
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<Object> setAta(@RequestBody @Valid AtaDto ataDto) throws ParseException {

        if(!funcionarioService.existsByCpf(ataDto.getEmissor())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR: Não achamos o emissor em nossa base de dados");
        }

        for(ParticipanteParser participanteParser : ataDto.getParticipantes()){
            if(!funcionarioService.existsByCpf(participanteParser.getCpf())){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERRO PARTICIPANTE: '" + participanteParser.getCpf() + "', Não inscrito em nossa base de dados" );
            }
        }

        AtaModel ataModel = new AtaModel();
        BeanUtils.copyProperties(ataDto, ataModel);
        List<FuncionarioModel> funcionarioModels = new ArrayList<>();

        for (ParticipanteParser participante: ataDto.getParticipantes()) {
            funcionarioModels.add(funcionarioService.findByCpf(participante.getCpf()).get());
        }

        ataModel.setEmissor(funcionarioService.findByCpf(ataDto.getEmissor()).get());
        ataModel.setParticipantes(funcionarioModels);
        ataModel.setHoraFim(EmiteData.getHoraFinal());
        ataModel.setData(EmiteData.getYearMothDay());

        return ResponseEntity.status(HttpStatus.CREATED).body(ataService.save(ataModel));
    }


    @GetMapping("/{cpf}")
    public ResponseEntity<Object> getAtaByEmissorAndEmEdicao(@PathVariable String cpf){

        if(!funcionarioService.existsByCpf(cpf)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionario não consta em nossa base de dados");
        }

        FuncionarioModel emissor = funcionarioService.findByCpf(cpf).get();
        if(!ataService.existsByEmissorAndEmEdicao(emissor, String.valueOf(EstadoAta.EM_EDICAO))){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Este emissor não tem nenhuma ATA no estado: EM_EMISSAO");
        }

        return ResponseEntity.status(HttpStatus.OK).body(ataService.findByEmissorAndAndEstado(emissor));
    }

}
