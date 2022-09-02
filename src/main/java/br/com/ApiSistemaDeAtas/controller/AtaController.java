package br.com.ApiSistemaDeAtas.controller;

import br.com.ApiSistemaDeAtas.dto.AtaDto;
import br.com.ApiSistemaDeAtas.enuns.EstadoAta;
import br.com.ApiSistemaDeAtas.model.AtaModel;
import br.com.ApiSistemaDeAtas.model.FuncionarioModel;
import br.com.ApiSistemaDeAtas.service.AtaService;
import br.com.ApiSistemaDeAtas.service.FuncionarioService;
import br.com.ApiSistemaDeAtas.util.EmiteData;
import br.com.ApiSistemaDeAtas.util.GeradorNumeroAta;
import br.com.ApiSistemaDeAtas.util.ParticipanteParser;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.ReactiveTransaction;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ata")
public class AtaController {

    final AtaService ataService;
    final FuncionarioService funcionarioService;
    final GeradorNumeroAta geradorNumeroAta;
    public AtaController(AtaService ataService, FuncionarioService funcionarioService, GeradorNumeroAta geradorNumeroAta) {
        this.ataService = ataService;
        this.funcionarioService = funcionarioService;
        this.geradorNumeroAta = geradorNumeroAta;
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
        ataModel.setPublica(ataDto.getIsPublica());
        ataModel.setNumeroAta(geradorNumeroAta.geraNumeroAta());

        return ResponseEntity.status(HttpStatus.CREATED).body(ataService.save(ataModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAllAtas(){
        return ResponseEntity.status(HttpStatus.OK).body(ataService.findAll());
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

    @GetMapping("/publica")
    public ResponseEntity<Object> getAllPublica(){
        return ResponseEntity.status(HttpStatus.OK).body(ataService.findAllByIsPublica());
    }

    @GetMapping(value = "/participante/{cpf}")
    public ResponseEntity<Object> getAllByParticipante(@PathVariable String cpf){

        if(!funcionarioService.existsByCpf(cpf)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF não consta em nossa base de dados");
        }

        FuncionarioModel participante = funcionarioService.findByCpf(cpf).get();

        List<Optional<AtaModel>> ataComParticipante = ataService.findAllByParticipantes(participante);

        if(ataComParticipante.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Participante não consta em nossa base de dados");
        }

        return ResponseEntity.status(HttpStatus.OK).body(ataComParticipante);
    }

    @DeleteMapping(value = "/{numeroAta}")
    public ResponseEntity<Object> deleteAtaByNumero(@PathVariable String numeroAta){
        if(!ataService.existsByNumeroAta(numeroAta)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ATA não existe em nossa base de dados");
        }
        ataService.deleteByNumeroAta(numeroAta);
        return ResponseEntity.status(HttpStatus.OK).body("ATA nº: " + numeroAta + ", foi deletada da nossa base de dados");
    }

    @PutMapping("/update/{numeroAta}")
    public ResponseEntity<Object> updateAta(@RequestBody @Valid AtaDto ataDto, @PathVariable String numeroAta){

        if(!ataService.existsByNumeroAta(numeroAta)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ATA não existe em nossa base de dados");
        }

        AtaModel ataModel = ataService.findByNumeroAta(numeroAta);

        if(ataModel.getEstado().equals(String.valueOf(EstadoAta.POSTADA))){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ATA não pode ser editada, pois já foi publicada");
        }

        List<FuncionarioModel> participantes = new ArrayList<>();
        for (ParticipanteParser participanteParser: ataDto.getParticipantes()) {
            if(!funcionarioService.existsByCpf(participanteParser.getCpf())){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CPF não consta em nossa base de dados");
            }
            participantes.add(funcionarioService.findByCpf(participanteParser.getCpf()).get());

        }

        ataModel.setPublica(ataDto.getIsPublica());
        ataModel.setTitulo(ataDto.getTitulo());
        ataModel.setPautas(ataDto.getPautas());
        ataModel.setParticipantes(participantes);
        ataModel.setEstado(String.valueOf(EstadoAta.EDITADA));

        return ResponseEntity.status(HttpStatus.OK).body(ataService.save(ataModel));
    }

    @PutMapping("/post/{numeroAta}")
    public ResponseEntity<Object> postaAta(@PathVariable String numeroAta){

        if(!ataService.existsByNumeroAta(numeroAta)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ATA não consta em nosso sistema");
        }

        AtaModel ataModel = ataService.findByNumeroAta(numeroAta);

        ataModel.setEstado(String.valueOf(EstadoAta.POSTADA));
        ataService.save(ataModel);

        return ResponseEntity.status(HttpStatus.OK).body("ATA Nº: " + numeroAta + ", foi postada");
    }




}
