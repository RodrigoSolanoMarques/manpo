package br.com.rodrigosolanomarques.manpo.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import java.io.Serializable;

@Entity
public class Usuario implements Serializable {

    @PrimaryKey
    private long id;

    @Nullable
    @ColumnInfo
    private String nome;

    @Nullable
    @ColumnInfo
    private String senha;

    @Ignore
    private String senhaNovamente;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] image;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Nullable
    public String getNome() {
        return nome;
    }

    public void setNome(@Nullable String nome) {
        this.nome = nome;
    }

    @Nullable
    public String getSenha() {
        return senha;
    }

    public void setSenha(@Nullable String senha) {
        this.senha = senha;
    }

    public String getSenhaNovamente() {
        return senhaNovamente;
    }

    public void setSenhaNovamente(String senhaNovamente) {
        this.senhaNovamente = senhaNovamente;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
