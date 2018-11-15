package br.com.rodrigosolanomarques.manpo.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;

@Dao
public interface TarefaDao {

    @Insert
    Long insert(Tarefa tarefa);

    @Query("SELECT * FROM tarefa")
    List<Tarefa> getAll();

    @Query("SELECT * FROM tarefa WHERE id = :id")
    Tarefa findByName(String id);

    @Insert
    void insertAll(Tarefa... users);

    @Delete
    void delete(Tarefa user);

}
