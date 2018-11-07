package br.com.rodrigosolanomarques.manpo.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
@ForeignKey(
        entity = Atividade.class,
        parentColumns = "id",
        childColumns = "tarefaId",
        onDelete = CASCADE)
public class Atividade implements Serializable {

    @PrimaryKey
    private Long id;

    @Nullable
    @ColumnInfo
    private Long tarefaId;

    @Nullable
    @ColumnInfo
    private String descricao;

    @Nullable
    @ColumnInfo
    private Integer tempo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public Long getTarefaId() {
        return tarefaId;
    }

    public void setTarefaId(@Nullable Long tarefaId) {
        this.tarefaId = tarefaId;
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
}
