package Monedas;

public final class M5 extends Moneda {
    
    public final int getValor() { return 5; }

    static { tiposMonedas.add(new M5()); }
}

