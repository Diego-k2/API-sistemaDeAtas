package br.com.ApiSistemaDeAtas.repository;

import br.com.ApiSistemaDeAtas.model.SetorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SetorRepository extends JpaRepository<SetorModel, UUID> {

    Optional<SetorModel> findByNomeSetor(String nomeSetor);

    boolean existsByNomeSetor(String nome);

    boolean existsByNumeroSetor(String numero);

    Optional<SetorModel> findByNumeroSetor(String numero);

    void deleteByNumeroSetor(String numeroSetor);
}
