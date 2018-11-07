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
        entity = Usuario.class,
        parentColumns = "id",
        childColumns = "userId",
        onDelete = CASCADE)
public class Configuracao implements Serializable {

    @PrimaryKey
    private Integer id;

    @Nullable
    @ColumnInfo
    private Integer userId;

    @Nullable
    @ColumnInfo
    private Integer tempoConcentracao = 25;

    @Nullable
    @ColumnInfo
    private Integer tempoIntervaloCurto = 5;

    @Nullable
    @ColumnInfo
    private Integer tempoIntervaloLongo = 30;

    @Nullable
    @ColumnInfo
    private Boolean notificarConcentracao = true;

    @Nullable
    @ColumnInfo
    private Boolean notificarIntervaloCurto = true;

    @Nullable
    @ColumnInfo
    private Boolean notificarIntervaloLongo = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Nullable
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(@Nullable Integer userId) {
        this.userId = userId;
    }

    @Nullable
    public Integer getTempoConcentracao() {
        return tempoConcentracao;
    }

    public void setTempoConcentracao(@Nullable Integer tempoConcentracao) {
        this.tempoConcentracao = tempoConcentracao;
    }

    @Nullable
    public Integer getTempoIntervaloCurto() {
        return tempoIntervaloCurto;
    }

    public void setTempoIntervaloCurto(@Nullable Integer tempoIntervaloCurto) {
        this.tempoIntervaloCurto = tempoIntervaloCurto;
    }

    @Nullable
    public Integer getTempoIntervaloLongo() {
        return tempoIntervaloLongo;
    }

    public void setTempoIntervaloLongo(@Nullable Integer tempoIntervaloLongo) {
        this.tempoIntervaloLongo = tempoIntervaloLongo;
    }

    @Nullable
    public Boolean getNotificarConcentracao() {
        return notificarConcentracao;
    }

    public void setNotificarConcentracao(@Nullable Boolean notificarConcentracao) {
        this.notificarConcentracao = notificarConcentracao;
    }

    @Nullable
    public Boolean getNotificarIntervaloCurto() {
        return notificarIntervaloCurto;
    }

    public void setNotificarIntervaloCurto(@Nullable Boolean notificarIntervaloCurto) {
        this.notificarIntervaloCurto = notificarIntervaloCurto;
    }

    @Nullable
    public Boolean getNotificarIntervaloLongo() {
        return notificarIntervaloLongo;
    }

    public void setNotificarIntervaloLongo(@Nullable Boolean notificarIntervaloLongo) {
        this.notificarIntervaloLongo = notificarIntervaloLongo;
    }
}
