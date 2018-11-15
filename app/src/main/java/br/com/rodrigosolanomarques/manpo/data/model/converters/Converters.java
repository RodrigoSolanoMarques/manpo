package br.com.rodrigosolanomarques.manpo.data.model.converters;

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
    public static String prioridadeParaString(Prioridade prioridade) {
        return prioridade == null ? null : prioridade.getValor();
    }

    @TypeConverter
    public static Prioridade stringParaPrioridade(String prioridade) {
        return prioridade == null || prioridade.isEmpty() ? null : Prioridade.valueOf(prioridade);
    }
}
