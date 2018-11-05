package br.com.rodrigosolanomarques.manpo.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

import br.com.rodrigosolanomarques.manpo.enumeration.Prioridade;

public class Converters {

    @TypeConverter
    public static Date deTimeStamp(Long timeStamp) {
        return timeStamp == null ? null : new Date(timeStamp);
    }

    @TypeConverter
    public static Long dateParaTimeStamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String enumParaString(Prioridade prioridade) {
        return prioridade == null ? null : prioridade.getValor();
    }
}
