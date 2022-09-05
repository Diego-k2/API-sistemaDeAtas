package br.com.ApiSistemaDeAtas.repository;

import br.com.ApiSistemaDeAtas.model.RoleModel;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, UUID> {

    boolean existsByRoleName(String roleBame);

    Optional<RoleModel> findByRoleName(String roleName);
}
