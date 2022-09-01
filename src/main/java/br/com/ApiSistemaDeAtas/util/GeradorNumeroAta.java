package br.com.ApiSistemaDeAtas.util;

import br.com.ApiSistemaDeAtas.service.AtaService;
import org.springframework.stereotype.Service;

@Service
public class GeradorNumeroAta {

    final AtaService ataService;
    public GeradorNumeroAta(AtaService ataService) {
        this.ataService = ataService;
    }

    public String geraNumeroAta(){
        Integer matriculaAleatoria = Math.toIntExact(Math.round(Math.random() * 10000));
        if(ataService.existsByNumeroAta(matriculaAleatoria.toString())){
            geraNumeroAta();
        }
        return matriculaAleatoria.toString();
    }

}
