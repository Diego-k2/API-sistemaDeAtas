package br.com.ApiSistemaDeAtas.dto;


import br.com.ApiSistemaDeAtas.util.ParticipanteParser;

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
    private String isPublica;

    private List<ParticipanteParser> participanteParsers;

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

    public List<ParticipanteParser> getParticipantes() {
        return participanteParsers;
    }

    public void setParticipantes(List<ParticipanteParser> participanteParsers) {
        this.participanteParsers = participanteParsers;
    }

    public String getIsPublica() {
        return isPublica;
    }

    public void setIsPublica(String isPublica) {
        this.isPublica = isPublica;
    }

    public List<ParticipanteParser> getParticipanteParsers() {
        return participanteParsers;
    }

    public void setParticipanteParsers(List<ParticipanteParser> participanteParsers) {
        this.participanteParsers = participanteParsers;
    }
}
