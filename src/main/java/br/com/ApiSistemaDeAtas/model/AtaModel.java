package br.com.ApiSistemaDeAtas.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_ATA")
public class AtaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Date data;

    @Column(nullable = false)
    private Date horaInicio;

    @Column(nullable = false)
    private Date horaFim;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, length = 10000)
    private String pautas;

    private boolean isPublica = false;

    private String estado = "EM EDIÇÃO";

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Date horaFim) {
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

    public boolean isPublica() {
        return isPublica;
    }

    public void setPublica(boolean publica) {
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
}
