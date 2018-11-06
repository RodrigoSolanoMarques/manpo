package br.com.rodrigosolanomarques.manpo.config;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import br.com.rodrigosolanomarques.manpo.data.local.TarefaDao;
import br.com.rodrigosolanomarques.manpo.data.model.Tarefa;
import br.com.rodrigosolanomarques.manpo.data.model.converters.Converters;

@Database(entities = {
        Tarefa.class
}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private AppDatabase appDatabase;

    public AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class, "rsm-manpo").build();
            return appDatabase;
        }
        return appDatabase;
    }

    public abstract TarefaDao tarefaDao();
}
