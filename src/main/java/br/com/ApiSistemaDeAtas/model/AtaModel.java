package br.com.ApiSistemaDeAtas.model;

import br.com.ApiSistemaDeAtas.enuns.EstadoAta;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_ATA")
public class AtaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String data;

    @Column(nullable = false)
    private String horaInicio;

    @Column(nullable = false)
    private String horaFim;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 10000)
    private String pautas;

    @Column(nullable = false)
    private String isPublica;

    @Column(nullable = false, unique = true)
    private String numeroAta;

    private String estado = String.valueOf(EstadoAta.EM_EDICAO);

    @ManyToOne
    @JoinColumn(name = "emissor_id", nullable = false)
    private FuncionarioModel emissor;

    @ManyToMany
    @JoinTable(name = "TB_PARTICIPANTES",
            joinColumns = @JoinColumn(name = "ata_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id"))
    private List<FuncionarioModel> participantes;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
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

    public String isPublica() {
        return isPublica;
    }

    public void setPublica(String publica) {
        isPublica = publica;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public FuncionarioModel getEmissor() {
        return emissor;
    }

    public void setEmissor(FuncionarioModel emissor) {
        this.emissor = emissor;
    }

    public List<FuncionarioModel> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<FuncionarioModel> participantes) {
        this.participantes = participantes;
    }

    public String getIsPublica() {
        return isPublica;
    }

    public void setIsPublica(String isPublica) {
        this.isPublica = isPublica;
    }

    public String getNumeroAta() {
        return numeroAta;
    }

    public void setNumeroAta(String numeroAta) {
        this.numeroAta = numeroAta;
    }
}
