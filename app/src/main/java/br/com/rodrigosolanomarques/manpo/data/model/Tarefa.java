package br.com.rodrigosolanomarques.manpo.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.Date;

import br.com.rodrigosolanomarques.manpo.enumeration.Prioridade;

@Entity
public class Tarefa implements Serializable {

    @PrimaryKey
    private long id;

    @Nullable
    @ColumnInfo
    private String descricao;

    @Nullable
    @ColumnInfo
    private Integer tempo;

    @Nullable
    @ColumnInfo(name = "data_criacao")
    private Date dataCriacao;

    @ColumnInfo(name = "data_finalizacao")
    private Date dataFinalizacao;

    @Nullable
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

    @Nullable
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(@Nullable String descricao) {
        this.descricao = descricao;
    }

    @Nullable
    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(@Nullable Integer tempo) {
        this.tempo = tempo;
    }

    @Nullable
    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(@Nullable Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(Date dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    @Nullable
    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(@Nullable Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public boolean isFinalizada() {
        return isFinalizada;
    }

    public void setFinalizada(boolean finalizada) {
        isFinalizada = finalizada;
    }

}
