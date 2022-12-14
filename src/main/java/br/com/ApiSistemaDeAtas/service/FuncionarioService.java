package br.com.ApiSistemaDeAtas.service;

import br.com.ApiSistemaDeAtas.model.FuncionarioModel;
import br.com.ApiSistemaDeAtas.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public  Optional<FuncionarioModel> findByCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf);
    }

    @Transactional
    public List<FuncionarioModel> findAll(){
        return funcionarioRepository.findAll();
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

    @Transactional
    public void deleteByCpf(String cpf){
        funcionarioRepository.deleteByCpf(cpf);
    }

}
