package br.com.ApiSistemaDeAtas.util;

import br.com.ApiSistemaDeAtas.service.SetorService;
import org.springframework.stereotype.Service;

@Service
public class GeradorNumeroSetor {

    final SetorService setorService;
    public GeradorNumeroSetor(SetorService setorService) {
        this.setorService = setorService;
    }

    public String setNumeroSetor(){
        Integer matriculaAleatoria = Math.toIntExact(Math.round(Math.random() * 10000));
        if(setorService.existsByNumeroSetor(matriculaAleatoria.toString())){
            return setNumeroSetor();
        }
        return matriculaAleatoria.toString();
    }
}
