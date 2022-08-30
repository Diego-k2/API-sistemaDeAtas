package br.com.ApiSistemaDeAtas.service;

import br.com.ApiSistemaDeAtas.model.AtaModel;
import br.com.ApiSistemaDeAtas.repository.AtaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
