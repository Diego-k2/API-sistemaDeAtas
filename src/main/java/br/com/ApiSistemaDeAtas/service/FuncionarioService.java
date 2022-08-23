package br.com.ApiSistemaDeAtas.service;

import br.com.ApiSistemaDeAtas.model.FuncionarioModel;
import br.com.ApiSistemaDeAtas.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionarioService {

    final FuncionarioRepository funcionarioRepository;
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public FuncionarioModel saveFuncionario(FuncionarioModel funcionario){
        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public boolean existsByCpf(String cpf){
        return funcionarioRepository.existsByCpf(cpf);
    }

    @Transactional
    public boolean existsByEmail(String email){
        return  funcionarioRepository.existsByEmail(email);
    }

    @Transactional
    public boolean existsByMatricula(String matricula){
        return funcionarioRepository.existsByMatricula(matricula);
    }

}
