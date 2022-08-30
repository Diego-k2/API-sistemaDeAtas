package br.com.ApiSistemaDeAtas.util;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;


public class EmiteData {

    public static String getYearMothDay() {

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static String getHoraFinal() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(date);
    }

}