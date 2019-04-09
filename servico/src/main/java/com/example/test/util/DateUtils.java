package com.example.test.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

//    private static final String DD_MM_YYYY = "dd/MM/yyyy";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String HH_MM = "HH:mm";

    private DateUtils() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Retorna Hora atual formatada
     * @return String com hora atual formatada
     */
    public static String getCurrentTime() {
        return formatDateHour(new Date(), HH_MM);
    }
    
    /**
     * Retorna Data atual formatada
     * @return String com data atual formatada
     */
    public static String getCurrentDate() {
        return formatDateHour(new Date(), YYYY_MM_DD);
    }

    /**
     * Formatea Calendar a YYYY_MM_DD
     * @param calendar
     * @return
     */
    public static String formatDate(Calendar calendar) {
        String formatDate = null;
        if (calendar != null) {
            formatDate = formatDateHour(calendar.getTime(), YYYY_MM_DD);
        }
        return formatDate;
    }

    private static String formatDateHour(Date data, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if (data != null) {
            return simpleDateFormat.format(data);
        } else {
            return null;
        }
    }
}
