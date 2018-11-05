package br.com.rodrigosolanomarques.manpo.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import br.com.rodrigosolanomarques.manpo.enumeration.Prioridade;

@Entity
public class Tarefa implements Serializable {

    @PrimaryKey
    private long id;

    @ColumnInfo
    private String descricao;

    @ColumnInfo
    private Integer tempo;

    @ColumnInfo(name = "data_criacao")
    private Date dataCriacao;

    @ColumnInfo(name = "data_finalizacao")
    private Date dataFinalizacao;

    @ColumnInfo
    private Prioridade prioridade;

    @ColumnInfo
    private boolean isFinalizada = false;

    public Tarefa() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(Date dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public boolean isFinalizada() {
        return isFinalizada;
    }

    public void setFinalizada(boolean finalizada) {
        isFinalizada = finalizada;
    }

}
