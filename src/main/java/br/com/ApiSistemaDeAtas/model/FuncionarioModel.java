package br.com.ApiSistemaDeAtas.model;


import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TB_FUNCIONARIO")
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String sobrenome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, unique = true)
    private String matricula;

    @ManyToOne
    @JoinColumn(name = "setor_id", nullable = false)
    private SetorModel setor;

    public FuncionarioModel(){}

    public FuncionarioModel(String nome, String sobrenome, String cpf, String email, String senha, String matricula, SetorModel setor) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.matricula = matricula;
        this.setor = setor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public SetorModel getSetor() {
        return setor;
    }

    public void setSetor(SetorModel setor) {
        this.setor = setor;
    }
}
