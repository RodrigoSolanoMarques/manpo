package br.com.rodrigosolanomarques.manpo.config;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import br.com.rodrigosolanomarques.manpo.converters.Converters;
import br.com.rodrigosolanomarques.manpo.model.Tarefa;

@Database(entities = {
        Tarefa.class
}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private AppDatabase appDatabase;

    public AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context,
                    AppDatabase.class, "database-name").build();
            return appDatabase;
        }
        return appDatabase;
    }
}
