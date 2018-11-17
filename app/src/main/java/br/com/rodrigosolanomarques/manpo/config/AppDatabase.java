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

    private static AppDatabase appDatabase;

    public static synchronized AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = create(context);
        }
        return appDatabase;
    }

    private static AppDatabase create(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "rsm-manpo").build();
    }

    public abstract TarefaDao tarefaDao();
}
