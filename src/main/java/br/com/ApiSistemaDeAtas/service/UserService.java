package br.com.ApiSistemaDeAtas.service;

import br.com.ApiSistemaDeAtas.model.UserModel;
import br.com.ApiSistemaDeAtas.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserModel> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public UserModel save(UserModel userModel){
        return userRepository.save(userModel);
    }

}
