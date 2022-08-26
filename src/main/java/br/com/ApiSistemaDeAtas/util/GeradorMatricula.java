package br.com.ApiSistemaDeAtas.util;

import br.com.ApiSistemaDeAtas.service.FuncionarioService;
import org.springframework.stereotype.Service;

@Service
public class GeradorMatricula {

    final FuncionarioService funcionarioService;
    public GeradorMatricula(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    public String setMatriculaAleatoria(){
        Integer matriculaAleatoria = Math.toIntExact(Math.round(Math.random() * 10000));
        if(funcionarioService.existsByMatricula(matriculaAleatoria.toString())){
           return setMatriculaAleatoria();
        }
        return matriculaAleatoria.toString();
    }

}


