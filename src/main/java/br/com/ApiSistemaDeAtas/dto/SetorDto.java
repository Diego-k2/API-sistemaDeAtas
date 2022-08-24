package br.com.ApiSistemaDeAtas.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SetorDto {

    @NotBlank
    @Size(max = 70)
    private String nomeSetor;

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }
}
