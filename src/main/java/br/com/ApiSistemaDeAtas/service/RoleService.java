package br.com.ApiSistemaDeAtas.service;

import br.com.ApiSistemaDeAtas.model.RoleModel;
import br.com.ApiSistemaDeAtas.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public boolean existsByRoleName(String roleBame){
        return roleRepository.existsByRoleName(roleBame);
    }

    public RoleModel save(RoleModel roleModel){
        return roleRepository.save(roleModel);
    }

    public List<RoleModel> getAll(){
        return roleRepository.findAll();
    }

    public Optional<RoleModel> findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

}
