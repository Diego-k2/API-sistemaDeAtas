package br.com.ApiSistemaDeAtas.service;

import br.com.ApiSistemaDeAtas.enuns.EstadoAta;
import br.com.ApiSistemaDeAtas.model.AtaModel;
import br.com.ApiSistemaDeAtas.model.FuncionarioModel;
import br.com.ApiSistemaDeAtas.repository.AtaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AtaService{

    final AtaRepository ataRepository;
    public AtaService(AtaRepository ataRepository) {
        this.ataRepository = ataRepository;
    }


    @Transactional
    public AtaModel save(AtaModel ataModel){
        return ataRepository.save(ataModel);
    }

    @Transactional
    public void deleteById(String id){
        ataRepository.deleteById(UUID.fromString(id));
    }

    @Transactional
    public List<AtaModel> findAll(){
        return ataRepository.findAll();
    }

    @Transactional
    public Optional<AtaModel> findByEmissorAndAndEstado(FuncionarioModel emissor){
        return ataRepository.findByEmissorAndAndEstado(emissor, String.valueOf(EstadoAta.EM_EDICAO));
    }

    @Transactional
    public boolean existsByEmissorAndEmEdicao(FuncionarioModel emissor, String estado){
        return ataRepository.existsByEmissorAndAndEstado(emissor, estado);
    }
    @Transactional
    public List<Optional<AtaModel>> findAllByIsPublica(){
        return ataRepository.findAllByIsPublica("true");
    }

    @Transactional
    public List<Optional<AtaModel>> findAllByParticipantes(FuncionarioModel partipante){
        return ataRepository.findAllByParticipantes(partipante);
    }

    @Transactional
    public boolean existsByNumeroAta(String numeroAta){
        return ataRepository.existsByNumeroAta(numeroAta);
    }

    @Transactional
    public void deleteByNumeroAta(String numero){
        ataRepository.deleteByNumeroAta(numero);
    }

}
