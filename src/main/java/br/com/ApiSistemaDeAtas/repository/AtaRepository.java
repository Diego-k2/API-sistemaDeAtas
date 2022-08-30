package br.com.ApiSistemaDeAtas.repository;

import br.com.ApiSistemaDeAtas.model.AtaModel;
import br.com.ApiSistemaDeAtas.model.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AtaRepository extends JpaRepository<AtaModel, UUID> {

    Optional<AtaModel> findByEmissorAndAndEstado(FuncionarioModel emissor, String estado);

    boolean existsByEmissorAndAndEstado(FuncionarioModel emissor, String estado);
}
