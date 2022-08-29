package br.com.ApiSistemaDeAtas.repository;

import br.com.ApiSistemaDeAtas.model.AtaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AtaRepository extends JpaRepository<AtaModel, UUID> {
}
