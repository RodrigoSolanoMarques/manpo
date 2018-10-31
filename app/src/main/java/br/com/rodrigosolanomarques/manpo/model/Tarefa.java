package br.com.rodrigosolanomarques.manpo.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Tarefa implements Serializable {

    @PrimaryKey
    private long id;

    @ColumnInfo
    private String descricao;

    @ColumnInfo
    private Integer tempo;

    private Date dataCriacao;

    private Date dataFinalizacao;

    @ColumnInfo
    private boolean isFinalizada = false;
}
