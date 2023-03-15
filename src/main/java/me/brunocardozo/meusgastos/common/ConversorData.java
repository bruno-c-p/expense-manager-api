package me.brunocardozo.meusgastos.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorData {
    public static String converterDateParaString(Date data) {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatador.format(data);
    }
}
