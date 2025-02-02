package br.com.liscandeia.domain;

import br.com.liscandeia.anotacao.TipoChave;

public class Cliente implements Persistente {

    private String nome;

    @TipoChave("getCpf")
    private Long cpf;

    private Long telefone;

    private String endereco;

    private Integer numero;

    private String cidade;

    private String estado;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Long getCpf() {
        return cpf;
    }
    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }
    public Long getTelefone() {
        return telefone;
    }
    public void setTelefone(Long tel) {
        this.telefone = tel;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String end) {
        this.endereco = end;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }



}