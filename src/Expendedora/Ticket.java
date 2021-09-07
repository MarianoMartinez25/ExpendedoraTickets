package Expendedora;

import java.io.Serializable;
import java.util.Calendar;

final class Ticket implements Serializable {
    public final String destino, precio, fecha, hora;

    public Ticket(String destino, String precio) {
        String s;
        this.destino = destino;
        this.precio = precio;
        Calendar c = Calendar.getInstance();
        s = c.DAY_OF_MONTH + "/" + c.MONTH + "/" + c.YEAR;
        this.fecha = s;
        s = c.HOUR_OF_DAY + ":" + c.MINUTE + ":" + c.SECOND;
        this.hora = s;
    }
}
