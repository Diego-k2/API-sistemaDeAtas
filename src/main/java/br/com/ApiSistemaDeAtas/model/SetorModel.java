package br.com.ApiSistemaDeAtas.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TB_SETORES")
public class SetorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nomeSetor;

    public SetorModel(){}

    public SetorModel(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }
}
