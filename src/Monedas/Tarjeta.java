package Monedas;

/**
 * Los objetos Tarjeta son un MedioDePago, Calificado, pero NO acumulable
 */

public class Tarjeta extends Calificado {
    public Tarjeta(){ 
        super(1500);    // crédito por defecto de 1500
    }

    public void debitar(int v) {
        super.valor -= v;
    }
}
