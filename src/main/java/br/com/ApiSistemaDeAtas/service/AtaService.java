package br.com.ApiSistemaDeAtas.service;

import br.com.ApiSistemaDeAtas.model.AtaModel;
import br.com.ApiSistemaDeAtas.repository.AtaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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



}
