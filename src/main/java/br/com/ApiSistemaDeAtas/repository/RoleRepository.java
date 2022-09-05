package br.com.ApiSistemaDeAtas.repository;

import br.com.ApiSistemaDeAtas.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID> {

    boolean existsByRoleName(String roleBame);
}
