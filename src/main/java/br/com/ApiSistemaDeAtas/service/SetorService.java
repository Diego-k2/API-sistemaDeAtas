package br.com.ApiSistemaDeAtas.service;

import br.com.ApiSistemaDeAtas.model.SetorModel;
import br.com.ApiSistemaDeAtas.repository.SetorRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SetorService {


    final SetorRepository setorRepository;
    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    @Transactional
    public SetorModel save(SetorModel setorModel){
        return setorRepository.save(setorModel);
    }

    @Transactional
    public List<SetorModel> findAll(){
        return setorRepository.findAll();
    }

    @Transactional
    public Optional<SetorModel> findByNomeSetor(String nome){
        return setorRepository.findByNomeSetor(nome.toUpperCase());
    }

    @Transactional
    public boolean existsByNomeSetor(String nomeSetor){
        return setorRepository.existsByNomeSetor(nomeSetor);
    }

    @Transactional
    public boolean existsByNumeroSetor(String numero){
        return setorRepository.existsByNumeroSetor(numero);
    }

    @Transactional
    public Optional<SetorModel> findByNumeroSetor(String numeroSetor){
        return setorRepository.findByNumeroSetor(numeroSetor);
    }

    @Transactional
    public void delete(String numeroSetor){
        setorRepository.deleteByNumeroSetor(numeroSetor);
    }


}
