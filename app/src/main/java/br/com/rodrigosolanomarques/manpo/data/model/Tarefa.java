package br.com.rodrigosolanomarques.manpo.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.Date;

import br.com.rodrigosolanomarques.manpo.enumeration.Prioridade;

@Entity
public class Tarefa implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo
    private String descricao;

    @ColumnInfo
    private Integer tempoPrevisto = 0;

    @Nullable
    @ColumnInfo
    private Integer tempoExcutado = 0;

    @ColumnInfo(name = "data_criacao")
    private Date dataCriacao;

    @Nullable
    @ColumnInfo(name = "data_finalizacao")
    private Date dataFinalizacao;

    @ColumnInfo
    private Prioridade prioridade;

    @ColumnInfo
    private boolean isFinalizada = false;

    public Tarefa() {
        dataCriacao = new Date();
        prioridade = Prioridade.BAIXA;
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

    public Integer getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(Integer tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    public Integer getTempoExcutado() {
        return tempoExcutado;
    }

    public void setTempoExcutado(Integer tempoExcutado) {
        this.tempoExcutado = tempoExcutado;
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
