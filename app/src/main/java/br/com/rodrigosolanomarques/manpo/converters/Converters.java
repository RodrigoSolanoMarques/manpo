package br.com.rodrigosolanomarques.manpo.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class Converters {

    @TypeConverter
    public static Date deTimeStamp(Long timeStamp) {
        return timeStamp == null ? null : new Date(timeStamp);
    }

    @TypeConverter
    public static Long dateParaTimeStamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
