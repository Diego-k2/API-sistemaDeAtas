package br.com.ApiSistemaDeAtas.repository;

import br.com.ApiSistemaDeAtas.model.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, UUID> {


    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByMatricula(String matricula);

    void deleteByCpf(String cpf);

}
