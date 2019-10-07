package com.example.testappmarzy.Util;

import android.text.format.DateFormat;
import android.util.Log;

import java.util.Calendar;
import java.util.Locale;

/**
 * Clase de utilería para fechas
 */
public class DateUtil {
    /**
     * Método que convierte Timestamp a date
     * con formato MMM d, h:mm a
     * @param time Valor de Timestamp
     * @return Cadena de texto
     */
    public static String convertTimetimestamp(long time){
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time * 1000);
        return DateFormat.format("MMM d, h:mm a", calendar).toString();
    }
}
