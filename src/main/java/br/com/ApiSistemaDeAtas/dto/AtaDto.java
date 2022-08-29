package br.com.ApiSistemaDeAtas.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class AtaDto {


    @NotBlank
    private String horaInicio;

    @NotBlank
    private String titulo;

    @NotBlank
    private String pautas;

    @NotBlank
    private String emissor;

    @NotBlank
    private List<String> participantes;

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPautas() {
        return pautas;
    }

    public void setPautas(String pautas) {
        this.pautas = pautas;
    }

    public String getEmissor() {
        return emissor;
    }

    public void setEmissor(String emissor) {
        this.emissor = emissor;
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }
}
