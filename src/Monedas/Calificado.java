package Monedas;

public abstract class Calificado implements MedioDePago {
    protected int valor;
    
    public Calificado(int v){ 
        valor = v; 
    }

    public final int getValor() { 
        return valor;
    }
}
